import org.junit.Test;

public class test_bet_loss {

    @Test
    public void test1()
    {
        User user = new User();
        user.loseBet(50);
        assertEquals(user.balance, 450);
    }


    public void test1()
    {
        User user = new User();
        user.loseBet(500);
        assertEquals(user.balance, 0);
    }
}
