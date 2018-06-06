import com.betcha.SocketChat.RosterCtrl;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_get_userJSON {

    @Test
    public void test1()
    {
        RosterCtrl rc = new RosterCtrl();
        assertEquals(rc.getUser("ash"), "ash");
    }

    public void test2()
    {
        RosterCtrl rc = new RosterCtrl();
        assertEquals(rc.getUser("123"), "123");
    }
}
