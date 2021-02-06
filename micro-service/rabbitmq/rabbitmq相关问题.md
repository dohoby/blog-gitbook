---
title: rabbitmq相关问题
tags: [rabbitmq]
date: 2021-02-06 15:42:17
categories: rabbitmq
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### Channel shutdown问题、 rabbit 406问题、basicAck导致项目一直重启问题

```java
2021-02-05 00:00:05.706 [AMQP Connection 10.20.70.228:5672] ERROR o.s.a.rabbit.connection.CachingConnectionFactory - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - unknown delivery tag 1, class-id=60, method-id=80)
2021-02-05 00:00:06.654 [AMQP Connection 10.20.70.228:5672] ERROR o.s.a.rabbit.connection.CachingConnectionFactory - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - unknown delivery tag 1, class-id=60, method-id=80)
2021-02-05 00:00:06.665 [AMQP Connection 10.20.70.228:5672] ERROR o.s.a.rabbit.connection.CachingConnectionFactory - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - unknown delivery tag 1, class-id=60, method-id=80)
2021-02-05 00:00:06.668 [AMQP Connection 10.20.70.228:5672] ERROR o.s.a.rabbit.connection.CachingConnectionFactory - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - unknown delivery tag 1, class-id=60, method-id=80)
2021-02-05 00:00:06.673 [AMQP Connection 10.20.70.228:5672] ERROR o.s.a.rabbit.connection.CachingConnectionFactory - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - unknown delivery tag 1, class-id=60, method-id=80)
2021-02-05 00:00:06.676 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#12-1896] ERROR c.f.i.f.mq.consumer.FlowSyncBusinessHandleListener - IotCardHistoryBatchInsert onMessage error
org.springframework.amqp.AmqpException: PublisherCallbackChannel is closed
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory$CachedChannelInvocationHandler.invoke(CachingConnectionFactory.java:1166)
	at com.sun.proxy.$Proxy161.basicAck(Unknown Source)
	at com.fzs.iotcard.flowsync.mq.consumer.FlowSyncBusinessHandleListener.flowSyncBusinessHandleListener(FlowSyncBusinessHandleListener.java:62)
	at sun.reflect.GeneratedMethodAccessor234.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:171)
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:120)
	at org.springframework.amqp.rabbit.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:53)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:220)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.invokeHandlerAndProcessResult(MessagingMessageListenerAdapter.java:148)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.onMessage(MessagingMessageListenerAdapter.java:133)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.doInvokeListener(AbstractMessageListenerContainer.java:1591)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.actualInvokeListener(AbstractMessageListenerContainer.java:1510)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.invokeListener(AbstractMessageListenerContainer.java:1498)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.doExecuteListener(AbstractMessageListenerContainer.java:1489)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.executeListener(AbstractMessageListenerContainer.java:1433)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.doReceiveAndExecute(SimpleMessageListenerContainer.java:970)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.receiveAndExecute(SimpleMessageListenerContainer.java:916)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.access$1600(SimpleMessageListenerContainer.java:83)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.mainLoop(SimpleMessageListenerContainer.java:1291)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.run(SimpleMessageListenerContainer.java:1197)
	at java.lang.Thread.run(Thread.java:748)
2021-02-05 00:00:06.685 [AMQP Connection 10.20.70.228:5672] ERROR o.s.a.rabbit.connection.CachingConnectionFactory - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - unknown delivery tag 1, class-id=60, method-id=80)
2021-02-05 00:00:06.688 [AMQP Connection 10.20.70.228:5672] ERROR o.s.a.rabbit.connection.CachingConnectionFactory - Channel shutdown: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - unknown delivery tag 1, class-id=60, method-id=80)
2021-02-05 00:00:06.690 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#12-1897] ERROR c.f.i.f.mq.consumer.FlowSyncBusinessHandleListener - IotCardHistoryBatchInsert onMessage error
org.springframework.amqp.AmqpException: PublisherCallbackChannel is closed
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory$CachedChannelInvocationHandler.invoke(CachingConnectionFactory.java:1166)
	at com.sun.proxy.$Proxy161.basicAck(Unknown Source)
	at com.fzs.iotcard.flowsync.mq.consumer.FlowSyncBusinessHandleListener.flowSyncBusinessHandleListener(FlowSyncBusinessHandleListener.java:62)
	at sun.reflect.GeneratedMethodAccessor234.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:171)
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:120)
	at org.springframework.amqp.rabbit.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:53)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:220)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.invokeHandlerAndProcessResult(MessagingMessageListenerAdapter.java:148)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.onMessage(MessagingMessageListenerAdapter.java:133)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.doInvokeListener(AbstractMessageListenerContainer.java:1591)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.actualInvokeListener(AbstractMessageListenerContainer.java:1510)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.invokeListener(AbstractMessageListenerContainer.java:1498)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.doExecuteListener(AbstractMessageListenerContainer.java:1489)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.executeListener(AbstractMessageListenerContainer.java:1433)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.doReceiveAndExecute(SimpleMessageListenerContainer.java:970)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.receiveAndExecute(SimpleMessageListenerContainer.java:916)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.access$1600(SimpleMessageListenerContainer.java:83)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.mainLoop(SimpleMessageListenerContainer.java:1291)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.run(SimpleMessageListenerContainer.java:1197)
	at java.lang.Thread.run(Thread.java:748)
```

