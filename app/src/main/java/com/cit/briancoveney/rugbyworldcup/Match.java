package com.cit.briancoveney.rugbyworldcup;

/**
 * Created by briancoveney on 10/22/15.
 */
public class Match {
    private Team teamOne;
    private Team teamTwo;

    public Match(){

    }


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
        if(randomNum <= 0.9)
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
