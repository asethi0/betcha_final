package com.betcha.SocketChat;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Contains identifying information for a JSON message.
 */
public class MessageWrapper
{
    private String type;        //type is "userdata", "betdata", "place", "make", "cancel", or "resolve"
    private SignalInfo message;

    public String getType()     { return type; }
    public SignalInfo getMessage()  { return message; }

    @JsonCreator
    public MessageWrapper(@JsonProperty("type") String type, @JsonProperty("message") SignalInfo message)
    {
        this.type = type;
        this.message = message;
    }
}
