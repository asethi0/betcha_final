package com.betcha.SocketChat;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class CancelInfo extends SignalInfo
{
    private String betName;

    public String getBetName()  { return betName; }

    @JsonCreator
    public CancelInfo(@JsonProperty("betName") String betName) { this.betName = betName; }

    //dummy constructor
    public CancelInfo() { }
}
