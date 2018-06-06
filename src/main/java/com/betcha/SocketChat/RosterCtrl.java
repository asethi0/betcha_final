package com.betcha.SocketChat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Controller class for the BetRoster.
 */
public class RosterCtrl
{
    private Map<String, User> betterMap = new ConcurrentHashMap<>();
    private BetRoster betRoster = new BetRoster(this);
    private ObjectMapper mapper = new ObjectMapper();


    //add user to room map
    public void add(String username)
    {
        User usr = new User(username);
        betterMap.put(username, usr);
    }

    public User getUser(String username)    { return betterMap.get(username); }

    //parse bet input and perform action
    public void parse(String message)
    {
        SignalInfo info;

        //extract from wrapper
        try
        {
            info = mapper.readValue(message, SignalInfo.class);

            if (info instanceof MakeInfo)
            {
                System.out.println("It's a MAKE");
                MakeInfo make = (MakeInfo)info;
                betRoster.makeBet(make.getBetName());
            }
            else if (info instanceof PlaceInfo)
            {
                System.out.println("It's a PLACE");
                PlaceInfo place = (PlaceInfo)info;
                betRoster.placeBet(place.getUsername(), place.getBetName(), place.getAmnt(), place.getOutcome());
            }
            else if (info instanceof ResolveInfo)
            {
                System.out.println("It's a RESOLVE");
                ResolveInfo resolve = (ResolveInfo)info;
                betRoster.resolveBet(resolve.getBetName(), resolve.getOutcome());
            }
            else if (info instanceof CancelInfo)
            {
                System.out.println("It's a CANCEL");
                CancelInfo cancel = (CancelInfo)info;
                betRoster.cancelBet(cancel.getBetName());
            }
            else
                System.out.println("It's a PROBLEM!");
            /*
            //interpret command
            switch (info.getType())
            {
                //make a new bet
                case "make":
                    parseMake(wrapper.getMessage());
                    break;

                //wager on a bet
                case "place":
                    parsePlace(wrapper.getMessage());
                    break;

                //resolve a bet
                case "resolve":
                    parseResolve(wrapper.getMessage());
                    break;

                //void a bet
                case "cancel":
                    parseCancel(wrapper.getMessage());
                    break;

                //error
                default:
                    System.out.println("Error: unknown command");
                    break;

            }*/
        }
        catch (JsonParseException e)    { e.printStackTrace(); }
        catch (JsonMappingException e)  { e.printStackTrace(); }
        catch (IOException e)           { e.printStackTrace(); }
        catch (Exception e)             { e.printStackTrace(); }
    }

    //parse information needed to make a bet, send new bet roster
    private void parseMake(String message)
    {
        MakeInfo info = null; //parse info
        try
        {
            info = mapper.readValue(message, MakeInfo.class);
        }
        catch (JsonParseException e)    { e.printStackTrace(); }
        catch (JsonMappingException e)  { e.printStackTrace(); }
        catch (IOException e)           { e.printStackTrace(); }
        catch (Exception e)             { e.printStackTrace(); }

        //make bet
        if (info != null)
            betRoster.makeBet(info.getBetName());


        //sendRoster(session);            //send roster
    }

    //parse information needed to wager on a bet, send new bet roster and user info
    private void parsePlace(String message)
    {
        //parse info
        PlaceInfo info = null;
        try
        {
            info = mapper.readValue(message, PlaceInfo.class);
        }
        catch (JsonParseException e)    { e.printStackTrace(); }
        catch (JsonMappingException e)  { e.printStackTrace(); }
        catch (IOException e)           { e.printStackTrace(); }
        catch (Exception e)             { e.printStackTrace(); }

        //place bet
        if (info != null)
            betRoster.placeBet(info.getUsername(), info.getBetName(), info.getAmnt(), info.getOutcome());


        //sendRoster(session);            //send roster
        //sendUsers(session);             //send user info
    }

    //parse information needed to resolve a bet, send new bet roster and user info
    private void parseResolve(String message)
    {
        //parse info
        ResolveInfo info = null;
        try
        {
            info = mapper.readValue(message, ResolveInfo.class);
        }
        catch (JsonParseException e)    { e.printStackTrace(); }
        catch (JsonMappingException e)  { e.printStackTrace(); }
        catch (IOException e)           { e.printStackTrace(); }
        catch (Exception e)             { e.printStackTrace(); }

        //resolve bet
        if (info != null)
            betRoster.resolveBet(info.getBetName(), info.getOutcome());

        //sendRoster(session);            //send roster
        //sendUsers(session);             //send user info
    }

    //parse information needed to cancel a bet, send new bet roster and user info
    private void parseCancel(String message)
    {
        //parse info
        CancelInfo info = null;
        try
        {
            info = mapper.readValue(message, CancelInfo.class);
        }
        catch (JsonParseException e)    { e.printStackTrace(); }
        catch (JsonMappingException e)  { e.printStackTrace(); }
        catch (IOException e)           { e.printStackTrace(); }
        catch (Exception e)             { e.printStackTrace(); }

        //cancel bet
        if (info != null)
            betRoster.cancelBet(info.getBetName());

        //sendRoster(session);            //send roster
        //sendUsers(session);             //send user info
    }

    public Map<String, User> getBetterMap() { return betterMap; }

    //send roster info through socket
    public String getRosterJSON()
    {
        String out = "";
        BetInfo info = new BetInfo(betRoster.getRoster());

        //convert to JSON
        try
        {
            out = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }
        catch (Exception e)                 { e.printStackTrace(); }

        System.out.println(out);            //print result (testing)

        /*
        //wrap info
        MessageWrapper wrap = new MessageWrapper("betdata", mes);

        try
        {
            out = mapper.writeValueAsString(wrap);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }
        catch (Exception e)                 { e.printStackTrace(); }
        */

        //send(session, out);                 //send through socket
        return out;
    }

    //send user info through socket
    public String getUserJSON()
    {
        String out = "";
        UserInfo info = new UserInfo(betterMap);


        //convert to JSON
        try
        {
            out = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }
        catch (Exception e)                 { e.printStackTrace(); }

        System.out.println(out);            //print result (testing)

        /*
        MessageWrapper wrap = new MessageWrapper("userdata", mes);

        try
        {
            out = mapper.writeValueAsString(wrap);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }
        catch (Exception e)                 { e.printStackTrace(); }
        */

        //send(session, out);                 //send through socket
        return out;
    }

}
