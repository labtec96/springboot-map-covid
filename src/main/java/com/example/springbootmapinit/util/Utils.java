package com.example.springbootmapinit.util;

import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ch on 2020-04-20
 */
@UtilityClass
public class Utils {
    final DateFormat dateFormat = new SimpleDateFormat( "M/dd/YY");

    public String getDatOfYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime()).toString();
    }
}
