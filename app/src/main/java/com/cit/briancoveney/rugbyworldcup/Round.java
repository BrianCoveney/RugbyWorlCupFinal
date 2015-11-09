package com.cit.briancoveney.rugbyworldcup;

import java.util.ArrayList;

/**
 * Created by Brian Coveney on 10/22/15.
 * Student ID: R00105727
 */
public class Round {
    private ArrayList<Match> theMatches;

    public Round()
    {
        this.theMatches = new ArrayList<Match>();
    }

    public void addMatch(Match aMatch)
    {
        this.theMatches.add(aMatch);
    }

    public  ArrayList<Team> playMatchesForRound()
    {
        ArrayList<Team> winners = new ArrayList<Team>();

        for(Match aMatch : theMatches)
        {
            winners.add(aMatch.chooseAWinner());
        }
        return winners;
    }

}
