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


