---
title: 建模工具plantuml介绍
date: 2019-05-24 20:35:37
tags: [uml,plantuml]
categories: uml
---
** {{ title }} ** <Excerpt in index | 首页摘要>

### 前言
听说过写程序画图吗，没听错吧，一般不是通过powerdesigner。没错，就是写程序画图。哈哈，本文就简单介绍下平时在项目开发中最常用的通过写程序来进行数据库建模，十分简单实用。

<!-- more -->  

听说过写程序画图吗？ 


没听错吧，一般不是通过powerdesigner或者visual studio来画吗，写程序怎么画 


哈哈，你没听错，下面就让我简单介绍下plantuml给你 

首先了解下UML是统一建模语言的意思，而PlantUML可以画UML各种图，比如类图、对象图、用例图、时序图等

那怎么开始用呢 

首先要知道在很多软件上，比如sublime、notepad++、eclipse或者命令行上都可以使用<span style="color: rgb(51, 51, 51); font-size: 14px;">Pla</span><span style="color: rgb(51, 51, 51); font-size: 14px;">ntUML，但是</span>必须得装一些必须的依赖软件，如果你在window下，则必须安装graphviz，下面列出安装的步骤 


### 1、下载graphviz并安装
<p>下载地址：<span style="color: inherit;">http://www.graphviz.org/Download_windows.php</span></p><p>安装目录：<span style="color: inherit;">D:\Program Files\graphviz下</span></p><p><br></p>

### 2、设置graphviz环境变量

<p>a、新建GRAPHVIZ_DOT系统变量，设置其值为graphviz安装目录下的bin目录下的dot.exe文件路径</p><p>D:\Program Files\graphviz\bin\dot.exe</p><p>b、修改path变量，在后面添加</p><p>D:\Program Files\graphviz\bin;</p><p><br></p>

### 3、验证安装和设置

<p>进入windows命令行界面，输入dot -version，若显示graphviz相关信息即为安装和配置成功</p> 


eclipse下怎么使用呢 

eclipse使用下首先得安装插件 
<p><span style="font-family: 微软雅黑;">打开 Eclipse，Help-&gt;install new software...。填入相应的 URL：</span><a href="http://plantuml.sourceforge.net/updatesite" _href="http://plantuml.sourceforge.net/updatesite" style="font-family: Arial; color: rgb(0, 0, 0); text-decoration-line: underline;">http://plantuml.sourceforge.net/updatesite/</a></p><p><span style="font-family: 微软雅黑;"></span>搜索出来后安装即可，然后在window-&gt;show view下搜索出PlantUML然后在eclipse中显示相应的面板</p> 

那它有什么语法呢 

它的语法很简单，下面就介绍下其语法以及平时开发常用的数据建模，也就是对象图 

<p>1、首先在任意工程下，新建一个后缀名为uml的文件</p><p>2、该uml文件具有如下语法，先看个简单例子</p> 

```
@startuml



class jd_doctor{

-id:String 

-skills:String

}



note left

这是个备注

end note



@enduml
```

https://www.ibm.com/developerworks/cn/opensource/os-cn-ecl-plantuml/


http://www.135editor.com/  

http://plantuml.com/object-diagram

http://bj.96weixin.com/

##### demo
![建模工具plantuml介绍](source/_posts/devops/uml/建模工具plantuml介绍/建模工具plantuml介绍-1.png)


##### 待学习
1. PlantUML画类图(一) 类与类之间的关系
https://blog.csdn.net/u_ranfa/article/details/89646218

2. Java 类与类之间的关系
https://blog.csdn.net/u_ranfa/article/details/89645757