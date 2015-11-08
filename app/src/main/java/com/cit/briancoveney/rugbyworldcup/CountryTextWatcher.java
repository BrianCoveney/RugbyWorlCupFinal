package com.cit.briancoveney.rugbyworldcup;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by brian on 11/7/2015.
 */
public class CountryTextWatcher implements TextWatcher{

    private EditText text;


    public CountryTextWatcher(EditText text)
    {
        this.text = text;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {this.text.setError(null);}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {this.text.setError(null);}



    //Change EditText text colour when entering Country Name abbreviations
    @Override
    public void afterTextChanged(Editable e)
    {
        try{
            if(e.toString().equalsIgnoreCase(Team.IRE.toString())) {
                this.text.setTextColor(Team.IRE.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.ENG.toString())){
                this.text.setTextColor(Team.ENG.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.WAL.toString())){
                this.text.setTextColor(Team.WAL.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.SCT.toString())){
                this.text.setTextColor(Team.SCT.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.ITA.toString())){
                this.text.setTextColor(Team.ITA.getColor());
            }else if (e.toString().equalsIgnoreCase(Team.RSA.toString())){
                this.text.setTextColor(Team.RSA.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.SAM.toString())){
                this.text.setTextColor(Team.SAM.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.JPN.toString())) {
                this.text.setTextColor(Team.JPN.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.ROM.toString())) {
                this.text.setTextColor(Team.ROM.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.CAN.toString())) {
                this.text.setTextColor(Team.CAN.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.FIJ.toString())) {
                this.text.setTextColor(Team.FIJ.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.URG.toString())) {
                this.text.setTextColor(Team.URG.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.NZL.toString())) {
                this.text.setTextColor(Team.NZL.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.FRA.toString())) {
                this.text.setTextColor(Team.FRA.getColor());
            }else if(e.toString().equalsIgnoreCase(Team.ARG.toString())) {
                this.text.setTextColor(Team.FRA.getColor());
            }
        }catch(Exception ex){ex.printStackTrace();}


        //Catches when user enter a number instead of a string
        String regexStr = "^[0-9]*$";
        try {
            if (!(e.toString().trim().matches(regexStr))) {
                this.text.setError(null);
            } else {
                this.text.setError("No digits");
            }

            //Clear error when user clears field
            if (e.toString().equals("")) {
                this.text.setError(null);
            }
        }catch(Exception exc){exc.printStackTrace();}
    }
}//end QuartersTextWatcher

