---
title: mysql版本升级
tags: []
date: 2020-09-18 20:40:40
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

将mysql版本从5.6升级到5.7

### window下mysql版本从5.6升级5.7
#### 1、下载

下载地址：[https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)
最新的mysql5.7的压缩包解压开你会发现，没有data目录和my.ini文件，跟之前的版本不一样

我下载的是mysql-5.7.31-winx64.zip



#### 2、查看原来运行的mysql5.6

控制面板-管理工具-服务
搜索mysql，并且点击属性，查看可执行文件路径，看到如下

```shell script
"D:\Program Files\MySQL\MySQL Server 5.6\bin\mysqld" --defaults-file="D:\Program Files\myqldata\my.ini" MySQL56
```

可以看到启动的mysql进程为myqld，这个在任务管理器哪里也可以看到，可以在任务管理器或者服务里关掉mysql进程  
同时可以看到使用的配置文件是D:\Program Files\myqldata\my.ini，这个就是之前安装mysql5.6时指定的配置文件  
打开这个文件对应的目录D:\Program Files\myqldata就看到有个data，这个就是存放数据的的目录  

#### 3、关闭mysql

服务-右键停止启动mysql56


使用cmd窗口，进入到mysql目录下面，将mysql服务移除（注意用管理员身份运行）

```shell script
D:\Program Files\MySQL\MySQL Server 5.6\bin
λ mysqld.exe --remove mysql56
Service successfully removed.
```
再次搜索服务，发现没有mysql56这个服务了


#### 4、修改my.ini文件

主要是修改basedir改为上面下载的mysql5.7解压的目录，如下：

```shell script
basedir="D:\Program Files\mysql-5.7.31-winx64\"
```

#### 5、将mysql5.7的服务添加到win的服务队列中，并且启动mysql服务。

cd 到D:\Program Files\mysql-5.7.31-winx64\bin，执行下面的，如下：
```shell script
mysqld.exe --install   MySQL57 --defaults-file="D:\Program Files\myqldata\my.ini"
Service successfully installed.
```

其中MySQL57是指安装的服务，--defaults-file指向my.ini配置文件  
再次看服务，刷新就看到mysql57的服务


#### 6、启动mysql服务
```shell script
net start mysql57
```

(1)然后报下面的错
```shell script
D:\Program Files\mysql-5.7.31-winx64\bin
λ net start mysql57
MySQL57 服务正在启动 ..
MySQL57 服务无法启动。

服务没有报告任何错误。

请键入 NET HELPMSG 3534 以获得更多的帮助。
```

将mysql5.7安装目录添加到path环境变量也不行

(2)解决办法：
直接干掉my.ini文件里的内容，将下面的内容复制进去
```shell script
[mysqld]
# 设置mysql的安装目录[根据本地情况进行修改]
basedir="D:\Program Files\mysql-5.7.31-winx64"
# 设置mysql数据库的数据的存放目录[根据本地情况进行修改]
datadir="D:\Program Files\myqldata\data"
#设置3306端口
port = 3306
# 允许最大连接数
max_connections=200
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8
```
原因：mysql5.7的my.ini和mysql5.6的有些不一样

(3)cd 到D:\Program Files\mysql-5.7.31-winx64目录下  
若有data文件夹，则删掉，然后执行下面的

```shell script
mysqld.exe　--initialize 
```

不行就执行下面的的
```shell script
mysqld.exe --initialize-insecure 
```
这个命令会在安装目录下产生data文件夹，对个人在D:\Program Files\myqldata目录下建的data文件夹(my.ini指定的datadir)没影响的  

(4)重新执行net start mysql57命令
经过上面步骤，这个就可以启动了

#### 7、升级mysql：mysql_upgrade -uroot -p
```shell script
mysql_upgrade -uroot -proot
```
升级速度具体看data目录的大小情况而定。
必须升级，要不原来mysql5.6中的数据库，在navicate中连接报错

#### 8、升级成功后，再次重启mysql5.7服务
```shell script
D:\Program Files\mysql-5.7.31-winx64\bin
λ net stop mysql57
MySQL57 服务正在停止.
MySQL57 服务已成功停止。


D:\Program Files\mysql-5.7.31-winx64\bin
λ net start mysql57
MySQL57 服务正在启动 .
MySQL57 服务已经启动成功。
```

启动完就可以了

#### 9、查看mysql版本
登录
```shell script
mysql -uroot -proot
```
输入select version();
```shell script
mysql> select version();
+-----------+
| version() |
+-----------+
| 5.7.31    |
+-----------+
1 row in set (0.00 sec)
```
或者status
```shell script
mysql> status
--------------
mysql  Ver 14.14 Distrib 5.7.31, for Win64 (x86_64)

Connection id:          5
Current database:
Current user:           root@localhost
SSL:                    Cipher in use is ECDHE-RSA-AES128-GCM-SHA256
Using delimiter:        ;
Server version:         5.7.31 MySQL Community Server (GPL)
Protocol version:       10
Connection:             localhost via TCP/IP
Server characterset:    utf8
Db     characterset:    utf8
Client characterset:    gbk
Conn.  characterset:    gbk
TCP port:               3306
Uptime:                 4 min 5 sec

Threads: 3  Questions: 40  Slow queries: 0  Opens: 124  Flush tables: 1  Open tables: 117  Queries per second avg: 0.163
--------------
```


参考：
[https://www.cnblogs.com/java-123/p/10624600.html](https://www.cnblogs.com/java-123/p/10624600.html)


### 扩展知识

#### 修改mysql用户密码
```shell script
mysqld -nt --skip-grant-tables 
```
以管理员身份运行这段命令，相当于在my.ini中[mysqld]下加入skip-grant-tables，就可以跳过登录校验
，此时命令窗口不会动啦，  

重开一个窗口 ，直接接登录：

```shell script
mysql -uroot -proot
use mysql
update mysql.user set authentication_string=password('root') where user='root' and Host = 'localhost';
flush privileges;

```

#### 查看注册表
win10左下角搜索框输入
```s
regedit
```
然后依次打开
计算机\HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\MySQL57



