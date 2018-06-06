package com.betcha.SocketChat;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class BetInfo extends SignalInfo
{
    private List<Bet> bets;
    public List<Bet> getBets()    { return bets; }

    @JsonCreator
    public BetInfo(@JsonProperty("bets") List<Bet> bets)
    {
        //super("betdata");
        this.bets = bets;
    }

    //dummy constructor
    public BetInfo() { }
}
