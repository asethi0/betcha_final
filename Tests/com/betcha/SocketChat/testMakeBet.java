package com.betcha.SocketChat;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testMakeBet {

    BetRoster br = new BetRoster();
    @Test

    public void test1()
    {
       assertEquals(br.makeBet("player dies"),"player dies");
    }

    @Test
    public void test2()
    {
        assertEquals(br.makeBet("player curses"), "player curses");
    }
}
