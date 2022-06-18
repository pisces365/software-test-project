import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 杨捷宁
 * @Date: 2022/04/15/10:23
 * @Description:
 */
public class RectTest extends TestCase {

    @Test
    public void testGetArea() {
        Rect r = new Rect(3, 4);
        int area = r.getArea();
        assertEquals(12,area);
    }

    @Test
    public void testGetPerimeter() {
        Rect r = new Rect(3, 4);
        int perimeter = r.getPerimeter();
        assertEquals(14,perimeter);
    }

    @Test
    public void testFindMax() {
        Rect[] arr = new Rect[]{
                new Rect(10, 20), new Rect(2, 65),
                new Rect(3, 10), new Rect(6, 20)
        };

        assertEquals(new Rect(10, 20).getObject(), Rect.findMax(arr, new Rect.areaCompare()).getObject());
        assertEquals(new Rect(2, 65).getObject(), Rect.findMax(arr, new Rect.perimeterCompare()).getObject());
    }
}
