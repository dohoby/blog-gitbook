---
title: sentinel集群流控之TransportConfig
tags: [sentinel]
date: 2020-11-30 20:42:37
categories: sentinel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->



#### TransportConfig

TransportConfig.runtimePort这个是配置 "machineId": "10.20.11.237@8719" 用的
 
 
```java
public class TransportConfig {
    public static final String CONSOLE_SERVER = "csp.sentinel.dashboard.server";
    public static final String SERVER_PORT = "csp.sentinel.api.port";
    public static final String HEARTBEAT_INTERVAL_MS = "csp.sentinel.heartbeat.interval.ms";
    public static final String HEARTBEAT_CLIENT_IP = "csp.sentinel.heartbeat.client.ip";
    public static final String HEARTBEAT_API_PATH = "csp.sentinel.heartbeat.api.path";
    public static final String HEARTBEAT_DEFAULT_PATH = "/registry/machine";
    private static int runtimePort = -1;

```

有三个调用的地方
![](source/_posts/micro-service/限流/sentinel/集群流控/sentinel集群流控/集群流控-4.png)

###### 第一个地方：
com.alibaba.csp.sentinel.transport.command.netty.HttpServer#start
```java
 int retryCount = 0;
            ChannelFuture channelFuture = null;
            // loop for an successful binding
            while (true) {
                int newPort = getNewPort(port, retryCount);
                try {
                    channelFuture = b.bind(newPort).sync();
                    TransportConfig.setRuntimePort(newPort);
                    CommandCenterLog.info("[NettyHttpCommandCenter] Begin listening at port " + newPort);
                    break;
                } catch (Exception e) {
                    TimeUnit.MILLISECONDS.sleep(30);
                    RecordLog.warn("[HttpServer] Netty server bind error, port={}, retry={}", newPort, retryCount);
                    retryCount ++;
                }
            }
```

```java
    /**
     * Increase port number every 3 tries.
     * 
     * @param basePort base port to start
     * @param retryCount retry count
     * @return next calculated port
     */
    private int getNewPort(int basePort, int retryCount) {
        return basePort + retryCount / 3;
    }
```
io.netty.bootstrap.AbstractBootstrap#bind(int)
```java
    public ChannelFuture bind(int inetPort) {
        return this.bind(new InetSocketAddress(inetPort));
    }
```

分析：
1、先获取端口getNewPort(port, retryCount)  
2、绑定端口b.bind(newPort).sync();
3、绑定失败则retryCount ++循环重新获取端口,所以端口会递增


问题：
1、什么时候HttpServer#start方法会调用到???



###### 第二个地方：
com.alibaba.csp.sentinel.transport.command.SimpleHttpCommandCenter#start
```java
            @Override
            public void run() {
                boolean success = false;
                ServerSocket serverSocket = getServerSocketFromBasePort(port);

                if (serverSocket != null) {
                    CommandCenterLog.info("[CommandCenter] Begin listening at port " + serverSocket.getLocalPort());
                    socketReference = serverSocket;
                    executor.submit(new ServerThread(serverSocket));
                    success = true;
                    port = serverSocket.getLocalPort();
                } else {
                    CommandCenterLog.info("[CommandCenter] chooses port fail, http command center will not work");
                }

                if (!success) {
                    port = PORT_UNINITIALIZED;
                }

                TransportConfig.setRuntimePort(port);
                executor.shutdown();
            }
    
    }
```

![](source/_posts/micro-service/限流/sentinel/集群流控/sentinel集群流控/集群流控-5.png)

分析：
1、这里也是尝试根据port建立ServerSocket
     new ServerSocket(basePort + tryCount / 3, 100);
2、然后获取建立的ServerSocket对应真正的端口
    this.port = serverSocket.getLocalPort();
3、将端口重新设置回TransportConfig
   TransportConfig.setRuntimePort(this.port);


调用链：





###### 第三个地方
com.alibaba.csp.sentinel.transport.command.SimpleHttpCommandCenter#stop
```java
    @Override
    public void stop() throws Exception {
        if (socketReference != null) {
            try {
                socketReference.close();
            } catch (IOException e) {
                CommandCenterLog.warn("Error when releasing the server socket", e);
            }
        }
        bizExecutor.shutdownNow();
        executor.shutdownNow();
        TransportConfig.setRuntimePort(PORT_UNINITIALIZED);
        handlerMap.clear();
    }
```
这个地方是http服务关闭后重新设置TransportConfig中端口为-1

  TransportConfig.setRuntimePort(PORT_UNINITIALIZED);


####  TransportConfig#getPort获取配置文件配置的端口

com.alibaba.csp.sentinel.transport.config.TransportConfig#getPort

```java
    public static String getPort() {
        return runtimePort > 0 ? String.valueOf(runtimePort) : SentinelConfig.getConfig("csp.sentinel.api.port");
    }
```
1、先判断当前TransportConfig的runtimePort是否大于0，小于0则从SentinelConfig.getConfig("csp.sentinel.api.port")取

2、而csp.sentinel.api.port来源于下面

com.fzs.iotcard.sentinel.cluster.core.autoconfigure.ClusterSentinelAutoConfiguration#init
```java
        if (StringUtils.isEmpty(System.getProperty(TransportConfig.SERVER_PORT))
                && StringUtils.hasText(properties.getTransport().getPort())) {
            System.setProperty(TransportConfig.SERVER_PORT,
                    properties.getTransport().getPort());
        }
```
其中TransportConfig.SERVER_PORT为
```java
public class TransportConfig {
    public static final String SERVER_PORT = "csp.sentinel.api.port";

}
```


