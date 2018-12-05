package com.test.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Demo {

    public static void main(String[] args) throws JsonProcessingException, ParseException, IllegalAccessException, InstantiationException {



//        System.out.println(LocalDateTime.now());
//        System.out.println(LocalDateTime.now());
//
//        System.out.println(Instant.now());
//
//        System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)));
//        System.out.println(ZonedDateTime.of(LocalDateTime.of(2014, 1, 20, 3, 30, 20), ZoneId.of("+08")));



//        String date = "2018-05-31T19:40:00+0800";
//        String dateStr = dealDateFormat(date);
//        System.out.println(dateStr);
//        System.out.println(getUtcOrDate(dateStr));
//        System.out.println(longToDate(getUtcOrDate(dateStr)));
//        String[] a = dateStr.split(" ");
//        System.out.println(a[1]);
//
//        String jsonStr = " [{\"dancing\": \"true\"},{\"badminton\": \"true\"}]"; //json字符串
//        Object json = null;
//        try {
//            json = new JSONTokener(jsonStr).nextValue();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if(json instanceof JSONObject){
//            JSONObject jsonObject = (JSONObject)json;
//            System.out.println("object");
//            //further actions on jsonObjects
//            //...
//        }else if (json instanceof JSONArray){
//            JSONArray jsonArray = (JSONArray)json;
//            System.out.println("array");
//        }


//
////        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
//        Optional<Integer> in = Optional.empty();
//        System.out.println(in.isPresent());


//        System.out.println(LocalDate.parse("2018-02-28",DateTimeFormatter.ISO_LOCAL_DATE));

        User user = new User();
        user.setName("zhangsan");
        user.setEmail("zhangsan@163.com");
        user.setAge(20);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDate = LocalDateTime.now().minusYears(30L);
        ZonedDateTime zdt = localDate.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        user.setBirthday(date);
//
//        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//        user.setBirthday(dateformat.parse("1996-10-01"));
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(user);
//        List<User> userList1 = new ArrayList<>();
//        userList1.add(user);
//
//        String json1 = mapper.writeValueAsString(userList1);
//        System.out.println(json1);
//
//
//
//
//        User a = new User();
//        List<User> userList = new ArrayList<>();
//         try {
//             userList = mapper.readValue(json1,List.class);
//            System.out.println(userList);
//        } catch (IOException e) {
//            System.out.println("faild");
//        }
//        System.out.println(json);

        ObjectMapper objectMapper  = new ObjectMapper();
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"data\": [],\n" +
                "    \"msg\": null,\n" +
                "    \"errorMsg\": null\n" +
                "}";

        try {
            BaseVo baseVo = objectMapper.readValue(json,BaseVo.class);
            System.out.println(baseVo);

            Class clazz = baseVo.getClass();
            BaseVo o =  (BaseVo)clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                field.setAccessible(true);
                if (key.equals("code")){
                    field.set(o,1);
                }
                if (key.equals("msg")){
                    field.set(o,"ok");
                }
                if (key.equals("errorMsg")){
                    field.set(o,"");
                }
            }
            System.out.println(o);

        } catch (IOException e) {
            e.printStackTrace();
        }




        BaseVo baseVo = BaseVo.builder()
                .code(1)
                .msg("成功")
                .errorMsg("空")
                .data(Collections.singletonList(user))
                .build();
        System.out.println(baseVo);

        String baseVoJson = objectMapper.writeValueAsString(baseVo);
        System.out.println(baseVoJson);
        //System.out.println(baseVoJson.hashCode());


        String uuid = UUID.randomUUID().toString().replace("-","").toUpperCase();
        System.out.println(uuid);






    }






    @Builder(toBuilder = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class BaseVo<T> {

        private Integer code;
        private List<T> data;
        private String msg;
        private String errorMsg;




    }



    public static String dealDateFormat(String oldDateStr) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
//        Date date = null;
//        try {
//            date = df.parse(oldDateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        return df2.format(date);


        LocalDateTime localDateTime = LocalDateTime.parse(oldDateStr,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX"));
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }


    public static Long getUtcOrDate(String beginTime) {

//        Long beginTimeLong = 0L;
//        try
//        {
//            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
//            Date date=sd.parse(beginTime);
//            beginTimeLong=date.getTime();
//        }
//        catch(Exception e)
//        {
//            return beginTimeLong;
//        }
//        return beginTimeLong;



        LocalDateTime localDateTime = LocalDateTime.parse(beginTime,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(8));
        return instant.toEpochMilli();
    }

    public static String longToDate(Long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,ZoneId.of("+8"));
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }




    @Data
    public static class User {

        private String name;
        private Integer age;
        private Date birthday;
        private String email;

    }


}
