package com.example.android.courtcounter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Creating two Stacks to record point value assignment using FILO for undo
    Stack<Integer> teamAScoreStack = new Stack<Integer>();
    Stack<Integer> teamBScoreStack = new Stack<Integer>();

    //Team score's starting at 0 points.
    int teamA_Score = 0;
    int teamB_Score = 0;

    //Possible Point value's
    int touchDown = 6;
    int extraPoint = 1;
    int twoConversion = 2;
    int fieldGoal = 3;
    int safety = 2;


    /*
    This method will add points to Team A or Team B
    depending which point value button is selected.
    It will also Stack.push() a point value to Team's stack
    to allow the undo button to operate.
    */
    public void addPoints(View view) {
        switch (view.getId()) {
            case R.id.Team_A_TouchDown_Button:
                teamA_Score = teamA_Score + touchDown;
                teamAScoreStack.push(touchDown);
                displayForTeamA(teamA_Score);
                break;

            case R.id.Team_A_ExtraPoint_Button:
                teamA_Score = teamA_Score + extraPoint;
                teamAScoreStack.push(extraPoint);
                displayForTeamA(teamA_Score);
                break;

            case R.id.Team_A_TwoConversion_Button:
                teamA_Score = teamA_Score + twoConversion;
                teamAScoreStack.push(twoConversion);
                displayForTeamA(teamA_Score);
                break;

            case R.id.Team_A_FieldGoal_Button:
                teamA_Score = teamA_Score + fieldGoal;
                teamAScoreStack.push(fieldGoal);
                displayForTeamA(teamA_Score);
                break;

            case R.id.Team_A_Safety_Button:
                teamA_Score = teamA_Score + safety;
                teamAScoreStack.push(safety);
                displayForTeamA(teamA_Score);
                break;

            case R.id.Team_B_TouchDown_Button:
                teamB_Score = teamB_Score + touchDown;
                teamBScoreStack.push(touchDown);
                displayForTeamB(teamB_Score);
                break;

            case R.id.Team_B_ExtraPoint_Button:
                teamB_Score = teamB_Score + extraPoint;
                teamBScoreStack.push(extraPoint);
                displayForTeamB(teamB_Score);
                break;

            case R.id.Team_B_TwoConversion_Button:
                teamB_Score = teamB_Score + twoConversion;
                teamBScoreStack.push(twoConversion);
                displayForTeamB(teamB_Score);
                break;

            case R.id.Team_B_FieldGoal_Button:
                teamB_Score = teamB_Score + fieldGoal;
                teamBScoreStack.push(fieldGoal);
                displayForTeamB(teamB_Score);
                break;

            case R.id.Team_B_Safety_Button:
                teamB_Score = teamB_Score + safety;
                teamBScoreStack.push(safety);
                displayForTeamB(teamB_Score);
                break;


        }
    }


    /*This will undo the last point entered for Team A.
    It is using Stack operations (FILO) with exception handling if stack is empty
    Will also prevent a negative score from being displayed
    and will provide a toast message to let the user know.
    */
    public void undoAScore(View view) {

        if (!teamAScoreStack.isEmpty()) {
            Integer pop = teamAScoreStack.pop();
            teamA_Score = teamA_Score - pop;
            if (teamA_Score > 0) {

                displayForTeamA(teamA_Score);
            } else {
                Toast.makeText(MainActivity.this, "No More Scores to Undo",
                        Toast.LENGTH_SHORT).show();
                teamAScoreStack.clear();
                teamA_Score = 0;
                displayForTeamA(teamA_Score);
            }

        } else {
            Toast.makeText(MainActivity.this, "Ok we get it, you like pushing buttons",
                    Toast.LENGTH_SHORT).show();
            teamA_Score = 0;
        }
    }

    /*This will undo the last point entered for Team B.
    It is using Stack operations (FILO) with exception handling if stack is empty
    Will also prevent a negative score from being displayed
    and will provide a toast message to let the user know.
    */
    public void undoBScore(View view) {

        if (!teamBScoreStack.isEmpty()) {
            Integer pop = teamBScoreStack.pop();
            teamB_Score = teamB_Score - pop;
            if (teamB_Score > 0) {
                displayForTeamB(teamB_Score);
            } else {
                Toast.makeText(MainActivity.this, "No More Scores to Undo",
                        Toast.LENGTH_SHORT).show();
                teamBScoreStack.clear();
                teamB_Score = 0;
                displayForTeamB(teamB_Score);
            }

        } else {
            Toast.makeText(MainActivity.this, "Ok we get it, you like pushing buttons",
                    Toast.LENGTH_SHORT).show();
            teamB_Score = 0;
        }
    }


    //Reset both team scores to 0
    public void resetScore(View view) {
        teamB_Score = 0;
        teamA_Score = 0;
        displayForTeamB(teamB_Score);
        displayForTeamA(teamA_Score);
    }


    //  Displays the given score for Team A.

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));

    }


    // Displays the given score for Team B.

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }


}



