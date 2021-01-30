---
title: sentinel通过spi方式注册数据源
tags: [sentinel,限流]
date: 2020-11-23 10:55:57
categories: sentinel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1、
[Sentinel初始化之InitFunc实现类加载](https://blog.csdn.net/admin1973/article/details/85764525)


参考：
[https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95](https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95)


ApolloDataSourceProperties

SentinelProperties


#### sentinel加载Apollo数据源相关入口


SentinelWebAutoConfiguration

com.alibaba.cloud.sentinel.custom.SentinelDataSourceHandler
com.alibaba.cloud.sentinel.custom.SentinelDataSourceHandler#afterSingletonsInstantiated


com.alibaba.cloud.sentinel.datasource.factorybean.ApolloDataSourceFactoryBean#getObject
com.alibaba.csp.sentinel.datasource.apollo.ApolloDataSource#ApolloDataSource

    private void loadAndUpdateRules() {
        try {
            T newValue = this.loadConfig();
            if (newValue == null) {
                RecordLog.warn("[ApolloDataSource] WARN: rule config is null, you may have to check your data source", new Object[0]);
            }

            this.getProperty().updateValue(newValue);
        } catch (Throwable var2) {
            RecordLog.warn("[ApolloDataSource] Error when loading rule config", var2);
        }

    }
    

其他：少量参考，有些配置不适用
[http://blog.didispace.com/spring-cloud-alibaba-sentinel-2-2/](http://blog.didispace.com/spring-cloud-alibaba-sentinel-2-2/)


#### 2、
```java

```

```java

```
[]()

#### 3、


```java

```

```java

```
[]()
```




1. 
2. 
3. 
![]()