网上搜索了好久没结果，后来发现网上有说406是double 确认消息导致，然后又仔细看了下日志，发现有行

```java
	at com.fzs.iotcard.flowsync.mq.consumer.FlowSyncBusinessHandleListener.flowSyncBusinessHandleListener(FlowSyncBusinessHandleListener.java:62)
```
然后对比代码,发现有行
```java
                channel.basicAck(message2.getMessageProperties().getDeliveryTag(), false);
```
然后finally里面也有行一样的代码，所以就是这里导致的，删掉第一个就可以了  

同时在下面rabbitmq的配置代码里改为手动确认消息模式AcknowledgeMode.MANUAL

```java

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean(name = "defaultRabbitContainerFactory")
    public SimpleRabbitListenerContainerFactory defaultContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setConnectionTimeout(0);
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(concurrentConsumers);
        factory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        factory.setPrefetchCount(prefetch);

        return factory;
    }
```
另外，注意这里由于用Autowired注入CachingConnectionFactory，所以配置文件里加上下面这些可能会无效

```java
spring.rabbitmq.listener.max-concurrency = 20
spring.rabbitmq.listener.prefetch = 3
spring.rabbitmq.publisher-confirm-type = correlated
spring.rabbitmq.publisher-returns = true
spring.rabbitmq.listener.simple.acknowledge-mode = manual
```

另外当时也怀疑是不是并发连接rabbitmq线程数,所以就把下面的配置concurrentConsumers改为2000了
```java
        factory.setConcurrentConsumers(concurrentConsumers);
```


