---
title: rabbitmq延迟队列
tags: []
date: 2024-02-20 19:53:38
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 安装和启动插件

切换到/d/Program Files/RabbitMQ Server/rabbitmq_server-3.8.9/sbin下

```java
hoby@LAPTOP-6VJBADD9 MINGW64 /d/Program Files/RabbitMQ Server/rabbitmq_server-3.8.9/sbin
$ rabbitmq-plugins.bat list
Listing plugins with pattern ".*" ...
 Configured: E = explicitly enabled; e = implicitly enabled
 | Status: * = running on rabbit@LAPTOP-6VJBADD9
 |/
[  ] rabbitmq_amqp1_0                  3.8.9
[  ] rabbitmq_auth_backend_cache       3.8.9
[  ] rabbitmq_auth_backend_http        3.8.9
[  ] rabbitmq_auth_backend_ldap        3.8.9
[  ] rabbitmq_auth_backend_oauth2      3.8.9
[  ] rabbitmq_auth_mechanism_ssl       3.8.9
[  ] rabbitmq_consistent_hash_exchange 3.8.9
[E*] rabbitmq_delayed_message_exchange 3.8.9.0199d11c
[  ] rabbitmq_event_exchange           3.8.9
[  ] rabbitmq_federation               3.8.9
[  ] rabbitmq_federation_management    3.8.9
[  ] rabbitmq_jms_topic_exchange       3.8.9
[E*] rabbitmq_management               3.8.9
[e*] rabbitmq_management_agent         3.8.9
[  ] rabbitmq_mqtt                     3.8.9
[  ] rabbitmq_peer_discovery_aws       3.8.9
[  ] rabbitmq_peer_discovery_common    3.8.9
[  ] rabbitmq_peer_discovery_consul    3.8.9
[  ] rabbitmq_peer_discovery_etcd      3.8.9
[  ] rabbitmq_peer_discovery_k8s       3.8.9
[  ] rabbitmq_prometheus               3.8.9
[  ] rabbitmq_random_exchange          3.8.9
[  ] rabbitmq_recent_history_exchange  3.8.9
[  ] rabbitmq_sharding                 3.8.9
[  ] rabbitmq_shovel                   3.8.9
[  ] rabbitmq_shovel_management        3.8.9
[  ] rabbitmq_stomp                    3.8.9
[  ] rabbitmq_top                      3.8.9
[  ] rabbitmq_tracing                  3.8.9
[  ] rabbitmq_trust_store              3.8.9
[e*] rabbitmq_web_dispatch             3.8.9
[  ] rabbitmq_web_mqtt                 3.8.9
[  ] rabbitmq_web_mqtt_examples        3.8.9
[  ] rabbitmq_web_stomp                3.8.9
[  ] rabbitmq_web_stomp_examples       3.8.9

```

```java

```
[]()

#### 定义队列和交换机


```java
package com.fzs.iotcard.business.mq.consumer;

import com.fzs.iotcard.common.business.constant.CommonConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SmsTaskDelayConfig {
    // 创建一个立即消费队列
    @Bean
    public Queue immediateQueue() {
        // 第一个参数是创建的queue的名字，第二个参数是是否支持持久化
        return new Queue(CommonConstant.SMS_TASK_QUEUE, true);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(CommonConstant.SMS_TASK_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotify() {
        return BindingBuilder.bind(immediateQueue()).to(delayExchange()).with(CommonConstant.SMS_TASK_ROUTINGKEY).noargs();
    }


}


```
消费者
```java
@RabbitListener(queues = CommonConstant.SMS_TASK_QUEUE)
   public void smsTask(@Payload String message, Channel channel, Message message2) {

        try {
            String taskNo = message;
            if (StringUtils.hasText(taskNo)) {
                businessSmsTaskRecordService.dealSmsOrder(taskNo);
            } else {
                log.info("taskNo为空");
            }
        } catch (Exception e) {
            log.error("exception queue  message:", e);
            throw new AmqpRejectAndDontRequeueException(e);
        } finally {
            try {
                channel.basicAck(message2.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                log.error("basicAck异常:{}", e);
            }

        }
    }
```


注意用消费者的注解老是报错，弄不了，如下
```java
Caused by: com.rabbitmq.client.ShutdownSignalException: connection error; protocol method: #method<connection.close>(reply-code=503, reply-text=COMMAND_INVALID - invalid exchange type 'x-delayed-message', class-id=40, method-id=10)
	at com.rabbitmq.utility.ValueOrException.getValue(ValueOrException.java:66)
	at com.rabbitmq.utility.BlockingValueOrException.uninterruptibleGetValue(BlockingValueOrException.java:36)
	at com.rabbitmq.client.impl.AMQChannel$BlockingRpcContinuation.getReply(AMQChannel.java:505)
	at com.rabbitmq.client.impl.AMQChannel.privateRpc(AMQChannel.java:296)
	at com.rabbitmq.client.impl.AMQChannel.exnWrappingRpc(AMQChannel.java:144)
	... 33 common frames omitted

2024-02-26 16:59:15.467 ERROR 25040 --- [ 127.0.0.1:5672] o.s.a.r.c.CachingConnectionFactory       : Shutdown Signal: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - Invalid argument, 'x-delayed-message' can't be used for 'x-delayed-type', class-id=40, method-id=10)

```
[]()

参考：

https://blog.csdn.net/qq_41293765/article/details/125506964

https://blog.csdn.net/wootengxjj/article/details/87871398


https://www.cnblogs.com/walkingcamel/p/13525026.html
https://www.jianshu.com/p/04e414d1ae97
https://blog.csdn.net/wootengxjj/article/details/87871398
https://blog.csdn.net/zhangyuxuan2/article/details/82986802


#### 


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