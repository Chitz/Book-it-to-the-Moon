package com.me.bookittothemoon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivitySelection extends AppCompatActivity {
    protected int ageGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_selection);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPref.getString("userName", "");
        ageGroup= sharedPref.getInt("ageGroup", 0);

        TextView greetings = (TextView)findViewById(R.id.txtGreetings);
        Button option1 = (Button)findViewById(R.id.btnOption1);
        Button option2 = (Button)findViewById(R.id.btnOption2);
        Button option3 = (Button)findViewById(R.id.btnOption3);

        greetings.setText("Greetings " + userName + "\nWhat would you like to do today?");

        if (ageGroup == 1){
            option1.setText("Play Game");
            option2.setText("Watch Videos");
            option3.setText("View Pictures");
        }
        if (ageGroup == 2){
            option1.setText("Images");
            option2.setText("Fun Facts");
            option3.setText("Flashcards");
        }
        if (ageGroup == 3){
            option1.setText("Images");
            option2.setText("Fun Facts");
            option3.setText("Quiz");
        }

    }

    public void optionListener(View view){
        int selectedOption;
        switch (view.getId()){
            case R.id.btnOption1:
                selectedOption = 1;
                break;
            case R.id.btnOption2:
                selectedOption = 2;
                break;
            case R.id.btnOption3:
                selectedOption = 3;
                break;
            case R.id.btnFindMoon:
                selectedOption = 4;
                break;
            default:
                selectedOption = 0;

        }

        if (selectedOption == 4)
            startActivity(new Intent(ActivitySelection.this, FindTheMoon.class));
       else if (ageGroup == 1 && selectedOption == 1)
                startActivity(new Intent(ActivitySelection.this, PlanetGame.class));
       else if (ageGroup == 1 && selectedOption == 2)
            startActivity(new Intent(ActivitySelection.this, PreK_Videos.class));
       else if (ageGroup == 1 && selectedOption == 3)
            startActivity(new Intent(ActivitySelection.this, PreK_Images.class));
        else if ((ageGroup == 2 || ageGroup == 3) && selectedOption == 1)
            startActivity(new Intent(ActivitySelection.this, Images.class));
        else if ((ageGroup == 2 || ageGroup == 3) && selectedOption == 2)
            startActivity(new Intent(ActivitySelection.this, FunFacts.class));
        else if (ageGroup == 2 && selectedOption == 3)
            startActivity(new Intent(ActivitySelection.this, FlashCards.class));
        else if (ageGroup == 3 && selectedOption == 3)
            startActivity(new Intent(ActivitySelection.this, Quiz.class));



    }
}
