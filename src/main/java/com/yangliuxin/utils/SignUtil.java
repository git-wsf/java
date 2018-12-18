package com.yangliuxin.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.SortedMap;

@Slf4j
public class SignUtil {



    public static String getSign(SortedMap<String, String> params,String secret) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : params.entrySet()) {
            if (!entry.getKey().equals("sign")) {
                if (!StringUtils.isEmpty(entry.getKey()) && !StringUtils.isEmpty(entry.getValue()))
                    sb.append(entry.getKey()).append(entry.getValue());
            }
        }
        sb.append(secret);
        String result = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        log.info("Before Sign : {}", sb.toString());
        log.info("After Sign : {}", result);
        return result;
    }


    public static boolean verifySign(SortedMap<String, String> params,String secret) {
        if (params == null || StringUtils.isEmpty(params.get("sign"))) return false;
        String sign = getSign(params,secret);
        log.info("verify Sign : {}", sign);
        return !StringUtils.isEmpty(sign) && params.get("sign").equals(sign);
    }

}