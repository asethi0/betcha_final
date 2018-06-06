import com.betcha.SocketChat.User;
import com.betcha.SocketChat.UserInfo;
import org.junit.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_get_users {



    @Test
    public void test1()
    {
        UserInfo ui = new UserInfo();
        assertEquals(ui.getUsers(), null)

    }

    public void test2()
    {
        UserInfo ui = new UserInfo(new Map<"ash", "ash">());
        assertEquals("ash", ui.getUsers());
    }
}