参考：  
![http://blog.chinaunix.net/uid-192452-id-5837248.html](http://blog.chinaunix.net/uid-192452-id-5837248.html)
![https://blog.csdn.net/more_try/article/details/82804387](https://blog.csdn.net/more_try/article/details/82804387)
![https://www.cnblogs.com/littleatp/p/6087856.html](https://www.cnblogs.com/littleatp/p/6087856.html)
![]()
![]()

#### 连接超时问题

```java
2021-02-05 17:29:44.868  INFO 11360 --- [tContainer#3-38] o.s.a.r.l.SimpleMessageListenerContainer : Restarting Consumer@4aa5edf: tags=[[amq.ctag-9EeR86pC4jHA0zXYAFdrag]], channel=Cached Rabbit Channel: PublisherCallbackChannelImpl: AMQChannel(amqp://iot_test@10.20.70.228:5672/,111), conn: Proxy@2fb48970 Shared Rabbit Connection: SimpleConnection@37ab8291 [delegate=amqp://iot_test@10.20.70.228:5672/, localPort= 59376], acknowledgeMode=MANUAL local queue size=0
2021-02-05 17:29:44.870  INFO 11360 --- [Container#12-35] o.s.a.r.l.SimpleMessageListenerContainer : Restarting Consumer@71be1c4d: tags=[[amq.ctag-99PRhByyfPPeGfusdSyeeg]], channel=Cached Rabbit Channel: PublisherCallbackChannelImpl: AMQChannel(amqp://iot_test@10.20.70.228:5672/,109), conn: Proxy@2fb48970 Shared Rabbit Connection: SimpleConnection@37ab8291 [delegate=amqp://iot_test@10.20.70.228:5672/, localPort= 59376], acknowledgeMode=MANUAL local queue size=0
2021-02-05 17:29:44.870  INFO 11360 --- [tContainer#0-38] o.s.a.r.l.SimpleMessageListenerContainer : Restarting Consumer@472d1192: tags=[[amq.ctag-f9UHqoKjMD-vZlTlSEAjiw]], channel=Cached Rabbit Channel: PublisherCallbackChannelImpl: AMQChannel(amqp://iot_test@10.20.70.228:5672/,113), conn: Proxy@2fb48970 Shared Rabbit Connection: SimpleConnection@37ab8291 [delegate=amqp://iot_test@10.20.70.228:5672/, localPort= 59376], acknowledgeMode=MANUAL local queue size=0
2021-02-05 17:29:44.870  INFO 11360 --- [Container#15-28] o.s.a.r.l.SimpleMessageListenerContainer : Restarting Consumer@55267225: tags=[[amq.ctag-epbtQkURKWHwXEw0tOQleQ]], channel=Cached Rabbit Channel: PublisherCallbackChannelImpl: AMQChannel(amqp://iot_test@10.20.70.228:5672/,108), conn: Proxy@2fb48970 Shared Rabbit Connection: SimpleConnection@37ab8291 [delegate=amqp://iot_test@10.20.70.228:5672/, localPort= 59376], acknowledgeMode=MANUAL local queue size=0
2021-02-05 17:29:44.874  WARN 11360 --- [.20.70.228:5672] c.r.c.impl.ForgivingExceptionHandler     : An unexpected connection driver error occured (Exception message: Connection reset)
2021-02-05 17:29:44.878 ERROR 11360 --- [tContainer#5-42] o.s.a.r.c.CachingConnectionFactory       : Could not configure the channel to receive publisher confirms

java.io.IOException: null
	at com.rabbitmq.client.impl.AMQChannel.wrap(AMQChannel.java:129)
	at com.rabbitmq.client.impl.AMQChannel.wrap(AMQChannel.java:125)
	at com.rabbitmq.client.impl.AMQChannel.exnWrappingRpc(AMQChannel.java:147)
	at com.rabbitmq.client.impl.ChannelN.confirmSelect(ChannelN.java:1558)
	at com.rabbitmq.client.impl.ChannelN.confirmSelect(ChannelN.java:46)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.doCreateBareChannel(CachingConnectionFactory.java:726)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.createBareChannel(CachingConnectionFactory.java:706)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.getCachedChannelProxy(CachingConnectionFactory.java:676)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.getChannel(CachingConnectionFactory.java:567)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.access$1600(CachingConnectionFactory.java:102)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory$ChannelCachingConnectionProxy.createChannel(CachingConnectionFactory.java:1439)
	at org.springframework.amqp.rabbit.core.RabbitTemplate.doExecute(RabbitTemplate.java:2095)
	at org.springframework.amqp.rabbit.core.RabbitTemplate.execute(RabbitTemplate.java:2062)
	at org.springframework.amqp.rabbit.core.RabbitTemplate.execute(RabbitTemplate.java:2042)
	at org.springframework.amqp.rabbit.core.RabbitAdmin.initialize(RabbitAdmin.java:604)
	at org.springframework.amqp.rabbit.core.RabbitAdmin.lambda$null$10(RabbitAdmin.java:532)
	at org.springframework.retry.support.RetryTemplate.doExecute(RetryTemplate.java:287)
	at org.springframework.retry.support.RetryTemplate.execute(RetryTemplate.java:164)
	at org.springframework.amqp.rabbit.core.RabbitAdmin.lambda$afterPropertiesSet$11(RabbitAdmin.java:531)
	at org.springframework.amqp.rabbit.connection.CompositeConnectionListener.onCreate(CompositeConnectionListener.java:36)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.createConnection(CachingConnectionFactory.java:757)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.createBareChannel(CachingConnectionFactory.java:702)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.getCachedChannelProxy(CachingConnectionFactory.java:676)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.getChannel(CachingConnectionFactory.java:567)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.access$1600(CachingConnectionFactory.java:102)
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory$ChannelCachingConnectionProxy.createChannel(CachingConnectionFactory.java:1439)
	at org.springframework.amqp.rabbit.core.RabbitTemplate.doExecute(RabbitTemplate.java:2095)
	at org.springframework.amqp.rabbit.core.RabbitTemplate.execute(RabbitTemplate.java:2062)
	at org.springframework.amqp.rabbit.core.RabbitTemplate.execute(RabbitTemplate.java:2042)
	at org.springframework.amqp.rabbit.core.RabbitAdmin.getQueueInfo(RabbitAdmin.java:407)
	at org.springframework.amqp.rabbit.core.RabbitAdmin.getQueueProperties(RabbitAdmin.java:391)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.attemptDeclarations(AbstractMessageListenerContainer.java:1842)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.redeclareElementsIfNecessary(AbstractMessageListenerContainer.java:1823)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.initialize(SimpleMessageListenerContainer.java:1349)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.run(SimpleMessageListenerContainer.java:1195)
	at java.lang.Thread.run(Thread.java:748)
Caused by: com.rabbitmq.client.ShutdownSignalException: connection error
	at com.rabbitmq.utility.ValueOrException.getValue(ValueOrException.java:66)
	at com.rabbitmq.utility.BlockingValueOrException.uninterruptibleGetValue(BlockingValueOrException.java:36)
	at com.rabbitmq.client.impl.AMQChannel$BlockingRpcContinuation.getReply(AMQChannel.java:502)
	at com.rabbitmq.client.impl.AMQChannel.privateRpc(AMQChannel.java:293)
	at com.rabbitmq.client.impl.AMQChannel.exnWrappingRpc(AMQChannel.java:141)
	... 33 common frames omitted
Caused by: java.net.SocketException: Connection reset
	at java.net.SocketInputStream.read(SocketInputStream.java:210)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	at java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
	at com.rabbitmq.client.impl.Frame.readFrom(Frame.java:91)
	at com.rabbitmq.client.impl.SocketFrameHandler.readFrame(SocketFrameHandler.java:184)
	at com.rabbitmq.client.impl.AMQConnection$MainLoop.run(AMQConnection.java:665)
	... 1 common frames omitted

2021-02-05 17:29:44.878 ERROR 11360 --- [tContainer#5-42] o.s.a.r.c.CachingConnectionFactory       : Channel shutdown: connection error
2021-02-05 17:29:44.881  INFO 11360 --- [tContainer#6-37] o.s.a.r.l.SimpleMessageListenerContainer : Restarting Consumer@51b6c389: tags=[[amq.ctag-guPwnFBcx45Hqz6wErsN4w]], channel=Cached Rabbit Channel: PublisherCallbackChannelImpl: AMQChannel(amqp://iot_test@10.20.70.228:5672/,115), conn: Proxy@2fb48970 Shared Rabbit Connection: SimpleConnection@37ab8291 [delegate=amqp://iot_test@10.20.70.228:5672/, localPort= 59376], acknowledgeMode=MANUAL local queue size=0
2021-02-05 17:29:44.882  INFO 11360 --- [tContainer#1-37] o.s.a.r.l.SimpleMessageListenerContainer : Restarting Consumer@7b342267: tags=[[amq.ctag-SBgkb06moG6nE69lNlGwBg]], channel=Cached Rabbit Channel: PublisherCallbackChannelImpl: AMQChannel(amqp://iot_test@10.20.70.228:5672/,104), conn: Proxy@2fb48970 Shared Rabbit Connection: SimpleConnection@37ab8291 [delegate=amqp://iot_test@10.20.70.228:5672/, localPort= 59376], acknowledgeMode=MANUAL local queue size=0
2
```

连接超时问题是由于spring.rabbitmq.connection-timeout 设置过小引起
后来将其设置为0就好了，好像0表示无穷大，不超时

rabbitmq基本配置
```java
spring.rabbitmq.host: 服务Host
spring.rabbitmq.port: 服务端口
spring.rabbitmq.username: 登陆用户名
spring.rabbitmq.password: 登陆密码
spring.rabbitmq.virtual-host: 连接到rabbitMQ的vhost
spring.rabbitmq.addresses: 指定client连接到的server的地址，多个以逗号分隔(优先取addresses，然后再取host)
spring.rabbitmq.requested-heartbeat: 指定心跳超时，单位秒，0为不指定；默认60s
spring.rabbitmq.publisher-confirms: 是否启用【发布确认】
spring.rabbitmq.publisher-returns: 是否启用【发布返回】
spring.rabbitmq.connection-timeout: 连接超时，单位毫秒，0表示无穷大，不超时
spring.rabbitmq.parsed-addresses:

```


![https://www.cnblogs.com/imfx/p/12454299.html](https://www.cnblogs.com/imfx/p/12454299.html)

![https://www.cnblogs.com/qts-hope/p/11242559.html](https://www.cnblogs.com/qts-hope/p/11242559.html)


#### ForgivingExceptionHandler     : An unexpected connection driver error occured

下面这个还没找到具体是什么问题

```java
2021-02-05 17:43:31.989  INFO 12156 --- [nio-9090-exec-1] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2021-02-05 17:43:34.540  INFO 12156 --- [           main] o.s.a.r.l.SimpleMessageListenerContainer : Broker not available; cannot force queue declarations during start: java.util.concurrent.TimeoutException
2021-02-05 17:43:34.542 ERROR 12156 --- [.20.70.228:5672] c.r.c.impl.ForgivingExceptionHandler     : An unexpected connection driver error occured

java.net.SocketException: Socket Closed
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.io.BufferedInputStream.fill(BufferedInputStream.java:246)
	at java.io.BufferedInputStream.read(BufferedInputStream.java:265)
	at java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
	at com.rabbitmq.client.impl.Frame.readFrom(Frame.java:91)
	at com.rabbitmq.client.impl.SocketFrameHandler.readFrame(SocketFrameHandler.java:184)
	at com.rabbitmq.client.impl.AMQConnection$MainLoop.run(AMQConnection.java:665)
	at java.lang.Thread.run(Thread.java:748)

2021-02-05 17:43:34.547  INFO 12156 --- [nio-9090-exec-2] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [10.20.70.228:5672]
2021-02-05 17:43:34.934  INFO 12156 --- [nio-9090-exec-2] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory#708eb310:1/SimpleConnection@2175c78b [delegate=amqp://iot_test@10.20.70.228:5672/, localPort= 61567]
log4j:WARN No appenders could be found for logger (freemarker.cache).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
log4j:WARN No appenders could be found for logger (freemarker.cache).
log4j:WARN Please initialize the log4j system properly.
```


#### ConcurrentModificationException问题


```java
2021-02-06 10:00:12.428  INFO 12 --- [tContainer#12-4] f.i.f.m.c.FlowSyncBusinessHandleListener : iotCardId:40700000149846,总耗时：1
2021-02-06 10:00:12.427 ERROR 12 --- [tContainer#12-1] f.i.f.m.c.FlowSyncBusinessHandleListener : IotCardHistoryBatchInsert onMessage error:{}

org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.PersistenceException: 
### Error updating database.  Cause: java.util.ConcurrentModificationException
### The error may exist in URL [jar:file:/usr/local/flow-sync-service/flow-sync-service-master.jar!/BOOT-INF/classes!/mapper/FlowSyncCardMonitorHistoryMapper.xml]
### The error may involve com.fzs.iotcard.flowsync.mapper.FlowSyncCardMonitorHistoryMapper.insertHistoryBatch
### The error occurred while executing an update
### Cause: java.util.ConcurrentModificationException
	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:92)
	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:440)
	at com.sun.proxy.$Proxy89.insert(Unknown Source)
	at org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:271)
	at com.baomidou.mybatisplus.core.override.MybatisMapperMethod.execute(MybatisMapperMethod.java:60)
	at com.baomidou.mybatisplus.core.override.MybatisMapperProxy$PlainMethodInvoker.invoke(MybatisMapperProxy.java:148)
	at com.baomidou.mybatisplus.core.override.MybatisMapperProxy.invoke(MybatisMapperProxy.java:89)
	at com.sun.proxy.$Proxy110.insertHistoryBatch(Unknown Source)
	at com.fzs.iotcard.flowsync.service.impl.FlowSyncCardMonitorHistoryServiceImpl.insertHistoryBatch(FlowSyncCardMonitorHistoryServiceImpl.java:40)
	at com.fzs.iotcard.flowsync.mq.consumer.FlowSyncBusinessHandleListener.flowSyncBusinessHandleListener(FlowSyncBusinessHandleListener.java:71)
	at sun.reflect.GeneratedMethodAccessor157.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:171)
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:120)
	at org.springframework.amqp.rabbit.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:53)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:220)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.invokeHandlerAndProcessResult(MessagingMessageListenerAdapter.java:148)
	at org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter.onMessage(MessagingMessageListenerAdapter.java:133)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.doInvokeListener(AbstractMessageListenerContainer.java:1591)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.actualInvokeListener(AbstractMessageListenerContainer.java:1510)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.invokeListener(AbstractMessageListenerContainer.java:1498)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.doExecuteListener(AbstractMessageListenerContainer.java:1489)
	at org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer.executeListener(AbstractMessageListenerContainer.java:1433)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.doReceiveAndExecute(SimpleMessageListenerContainer.java:970)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.receiveAndExecute(SimpleMessageListenerContainer.java:916)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer.access$1600(SimpleMessageListenerContainer.java:83)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.mainLoop(SimpleMessageListenerContainer.java:1291)
	at org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer$AsyncMessageProcessingConsumer.run(SimpleMessageListenerContainer.java:1197)
	at java.lang.Thread.run(Thread.java:748)
Caused by: org.apache.ibatis.exceptions.PersistenceException: 
### Error updating database.  Cause: java.util.ConcurrentModificationException
### The error may exist in URL [jar:file:/usr/local/flow-sync-service/flow-sync-service-master.jar!/BOOT-INF/classes!/mapper/FlowSyncCardMonitorHistoryMapper.xml]
### The error may involve com.fzs.iotcard.flowsync.mapper.FlowSyncCardMonitorHistoryMapper.insertHistoryBatch
### The error occurred while executing an update
### Cause: java.util.ConcurrentModificationException
	at org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:30)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:199)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:184)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:426)
	... 28 common frames omitted
Caused by: java.util.ConcurrentModificationException: null
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
	at java.util.ArrayList$Itr.next(ArrayList.java:859)
	at org.apache.ibatis.scripting.xmltags.ForEachSqlNode.apply(ForEachSqlNode.java:61)
	at org.apache.ibatis.scripting.xmltags.MixedSqlNode.lambda$apply$0(MixedSqlNode.java:32)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at org.apache.ibatis.scripting.xmltags.MixedSqlNode.apply(MixedSqlNode.java:32)
	at org.apache.ibatis.scripting.xmltags.DynamicSqlSource.getBoundSql(DynamicSqlSource.java:39)
	at org.apache.ibatis.mapping.MappedStatement.getBoundSql(MappedStatement.java:305)
	at org.apache.ibatis.executor.statement.BaseStatementHandler.<init>(BaseStatementHandler.java:64)
	at org.apache.ibatis.executor.statement.PreparedStatementHandler.<init>(PreparedStatementHandler.java:41)
	at org.apache.ibatis.executor.statement.RoutingStatementHandler.<init>(RoutingStatementHandler.java:46)
	at org.apache.ibatis.session.Configuration.newStatementHandler(Configuration.java:636)
	at org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:48)
	at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.ibatis.plugin.Invocation.proceed(Invocation.java:49)
	at com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor.intercept(MybatisPlusInterceptor.java:82)
	at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:61)
	at com.sun.proxy.$Proxy168.update(Unknown Source)
	at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:197)
	... 34 common frames omitted

```

这个貌似是删除ArrayList的问题，还没找到解决办法
由于全局定义了个ArrayList，而在mq接收信息里存在并发修改这个，代码如下：

```java
    List<CardMonitorHistory> historyList = Lists.newArrayList();

    int defaultCount = 100;


    @RabbitListener(containerFactory = "defaultRabbitContainerFactory",
            bindings = @QueueBinding(
                    exchange = @Exchange(value = QueueConstant.FLOWSYN_INSERT_DB_EXCHANGE),
                    value = @Queue(value = QueueConstant.FLOWSYN_INSERT_DB_QUEUE, durable = "true"),
                    key = QueueConstant.FLOWSYN_INSERT_DB_ROUTINGKEY
            ),
            admin = RabbitMqCoreConfig.adminName,
            concurrency = "1")
    public void flowSyncBusinessHandleListener(Message message, Channel channel, Message message2) {

        try {
            long beginTime = System.currentTimeMillis();
            log.info("IotCardHistoryBatchInsert receive queue: {},hashCode:{}",
                    message.getMessageProperties().getReceivedRoutingKey(), hashCode());
            String msg = new String(message.getBody(), "utf-8");
            CardMonitorHistory iotCardMonitorHistory = Jacksons.parse(msg, CardMonitorHistory.class);
            historyList.add(iotCardMonitorHistory);

            String countStr = BeanFactory.getMessageFromApollo("historyBatchInsert");
            if (StringUtil.isNEmpty(countStr)) {
                defaultCount = Integer.valueOf(countStr);
            }
            if (historyList.size() == defaultCount) {
                log.info("IotCardHistoryBatchInsert 批量插入数据库限制数量数量={}", defaultCount);
                flowSyncCardMonitorHistoryService.insertHistoryBatch(historyList);
                log.info("IotCardHistoryBatchInsert 已批量插入数据库");

                for (CardMonitorHistory cardMonitorHistory : historyList) {
                    flowSyncBusinessHandleService.syncHistoryToTrans(cardMonitorHistory);
                }

                historyList.clear();
                log.info("IotCardHistoryBatchInsert 清空list数据");
            }
            log.info("iotCardId:{},总耗时：{}", iotCardMonitorHistory.getIotCardId(), System.currentTimeMillis() - beginTime);
        } catch (Exception e) {
            historyList.clear();
            log.error("IotCardHistoryBatchInsert onMessage error:{}", e);
        } finally {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            //手动应答消息　　第一个参数是所确认消息的标识，第二参数是是否批量确认
            try {
                channel.basicAck(message2.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                log.error("basicAck异常:{}", e);
            }
        }
    }

```



```java


```
1. 
2. 
3. 
![SpringBoot2.x下RabbitMQ的并发参数（concurrency和prefetch）](https://www.cnblogs.com/liukunjava/p/13163951.html)

![https://www.cnblogs.com/dolphin0520/p/3933551.html](https://www.cnblogs.com/dolphin0520/p/3933551.html)

![Java ConcurrentModificationException异常原因和解决方法](https://www.cnblogs.com/dolphin0520/p/3933551.html)

