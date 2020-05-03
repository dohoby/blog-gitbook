---
title: idea插件
tags: [idea插件]
date: 2019-07-26 19:24:27
categories: idea
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 快速通过mapper跳转到xml文件 free-idea-mybatis

![jenkins](idea插件/idea插件-1.png)

官网：https://github.com/wuzhizhan/free-idea-mybatis


#### 点击快速查看调用处和被调用处，快速切换 mybatis-lite

注意： 此功能默认打开(可能会与Mybatis Plugin / free mybatis
plugin等插件冲突)，需要关闭的同学，请在IDEA->File菜单->Setting菜单->Other
Setting菜单->Mybatis菜单->导航开关 关闭

使用方法：如下图，按住ctrl健，点击方法名，会显示跳转到xml文件还是impl实训类，自己选择

![jenkins](idea插件/idea插件-2.png)


官网：https://github.com/mustfun/mybatis-lite

#### 常用插件

###### Grep console
自定义日志颜色，idea控制台可以彩色显示各种级别的log，安装完成后，在console中右键就能打开。

settings->other setting


###### MyBatis Log Plugin
直接将Mybatis执行的sql脚本显示出来，无需处理，可以直接复制出来执行的

Tools -- >  Mybatis Log Plugin  打开其日志框，注意其转换的SQL不是输出到IDE的控制台!!!

###### String Manipulation
强大的字符串转换工具。使用快捷键，Alt+m。 或者点击右键


###### CodeGlance 
CodeGlance是一款代码编辑区缩略图插件，可以快速定位代码，使用起来比拖动滚动条方便多了

###### Maven Helper 
分析依赖冲突插件
打开项目中的pom文件，在底部会显示一个“Dependency Analyzer”,

###### Rainbow Brackets
彩虹颜色的括号  在黑色主题下看的比较清楚舒服，白色主题下看的很不明显，看自己选择了，除了看着舒服，也有助于

帮助区分前后括号对应关系。

###### GenerateAllSetter
一键调用一个对象的所有set方法并且赋予默认值
在对象字段多的时候非常方便，在做项目时，每层都有各自的实体对象需要相互转换，但是考虑BeanUtil.copyProperties()等这些工具的弊端，有些地方就需要手动的赋值时，有这个插件就会很方便，创建完对象后在变量名上面按Alt+Enter就会出来
generate all setter选项。
https://github.com/gejun123456/intellij-generateAllSetMethod
https://blog.csdn.net/qq_38225558/article/details/88388841
注意：若alter+enter没出现菜单，则看看对象里面有没set方法，必须有set方法才能出现菜单
看：https://github.com/gejun123456/intellij-generateAllSetMethod/issues/32


###### Lombok
代码注解插件

###### Key promoter X
快捷键提示工具
https://github.com/halirutan/IntelliJ-Key-Promoter-X

###### GsonFormat  jsonFormat
 JSON转领域对象工具
 
###### Restfultookit
一套 RESTful 服务开发辅助工具集。

    1.根据 URL 直接跳转到对应的方法定义 ( or Ctrl Alt N );
    2.提供了一个 Services tree 的显示窗口;
    3.一个简单的 http 请求工具;
    4.在请求方法上添加了有用功能: 复制生成 URL;,复制方法参数...
    5.其他功能: java 类上添加 Convert to JSON 功能，格式化 json 数据 ( Windows: Ctrl + Enter; Mac: Command + Enter )。


###### CamelCase

将不是驼峰格式的名称，快速转成驼峰格式，安装好后，选中要修改的名称，按快捷键shift+alt+u。

###### Mybatis plugin
 可以在mapper接口中和mapper的xml文件中来回跳转，就想接口跳到实现类那样简单。
 
###### Translation
中英文翻译工具


###### CodeMaker
代码生成工具
https://github.com/x-hansong/CodeMaker

###### Iedis
Redis可视化


###### Alibaba Java Coding Guidelines

###### K8s工具：Kubernetes

###### SonarLint

###### FindBugs-IDEA

###### CheckStyle-IDEA

###### mybatis-generator
https://gitee.com/rohou/mybatis-generator


