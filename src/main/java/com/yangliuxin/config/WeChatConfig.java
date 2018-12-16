package com.yangliuxin.config;

import com.yangliuxin.property.WeChatAccountProperty;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeChatConfig {

    @Autowired
    private WeChatAccountProperty weChatAccountProperty;

    @Bean
    public WxMpService wxMpService(){
        //创建WxMpService实例并设置appid和sectret
        WxMpService wxMpService = new WxMpServiceImpl();
        //这里的设置方式是跟着这个sdk的文档写的
        wxMpService.setWxMpConfigStorage(wxConfigProvider());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxConfigProvider(){
        WxMpInMemoryConfigStorage wxConfigProvider = new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId(weChatAccountProperty.getAppId());
        wxConfigProvider.setSecret(weChatAccountProperty.getSecret());
        return wxConfigProvider;
    }

}
