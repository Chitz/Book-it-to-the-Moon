package com.me.bookittothemoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import android.content.Context;

/**
 * Created by ctewani on 4/24/16.
 */

public class FetchMoonData {

    public String getMoonPhase(Context context){
        final Context contextObj = context;
        Utils utils = new Utils();
        String moonPhaseName = utils.getStringFromPreferences(contextObj, null, "moonPhaseName", "app_prefs");
        return moonPhaseName;
    }

    public void getDetails(Context context, String latitude, String longitude) throws IOException, JSONException {
        String baseURL = "http://api.aerisapi.com/sunmoon/";

        final Context contextObj = context;
		/* API ID and Secret of Aries Weather*/
        String client_ID = "wROYgu59EnhSVfpFbRI1S";
        String client_secret = "lr4GAXTbUEqozMgWYEPDcmBTL0FbZvZqMRaJO8Qp";
        String queryString = baseURL + latitude + "," + longitude + "?"
                + "client_id=" + client_ID + "&client_secret=" + client_secret;

        try {
            URL url = new URL(queryString);
            final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // Buffer the result into a string

            urlConn.setReadTimeout(10000 /* milliseconds */);
            urlConn.setConnectTimeout(15000 /* milliseconds */);
            urlConn.setRequestMethod("GET");
            urlConn.setDoInput(true);

            // Starts the query
            new Thread(){
                public void run() {
                    try {
                        urlConn.connect();
                        //int response = urlConn.getResponseCode();

                        BufferedReader rd = new BufferedReader(
                                new InputStreamReader(urlConn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            sb.append(line);
                        }
                        rd.close();
                        urlConn.disconnect();
                        String jsonString = sb.toString();
                        JSONObject object = (JSONObject) new JSONObject(jsonString);
                        JSONArray response = object.getJSONArray("response");
                        System.out.println(response.toString());
                        JSONObject responseJS = response.getJSONObject(0);
                        // String moonRiseISO_Get = utils.getStringFromPreferences(contextObj, null, "moonRiseISO", "app_prefs");

                        // System.out.println("moonRiseISO_Get " + moonRiseISO_Get);

                        JSONObject moon = responseJS.optJSONObject("moon");
                        String moonRiseISO = moon.getString("riseISO");
                        String moonSetISO = moon.getString("setISO");

                        System.out.println("moonSetISO_First " + moonSetISO);
                        JSONObject moonPhase = moon.optJSONObject("phase");
                        String moonPhaseName = moonPhase.getString("name");
                        String moonPhasePhase = moonPhase.getString("phase");
                        String moonPhaseIllum = moonPhase.getString("illum");
                        String moonPhaseAge = moonPhase.getString("age");
                        String moonPhaseAngle = moonPhase.getString("angle");


                         /* Store in Shared Preferences */
                        Utils utils = new Utils();
                        utils.putStringInPreferences(contextObj,moonRiseISO, "moonRiseISO", "app_prefs");
                        utils.putStringInPreferences(contextObj,moonSetISO, "moonSetISO", "app_prefs");
                        utils.putStringInPreferences(contextObj,moonPhaseName, "moonPhaseName", "app_prefs");
                        utils.putStringInPreferences(contextObj,moonPhaseIllum, "moonPhaseIllum", "app_prefs");
                        utils.putStringInPreferences(contextObj,moonPhasePhase, "moonPhasePhase", "app_prefs");
                        utils.putStringInPreferences(contextObj,moonPhaseAge, "moonPhaseAge", "app_prefs");
                        utils.putStringInPreferences(contextObj,moonPhaseAngle, "moonPhaseAngle", "app_prefs");



                        } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }}.start();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
