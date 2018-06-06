import com.betcha.SocketChat.User;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.AssertTrue.assertTrue;

public class test_bet_history {

    @Test
    public void test1() {
        User user = new User();
        assertTrue(user.updateBalance());
    }


    @Test
    public void test2() {
        User user = new User();
        assertFalse(user.updateBalance(2));
    }

}