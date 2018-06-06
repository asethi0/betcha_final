package com.betcha.SocketChat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=UserInfo.class, name="userdata"),
        @JsonSubTypes.Type(value=BetInfo.class, name="betdata"),
        @JsonSubTypes.Type(value=MakeInfo.class, name="make"),
        @JsonSubTypes.Type(value=PlaceInfo.class, name="place"),
        @JsonSubTypes.Type(value=ResolveInfo.class, name="resolve"),
        @JsonSubTypes.Type(value=CancelInfo.class, name="cancel")
})
public class SignalInfo
{
    /*protected String type;

    public String getType() { return type; }

    @JsonCreator
    public SignalInfo(@JsonProperty("type") String type) { this.type = type; }*/
}
