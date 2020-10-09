---
title: xxl-job定时任务教程
date: 2019-05-20 16:35:37
tags: [xxl-job,定时任务]
categories: 定时任务
---
** {{ title }} ** <Excerpt in index | 首页摘要>

<!-- more -->

## 1、pom引入


```
    <dependency>
      <groupId>com.xuxueli</groupId>
      <artifactId>xxl-job-core</artifactId>
      <version>1.9.0</version>
    </dependency>
```

## 2、配置文件

```

xxl:
  job:
    admin:
      addresses: http://192.168.1.1:8480/xxl-job-admin #调度中心url
    executor:
      appname: accountTask  #当前任务器的名字
      ip:
      port: 9502 #调度中心调度任务的端口
      logpath: ../logs/xxl-job-executor-demo/ # 日志位置
    accessToken: emVuZ3FpLWJhbmdnb29k #简单安全AccessToken

```

```
package com.tobe.erp.fit.account.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "com.tobe.erp.cashweb.task")
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);


    @Value("${xxl.job.admin.addresses}")
    private String addresses;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logpath;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setIp(ip);
        xxlJobExecutor.setPort(port);
        xxlJobExecutor.setAppName(appname);
        xxlJobExecutor.setAdminAddresses(addresses);
        xxlJobExecutor.setLogPath(logpath);
        xxlJobExecutor.setAccessToken(accessToken);
        return xxlJobExecutor;
    }

}
```



## 3、调用


```
package com.tobe.erp.fit.account.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import fit.basic.convert.StringToDateConvert;
import fit.basic.util.aop.WebExceptionConfig;
import fit.basic.util.idwork.IdUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@JobHandler(value="notifyTask")
@Component
public class notifyTask extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        Date excuteDate = new Date();

        StringBuffer message = new StringBuffer();
        XxlJobLogger.log("开始任务。");
        try{

        }catch (Exception e){
            XxlJobLogger.log("信息: "+ WebExceptionConfig.printStackTraceToString(e),e);
        }

        XxlJobLogger.log("结束任务");
        return SUCCESS;
    }
}

```







---


官网：http://www.xuxueli.com/xxl-job/#/?id=%e6%ba%90%e7%a0%81%e4%bb%93%e5%ba%93%e5%9c%b0%e5%9d%80
