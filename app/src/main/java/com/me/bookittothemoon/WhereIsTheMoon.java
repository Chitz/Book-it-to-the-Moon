package com.me.bookittothemoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WhereIsTheMoon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_is_the_moon);
        WebView fullScreenMoonFromEarth = (WebView) findViewById(R.id.fullScreenMoonFromEarth);
        fullScreenMoonFromEarth.loadUrl("http://www.timeanddate.com/scripts/sunmap.php?obj=moon&iso=20160424T0510&month=4&day=24&year=2016&hour=10&min=40&sec=0&n=%3A&ntxt=Indianapolis&earth=0");
    }

}
