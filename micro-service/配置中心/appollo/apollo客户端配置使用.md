---
title: apollo客户端配置使用.md
tags: [apollo,配置中心]
date: 2020-09-08 09:40:52
categories: 配置中心
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 1、pom添加依赖

```
        <dependency>
            <groupId>com.ctrip.framework.apollo</groupId>
            <artifactId>apollo-client</artifactId>
            <version>1.4.0</version>
        </dependency>
```



#### 2、启动类添加@EnableApolloConfig

```
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableSwagger2
@EnableApolloConfig
public class FzsIotCardApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FzsIotCardApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
```

#### 3、application.properties增加配置

```

# 方式1 springboot的配置properties或yml文件添加下面的
app.id=fzs_iot_card2
# 方式2：环境变量里加-Dapp.id=YOUR-APP-ID
# 方式3：classpath:/META-INF/app.properties文件存在 内容：app.id=YOUR-APP-ID

apollo.meta=http://config-service-url:port

##下面这个不配也可以的
#env=DEV
```

#### 4.使用
新建controller，里面通过${}引用，例如${swagger.context}


```
@Slf4j
@Api(value = "API", description = "API")
@RestController
@RequestMapping("/tIotCard")
public class TIotCardController {
    @Value("${swagger.context}")
    private String swaggerContext;
    @Value("${test.testabc}")
    private String testabc;


    @GetMapping(value = "/get")
    @ApiOperation(value = "获取", notes = "")
    public String get() {

        return swaggerContext+testabc;
    }

}

```

使用参考：
[https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97#3213-spring-boot%E9%9B%86%E6%88%90%E6%96%B9%E5%BC%8F%E6%8E%A8%E8%8D%90](https://github.com/ctripcorp/apollo/wiki/Java%E5%AE%A2%E6%88%B7%E7%AB%AF%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97#3213-spring-boot%E9%9B%86%E6%88%90%E6%96%B9%E5%BC%8F%E6%8E%A8%E8%8D%90)


```

```
