package com.cit.briancoveney.rugbyworldcup;

/**
 * Created by briancoveney on 10/22/15.
 */
public enum Team {
    IRE("ire"),
    SCT("sct"),
    AUS("aus"),
    NZL("nzl"),
    WAL("wal"),
    RSA("rsa"),
    ARG("arg"),
    FRA("fra"),
    SAM("sam"),
    ENG("eng"),
    ITA("ita"),
    JPN("jpn"),
    ROM("rom"),
    CAN("can"),
    FIJ("fij"),
    URG("urg");

    private String teamName;


    private Team(String teamName)
    {
        this.teamName = teamName;

    }

    public String getTeamName()
    {
        return teamName;
    }
}
