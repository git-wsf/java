package com.test.demo;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class MyThreadLocal {

//    public static void main(String[] args) throws JsonProcessingException, ParseException, IllegalAccessException, InstantiationException {



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

//        User user = new User();
//        user.setName("zhangsan");
//        user.setEmail("zhangsan@163.com");
//        user.setAge(20);
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDateTime localDate = LocalDateTime.now().minusYears(30L);
//        ZonedDateTime zdt = localDate.atZone(zoneId);
//        Date date = Date.from(zdt.toInstant());
//        user.setBirthday(date);
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

//        ObjectMapper objectMapper  = new ObjectMapper();
//        String json = "{\n" +
//                "    \"code\": 0,\n" +
//                "    \"data\": [],\n" +
//                "    \"msg\": null,\n" +
//                "    \"errorMsg\": null\n" +
//                "}";

//        try {
//            BaseVo object = objectMapper.readValue(json,BaseVo.class);
//            System.out.println(object);
//
//            Class clazz = object.getClass();
//            Object o = (BaseVo) clazz.newInstance();
//            Field[] fields = clazz.getDeclaredFields();
//            for (Field field : fields) {
//                String key = field.getName();
//                field.setAccessible(true);
//                if (key.equals("code")){
//                    field.set(o,1);
//                }
//            }
//            System.out.println(o);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }




//        BaseVo baseVo = BaseVo.builder()
//                .code(1)
//                .msg("成功")
//                .errorMsg("空")
//                .data(Collections.singletonList(user))
//                .build();
//        System.out.println(baseVo);
//
//        String baseVoJson = objectMapper.writeValueAsString(baseVo);
//        System.out.println(baseVoJson);
//        //System.out.println(baseVoJson.hashCode());
//
//
//        String uuid = UUID.randomUUID().toString().replace("-","").toUpperCase();
//        System.out.println(uuid);


//        System.out.println(Demo.class);
////
////        //以 String 的形式返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称。
////        System.out.println(Demo.class.getName());
////
////        // 返回源代码中给出的底层类的简称。
////        System.out.println(Demo.class.getSimpleName());
////
////        //返回该类的类加载器。
////        System.out.println(Demo.class.getClassLoader());
////
////        //返回本类的类加载器的字节码对象
////        System.out.println(Demo.class.getClassLoader().getClass());
////
////        //返回本类的类加载器的字节码对象的名称
////        System.out.println(Demo.class.getClassLoader().getClass().getName());
////
////        //返回系统类的加载器--没有
////        System.out.println(System.class.getClassLoader());
//        String str = "";
//
//        str.split(" ");
//
//        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world",
//                "hello world welcome");
//
//        //map和flatmap的区别
//        list.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);
//        System.out.println("---------- ");
//        list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);
//
//        //实际上返回的类似是不同的
//        List<Stream<String>> listResult = list.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList());
//        List<String> listResult2 = list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().collect(Collectors.toList());
//
//        System.out.println("---------- ");
//
//        //也可以这样
//        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);
//
//        System.out.println("================================================");
//
//        /**相互组合**/
//        List<String> list2 = Arrays.asList("hello", "hi", "你好");
//        List<String> list3 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");
//
//        list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);
//        list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList()).forEach(System.out::println);
//
//        //实际上返回的类似是不同的
//        List<Stream<String>> list2Result = list2.stream().map(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
//        List<String> list2Result2 = list2.stream().flatMap(item -> list3.stream().map(item2 -> item + " " + item2)).collect(Collectors.toList());
        //LocalDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

//        Long tp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        System.out.println(tp);
//
//
//        System.out.println(Instant.now().getEpochSecond());
//
//
//        Instant instant = Instant.ofEpochMilli(tp);
//
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,ZoneOffset.of("+8"));
//        System.out.println(localDateTime);











//    }



    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue()
        {
            System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return null;
        }
    };

    public static void main(String[] args)
    {
        new Thread(new MyIntegerTask("IntegerTask1")).start();
        new Thread(new MyStringTask("StringTask1")).start();
        new Thread(new MyIntegerTask("IntegerTask2")).start();
        new Thread(new MyStringTask("StringTask2")).start();
    }

    public static class MyIntegerTask implements Runnable
    {
        private String name;

        MyIntegerTask(String name)
        {
            this.name = name;
        }

        @Override
        public void run()
        {
            for(int i = 0; i < 5; i++)
            {
                // ThreadLocal.get方法获取线程变量
                if(null == MyThreadLocal.threadLocal.get())
                {
                    // ThreadLocal.et方法设置线程变量
                    MyThreadLocal.threadLocal.set(0);
                    System.out.println("线程" + name + ": 0");
                }
                else
                {
                    int num = (Integer)MyThreadLocal.threadLocal.get();
                    MyThreadLocal.threadLocal.set(num + 1);
                    System.out.println("线程" + name + ": " + MyThreadLocal.threadLocal.get());
                    if(i == 3)
                    {
                        MyThreadLocal.threadLocal.remove();
                    }
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    public static class MyStringTask implements Runnable
    {
        private String name;

        MyStringTask(String name)
        {
            this.name = name;
        }

        @Override
        public void run()
        {
            for(int i = 0; i < 5; i++)
            {
                if(null == MyThreadLocal.threadLocal.get())
                {
                    MyThreadLocal.threadLocal.set("a");
                    System.out.println("线程" + name + ": a");
                }
                else
                {
                    String str = (String)MyThreadLocal.threadLocal.get();
                    MyThreadLocal.threadLocal.set(str + "a");
                    System.out.println("线程" + name + ": " + MyThreadLocal.threadLocal.get());
                }
                try
                {
                    Thread.sleep(800);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }


    @Builder(toBuilder = true)
    @Data
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
