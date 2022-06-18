import entity.User;
import org.junit.After;
import org.junit.Before;
import dao.UserDao;
import junit.framework.TestCase;
import org.easymock.EasyMock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.UserService;

import java.util.Arrays;
import static org.easymock.EasyMock.*;

@RunWith(value = Parameterized.class)
public class UserServiceTest extends TestCase {

    private UserService service;
    private UserDao userDao;
    private String username;
    private String password;
    private boolean expect1;
    private boolean expect2;

    public UserServiceTest(String username, String password, boolean expect1, boolean expect2){
        this.username = username;
        this.password = password;
        this.expect1 = expect1;
        this.expect2 = expect2;
    }

    @Parameterized.Parameters(name = "{index}: username = {0} , password = {1}, expect1 = {2}, expect2 = {3} ")
    public static  Iterable<Object[]> area(){
        return Arrays.asList(new Object[][] {
                {"admin","admin", true, true},
                {"admin","", true, false},
                {"","123456", false, false},
                {"1234", "1234", false, false},
                {"", "", false, false}
        });
    }

    @Before
    public void setUp() throws Exception{
        userDao = EasyMock.createMock(UserDao.class);
        service = new UserService();
        service.setUserDao(userDao);
    }
    @After
    public void tearDown() throws Exception{

    }
    @Test
    public void testLoginVerify() throws Exception {
        expect(userDao.verifyUsername(username)).andReturn(expect1);
        if(expect1==true) {
            expect(userDao.verifyPassword(username, password)).andReturn(expect2);
        }
        replay(userDao);

        assertEquals(expect2, service.loginVerify(username, password));
        verify(userDao);
    }
    @Test
    public void testUserRegister() throws Exception {
        expect(userDao.addUser(EasyMock.anyObject(User.class))).andReturn(true);
        replay(userDao);

        assertEquals(true, service.userRegister(username, password));
        verify(userDao);
    }
}
