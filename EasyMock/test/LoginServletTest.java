import org.easymock.EasyMock;
import org.easymock.MockType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 杨捷宁
 * @Date: 2022/04/22/9:11
 * @Description:
 */
public class LoginServletTest {
    private HttpServletRequest request;
    private LoginServlet servlet;
    private ServletContext context;
    private RequestDispatcher dispatcher;
    @Before
    //setup用于环境的建立，在其中mock需要用到的接口
    public void setUp() throws Exception{
        request = EasyMock.createMock(HttpServletRequest.class);
        context = EasyMock.createMock(ServletContext.class);
        dispatcher = EasyMock.createMock(RequestDispatcher.class);
        servlet = new LoginServlet();
    }
    @After
    //好像没什么要释放的，可以不写，这里为了完整性
    public void tearDown() {

    }
    @Test
    public void testLoginFailed() throws Exception {
        //设置期望得到的值
        expect(request.getParameter("username")).andReturn("admin");
        expect(request.getParameter("password")).andReturn("1234");
        //setup完成激活mock
        replay(request);
        try {
            servlet.doPost(request, null);
            fail("Not caught exception!");
        }
        catch(RuntimeException re) {
            assertEquals("Login failed.", re.getMessage());
        }
        //检查mock对象的状态
        verify(request);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        //设置期望得到的值，最好按照doPost的语句顺序来
        expect(request.getParameter("username")).andReturn("admin");
        expect(request.getParameter("password")).andReturn("123456");
        dispatcher.forward(request, null);
        expect(context.getNamedDispatcher("dispatcher")).andReturn(dispatcher);

        //setup完成激活mock
        replay(request);
        replay(context);
        replay(dispatcher);
        /*为了让getServletContext()方法返回我们创建的ServletContext Mock对象，
         我们定义一个匿名类并覆写getServletContext()方法*/
        LoginServlet servlet = new LoginServlet() {
            public ServletContext getServletContext() {
                return context;
            }
        };
        //测试doPost方法
        servlet.doPost(request, null);
        //检查mock对象的状态
        verify(request);
        verify(context);
        verify(dispatcher);
    }

}
