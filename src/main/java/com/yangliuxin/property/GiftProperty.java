package com.yangliuxin.property;


import com.yangliuxin.vo.Gift;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("weChat.gift")
@Data
public class GiftProperty {

    private List<Gift> giftList;

}

