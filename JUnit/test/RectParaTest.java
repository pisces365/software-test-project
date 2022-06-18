import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 杨捷宁
 * @Date: 2022/04/15/14:51
 * @Description:
 */
@RunWith(value = Parameterized.class)
public class RectParaTest {
    private int length;
    private int width;
    private int result;

    public RectParaTest(int length, int width, int result) {
        this.length = length;
        this.width = width;
        this.result = result;
    }

    @Parameterized.Parameters(name = "{a}")
    public static Iterable<Object[]> data1() {
        return Arrays.asList(new Object[][] {
                { 3, 4, 12 },
                { 2, 2, 4 },
                { 8, 2, 16 },
                { 4, 5, 20 }
        });
    }

    @Test
    public void testGetArea() {
        Rect r = new Rect(length, width);
        int area = r.getArea();
        assertEquals(result, area);
    }

}
