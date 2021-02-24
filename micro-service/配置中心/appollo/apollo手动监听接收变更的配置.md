---
title: apollo手动监听接收变更的配置
tags: []
date: 2021-02-21 20:44:35
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### @ApolloConfigChangeListener

应用端监控Apollo配置改变后，接收消息，然后自定义处理，主要通过注解@ApolloConfigChangeListener来实现，如下：

```java
    @ApolloConfigChangeListener("application")
    public void configChangListener(ConfigChangeEvent changeEvent) {
        for (String key : changeEvent.changedKeys()) {
            log.info("监听到变化的配置key:{}", key);
            if ("cluster-flow-rules".equalsIgnoreCase(key)) {
                ConfigChange configChange = changeEvent.getChange(key);
                String newFlowRules = configChange.getNewValue();
                //更新redis
                stringRedisTemplate.opsForValue().set(clusterFlowRulesKey, newFlowRules);
                //更新sentinel
                stringRedisTemplate.convertAndSend(channel + clusterFlowRulesKey, newFlowRules);
            }else {
                log.info("变化的key不是cluster-flow-rules，不用操作");
            }
        }
    }
```

另外Apollo配置改变时，调试发现会跳到下面这个类中
```java
com.ctrip.framework.apollo.spring.property.AutoUpdateConfigChangeListener#updateSpringValue
```


[Apollo客户端监听配置变化、动态刷新](https://blog.csdn.net/weixin_43453386/article/details/112787765)


