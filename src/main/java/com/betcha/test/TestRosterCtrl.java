package com.betcha.test;

import com.betcha.SocketChat.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TestRosterCtrl
{
    private RosterCtrl rio;
    private ObjectMapper mapper;

    //public TestJSON(RosterCtrl rio)     { this.rio = rio; }

    private void setupTest()
    {
        rio = new RosterCtrl();
        mapper = new ObjectMapper();

        //add betters
        rio.add("Larry");
        rio.add("Curly");
        rio.add("Moe");

        //make two bets
        makeBet("bet1");
        makeBet("bet2");
        makeBet("bet3");

        //each bets different amounts on each
        placeBet("Larry", "bet1", 200, true);
        placeBet("Curly", "bet1", 100, true);
        placeBet("Moe", "bet1", 100, false);
        placeBet("Larry", "bet2", 100, true);
        placeBet("Curly", "bet2", 300, false);
        placeBet("Moe", "bet2", 100, false);

        //resolve one bet
        resolveBet("bet1", true);

        //interpret outcome
    }

    private void makeBet(String betName)
    {
        MakeInfo info = new MakeInfo(betName);                                  //create bet info
        //String message = "";
        String out = "";

        //serialize
        /*
        try
        {
            message = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }


        MessageWrapper wrap = new MessageWrapper("make", info);      //put into wrapper
        */

        //serialize
        try
        {
            out = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }

        System.out.println("Message out: " + out);
        rio.parse(out);                                                         //send to ctrl
    }

    private void placeBet(String better, String betName, int amnt, boolean outcome)
    {
        PlaceInfo info = new PlaceInfo(better, betName, amnt, outcome);       //create place info
        //String message = "";
        String out = "";

        //serialize
        /*
        try
        {
            message = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }

        MessageWrapper wrap = new MessageWrapper("place", info);     //put into wrapper
        */

        //serialize)
        try
        {
            out = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }

        rio.parse(out);                                                     //send to ctrl
    }

    private void resolveBet(String betname, boolean outcome)
    {
        ResolveInfo info = new ResolveInfo(betname, outcome);               //create resolve info
        //String message = "";
        String out = "";

        //serialize
        /*
        try
        {
            message = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }

        MessageWrapper wrap = new MessageWrapper("resolve", message);    //put into wrapper
        */

        //serialize
        try
        {
            out = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }

        System.out.println("Message out: " + out);
        rio.parse(out);                                                     //send to ctrl
    }

    private void cancelBet(String betname)
    {
        CancelInfo info = new CancelInfo(betname);                          //create cancel info
        //String message = "";
        String out = "";

        //serialize
        /*
        try
        {
            message = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }

        MessageWrapper wrap = new MessageWrapper("cancel", message);    //put into wrapper
        */

        //serialize
        try
        {
            out = mapper.writeValueAsString(info);
        }
        catch (JsonProcessingException e)   { e.printStackTrace(); }

        rio.parse(out);                                                     //send to ctrl
    }

    private Map<String, User> readUsers()
    {
        String in = rio.getUserJSON();                                      //retrieve message
        Map<String, User> users = new ConcurrentHashMap<>();
        SignalInfo info;

        //deserialize
        try
        {
            UserInfo ui;
            info = mapper.readValue(in, UserInfo.class);

            //check type
            if (!(info instanceof UserInfo))
                System.out.println("Message is not of type userinfo!");
            else
            {
                ui = (UserInfo) info;                                   //cast to UserInfo type
                users = ui.getUsers();
            }

        }
        catch (JsonMappingException e)  { e.printStackTrace(); }
        catch (JsonParseException e)    { e.printStackTrace(); }
        catch (IOException e)           { e.printStackTrace(); }

        return users;
    }

    private List<Bet> readBets()
    {
        String in = rio.getRosterJSON();                                //retrieve message
        List<Bet> bets = new ArrayList<Bet>();
        SignalInfo info;

        //deserialize
        try
        {
            BetInfo bi;
            info = mapper.readValue(in, SignalInfo.class);

            //check type
            if (!(info instanceof BetInfo))
                System.out.println("Message is not of type betinfo!");
            else
            {
                bi = (BetInfo)info;
                bets = bi.getBets();
            }
        }
        catch (JsonMappingException e)  { e.printStackTrace(); }
        catch (JsonParseException e)    { e.printStackTrace(); }
        catch (IOException e)           { e.printStackTrace(); }

        return bets;
    }

    @Test
    public void testUsers()
    {
        setupTest();
        Map<String, User> expected = rio.getBetterMap();
        System.out.println("Expected: " + expected.get("Larry").tokens);

        Map<String, User> users = readUsers();

        for (User u : users.values())
            u.printTokens();

        assertEquals(466, users.get("Larry").tokens);
    }

    @Test
    public void testBets()
    {
        setupTest();
        List<Bet> bets = readBets();
        assertEquals("bet2", bets.get(0).getName());
    }

    @Test
    public void testCancel()
    {
        setupTest();
        cancelBet("bet2");
        List<Bet> bets = readBets();
        assertEquals("bet3", bets.get(0).getName());
    }
}
