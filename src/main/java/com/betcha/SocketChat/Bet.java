package com.betcha.SocketChat;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

//bet condition to be wagered on
public class Bet
{
    private String name;    //bet condition
    private List<BetPlacement> placements = new ArrayList<BetPlacement>();
    private RosterCtrl ctrl;

    @Override
    //used to check if bet already exists
    public boolean equals(Object obj)
    {
        //check reference
        if (obj == this)
            return true;

        if (!(obj instanceof Bet))
            return false;

        Bet b = (Bet) obj;

        return name.toLowerCase().equals(b.name.toLowerCase());
    }

    //gets the total pool on one side of the bet
    public int getPool(boolean side)
    {
        int sum = 0;

        for (BetPlacement place : placements)
        {
            if (place.outcome == side)
                sum += place.amnt;
        }

        return sum;
    }

    public String getName() { return name; }

    //prints out bet info that a user should see
    public void printBet()
    {
        System.out.println(name + "      Y: " + getPool(true) + " | N: " + getPool(false));
    }

    public boolean hasPlaced(String user)
    {
        for (BetPlacement wager : placements)
        {
            if (user.equals(wager.username))
                return true;
        }

        return false;
    }

    //deletes bet and returns all placed wagers
    public void refund()
    {
        User user;
        for (BetPlacement wager : placements)
        {
            user = ctrl.getUser(wager.username);
            user.tokens += wager.amnt;
            user.printTokens();
        }
        System.out.println();
        //roster.remove(this);
    }

    //resolve bet and give payout
    public void payout(boolean outcome)
    {
        //Bet bet = get(betName);
        int y, n = 0;

        //check if bet exists
        //if (bet != null)
        //{
            y = getPool(true);
            n = getPool(false);
            //check if unopposed
            if (y == 0 || n == 0)
                refund();
            else
            {
                User user;
                for (BetPlacement wager : placements)
                {
                    user = ctrl.getUser(wager.username);
                    if (wager.outcome == outcome)
                    {
                        user.tokens += (y + n) * wager.amnt / (outcome ? y : n);      //reward tokens
                    }
                    user.printTokens();                                               //give each entrant's totals
                }
                System.out.println();                                                       //formatting
                //roster.remove(bet);                                                         //remove bet from roster
            }
        //}
        //else
        //    System.out.println("Error: bet does not exist");
    }

    //adds bet placement
    public void addPlacement(String user, int amnt, boolean outcome)
    {
        placements.add(new BetPlacement(user, amnt, outcome));
    }

    @JsonCreator
    public Bet(@JsonProperty("name") String name, @JsonProperty("ctrl") RosterCtrl ctrl)
    {
        this.name = name;
        this.ctrl = ctrl;
    }

    //dummy constructor
    public Bet() { }

    //tracks a user's amount wagered on a bet
    private class BetPlacement
    {
        public int amnt;
        public String username;
        public boolean outcome;

        @JsonCreator
        public BetPlacement(@JsonProperty("username") String username,
                            @JsonProperty("amnt") int amnt,
                            @JsonProperty("outcome") boolean outcome)
        {
            this.amnt = amnt;
            this.username = username;
            this.outcome = outcome;
        }

        //dummy constructor
        public BetPlacement() { }

        @Override
        //to determine if user has already wagered on bet
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;
            if (!(obj instanceof BetPlacement))
                return false;

            BetPlacement b = (BetPlacement) obj;

            return username.equals(b.username);
        }
    }
}
