package com.cit.briancoveney.rugbyworldcup;

/**
 * Created by briancoveney on 10/22/15.
 */
public class Match {
    private Team teamOne;
    private Team teamTwo;

    private Match preceedingMatch;

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
        int randomNum = (int)Math.random();
        if(randomNum == 0)
        {
            return teamOne;
        }
        else
        {
            return teamTwo;
        }
    }

    public boolean isWinnerValid(String name)
    {
        if(name.equals(teamOne.getTeamName()) || name.equals(teamTwo.getTeamName()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isMatchReaadyToPlay()
    {
        if(teamOne != null && teamTwo != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
