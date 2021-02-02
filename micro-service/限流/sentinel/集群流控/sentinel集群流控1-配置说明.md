---
title: sentinel集群流控1-配置说明
tags: [sentinel]
date: 2021-1-30 21:23:47
categories: sentinel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->


## 集群流量规则限流配置

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
- resource //要限流的资源名称,对应@SentinelResource注解里的name
- count //每秒限流的数目

- fallbackToLocalWhenFail // 在 client 连接失败或通信失败时，是否退化到本地的限流模式
- flowId // （必需）全局唯一的规则 ID，由集群限流管控端分配.
- thresholdType // 阈值模式，默认（0）为单机均摊，1 为全局阈值.


[https://github.com/alibaba/Sentinel/wiki/%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7#%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7%E8%A7%84%E5%88%99](https://github.com/alibaba/Sentinel/wiki/%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7#%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7%E8%A7%84%E5%88%99)


#### 集群相关ip和端口配置

```java
[
  {
    "clientSet": [
      "10.20.11.237@8721",
      "10.20.11.237@8719"
    ],
    "ip": "10.20.11.237",
    "machineId": "10.20.11.237@8719",
    "port": 17631
  }
]
```

- ip : 分配token的tokenServer的ip
- port ： 分配token的tokenServer的端口(注：这个端口应该是给控制台推送规则到tokenServer时使用的，具体看下面的分析。
          这个port也用在tokenServer和tokenClient之间通信，)
- machineId : tokenServer的机器ip加port端口(这端口是tokenServer和tokenClient之间通信的？？？还是干嘛的
              是用来区分同一台机器的同个应用的不同启动服务？？？)
- clientSet ： 客户端集合


上面配置对应类com.fzs.iotcard.sentinel.cluster.core.entity.ClusterGroupEntity

```java
@Data
@ToString
public class ClusterGroupEntity {

    private String machineId;
    private String ip;
    private Integer port;

    private Set<String> clientSet;


}
```

## 启动配置参数

```java
-Dserver.port=8090   -Dcsp.sentinel.log.use.pid=true  
```

-Dcsp.sentinel.log.use.pid是防止本地启动同一应用的多个服务而导致日志不正常用的
