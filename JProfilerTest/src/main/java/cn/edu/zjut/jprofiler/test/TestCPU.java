package cn.edu.zjut.jprofiler.test;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TestCPU {

    public static ArrayList<Thread> threadList = new ArrayList<Thread>();

    public static void testCPU() throws Exception {

        Thread thread = new Thread(new CPUControlInSinShape());

        threadList.add(thread);
        thread.start();
    }

    @SuppressWarnings("deprecation")
    public static void free(Thread thread) {
        thread.stop();
    }

}

class SystemClock {
    private static final SystemClock MILLIS_CLOCK = new SystemClock(1);
    private final long precision;
    private final AtomicLong now;

    private SystemClock(long precision) {
        this.precision = precision;
        now = new AtomicLong((System.currentTimeMillis()));
        scheduleClockUpdating();
    }

    public static SystemClock millisClock() {
        return MILLIS_CLOCK;
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor(
                runnable -> {
                    Thread thread = new Thread(runnable, "system.clock");
                    thread.setDaemon(true);
                    return thread;
                }
        );
        schedule.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()),
                precision, precision, TimeUnit.MILLISECONDS);
    }

    public long now() {
        return now.get();
    }
}

class CPUControlInSinShape implements Runnable {

    final double SPLIT = 0.01;
    final int COUNT = (int) (2 / SPLIT);
    final double PI = Math.PI;
    final int INTERVAL = 250;
    long[] busySpan = new long[COUNT];
    long[] idleSpan = new long[COUNT];
    int half = INTERVAL / 2;
    double radian = 0.0;

    public void run() {
        for (int i = 0; i < COUNT; i++) {
            busySpan[i] = (long) (half + (Math.sin(PI * (radian - 0.5)) * half));
            idleSpan[i] = INTERVAL - busySpan[i];
            radian += SPLIT;
        }

        long startTime = 0;
        int j = 0;

        while (true) {
            j = j % COUNT;

            startTime = SystemClock.millisClock().now();

            while ((SystemClock.millisClock().now() - startTime) < busySpan[j]) {

                try {
                    Thread.sleep(idleSpan[j]);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            j++;
        }

    }
}
