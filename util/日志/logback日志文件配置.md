---
title: logback日志文件配置
tags: [logback,日志]
date: 2019-07-30 15:19:51
categories: 日志
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->



```
<logger name="com.basic" level="DEBUG"/>
```

level设置为DEBUG,则下面2个日志都打印出来

```
log.debug("这个是debug日志");
log.info("这个是info日志");
```

若设置为info，则只打印
```
log.info("这个是info日志");
```

若想设置不同环境不同级别，则利用springProfile,如下：
```
    <springProfile name="local,dev,staging">
        <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
        <logger name="com.basic" level="debug"/>
    </springProfile>
    <springProfile name="prod">
        <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
        <logger name="com.basic" level="info"/>
    </springProfile>


```

logback.xml demo
```
<configuration>
    <!--输出到控制台 STDOUT-->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d %t %p %C{3000}:%L [%t] %m%n%n</pattern>
        </encoder>
    </appender>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>/logs/basic-server/basic-server.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>/logs/basic-server/%d{YYYYMMdd}/basic-server.%i.log.zip</fileNamePattern>
            <maxFileSize>20MB</maxFileSize>
            <maxHistory>300</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d %p %C{30}:%L [%t] %m%n%n</pattern>
        </encoder>
    </appender>
    <appender name="INTERFACE_LOG" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>/logs/basic-server/basic-server_interface.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>/logs/basic-server/%d{YYYYMMdd}/basic-server_interface.%i.log.zip</fileNamePattern>
            <maxFileSize>20MB</maxFileSize>
            <maxHistory>300</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d %m%n</pattern>
        </encoder>
    </appender>
    <appender name="ASYNC_FILE" class="ch.qos.logback.classic.AsyncAppender">
        <!-- 不丢失日志.默认的,如果队列的80%已满,则会丢弃TRACT、DEBUG、INFO级别的日志 -->
        <discardingThreshold>0</discardingThreshold>
        <!-- 更改默认的队列的深度,该值会影响性能.默认值为256 -->
        <queueSize>512</queueSize>
        <includeCallerData>true</includeCallerData>
        <appender-ref ref="FILE" />
    </appender>
    <appender name="ASYNC_INTERFACE_LOG" class="ch.qos.logback.classic.AsyncAppender">
        <!-- 不丢失日志.默认的,如果队列的80%已满,则会丢弃TRACT、DEBUG、INFO级别的日志 -->
        <discardingThreshold>0</discardingThreshold>
        <!-- 更改默认的队列的深度,该值会影响性能.默认值为256 -->
        <queueSize>512</queueSize>
        <includeCallerData>true</includeCallerData>
        <appender-ref ref="INTERFACE_LOG" />
    </appender>


    <logger name="com.alibaba" level="INFO"/>
    <logger name="springfox" level="ERROR"/>
    <logger name="org.springframework" level="ERROR"/>
    <logger name="org.mybatis.spring.mapper" level="ERROR"/>
    <logger name="org.postgresql.jdbc" level="ERROR"/>
    <logger name="com.xxl.job.core" level="ERROR"/>
    <logger name="org.apache.http.impl.conn" level="ERROR"/>
    <logger name="org.pentaho" level="DEBUG"/>

    <variable name="basic_log_level" value="${logback.sql.level}" />


    <logger name="INTERFACE_LOG" level="info" additivity="false">
        <appender-ref ref="ASYNC_INTERFACE_LOG"/>
    </logger>

    <springProfile name="local,dev,staging">
        <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
        <logger name="com.basic" level="debug"/>
    </springProfile>
    <springProfile name="prod">
        <!-- configuration to be enabled when the "dev" or "staging" profiles are active -->
        <logger name="com.basic" level="info"/>
    </springProfile>

    <root level="info">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="ASYNC_FILE"/>　
    </root>
</configuration>
```

参考：https://blog.csdn.net/qq_27886997/article/details/83178948

阅读：
[springboot日志变量配置](https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/htmlsingle/#_environment_properties)
