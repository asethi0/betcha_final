package com.betcha.SocketChat;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_bet_roster {

    @Test
    public void test1(){
        Roster roster = new Roster();
        assertEquals(roster.add("user dies in next minute"), "user dies in next minute");
    }


    public void test2(){
        Roster roster = new Roster();
        assertFalse(roster.add(""), "user dies in next minute");
    }
}
