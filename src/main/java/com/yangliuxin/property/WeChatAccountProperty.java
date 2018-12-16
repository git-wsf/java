package com.yangliuxin.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "weChat")
@Data
@Component
public class WeChatAccountProperty {

    private String appId;

    private String secret;

    private String token;

    private Integer expire;

    private String siteUrl;


}
