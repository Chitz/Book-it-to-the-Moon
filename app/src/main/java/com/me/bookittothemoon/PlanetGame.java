package com.me.bookittothemoon;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class PlanetGame extends AppCompatActivity {

    String toSpeak;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_game);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }

            }
        });

    }

    // Text to speech
    private void SpeakText(View view) {
        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    //create listener
    public void planetListener(View view) {
        switch (view.getId()) {
            case R.id.btnSun:
                toSpeak = "Sun";
                break;
            case R.id.btnMercury:
                toSpeak = "Mercury";
                break;
            case R.id.btnVenus:
                toSpeak = "Venus";
                break;
            case R.id.btnEarth:
                toSpeak = "Earth";
                break;
            case R.id.btnMars:
                toSpeak = "Mars";
                break;
            case R.id.btnJupiter:
                toSpeak = "Jupiter";
                break;
            case R.id.btnSaturn:
                toSpeak = "Saturn";
                break;
            case R.id.btnUranus:
                toSpeak = "Uranus";
                break;
            case R.id.btnNeptune:
                toSpeak = "Neptune";
                break;
        }
        SpeakText(view);
    }
}
