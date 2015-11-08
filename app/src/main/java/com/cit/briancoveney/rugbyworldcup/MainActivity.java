package com.cit.briancoveney.rugbyworldcup;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.ArrayList;


/*--------------------------------------------------------------
>>> TABLE OF CONTENTS:
----------------------------------------------------------------
# 1.0 - Lucky Dip
# 2.0 - App in background
# 3.0 - User Input
---------------------------------------------------------------*/


public class MainActivity extends AppCompatActivity {

    //linked with (3.0 - User Input)
    private ArrayList<EditText> countryName;
    private EditText edTSemi1, edTSemi2, edTSemi3, edTSemi4, edTF1, edTF2, edTW,
            edTQrt1, edTQrt2, edTQrt3, edTQrt4, edTQrt5, edTQrt6, edTQrt7, edTQrt8;


    //linked with (2.0 - App in Background)
    private long startTime = 0;
    private long minAppStartTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linked with (3.0 - User Input)
        countryName = new ArrayList<EditText>();
        countryEditTextReferences();
        currentCountryTextWatcher();
        teamSelected();


/*========================================================================
   1.0 - Lucky Dip
==========================================================================*/

        //Constructors used to create Matches and initialise the fields to
        //to hold the fixed set of constants from the Team Enum.
        final Match match1 = new Match(Team.WAL, Team.RSA);
        final Match match2 = new Match(Team.NZL, Team.FRA);
        final Match match3 = new Match(Team.IRE, Team.ARG);
        final Match match4 = new Match(Team.AUS, Team.SCT);


        //Creating a Round for the ToggleButton below
        final Round rounds = new Round();


        //adding the above Matches to the Round object
        rounds.addMatch(match1);
        rounds.addMatch(match2);
        rounds.addMatch(match3);
        rounds.addMatch(match4);


        //ToggleButton for Show/Clear of Lucky Dip results
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    //Set Team 1 and Team 2 For Quarter Finals
                    edTQrt1.setText(Team.WAL.getTeamName());
                    edTQrt2.setText(Team.RSA.getTeamName());
                    edTQrt3.setText(Team.NZL.getTeamName());
                    edTQrt4.setText(Team.FRA.getTeamName());
                    edTQrt5.setText(Team.IRE.getTeamName());
                    edTQrt6.setText(Team.ARG.getTeamName());
                    edTQrt7.setText(Team.AUS.getTeamName());
                    edTQrt8.setText(Team.SCT.getTeamName());

                    /* Add Match and Choose Winners */
                    // for Semi Finals
                    ArrayList<Team> semis = rounds.playMatchesForRound();
                    edTSemi1.setText(semis.get(0).getTeamName());
                    edTSemi2.setText(semis.get(1).getTeamName());
                    edTSemi3.setText(semis.get(2).getTeamName());
                    edTSemi4.setText(semis.get(3).getTeamName());


                    // for Final games
                    Match finals1 = new Match(semis.get(0), semis.get(1));
                    Match finals2 = new Match(semis.get(2), semis.get(3));
                    edTF1.setText(finals1.chooseAWinner().toString());
                    edTF2.setText(finals2.chooseAWinner().toString());
                    ArrayList<String> theFinals = new ArrayList<String>();
                    theFinals.add(edTF1.getText().toString());
                    theFinals.add(edTF2.getText().toString());

                    //for the Winner
                    edTW.setText(theFinals.get(0).toString());

                } else {
                    //clear results by pressing Lucky Dip ToggleButton again
                    edTQrt1.getText().clear();
                    edTQrt2.getText().clear();
                    edTQrt3.getText().clear();
                    edTQrt4.getText().clear();
                    edTQrt5.getText().clear();
                    edTQrt6.getText().clear();
                    edTQrt7.getText().clear();
                    edTQrt8.getText().clear();
                    edTSemi1.setText(null);
                    edTSemi2.setText(null);
                    edTSemi3.setText(null);
                    edTSemi4.setText(null);
                    edTF1.setText(null);
                    edTF2.setText(null);
                    edTW.setText(null);
                }
            }
        });
    }// end onCreate



