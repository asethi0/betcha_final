package com.betcha.SocketChat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test_print_tokens {


    User user = new User("ash");
    User user2 = new User("amoneywayyy");

    @Test
    public void test_print_tokens_1(){
        assertEquals(user.printTokens(), 500);
    }

    @Test
    public void test_print_tokens_2(){
        assertEquals(user2.printTokens(), 500);
    }

}
