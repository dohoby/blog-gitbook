---
title: python入门
tags: []
date: 2021-01-06 19:47:46
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### Window CMD 中运行python 显示乱码问题解决办法

在CMD窗中输入 chcp 65001 后，直接运行Python文件就不会显示乱码了。


#### windows下安装python 且 安装pip

[https://www.cnblogs.com/baiyuer/p/9606773.html](https://www.cnblogs.com/baiyuer/p/9606773.html)

#### 安装pip    
下载地址是：https://pypi.org/project/pip/#files   

1、下载完成之后，解压到一个文件夹，用CMD控制台进入解压文件的目录 （目录中不要包含汉字。放到比较好找的位置）  
2、然后，在文件目录下，输入：
```java
python setup.py install  
```
3、安装好之后，我们直接在命令行输入pip，同样会显示‘pip’不是内部命令，也不是可运行的程序。因为我们还没有添加环境变量。   
4、按照之前介绍的添加环境变量的方法，我们在PATH最后添加：（添加变量的时候，g用“;”英文分号的分号隔开）  
```java
D:\Program Files\python27\Scripts
```
到现在我们才算完整安装好了pip  

注：  
上面添加pip到环境变量是安装pip命令python setup.py install 时从打印的日志里看的

```java

removing 'build\bdist.win32\egg' (and everything under it)
Processing pip-20.3.3-py2.7.egg
removing 'd:\program files\python27\lib\site-packages\pip-20.3.3-py2.7.egg' (and everything under it)
creating d:\program files\python27\lib\site-packages\pip-20.3.3-py2.7.egg
Extracting pip-20.3.3-py2.7.egg to d:\program files\python27\lib\site-packages
pip 20.3.3 is already the active version in easy-install.pth
Installing pip-script.py script to D:\Program Files\python27\Scripts
Installing pip.exe script to D:\Program Files\python27\Scripts
Installing pip.exe.manifest script to D:\Program Files\python27\Scripts
Installing pip2.7-script.py script to D:\Program Files\python27\Scripts
Installing pip2.7.exe script to D:\Program Files\python27\Scripts
Installing pip2.7.exe.manifest script to D:\Program Files\python27\Scripts
Installing pip2-script.py script to D:\Program Files\python27\Scripts
Installing pip2.exe script to D:\Program Files\python27\Scripts
Installing pip2.exe.manifest script to D:\Program Files\python27\Scripts

Installed d:\program files\python27\lib\site-packages\pip-20.3.3-py2.7.egg
Processing dependencies for pip==20.3.3
Finished processing dependencies for pip==20.3.3

hoby@LAPTOP-6VJBADD9 MINGW64 /d/Program Files/pip-20.3.3

```



#### 如何利用Intellij Idea搭建python编译运行环境
[https://blog.csdn.net/qq_38188725/article/details/80623710](https://blog.csdn.net/qq_38188725/article/details/80623710)   


#### 无法打开.xlsx文件，xlrd.biffh.XLRDError: Excel xlsx file； not supported
[https://blog.csdn.net/weixin_44073728/article/details/111054157](https://blog.csdn.net/weixin_44073728/article/details/111054157)


```java

```
![]()
```




1. 
2. 
3. 
![]()