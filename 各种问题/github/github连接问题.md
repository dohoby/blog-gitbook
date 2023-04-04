---
title: github连接问题
tags: []
date: 2022-09-07 15:13:27
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

## 问题：git-ssh: connect to host github.com port 22: Connection timed out

#### 修改项目对应的git的url
找到项目下git对应的config文件，比如
我的路径D:\idea-workspace-blog\lqx-node-reptile\.git  
下的config，修改url部分
```java
	url = git@ssh.github.com:xx/xxx.git
```
本来是git@github.com的，现在改成git@ssh.github.com


#### 测试连接github
```shell
 ssh -T git@github.com
ssh: connect to host github.com port 22: Connection timed out

```
发现非ssh下连接超时

改成ssh的连接
```shell
 ssh -T -p 443 git@ssh.github.com
The authenticity of host '[ssh.github.com]:443 ([20.205.243.160]:443)' can't be established.
ECDSA key fingerprint is SHA256:p2QAMXNIC1TJYWeIOttrVc98/R1BUFWu3/LiyKgUfQM.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added '[ssh.github.com]:443,[20.205.243.160]:443' (ECDSA) to the list of known hosts.
Hi dohoby! You've successfully authenticated, but GitHub does not provide shell access.



```
叫选择时则输入yes，发现ssh连接是可以的

#### 增加ssh的config
C:\Users\hoby\.ssh路径下找到config文件（没有则创建）

内容如下
```java
Host github.com
Hostname ssh.github.com
Port 443
```

增加下，再测试ssh，发现不用输入yes了
```java
 ssh -T -p 443 git@ssh.github.com
Hi dohoby! You've successfully authenticated, but GitHub does not provide shell access.

```

#### 在github个人设置页面上增加ssh的key

地址为：[https://github.com/settings/keys](https://github.com/settings/keys)

最后再尝试提交代码到github上

参考：
[https://www.jianshu.com/p/c3aac5024877](https://www.jianshu.com/p/c3aac5024877)




```java

```

```java

```
1. 
2. 
3. 
![]()