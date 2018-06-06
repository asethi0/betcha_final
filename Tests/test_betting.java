import com.betcha.SocketChat.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class test_betting {

    @Test
    public void test1()
    {
        User user = new User();
        assertEquals(user.balance, 500);
    }

    public void test2()
    {
        User user = new User();
        user.placeBet(50, true);
        assertEquals(user.balance, 550);
    }

}
