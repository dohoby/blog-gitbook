---
title: rabbitmq学习
tags: []
date: 2020-12-16 10:33:08
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->



[rabbitmq channel参数详解](https://www.cnblogs.com/piaolingzxh/p/5448927.html)

[RabbitMQ的下载和安装](https://www.cnblogs.com/z-1026/p/9985389.html)

[spring.rabbitmq.publisher-confirms过时解决](https://blog.csdn.net/m0_43409491/article/details/107042753)

[rabbitmq：publisher confirms发送消息确认扩展](https://blog.csdn.net/weixin_38380858/article/details/93227652)

[spring.rabbitmq.publisher-confirm-type详解](https://blog.csdn.net/yaomingyang/article/details/108410286)

[Spring Boot系列(8)——RabbitMQ确认、退回模式及死信队列](Spring Boot系列(8)——RabbitMQ确认、退回模式及死信队列)

[RabbitMQ（四）消息Ack确认机制](https://blog.csdn.net/Sadlay/article/details/86771830?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromBaidu-1.control)


[Concurrency与Prefetch参数\RabbitMQ消费者的几个参数](https://www.jianshu.com/p/04a1d36f52ba)

[消费RabbitMQ时的注意事项，如何禁止大量的消息涌到Consumer](https://www.cnblogs.com/atwind/p/5606120.html)



rabbitMq消费者如何设置同时处理的消息数？

node 使用 amqplib 库连接 rabbitMq，消费者采用需要手动ack方式。最近消息生产速度越来越快，消费者已经吃满cpu了，rabbitMq只要有消息就会马上发给消费者处理，如何才能让rabbitmq一次只给每个消费者几条消息，每ack一条消息再发一条呢？

[如何才能让rabbitmq一次只给每个消费者几条消息](https://segmentfault.com/q/1010000020495252/a-1020000020495352#)





#### 消费消息的两种方式
第一种是@RabbitListener注解，
```java
    @RabbitListener(containerFactory = "defaultRabbitContainerFactory",
            bindings = @QueueBinding(
                    exchange = @Exchange(value = QueueConstant.FLOWSYN_YDZG_GZ_EXCHANGE),
                    value = @Queue(value = QueueConstant.FLOWSYN_YDZG_GZ_QUEUE, durable = "false"),
                    key = QueueConstant.FLOWSYN_YDZG_GZ_ROUTINGKEY
            ),
            admin = RabbitMqCoreConfig.adminName,
            concurrency = "1",
            ackMode = "MANUAL")
    public void guangzhouListener(@Payload String message, Channel channel, Message message2) {
            ///log.info("消息数据：{}", message);

    }
```


第二种是实现ChannelAwareMessageListener接口。
```java
public class BillDetailsPushBackCus implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String msg = new String(message.getBody(), "utf-8");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {
            log.error(":", e);
        }
    }

}
```


![浅谈spring-boot-rabbitmq动态管理的方法](https://www.jb51.net/article/131708.htm)

#### 
```java
com.rabbitmq.client.impl.recovery.AutorecoveringChannel#recoverState
org.springframework.amqp.rabbit.listener.BlockingQueueConsumer#setQosAndreateConsumers


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