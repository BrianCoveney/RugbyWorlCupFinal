package com.cit.briancoveney.rugbyworldcup;

/**
 * Created by briancoveney on 10/22/15.
 */
public enum Team {
    IRE("ire", -16711936),
    SCT("sct", -1),
    AUS("aus", -256),
    NZL("nzl", -256),
    WAL("wal", -65536),
    RSA("rsa", -256),
    ARG("arg", -16711681),
    FRA("fra", -16776961),
    SAM("sam", -16776961),
    ENG("eng", -1),
    ITA("ita", -1),
    JPN("jpn", -256),
    ROM("rom", -256),
    CAN("can", -65281),
    FIJ("fij", -65536),
    URG("urg", -16711681);

    private String teamName;
    private int color;

    private Team(String teamName, int color)
    {
        this.teamName = teamName;
        this.color = color;
    }



    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTeamName()
    {
        return teamName.toUpperCase();
    }


}
