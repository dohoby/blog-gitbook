---
title: springboot自动装配问题
tags: []
date: 2022-12-01 16:28:11
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 报错问题

```java
java.lang.IllegalStateException: Unable to read meta-data for class

```

可以看到这个路径是有问题的

于是找到starter的spring.factories

发现原来是添加自动配置类时忘记使用逗号分割
解决：加上逗号即可

```java
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.fzs.parking.live.configuration.HttpFactoryConfiguration,\
com.fzs.parking.live.configuration.LiveConfiguration

```
注意多个自动装配的类要用逗号隔开，


[http://www.manongjc.com/detail/22-qvcmdejoyfkcjhx.html](http://www.manongjc.com/detail/22-qvcmdejoyfkcjhx.html)

#### 引用时若出现下面问题
```java
解决jackson报错：java.lang.ClassNotFoundException: com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase
```

则使用2.10以上的jackson

[https://devnote.pro/posts/10000062631238](https://devnote.pro/posts/10000062631238)


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