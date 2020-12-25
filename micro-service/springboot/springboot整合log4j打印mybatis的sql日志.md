---
title: springboot整合log4j打印mybatis的sql日志
tags: []
date: 2020-12-24 11:27:51
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### pom文件中假如依赖

```java
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-log4j</artifactId>
      <version>1.3.8.RELEASE</version>
</dependency>
```


#### 在application.yml中配置mybatis输出日志：
yml
```java
mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

property
```java
#mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
mybatis.configuration.log-impl=org.apache.ibatis.logging.log4j.Log4jImpl
```
上面2个StdOutImpl和Log4jImpl选其中一个就可以


#### 配置扫描的包路径，不配置打印不了
```java
logging.level.com.fzs.iotcard.common.business.mapper=DEBUG

```


参考：
[SpringBoot+MyBatis如何配置log4j日志输出（sql）](https://blog.csdn.net/qq_15006743/article/details/82464914)