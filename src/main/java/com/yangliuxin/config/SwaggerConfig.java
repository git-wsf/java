package com.yangliuxin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yangliuxin.controller.wechat"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("VIVO活动接口文档")
                .description("数据导入签名规则：\n" +
                        "\n" +
                        "请求进行md5签名，确保传输的安全可靠。参数名称sign，置于请求的header中\n" +
                        "\n" +
                        "签名原理：\n" +
                        "\n" +
                        "md5签名的原理如下：将所有的非空参数的key+value按参数key升序进行排列后得到原始sourceStr。\n" +
                        "\n" +
                        "sourceParam+secret子串得到destinationStr后转大写destinationStrUpper。\n" +
                        "\n" +
                        "Md5(destinationStrUpper)\n" +
                        "appSecret在签名中的顺序永远放置最后。\n" +
                        "\n" +
                        "注意：签名验证时，必须遍历request请求中的所有未空参数，进行签名验证。\n" +
                        "\n" +
                        "Secret Key：VIVO_WEDGE2_CN_%^*@#!_2018_&*7876")
                .version("1.0")
                .build();
    }
}
