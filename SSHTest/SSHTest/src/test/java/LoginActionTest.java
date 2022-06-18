import action.LoginAction;
import dao.UserDao;
import entity.User;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.UserService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.*;

@RunWith(value = Parameterized.class)
public class LoginActionTest extends TestCase {
    private UserService userService;
    private Map session;
    private LoginAction loginAction;

    private String username;
    private String password;
    private String expect1;
    private boolean expect2;

    public LoginActionTest(String username, String password, String expect1, boolean expect2){
        this.username = username;
        this.password = password;
        this.expect1 = expect1;
        this.expect2 = expect2;
    }

    @Parameterized.Parameters(name = "{index}: username = {0} , password = {1}, expect1 = {2}, expect2 = {3} ")
    public static  Iterable<Object[]> area(){
        return Arrays.asList(new Object[][] {
                {"admin","admin", "success", true},
                {"admin","", "input", false},
                {"","123456", "input", false},
                {"1234", "1234", "input", false},
                {"", "", "input", false}
        });
    }

    @Before
    public void setUp() throws Exception{
        userService = EasyMock.createMock(UserService.class);//创建一个模拟对象
        session = EasyMock.createMock(Map.class);
//        session = new HashMap();
        loginAction = new LoginAction();
        loginAction.setSession(session);
        loginAction.setUserService(userService);
        loginAction.setUsername(username);
        loginAction.setPassword(password);
    }
    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void testValidate() throws Exception{
        replay(userService);
        loginAction.validate();
        verify(userService);
    }

    @Test
    public void testExecute() throws Exception{
        expect(userService.loginVerify(username, password)).andReturn(expect2);
        replay(userService);
//        replay(session);
        assertEquals(expect1, loginAction.execute());
        verify(userService);
//        verify(session);
    }
}
