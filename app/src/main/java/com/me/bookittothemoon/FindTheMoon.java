package com.me.bookittothemoon;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FindTheMoon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_moon);


        DBAdapter dbAdapter = new DBAdapter(this);
        //dbAdapter.batchUpload_QuestionAndAnswer();
        //dbAdapter.batchUpload_Fun_Fact();
        ArrayList<HashMap<String, String>> res = dbAdapter.getQuestions(0);
        System.out.println(res.toString());

        ArrayList<HashMap<String, String>> res1 = dbAdapter.getFunFacts(0);
        System.out.println(res1.toString());

        //dbAdapter.onCreate();
        FetchMoonData fetchMoonData = new FetchMoonData();
        FetchCountDown fetchCountDown = new FetchCountDown();
        Long[] ret = new Long[0];
        try {
            fetchMoonData.getDetails(getApplicationContext(), "39.768403", "-86.15806800000001");
            ret = fetchCountDown.calculateCountDown(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final TextView txtCountDownTimer = (TextView) findViewById(R.id.txtCountDownTimer);
        final TextView txtMoonRiseOrSet = (TextView) findViewById(R.id.txtMoonRiseOrSet);


        String MoonPhaseName = fetchMoonData.getMoonPhase(getApplicationContext());
        MoonPhaseName = MoonPhaseName.replaceAll(" ", "_").toLowerCase();


        System.out.println("MoonPhaseName " + MoonPhaseName);

        String imageName = MoonPhaseName;  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());

        final ImageView imgMoonPhase = (ImageView) findViewById(R.id.imgMoonPhase);
        imgMoonPhase.setImageResource(imageResource);


//        String displayTxtMoonRiseOrSet = "";
//
//        if(ret[0] == 1)
//            displayTxtMoonRiseOrSet = "MOON SET in";
//        else
//            displayTxtMoonRiseOrSet = "MOON RISE in";

        //Long tsLong = System.currentTimeMillis()/1000;
        //final String ts = tsLong.toString();
        final Long isMoonRiseOrSet = ret[0];
        System.out.println("Final difference " + ret[1]);
        new CountDownTimer(ret[1], 1) {

            public void onTick(long millisUntilFinished) {
                txtCountDownTimer.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(millisUntilFinished)));
                String displayTxtMoonRiseOrSet = "";
                if (isMoonRiseOrSet == 1)
                    displayTxtMoonRiseOrSet = "MOON SET in";
                else
                    displayTxtMoonRiseOrSet = "MOON RISE in";
                txtMoonRiseOrSet.setText(displayTxtMoonRiseOrSet);
            }

            public void onFinish() {
                String displayTxtMoonRiseOrSet = "";
                if (isMoonRiseOrSet == 1)
                    displayTxtMoonRiseOrSet = "MOON SET";
                else
                    displayTxtMoonRiseOrSet = "MOON RISE";
                txtCountDownTimer.setText("GO SEE THE " + displayTxtMoonRiseOrSet + " IN THE SKY!");
            }
        }.start();

        Button btn = (Button) findViewById(R.id.btnNextLayout);

        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FindTheMoon.this, WhereIsTheMoon.class);
                FindTheMoon.this.startActivity(myIntent);
            }
        });
    }


}