/*========================================================================
   2.0 App in Background
==========================================================================*/

    /* When the app is put into the background by Rotating the screen,
       the length of time is saved and displayed in a Toast */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        startTime = System.currentTimeMillis();
        savedInstanceState.putLong("savedTimerKey", startTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle restoredInstanceState)
    {
        //current up-time of the app
        long backgroundTime = System.currentTimeMillis();

        //the length of time the app is placed in the background - retrieved from the Bundle
        long savedStartTime = restoredInstanceState.getLong("savedTimerKey");

        //Toast - to display result
        Context context = getApplicationContext();
        int toastDuration = Toast.LENGTH_LONG;
        long millis = backgroundTime - savedStartTime;
        int seconds = (int) (millis / 1000);
        seconds = seconds % 60;
        CharSequence displayedText = String.format("App Rotated for " + "%02d:%d", seconds, millis);
        Toast toast = Toast.makeText(context, displayedText, toastDuration);
        toast.show();

        super.onRestoreInstanceState(restoredInstanceState);
    }



    /* When the app is put into the background by minimising and then re-opening the app,
       the length of time is saved and displayed in a Toast */
    @Override
    public void onPause()
    {
        super.onPause();
        minAppStartTime = SystemClock.uptimeMillis();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();

        //Toast - to display result
        Context context = getApplicationContext();
        int toastDuration = Toast.LENGTH_LONG;
        long millis = SystemClock.uptimeMillis() - minAppStartTime;
        int seconds = (int) (millis / 1000);
        int shortMillis = (int) ((millis / 100) % 10);
        seconds = seconds % 60;
        CharSequence displayedText
                = String.format("App Minimised for " + "%02d:%d", seconds, shortMillis);
        Toast toast = Toast.makeText(context, displayedText, toastDuration);
        toast.show();
    }



