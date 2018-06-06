package com.betcha.SocketChat;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.AssertTrue.assertTrue;

public class test_twitch_stream {

    @Test
    public void test1(){

        User user = new User("m");

        assertFalse(user.create_room(""));
    }

    public void test2(){

        User user = new User("m");

        Assertions.assertTrue(user.create_room("Ninja"));
    }
}
