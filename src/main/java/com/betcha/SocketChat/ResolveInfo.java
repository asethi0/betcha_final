package com.betcha.SocketChat;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class ResolveInfo extends SignalInfo
{
    private String betName;
    private boolean outcome;

    public String getBetName()  { return betName; }
    public boolean getOutcome()  { return outcome; }

    @JsonCreator
    public ResolveInfo(@JsonProperty("betName") String betName, @JsonProperty("outcome") boolean outcome)
    {
        this.betName = betName;
        this.outcome = outcome;
    }

    //dummy constructor
    public ResolveInfo() { }
}
