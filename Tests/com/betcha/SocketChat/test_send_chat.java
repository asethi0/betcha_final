package com.betcha.SocketChat;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_send_chat {

    @Test
    public void test1()
    {
        User user = new User("ash");
        assertEquals(user.send("hello"), "hello");
    }


    @Test
    public void test2()
    {
        User user = new User("ash");
        assertEquals(user.send(""), null);
    }


}
