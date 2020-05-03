---
title: 更改swagger上下文
date: 2019-05-20 16:35:37
tags: [swagger,api文档]
categories: swagger
---
** {{ title }} ** <Excerpt in index | 首页摘要>
由于应用配置的请求上下文地址和swagger里的地址有可能不同，比如线上的是/business/tobe/country/getList
而本地的则是/country/getList,所以有必要配置swagger的上下文地址

<!-- more -->

### pom.xml
```
    <properties>
        <swagger.version>2.7.0</swagger.version>
    </properties>
    <dependencies>   
            <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${swagger.version}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${swagger.version}</version>
        </dependency>
    </dependencies>
    
```

### properties

```
swagger.context=/
```


### SwaggerConfig


```
package com.tobe.spcommon.common.config;

import com.tobe.spcommon.common.bean.AppcliationSwaggerPathProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String BASE_PACKAGE_PREFFIX = "com.tobe.spcommon.controller";
    @Resource
    AppcliationSwaggerPathProvider appcliationSwaggerPathProvider;

    private ApiInfo apiInfo() {
        // TODO: 修改下面描叙
        return new ApiInfoBuilder().title("侵权项目").description("侵权项目")
                .termsOfServiceUrl("")
                .contact(new Contact("liangqingxiang", "", "liangqingxiang@tobe.com")).version("1.0.0").build();
    }

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Auth").description("侵权接口授权码")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数


        return getDocketInstance("0、全部接口", BASE_PACKAGE_PREFFIX)
                .pathProvider(appcliationSwaggerPathProvider)
                ;//.globalOperationParameters(pars);
    }

	/*@Bean
    public Docket groupRestApi1() {
		return getDocketInstance("1、订单模块", BASE_PACKAGE_PREFFIX + ".order");
	}


	}*/

    private Docket getDocketInstance(String groupName, String basePackage) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName(groupName).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage)).paths(PathSelectors.any())
                .build();
    }


}
```

### AppcliationSwaggerPathProvider


```
package com.tobe.spcommon.common.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.paths.AbstractPathProvider;

/*
https://www.jianshu.com/p/a62aa157942c
 */
@Component
public class AppcliationSwaggerPathProvider extends AbstractPathProvider {

    @Value("${swagger.context}")
    private String swaggerContext;

    public AppcliationSwaggerPathProvider() {
    }

    @Override
    protected String applicationPath() {
        return swaggerContext;//"/business/tobe";
    }

    @Override
    protected String getDocumentationPath() {
        return swaggerContext;//"/business/tobe";
    }


}

```
