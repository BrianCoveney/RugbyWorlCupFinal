package com.cit.briancoveney.rugbyworldcup;

/**
 * Created by brian on 11/7/2015.
 */
public class Country {

    private String teamName;
    private int color;

    public Country(String teamName)
    {
        this.teamName = teamName;
    }

    public Country() {

    }


    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }



    public String getTeamName()
    {
        return teamName;
    }
}
