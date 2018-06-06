package com.betcha.SocketChat;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.AssertTrue.assertTrue;

public class test_ban_users {


    @Test
    public void test1() {
        RosterCtrl rc = new RosterCtrl(String < "ash", "abe", "ahmed" >);
        Assertions.assertTrue(rc.banPlauer("ash"), "ash");
    }

    public void test2(){
        RosterCtrl rc = new RosterCtrl(String < "ash", "abe", "ahmed" >);
        Assertions.assertFalse(rc.banPlauer("ash"), "Davide");
    }

}
}
