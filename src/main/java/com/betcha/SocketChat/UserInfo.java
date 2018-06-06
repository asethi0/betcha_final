package com.betcha.SocketChat;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserInfo extends SignalInfo
{
    private Map<String, User> users;
    public Map<String, User> getUsers() { return users; }

    public UserInfo()
    {
        //super("userdata");
        //this.users = new ConcurrentHashMap<String, User>();
    }

    @JsonCreator
    public UserInfo(@JsonProperty("users") Map<String, User> users)
    {
        //super("userdata");
        this.users = users;
    }
}
