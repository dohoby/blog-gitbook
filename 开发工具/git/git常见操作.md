---
title: git常见操作
tags: [git]
date: 2020-09-16 20:50:05
categories: git
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 1、新建项目流程

初始化
 ```java
 git init
```
添加文件
```java
 git add .
```
提交
```java
 git commit -am 'init'
```
关联远程git
 ```java
 git remote add origin git@github.com:xxx.git
```
关联远程分支
 ```java
 git branch --set-upstream-to=origin/master master
```
 拉取代码（若远程有代码了）
```java
 git pull
```
 拉取代码(有历史记录，拉取不了则用下面的)
```java
 git pull origin master --allow-unrelated-histories
```
推送
 ```java
 git push
```
推送到远程
 ```java
 git push -u origin master
 ```


### 2、打标签和发release版本

```java

```
[]()

、
```java

```

```java

```
[]()

#### 3、


```java

```

```java

```
[]()
```




1. 
2. 
3. 
![]()