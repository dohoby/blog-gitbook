---
title: window端口占用查询
tags: [端口占用]
date: 2020-12-23 16:10:24
categories: 端口占用
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 

```java
查看端口
netstat -ano 
netstat -ano |grep 8090
netstat -ano |findstr "8719"

查看端口对应那个任务
tasklist|findstr "17752"

```


例子：

```java

C:\Users\hoby
λ netstat -ano |grep 8090
  TCP    0.0.0.0:8090           0.0.0.0:0              LISTENING       17752
  TCP    [::]:8090              [::]:0                 LISTENING       17752

C:\Users\hoby
λ netstat -ano |grep 8719
  TCP    0.0.0.0:8719           0.0.0.0:0              LISTENING       17752
  TCP    [::]:8719              [::]:0                 LISTENING       17752

C:\Users\hoby
λ netstat -ano |findstr "8719"
  TCP    0.0.0.0:8719           0.0.0.0:0              LISTENING       17752
  TCP    [::]:8719              [::]:0                 LISTENING       17752

C:\Users\hoby
λ tasklist|findstr "17752"
java.exe                     17752 Console                   10    430,904 K
```


参考：
[windows本地端口占用查看](https://www.cnblogs.com/qwqs/articles/5112698.html)



## tasklist 查看Windows运行中的进程

找到相同的名称，可以看到名称是tasklist 里的名称是 chromedriver.exe

[Windows命令行模式快速杀掉进程](https://blog.csdn.net/m0_58846819/article/details/119972085)

## taskkill杀掉进程

```java
public static void stopProcess(long pid) {
		// 拼接命令
		String cmd = "taskkill /PID " + pid + " /F";
		// 运行命令
		String[] returnContent = RunUtils.run2(cmd);
	}


	// 阻塞
	public static String[] run2(String cmd) {
		String returnPrintContent = null;
		String returnErrorContent = null;
		String[] returnContent = new String[2];
		try {

			Process child = Runtime.getRuntime().exec(cmd);

			// 获取程序输入流
			OutputStream os = child.getOutputStream();
			// 正常输出流和异常输出流
			InputStream stdin = child.getInputStream();
			InputStream stderr = child.getErrorStream();
			// 启动线程

			ConsoleSimulator cs1 = new ConsoleSimulator(stdin, 0);
			ConsoleSimulator cs2 = new ConsoleSimulator(stderr, 1);
			Thread tIn = new Thread(cs1);
			Thread tErr = new Thread(cs2);
			tIn.start();
			tErr.start();
			int result = child.waitFor();
			tIn.join();
			tErr.join();
			returnPrintContent = cs1.getReturnPrintContent();
			returnErrorContent = cs2.getReturnErrorContent();
			// 处理中文乱码，需更改服务器端编码
			// 0是全部信息
			returnContent[0] = returnPrintContent;
			// 1是错误信息
			returnContent[1] = returnErrorContent;
			return returnContent;
		} catch (Exception e) {
			e.printStackTrace();
			return returnContent;
		}
	}
```


[https://www.freesion.com/article/9141153359/](https://www.freesion.com/article/9141153359/)

[https://blog.csdn.net/m0_58719994/article/details/122961648](https://blog.csdn.net/m0_58719994/article/details/122961648)
