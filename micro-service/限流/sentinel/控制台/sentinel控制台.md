---
title: sentinel控制台
tags: [sentinel]
date: 2020-11-25 16:40:00
categories: sentinel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->


### 启动和访问控制台：

```java

java -Dserver.port=9090 -Dcsp.sentinel.dashboard.server=localhost:9090 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.0.jar

```
    
默认用户名和密码都是 sentinel



### 应用连接 Sentinel 控制台

Sentinel 开源控制台支持实时监控和规则管理。接入控制台的步骤如下：

（1）下载控制台 jar 包并在本地启动：可以参见 此处文档。

（2）客户端接入控制台，需要：

客户端需要引入 Transport 模块来与 Sentinel 控制台进行通信。您可以通过 pom.xml 引入 JAR 包:

```java
 <dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-transport-simple-http</artifactId>
    <version>1.8.0</version>
</dependency>

```
启动时加入 JVM 参数 -Dcsp.sentinel.dashboard.server=consoleIp:port 指定控制台地址和端口。更多的参数参见 启动参数文档。
确保应用端有访问量
比如我的：

在edit configurations -> vm opitons添加如下即可：
```java
-Dcsp.sentinel.dashboard.server=localhost:9090  -Dserver.port=8091   -Dcsp.sentinel.log.use.pid=true  
```


springboot的或者cloud可以在配置文件中指定控制台的地址和端口，参考如下：
[Spring Cloud Alibaba Sentinel](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)

参考：
[Sentinel 控制台](https://github.com/alibaba/Sentinel/wiki/%E6%8E%A7%E5%88%B6%E5%8F%B0)
[启动配置项](https://github.com/alibaba/Sentinel/wiki/%E5%90%AF%E5%8A%A8%E9%85%8D%E7%BD%AE%E9%A1%B9)


#### 

注意：若在本地启动多个 Demo 示例，需要加上 -Dcsp.sentinel.log.use.pid=true 参数，否则控制台显示监控会不准确。


#### 1、

```java

```

```java

```
[]()

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