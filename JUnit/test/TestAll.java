import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 杨捷宁
 * @Date: 2022/04/15/14:20
 * @Description:
 */
public class TestAll extends TestSuite {
    public static Test suite() {
        TestSuite testSuite = new TestSuite();
//        testSuite.addTestSuite(RectTest.class);

        testSuite.addTest(TestSuite.createTest(RectTest.class, "testGetArea"));
        testSuite.addTest(TestSuite.createTest(RectTest.class, "testGetPerimeter"));
//        testSuite.addTest(TestSuite.createTest(RectTest.class, "testFindMax"));
        return testSuite;

    }

    public static void main(String args[]){
        TestRunner.run(suite());
    }
}

