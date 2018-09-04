package com.test.demo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.enums.WebCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnowflakeIdWorker {


// ==============================Fields===========================================
    /** 开始时间截 (2015-01-01) */
    private final long twepoch = 1420041600000L;

    /** 机器id所占的位数 */
    private final long workerIdBits = 5L;

    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 5L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识id，结果是31 */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在id中占的位数 */
    private final long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~31) */
    private long workerId;

    /** 数据中心ID(0~31) */
    private long datacenterId;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    public static synchronized String generateShareParamKey() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }
    //==============================Test=============================================
    /** 测试 */



    private  static  Boolean matchEngAndCn(String content){
        String regex="^[\u4E00-\u9FA5]{1,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match=pattern.matcher(content);
        if(match.matches()){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        String tag = "阿斯顿发\n";
        tag = tag.trim();
        //tag = StringUtils.trimAllWhitespace(tag);
        if(matchEngAndCn(tag)){
            System.out.println("匹配成功");
        }

        //System.out.println("true");
//        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//        Set<String> a = new HashSet<>();
//        String v;
//        for (int i = 0; i < 10000000; i++) {
//            v = generateShareParamKey();
//            a.add(v);
//            System.out.println(v);
//            System.out.println(a.size());
//        }
        //System.out.println(a.size());
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
