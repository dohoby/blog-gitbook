---
title: log4j日志配置例子
tags: []
date: 2021-05-13 11:48:19
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 

```java
#log4j.rootLogger=debug,INFO, CONSOLE, FILE
log4j.rootLogger=info, CONSOLE, FILE,stdout,debug

# for console
log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender  
log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout 
log4j.appender.CONSOLE.layout.ConversionPattern=%d{YYYY-MM-dd HH:mm:ss.SSS} pid[${PID:- }] thread[%thread] %p %c{3}%x:%L - %m%n

## for file
log4j.appender.FILE=org.apache.log4j.DailyRollingFileAppender  
log4j.appender.FILE.File=${profiles.logDir}
#log4j.appender.FILE.Threshold =DEBUG
log4j.appender.FILE.Append = true  
log4j.appender.FILE.layout=org.apache.log4j.PatternLayout  
log4j.appender.R.DatePattern = '.'yyyy-MM-dd
log4j.appender.FILE.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss.SSS} pid[${PID:- }] thread[%thread] %p %c{3}%x:%L - %m%n

#log4j.logger.com.ibatis=debug
#log4j.logger.java.sql=debug
#log4j.logger.com.sf.egmas.dcn.web.mapper=debug

###显示SQL语句部分
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n
#
#log4j.logger.com.web=DEBUG
#log4j.logger.com.ibatis=DEBUG
#log4j.logger.com.ibatis.common.jdbc.SimpleDataSource=DEBUG
#log4j.logger.com.ibatis.common.jdbc.ScriptRunner=DEBUG
#log4j.logger.com.ibatis.sqlmap.engine.impl.SqlMapClientDelegate=DEBUG
#log4j.logger.Java.sql.Connection=DEBUG
#log4j.logger.java.sql.Statement=DEBUG
#log4j.logger.java.sql.PreparedStatement=DEBUG,stdout
#log4j.logger.virtual.pkg.logger.mybatis=DEBUG
#virtual.pkg.logger.mybatis=DEBUG
#
#log4j.logger.rabbit=ERROR
#log4j.logger.org.springframework.amqp=ERROR
```



#### 显示 sql语句配置
```java
#log4j.logger.com.web=DEBUG
#log4j.logger.com.ibatis=DEBUG
#log4j.logger.com.ibatis.common.jdbc.SimpleDataSource=DEBUG
#log4j.logger.com.ibatis.common.jdbc.ScriptRunner=DEBUG
#log4j.logger.com.ibatis.sqlmap.engine.impl.SqlMapClientDelegate=DEBUG
#log4j.logger.Java.sql.Connection=DEBUG
#log4j.logger.java.sql.Statement=DEBUG
#log4j.logger.java.sql.PreparedStatement=DEBUG,stdout
#log4j.logger.virtual.pkg.logger.mybatis=DEBUG
#virtual.pkg.logger.mybatis=DEBUG
```

```java

```
![]()

#### 


```java

```

```java

```
![]()
```




1. 
2. 
3. 
![]()