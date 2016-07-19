package com.me.bookittothemoon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    protected String userName;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button done = (Button) findViewById(R.id.finish);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
       done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, ActivitySelection.class));
            }
        });
        EditText user = (EditText) findViewById(R.id.txtInputName);
        userName = user.getText().toString();
    }

    public void ageListener(View view) {
        int ageGroup;
        switch (view.getId()) {
            case R.id.radAgeGroupOne:
                ageGroup = 1;
                break;
            case R.id.radAgeGroupTwo:
                ageGroup = 2;
                break;
            case R.id.radAgeGroupThree:
                ageGroup = 3;
                break;
            default:
                ageGroup = 0;
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userName", userName);
        editor.putInt("ageGroup", ageGroup);
        editor.commit();
    }
}
