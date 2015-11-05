package com.cit.briancoveney.rugbyworldcup;

/**
 * Created by brian on 11/4/2015.
 */
public class Country {

    private String countryName;

    public Country() {

    }

    public Country(String countryName)
    {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
