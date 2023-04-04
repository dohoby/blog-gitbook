---
title: 动态创建bean
tags: []
date: 2022-12-05 14:18:36
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 

```java
        GenericBeanDefinition hiveConfigBean = new GenericBeanDefinition();
        hiveConfigBean.setBeanClass(LiveConfig.class);
        registry.registerBeanDefinition("liveConfig", hiveConfigBean);

```

```java
        GenericBeanDefinition deviceBean = new GenericBeanDefinition();
        deviceBean.setBeanClass(DeviceLiveServiceImpl.class);

        OkHttpClient okHttpClient = new OkHttpClient();
        HttpFactory httpFactory = new OkHttpHttpFactory(okHttpClient);
        ConstructorArgumentValues constructorArgumentValues=  new ConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0,hiveConfigBean);
        constructorArgumentValues.addIndexedArgumentValue(1,httpFactory);
        deviceBean.setConstructorArgumentValues(constructorArgumentValues);
        registry.registerBeanDefinition("deviceLiveService", deviceBean);
```

[5种方式获取ApplicationContext](https://www.cnblogs.com/nizuimeiabc1/p/16612705.html)

#### 
```java
Properties properties = new Properties();
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("/conf/live.properties");
        } catch (IOException e) {
            log.error("加载live配置文件出错");
            e.printStackTrace();
        }
```

```java

```
[SpringBoot基础篇Bean之动态注册](https://blog.csdn.net/liuyueyi25/article/details/83244255)
[项目源码](https://github.com/liuyueyi/spring-boot-demo/tree/master/spring-boot/006-dynamicbean)


[SpringBoot根据配置文件动态创建Bean](https://blog.csdn.net/Yh360311/article/details/126941798)

[springboot如何读取自定义properties并注入到bean中](https://www.jb51.net/article/230611.htm)

[](https://www.jb51.net/article/197130.htm)
[]()
[]()


#### 

自动装配如果没有生效，会不会是没有引入下面包导致的？

```java
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-configuration-processor</artifactId>
      <optional>true</optional>
    </dependency>
```
https://www.jianshu.com/p/57f06f92fbb2


[springboot项目将自定义的properties配置文件注入到实体Bean中](https://blog.csdn.net/zhangruibo_code/article/details/107319250)

```java
@Data
@Configuration
@PropertySource("classpath:/mail.properties")
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
	private String host;
	private Integer port;
	private Smtp smtp;
	private String from;
	private String username;
	private String password;
}

@RestController
public class TestController {

	@Autowired
	private MailProperties mailProperties;

	@RequestMapping("/test")
	public String test() {
		return mailProperties.toString();
	}
}


```

[springboot如何读取自定义properties并注入到bean中](https://www.jb51.net/article/230611.htm)

```java
package com.shanqis.parking.properties; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
 
/**
 * 读取resource下的.properties文件，将文件中的内容封装到map中，注入到bean中方便依赖注入
 *
 * @author Administrator
 */
@Configuration
public class PropertiesClassLoader { 
    private Logger logger = LoggerFactory.getLogger(PropertiesClassLoader.class); 
    private Map<String, Object> versionProperties = new HashMap<>(16); 
    private void init(String name) { 
        try {
            Properties properties = new Properties(); 
            InputStream in = PropertiesClassLoader.class.getClassLoader().getResourceAsStream(name + ".properties"); 
            properties.load(in); 
            logger.info("加载{}.properties参数", name); 
            for (String keyName : properties.stringPropertyNames()) {
                String value = properties.getProperty(keyName); 
                if ("version".equals(name)) {
                    versionProperties.put(keyName, value);
                } 
                logger.info("{}.properties---------key:{},value:{}", name, keyName, value);
            }
            logger.info("{}.properties参数加载完毕", name);
        } catch (IOException ignored) { 
        } 
    }
 
    @Bean(name = "versionProperties")
    public Map<String, Object> commonMap() {
        init("version");
        return versionProperties;
    }
}
```

@PropertySource如何加载配置文件

[Spring注解驱动开发第19讲——使用@PropertySource加载配置文件，我只看这一篇！！](https://liayun.blog.csdn.net/article/details/110485086)

```java

```



1. 
2. 
3. 
![]()