---
title: xxl-job打印日志问题
tags: [xxl-job,定时任务]
date: 2019-07-24 10:19:22
categories: 定时任务
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

注意不能像下面那样写,不能有{}或者,这些或者，应该直接用+拼起来，

```
        XxlJobLogger.log("shopid:{},skus:{}", shopid, skus);
```

否则会报类似下面的错：

```
----------- JobThread Exception:java.lang.IllegalArgumentException: can't parse argument number: 
	at java.text.MessageFormat.makeFormat(MessageFormat.java:1429)
	at java.text.MessageFormat.applyPattern(MessageFormat.java:479)
	at java.text.MessageFormat.(MessageFormat.java:362)
	at java.text.MessageFormat.format(MessageFormat.java:840)
	at com.xxl.job.core.log.XxlJobLogger.log(XxlJobLogger.java:58)
	at com.tobe.sp.AmazonTask.xxljob.AmazonOrderDisposeSyncHandler.execute(AmazonOrderDisposeSyncHandler.java:40)
	at com.xxl.job.core.thread.JobThread.run(JobThread.java:119)
Caused by: java.lang.NumberFormatException: For input string: ""
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Integer.parseInt(Integer.java:592)
	at java.lang.Integer.parseInt(Integer.java:615)
	at java.text.MessageFormat.makeFormat(MessageFormat.java:1427)
	... 6 more

----------- xxl-job job execute end(error) -----------

```

正确写法：
```
XxlJobLogger.log("arg0：" + arg0);
```
