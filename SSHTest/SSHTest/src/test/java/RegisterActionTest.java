import action.LoginAction;
import action.RegisterAction;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.UserService;

import java.util.Arrays;
import java.util.Map;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.verify;

@RunWith(value = Parameterized.class)
public class RegisterActionTest extends TestCase {
    private UserService userService;
    private RegisterAction registerAction;

    private String username;
    private String password;

    public RegisterActionTest(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Parameterized.Parameters(name = "{index}: username = {0} , password = {1}, expect1 = {2} ")
    public static  Iterable<Object[]> area(){
        return Arrays.asList(new Object[][] {
                {"admin","admin"},
                {"admin",""},
                {"","123456"},
                {"1234", "1234"},
                {"", ""}
        });
    }
    @Before
    public void setUp() throws Exception{
        userService = EasyMock.createMock(UserService.class);
        registerAction = new RegisterAction();
        registerAction.setUserService(userService);
        registerAction.setUsername(username);
        registerAction.setPassword(password);
    }
    @After
    public void tearDown() throws Exception{

    }


    @Test
    public void testValidate() throws Exception{
        replay(userService);
        registerAction.validate();
        verify(userService);
    }

    @Test
    public void testExecute() throws Exception{
        expect(userService.userRegister(username, password)).andReturn(true);
        replay(userService);
        assertEquals("success", registerAction.execute());
        verify(userService);
    }
}