/*========================================================================
   3.0 User Input
==========================================================================*/


    /* Color Text
    to match Country Colors */

    //add all EditText to the ArrayList countryName
    public void countryEditTextReferences()
    {
        countryName.add((EditText) findViewById(R.id.eTxtQrt1));
        countryName.add((EditText) findViewById(R.id.eTxtQrt2));
        countryName.add((EditText) findViewById(R.id.eTxtQrt3));
        countryName.add((EditText) findViewById(R.id.eTxtQrt4));
        countryName.add((EditText) findViewById(R.id.eTxtQrt5));
        countryName.add((EditText) findViewById(R.id.eTxtQrt6));
        countryName.add((EditText) findViewById(R.id.eTxtQrt7));
        countryName.add((EditText) findViewById(R.id.eTxtQrt8));
        countryName.add((EditText) findViewById(R.id.eTxtSemi1));
        countryName.add((EditText) findViewById(R.id.eTxtSemi2));
        countryName.add((EditText) findViewById(R.id.eTxtSemi3));
        countryName.add((EditText) findViewById(R.id.eTxtSemi4));
        countryName.add((EditText) findViewById(R.id.eTxtFinal1));
        countryName.add((EditText) findViewById(R.id.eTxtFinal2));
        countryName.add((EditText) findViewById(R.id.eTxtWinner));
    }


    //add listener to the EditText that has focus
    private void currentCountryTextWatcher()
    {
        for(EditText currField : countryName)
        {
            currField.addTextChangedListener(new colorTextWatcher(currField));
        }
    }



    // Inner Class - colorTextWatcher, used for each EditText of the Quarter Finals
    private class colorTextWatcher implements TextWatcher
    {
        private EditText text;


        public colorTextWatcher(EditText text)
        {
            this.text = text;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            this.text.setError(null);}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            this.text.setError(null);}


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
                    this.text.setTextColor(Team.ARG.getColor());

                //not using getColor() anymore, as it's now deprecated, but it still works
                }else if(e.toString().equalsIgnoreCase(Team.AUS.toString())){
                    this.text.setTextColor(getResources().getColor(R.color.gold));
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

                //Error was not clearing without this
                if (e.toString().equals("")) {
                    this.text.setError(null);
                }
            }catch(Exception exc){exc.printStackTrace();}
        }
    }//end QuartersTextWatcher




    /* Validation
    checking for matching Teams */

    public void teamSelected()
    {
        // variables for use in the 3No. TextWatchers below
        edTQrt1 = (EditText)findViewById(R.id.eTxtQrt1);
        edTQrt2 = (EditText)findViewById(R.id.eTxtQrt2);
        edTQrt3 = (EditText)findViewById(R.id.eTxtQrt3);
        edTQrt4 = (EditText)findViewById(R.id.eTxtQrt4);
        edTQrt5 = (EditText)findViewById(R.id.eTxtQrt5);
        edTQrt6 = (EditText)findViewById(R.id.eTxtQrt6);
        edTQrt7 = (EditText)findViewById(R.id.eTxtQrt7);
        edTQrt8 = (EditText)findViewById(R.id.eTxtQrt8);
        edTSemi1 = (EditText)findViewById(R.id.eTxtSemi1);
        edTSemi2 = (EditText)findViewById(R.id.eTxtSemi2);
        edTSemi3 = (EditText)findViewById(R.id.eTxtSemi3);
        edTSemi4 = (EditText)findViewById(R.id.eTxtSemi4);
        edTF1 = (EditText)findViewById(R.id.eTxtFinal1);
        edTF2 = (EditText)findViewById(R.id.eTxtFinal2);
        edTW = (EditText)findViewById(R.id.eTxtWinner);


        //assigning EditText to Listeners
        edTQrt1.addTextChangedListener(QuarterValTextWatcher);
        edTQrt2.addTextChangedListener(QuarterValTextWatcher);
        edTQrt3.addTextChangedListener(QuarterValTextWatcher);
        edTQrt4.addTextChangedListener(QuarterValTextWatcher);
        edTQrt5.addTextChangedListener(QuarterValTextWatcher);
        edTQrt6.addTextChangedListener(QuarterValTextWatcher);
        edTQrt7.addTextChangedListener(QuarterValTextWatcher);
        edTQrt8.addTextChangedListener(QuarterValTextWatcher);
        edTSemi1.addTextChangedListener(SemiValTextWatcher);
        edTSemi2.addTextChangedListener(SemiValTextWatcher);
        edTSemi3.addTextChangedListener(SemiValTextWatcher);
        edTSemi4.addTextChangedListener(SemiValTextWatcher);
        edTF1.addTextChangedListener(FinalValTextWatcher);
        edTF2.addTextChangedListener(FinalValTextWatcher);
        edTW.addTextChangedListener(WinnerValTextWatcher);
    }



    //1st TW Checking that Semi Final entries match their respective Quarter Final entries
    TextWatcher SemiValTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable e)
        {

            try{
                //Semi Result No. 1 must match either Quarter 1 or 2
                if (edTSemi1.getText().toString().equalsIgnoreCase(edTQrt1.getText().toString()) ||
                        (edTSemi1.getText().toString().equalsIgnoreCase(edTQrt2.getText().toString()))) {
                    edTSemi1.setError(null);
                }else {
                    edTSemi1.setError("Enter Team 1 or Team 2");
                }


                //Semi Result No. 2 must match either Quarter 3 or 4
                if (edTSemi2.getText().toString().equalsIgnoreCase(edTQrt3.getText().toString()) ||
                        (edTSemi2.getText().toString().equalsIgnoreCase(edTQrt4.getText().toString()))){
                    edTSemi2.setError(null);
                }else {
                    edTSemi2.setError("Enter Team 3 or Team 4");
                }

                //Semi Result No. 3 must match either Quarter 5 or 6
                if (edTSemi3.getText().toString().equalsIgnoreCase(edTQrt5.getText().toString()) ||
                        (edTSemi3.getText().toString().equalsIgnoreCase(edTQrt6.getText().toString()))){
                    edTSemi3.setError(null);
                }else {
                    edTSemi3.setError("Enter Team 5 or Team 6");
                }

                //Semi Result No. 4 must match either Quarter 7 or 8
                if (edTSemi4.getText().toString().equalsIgnoreCase(edTQrt7.getText().toString()) ||
                        (edTSemi4.getText().toString().equalsIgnoreCase(edTQrt8.getText().toString()))){
                    edTSemi4.setError(null);
                }else {
                    edTSemi4.setError("Enter Team 7 or Team 8");
                }
            }catch(Exception ex){ex.printStackTrace();}

        } //end afterTextChanged

    };//end SemiValTextWatcher



    //2nd TextWatcher - Checking that Final entries match their respective Semi Final entries
    TextWatcher FinalValTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable e) {
            try{
                //Final Result No. 1 must match either Semis 1 or 2
                if (edTF1.getText().toString().equalsIgnoreCase(edTSemi1.getText().toString()) ||
                        (edTF1.getText().toString().equalsIgnoreCase(edTSemi2.getText().toString()))) {
                    edTF1.setError(null);
                } else {
                    edTF1.setError("Enter Team 1 or Team 2");
                }

                //Semi Result No. 2 must match either Semis 3 or 4
                if (edTF2.getText().toString().equalsIgnoreCase(edTSemi3.getText().toString()) ||
                        (edTF2.getText().toString().equalsIgnoreCase(edTSemi4.getText().toString()))) {
                    edTF2.setError(null);
                } else {
                    edTF2.setError("Enter Team 3 or Team 4");
                }
            }catch(Exception ex){ex.printStackTrace();}
        }
    };//end FinalValTextWatcher



    //3rd TextWatcher -Checking that Winner entry matchrs eoth of the Semi Final entries
    TextWatcher WinnerValTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable e)
        {
            try{
                //The Winner must match either Final 1 or 2
                if (edTW.getText().toString().equalsIgnoreCase(edTF1.getText().toString()) ||
                        (edTW.getText().toString().equalsIgnoreCase(edTF2.getText().toString()))) {
                    edTW.setError(null);
                }else {
                    edTW.setError("Enter Final 1 or 2");
                }
            }catch(Exception ex){ex.printStackTrace();}
        }

    };//end WinnerValTextWatcher


    //4th  TextWatcher - Checking that Quarters Teams are not duplicated
    TextWatcher QuarterValTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable e)
        {

            //get index of the ArrayList and assign it to variables
            String team1 = countryName.get(0).getText().toString();
            String team2 = countryName.get(1).getText().toString();
            String team3 = countryName.get(2).getText().toString();
            String team4 = countryName.get(3).getText().toString();
            String team5 = countryName.get(4).getText().toString();
            String team6 = countryName.get(5).getText().toString();
            String team7 = countryName.get(6).getText().toString();
            String team8 = countryName.get(7).getText().toString();


            //use variables to catch duplication of Team entries in Quarter Finals
            try{
                if(team1.equals(team2)){
                    edTQrt2.setError("No duplicate teams");
                }else {
                    edTQrt2.setError(null);
                }
                //removes error when user clears field
                if (edTQrt2.getText().toString().equals("")){
                    edTQrt2.setError(null);
                }

                if(team3.equals(team4)){
                    edTQrt4.setError("No duplicate teams");
                }else {
                    edTQrt4.setError(null);
                }
                if (edTQrt4.getText().toString().equals("")){
                    edTQrt4.setError(null);
                }

                if(team5.equals(team6)){
                    edTQrt6.setError("No duplicate teams");
                }else {
                    edTQrt6.setError(null);
                }
                if (edTQrt6.getText().toString().equals("")){
                    edTQrt6.setError(null);
                }

                if(team7.equals(team8)){
                    edTQrt8.setError("No duplicate teams");
                }else {
                    edTQrt8.setError(null);
                }
                if (edTQrt8.getText().toString().equals("")){
                    edTQrt8.setError(null);
                }

            }catch(Exception ex){ex.printStackTrace();}
        }

    };//end QuarterValTextWatcher

}//end MainApplication