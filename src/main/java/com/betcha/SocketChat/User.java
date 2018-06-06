package com.betcha.SocketChat;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class User
{
    //public static Map<String, User> users = new ConcurrentHashMap<String, User>();

    private String name;
    public int tokens = 500;

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (!(obj instanceof User))
            return false;

        User u = (User) obj;

        return name.toLowerCase().equals(u.name.toLowerCase());
    }

    @JsonCreator
    //constructor
    public User(@JsonProperty("name") String name)  { this.name = name; }

    //dummy constructor
    public User() { }

    public String getName()   { return name; }

    //prints user's coin count (for testing)
    public int printTokens() { System.out.print(name + " tokens: " + tokens + ", ");
                return tokens;}
}
