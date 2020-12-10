---
title: sentinel集群流控
tags: []
date: 2020-11-25 16:42:37
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->


## 集群限流配置

```java
[
    {
        "resource": "sayHelloResource3",
        "count": 12,
        "clusterMode": true,
        "clusterConfig": {
            "flowId": "1",
            "thresholdType": 0,
            "fallbackToLocalWhenFail": true
        }
    },
    {
        "resource": "test3",
        "count": 60,
        "clusterMode": true,
        "clusterConfig": {
            "flowId": "2",
            "thresholdType": 0,
            "fallbackToLocalWhenFail": true
        }
    }
]

```

- fallbackToLocalWhenFail // 在 client 连接失败或通信失败时，是否退化到本地的限流模式
- flowId // （必需）全局唯一的规则 ID，由集群限流管控端分配.
- thresholdType // 阈值模式，默认（0）为单机均摊，1 为全局阈值.


[https://github.com/alibaba/Sentinel/wiki/%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7#%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7%E8%A7%84%E5%88%99](https://github.com/alibaba/Sentinel/wiki/%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7#%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7%E8%A7%84%E5%88%99)


## 启动配置参数

```java
-Dserver.port=8090   -Dcsp.sentinel.log.use.pid=true  
```

#### 

```java

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