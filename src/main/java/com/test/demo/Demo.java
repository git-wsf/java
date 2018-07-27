package com.test.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Optional;

public class Demo {

    public static void main(String[] args) throws ParseException, JSONException {


        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(LocalDateTime.now());
        Instant instant = Instant.now();
        System.out.println(instant);

        System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)));
        System.out.println(ZonedDateTime.of(LocalDateTime.of(2014, 1, 20, 3, 30, 20), ZoneId.of("+08")));



        String date = "2018-05-31T19:40:00+0800";
        String dateStr = dealDateFormat(date);
        String[] a = dateStr.split(" ");
        System.out.println(a[1]);

        String jsonStr = " [{\"dancing\": \"true\"},{\"badminton\": \"true\"}]"; //json字符串
        Object json = new JSONTokener(jsonStr).nextValue();
        if(json instanceof JSONObject){
            JSONObject jsonObject = (JSONObject)json;
            System.out.println("object");
            //further actions on jsonObjects
            //...
        }else if (json instanceof JSONArray){
            JSONArray jsonArray = (JSONArray)json;
            System.out.println("array");
        }

        Optional<Integer> in = Optional.empty();
        System.out.println(in.isPresent());
    }





    public static String dealDateFormat(String oldDateStr) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
        Date date = df.parse(oldDateStr);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df2.format(date);
    }


    public static Long getUtcOrDate(String beginTime) {

        Long beginTimeLong = 0L;
        try
        {
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            Date date=sd.parse(beginTime);
            beginTimeLong=date.getTime();
        }
        catch(Exception e)
        {
            return beginTimeLong;
        }
        return beginTimeLong;
    }


}
