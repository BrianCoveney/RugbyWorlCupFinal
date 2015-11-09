package com.cit.briancoveney.rugbyworldcup;

/**
 * Created by Brian Coveney on 10/22/15.
 * Student ID: R00105727
 */
public class Match {
    private Team teamOne;
    private Team teamTwo;


    public Match(Team teamOne, Team teamTwo)
    {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }


    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }


    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Team chooseAWinner()
    {
        double randomNum = Math.random();
        if(randomNum <= 0.9 )
        {
            return teamOne;
        }
        else
        {
            return teamTwo;
        }
    }
}
