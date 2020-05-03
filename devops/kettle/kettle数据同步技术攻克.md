---
title: kettle数据同步技术攻克
date: 2017-09-19 11:35:37
tags: [kettle,devOps]
devops: kettle
categories: [kettle]
---
** {{ title }} ** <Excerpt in index | 首页摘要>

#### 项目需求
通过在Linux下命令行启动kettle作业实现从sql server到mysql的同步，其中mysql库中完全没有任何表，需要创建

<!-- more -->

#### 技术攻克
##### 1、数据库动态设置
kettle数据库同步必须指定从哪个库同步到哪个库，在开发时每个作业和转换中都要设置数据库，而在测试环境和生产环境中这些库都不一样，所以有必要进行数据库动态设置  

推荐阅读：
http://blog.itpub.net/27120361/viewspace-1412216/

实战：http://note.youdao.com/noteshare?id=14eb2648fe33ad61c237e30768ecb1d3


##### 2、命令行启动作业（包括日志配置、定时）  




##### 3、动态同步所有表  
可以动态同步所有表，但是有问题  
1、如果2张表的定义不一样，数据有问题则会导致报错，进而同步失败 ，比如mysql中有个字段设置不能未空，但sql server中的数据有空则报错   
2、第一次同步过去没问题，第二次则发生错误，原因是使用了表输出，但是表输出貌似是不能更新的，    

推荐阅读：http://ainidehsj.iteye.com/blog/1735434  


##### 4、mysql数据库创建(与kettle无关) 
通过脚本或者Java代码实现  

http://www.cnblogs.com/avivaye/p/4938592.html  


##### 5、异常处理  
控制到每张表的同步，尽量减少同步错误，而且一有错误立刻邮件通知，避免错误  


##### 6、乱码  
http://duguyiren3476.iteye.com/blog/1345358

##### 7、要预防的问题
http://pentahochina.com:8080/biforum/topic-29-1.html

#### 感悟
需要用心，静下心去看错误，不要急，不要害怕，要相信能实现这功能，除非用尽办法还是解决不了  





