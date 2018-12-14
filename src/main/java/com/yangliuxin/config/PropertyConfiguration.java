package com.yangliuxin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
public class PropertyConfiguration {

    private TokenProperty token;

    private ValidateProperty validate;

    @Data
    public static class ValidateProperty {

        private ValidateCodeProperty code;

        private String product;

        private String domain;

        private String accessKeyId;

        private String accessKeySecret;

        private String iosAppVersion;

        private String androidAppVersion;

    }
    @Data
    public static class ValidateCodeProperty {

        private Integer count;

        private  Integer expireIn;

    }

    @Data
    public static class TokenProperty {

        private TokenExpire expire;

        private  String jwtSecret;

    }
    @Data
    public static class TokenExpire {

        private Integer seconds;
    }

}
