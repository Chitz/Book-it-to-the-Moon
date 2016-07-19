package com.me.bookittothemoon;


import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ctewani on 4/24/16.
 */
public class FetchCountDown {

    public Long[] calculateCountDown(Context context) throws ParseException {

        final Context contextObj = context;
        Long countDown;
        Long isYetToMoonRise = Long.valueOf(0);
        Utils utils = new Utils();
        String moonRiseISO = utils.getStringFromPreferences(contextObj, null, "moonRiseISO", "app_prefs");

        System.out.println("moonRiseISO " + moonRiseISO);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        calendar.setTime(sdf.parse(moonRiseISO));
        // System.out.println("" + calendar.getTimeInMillis());

        Long end = calendar.getTimeInMillis();
        Long start = System.currentTimeMillis();
        System.out.println("Difference in Time " + (end - start));
        //Long start = calendar.getTimeInMillis();

        // if already over moonRise, then fix to moonSet
        if((end - start) > 0){
            countDown = (end - start);
        }else{
            String moonSetISO = utils.getStringFromPreferences(contextObj, null, "moonSetISO", "app_prefs");
            System.out.println("moonSetISO " + moonSetISO);
            isYetToMoonRise.valueOf(1);
            calendar.setTime(sdf.parse(moonSetISO));
            end = calendar.getTimeInMillis();
            countDown = end - start;
        }

        System.out.println("Just before "+countDown);
        Long []ret = {isYetToMoonRise, countDown};
        return ret;
    }

}
