---
title: nacos安装和入门学习
tags: []
date: 2020-09-15 11:49:43
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 安装和配置启动
#### 1、下载nacos

下载地址：
[https://github.com/alibaba/nacos/releases](https://github.com/alibaba/nacos/releases)


解压到某目录，cd 到nacos\conf文件夹下


#### 2、修改nacos\conf\application.properties配置数据库信息
  
文件内容如下：
```
### Count of DB:
 db.num=1

### Connect URL of DB:
 db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
 db.user=root
 db.password=root

```

#### 3、执行nacos\conf\nacos-mysql.sql数据库脚本

在mysql中创建nacos数据库，然后执行nacos\conf\nacos-mysql.sql数据库脚本


#### 4、 startup.cmd -m standalone启动nacos
cd到nacos\bin目录，执行

```
startup.cmd -m standalone
```

注意单机模式下要加上参数 -m standalone 否则启动失败的

#### 5、登录Console看后台
 启动完成后，登录[http://10.20.11.161:8848/nacos/index.html](http://10.20.11.161:8848/nacos/index.html)
 默认用户名和密码都是nacos
 




### 服务注册&发现和配置管理

#### 1、服务注册
```
curl -X POST "http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=nacos.naming.serviceName&ip=20.18.7.10&port=8080"
```
#### 2、服务发现
```java
curl -X GET "http://127.0.0.1:8848/nacos/v1/ns/instance/list?serviceName=nacos.naming.serviceName"
```
返回
```java
{"dom":"nacos.naming.serviceName","hosts":[],"name":"DEFAULT_GROUP@@nacos.naming.serviceName","cacheMillis":3000,"lastRefTime":1600085004554,"checksum":"7f882d81002bc22181203d423a20a16d","useSpecifiedURL":false,"clusters":"","env":"","metadata":{}}
```

#### 3、发布配置
```java
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos.cfg.dataId&group=test&content=HelloWorld"

```

#### 4、获取配置
```java
curl -X GET "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos.cfg.dataId&group=test"

```



参考：
[https://nacos.io/zh-cn/docs/quick-start.html](https://nacos.io/zh-cn/docs/quick-start.html)


