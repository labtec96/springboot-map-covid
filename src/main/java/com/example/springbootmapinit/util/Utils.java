package com.example.springbootmapinit.util;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ch on 2020-04-20
 */
@UtilityClass
public class Utils {
    private final DateFormat dateFormat = new SimpleDateFormat( "M/dd/YY");

    String getDatOfYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime()).toString();
    }

    public BufferedReader getBufferReaderFromUrl(String url) throws IOException {
        URL data = new URL(url); // URL to Parse
        URLConnection yc = data.openConnection();
        yc.addRequestProperty("User-Agent", "Mozilla");
        yc.setReadTimeout(5000);
        yc.setConnectTimeout(5000);
        return new BufferedReader(new InputStreamReader(yc.getInputStream()));
    }
}
