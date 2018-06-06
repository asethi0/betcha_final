package com.betcha.SocketChat;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User user = new User("ash");
    User user2 = new User("amoneywayyy");

    @Test
    public void test_get_name_1(){
        assertEquals("ash", user.getName());

    }

    @Test
    public void test_get_name_2(){
        assertEquals("amoneywayyy", user2.getName());
    }


}