package com.betcha.SocketChat;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Contains the list of active bets and handles operations.
 */
public class BetRoster
{
    private RosterCtrl ctrl;
    private List<Bet> roster = new ArrayList<Bet>();

    public List<Bet> getRoster() { return roster; }

    //Add a user to the chat room and betting socket
    void parseBet(User usr, String message)
    {
        Scanner input = new Scanner(message);
        String cmd, betName, outcome = "";
        int amnt = 0;

        //check if bet command
        if (input.next().equals("bet"))
        {
            if (input.hasNext())
            {
                cmd = input.next();

                switch (cmd)
                {
                    //create new bet
                    case "make":
                        if (input.hasNext())
                        {
                            betName = input.next();
                            makeBet(betName);
                        }
                        else //error: name not specified
                            System.out.println("use: \"bet make <name>\"");
                        break;

                    //wager on bet
                    case "place":
                        if (input.hasNext())
                        {
                            betName = input.next();

                            if (input.hasNextInt())
                            {
                                amnt = input.nextInt();

                                if (input.hasNext())
                                {
                                    outcome = input.next();

                                    switch (outcome.toLowerCase())
                                    {
                                        case "y":
                                            placeBet(usr.getName(), betName, amnt, true);
                                            break;
                                        case "n":
                                            placeBet(usr.getName(), betName, amnt, false);
                                            break;
                                        default: //error: invalid outcome
                                            System.out.println("use: \"bet place <name> <amount> <Y/N>\"");
                                            break;
                                    }
                                }
                                else //error: outcome not specified
                                    System.out.println("use: \"bet place <name> <amount> <Y/N>\"");
                            }
                            else //error: no amount
                                System.out.println("use: \"bet place <name> <amount> <Y/N>\"");
                        } else //error: bet not specified
                            System.out.println("use: \"bet place <name> <amount> <Y/N>\"");
                        break;

                    //resolve bet
                    case "result":
                        if (input.hasNext())
                        {
                            betName = input.next();

                            if (input.hasNext())
                            {
                                outcome = input.next();

                                switch (outcome.toLowerCase())
                                {
                                    case "y":
                                        resolveBet(betName, true);
                                        break;
                                    case "n":
                                        resolveBet(betName, false);
                                        break;
                                    default: //error: invalid outcome
                                        System.out.println("use: \"bet result <name> <Y/N>\"");
                                        break;
                                }
                            }
                            else //error: outcome not specified
                                System.out.println("use: \"bet result <name> <Y/N>\"");
                        }
                        else //error: bet not specified
                            System.out.println("use: \"bet result <name> <Y/N>\"");
                        break;

                    //view placed bets
                    case "view":
                        printBets();
                        break;

                    //refund bet
                    case "cancel":
                        if (input.hasNext())
                        {
                            betName = input.next();
                            cancelBet(betName);
                        }
                        else
                            System.out.println("use: \"bet cancel <name>\"");
                        break;

                    //error: command not recognized
                    default:
                        System.out.println("use: \"bet make/place/result/view\"");
                        break;
                }
            }
            else
                System.out.println("use: \"bet make/place/result\"");

        } //else: not bet command, no error
    }

    //user wagers tokens on an existing bet
    public void placeBet(String usr, String betName, int amnt, boolean outcome)
    {
        final int minBet = 1;

        //BetPlacement wager = new BetPlacement(usr, amnt, outcome);
        Bet bet = get(betName);

        //check if valid amount
        if (amnt < minBet)
        {
            System.out.println("Error: bet amount must be positive");
            return;
        }

        //check if bet exists
        if (bet != null)
        {
            //check if user has already placed on this bet
            if (!bet.hasPlaced(usr))
            {
                // place wager
                ctrl.getUser(usr).tokens -= amnt;
                bet.addPlacement(usr, amnt, outcome);
            }
            else
                System.out.println("error: user has already wagered on this bet");

        }
        else //error: bet does not exist
            System.out.println("Error: bet does not exist");

        ctrl.getUser(usr).printTokens();      //print out user's tokens
    }

    //create a new bet
    public String makeBet(String betName)
    {
        //check if bet exists
        if (get(betName) != null)
            System.out.println("Bet already exists");
        else
            roster.add(new Bet(betName, ctrl));

        return betName
    }

    //resolve bet
    public void resolveBet(String betName, boolean outcome)
    {
        Bet bet = get(betName);

        if (bet != null)
        {
            bet.payout(outcome);
            roster.remove(bet);
        }
        else
            System.out.println("Error: bet does not exist");
    }

    //prints current active bets
    public  void printBets()
    {
        //empty case
        if (roster.isEmpty())
            System.out.println("No active bets");
        else
        {
            for (Bet b : roster)
                b.printBet();
        }
    }

    //cancels a bet and refunds all wagers
    public void cancelBet(String betName)
    {
        Bet bet = get(betName);

        //check if exists
        if (bet == null)
            System.out.println("Error: bet does not exist");
        else
        {
            bet.refund();
            roster.remove(bet);
        }
    }

    //gets a bet by name (not case-sensitive)
    @Nullable
    private Bet get(String betName)
    {
        for (Bet b : roster)
        {
            if (b.getName().toLowerCase().equals(betName.toLowerCase()))
                return b;
        }

        return null;
    }

    public BetRoster(RosterCtrl ctrl)    { this.ctrl = ctrl; }
}
