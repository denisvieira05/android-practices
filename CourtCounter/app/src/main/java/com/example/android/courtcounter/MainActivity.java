package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//
//        if(id == R.id.action_settings){
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    public void addPointForTeam(View v){

        switch(v.getId()){
            case R.id.btn_teamB_3:
                //Do something for button 1
                scoreTeamB += 3;
                displayForTeam("teamB");
                break;
            case R.id.btn_teamB_2:
                //Do something for button 2
                scoreTeamB += 2;
                displayForTeam("teamB");
                break;
            case R.id.btn_teamB_1:
                //Do something for button 3
                scoreTeamB += 1;
                displayForTeam("teamB");
                break;
            case R.id.btn_teamA_3:
                //Do something for button 1
                scoreTeamA += 3;
                displayForTeam("teamA");
                break;
            case R.id.btn_teamA_2:
                //Do something for button 2
                scoreTeamA += 2;
                displayForTeam("teamA");
                break;
            case R.id.btn_teamA_1:
                //Do something for button 3
                scoreTeamA += 1;
                displayForTeam("teamA");
                break;
        }

    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeam(String team) {

        if(team == "teamA"){
            TextView scoreView = (TextView) findViewById(R.id.team_a_score);
            scoreView.setText(String.valueOf(scoreTeamA));
        }else{
            TextView scoreView = (TextView) findViewById(R.id.team_b_score);
            scoreView.setText(String.valueOf(scoreTeamB));
        }

    }

    public void resetScore(View v){
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeam("teamA");
        displayForTeam("teamB");
    }
}
