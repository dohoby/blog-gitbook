---
title: sentinel学习
tags: [sentinel]
date: 2020-11-16 16:23:47
categories: sentinel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 技术攻克

- 本地测试流量控制(流量规则存在内存中)
- 控制台如何发送规则到应用的，发送的规则是什么样的格式的
- 应用如何连到控制台,心跳检测怎么实现
- 本地测试流量控制(流量规则配置在apollo)

- 控制台如何设置规则并发送到Apollo的原理，发送到apollo的数据是怎么样的

- 应用集群如何配置，


- springboot下，流量规则在Apollo，如何初始化流量规则
- 

- 外来流量，在账号授权情况下，如何控制某账号的速率

- 集群失效，单机默认限流规则
- 调接口后异常处理
- 项目启动，自动获取Apollo上的集群配置信息，而不用手动触发推送

- sentinel如何做到有访问流量时，才实例化集群?? 
- 故障切换如何实现
- 集群总体阀值原理



http://localhost:19988/testBlock?t=-1
http://localhost:8719/getRules?type=flow


### 相关文档

[新手指南](https://github.com/alibaba/Sentinel/wiki/%E6%96%B0%E6%89%8B%E6%8C%87%E5%8D%97#%E5%85%AC%E7%BD%91-demo)

[注解支持](https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81)

[Sentinel 控制台](https://github.com/alibaba/Sentinel/wiki/%E6%8E%A7%E5%88%B6%E5%8F%B0)

[启动配置项](https://github.com/alibaba/Sentinel/wiki/%E5%90%AF%E5%8A%A8%E9%85%8D%E7%BD%AE%E9%A1%B9)

[控制台接入](https://github.com/alibaba/Sentinel/wiki/%E6%96%B0%E6%89%8B%E6%8C%87%E5%8D%97)


[动态规则扩展](https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95)

[在生产环境中使用 Sentinel](https://github.com/alibaba/Sentinel/wiki/%E5%9C%A8%E7%94%9F%E4%BA%A7%E7%8E%AF%E5%A2%83%E4%B8%AD%E4%BD%BF%E7%94%A8-Sentinel)

[如何使用](https://github.com/alibaba/Sentinel/wiki/%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8#%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6%E8%A7%84%E5%88%99-flowrule)



[Sentinel工作主流程](https://github.com/alibaba/Sentinel/wiki/Sentinel%E5%B7%A5%E4%BD%9C%E4%B8%BB%E6%B5%81%E7%A8%8B)
[博客学习](https://github.com/sentinel-group/sentinel-awesome)


[Spring Cloud Alibaba Sentinel](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)


[集群流控](https://github.com/alibaba/Sentinel/wiki/%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7)
[Sentinel 控制台（集群流控管理）](https://github.com/alibaba/Sentinel/wiki/Sentinel-%E6%8E%A7%E5%88%B6%E5%8F%B0%EF%BC%88%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7%E7%AE%A1%E7%90%86%EF%BC%89#%E8%A7%84%E5%88%99%E9%85%8D%E7%BD%AE)





### 
com.alibaba.csp.sentinel.slots.block.flow.FlowRuleUtil#isValidRule





```java

```
