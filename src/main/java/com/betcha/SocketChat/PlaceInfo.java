package com.betcha.SocketChat;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class PlaceInfo extends SignalInfo
{
    private String username;
    private String betName;
    private int amnt;
    private boolean outcome;

    public String getBetName()  { return betName;   }
    public String getUsername() { return username;  }
    public int getAmnt()        { return amnt;      }
    public boolean getOutcome() { return outcome;   }

    @JsonCreator
    public PlaceInfo(@JsonProperty("username")  String username,
                     @JsonProperty("betName")   String betName,
                     @JsonProperty("amnt")      int amnt,
                     @JsonProperty("outcome")   boolean outcome)
    {
        //super("place");
        this.username   = username;
        this.betName    = betName;
        this.amnt       = amnt;
        this.outcome    = outcome;
    }

    //dummy constructor
    public PlaceInfo() {}
}
