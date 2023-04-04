---
title: springboot启动问题
tags: []
date: 2022-12-14 19:11:33
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### Error:(3, 32) java: 无法访问org.springframework.boot.SpringApplication
springboot 3.0.0版本，jdk8
可能2者版本不兼容，降低springboot版本即可
[https://blog.csdn.net/Lcynsyw/article/details/128123459](https://blog.csdn.net/Lcynsyw/article/details/128123459)




#### 
```java

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2022-12-14 19:07:46.918 ERROR 21432 --- [  restartedMain] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).


Process finished with exit code 0
```

要像下面排除数据源自动装配的才行
```java
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

```
应该是引入了下面自动装配的jar

```java
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

[https://blog.csdn.net/m0_51660523/article/details/117563226](https://blog.csdn.net/m0_51660523/article/details/117563226)

[https://blog.csdn.net/qq_40223688/article/details/88191732](https://blog.csdn.net/qq_40223688/article/details/88191732)


#### 


```java

```



```




1. 
2. 
3. 
![]()