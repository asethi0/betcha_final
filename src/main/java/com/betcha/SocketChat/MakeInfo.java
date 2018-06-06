package com.betcha.SocketChat;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonCreator;

public class MakeInfo extends SignalInfo
{
    private String betName;

    public String getBetName()      { return betName; }

    @JsonCreator
    public MakeInfo(@JsonProperty("betName") String betName) { this.betName = betName; }
}
