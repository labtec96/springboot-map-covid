package com.example.springbootmapinit.util;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    final DateFormat dateFormat = new SimpleDateFormat( "M/dd/YY");
    @Test
    void getDatOfYesterday() {
        Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
        assertEquals(dateFormat.format(yesterday),Utils.getDatOfYesterday());
    }
}