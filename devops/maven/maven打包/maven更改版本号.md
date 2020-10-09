---
title: maven更改版本号
tags: []
date: 2020-10-01 23:17:50
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1、pom添加versions-maven-plugin插件

```java
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>versions-maven-plugin</artifactId>
            <configuration>
                <generateBackupPoms>false</generateBackupPoms>
            </configuration>
        </plugin>
    </plugins>
</build>

```

```java

```
[]()

#### 2、设置新版本号
注意要在父项目下执行，子项目才会一起更改版本号，不是这个项目的子项目的不会更改
```java
mvn versions:set -DnewVersion=2.1.0
```

```java

```
[]()

#### 3、更新所有子 Module 的版本



```java
mvn versions:update-child-modules


```
#### 4、更新顶级项目的parent版本
在使用Spring Boot的多Module项目时，我们可能需要更新项目所依赖的Spring Boot版本。我们可以使用如下命令来进行更新。

```java
mvn versions:update-parent

```
参考：
[https://blog.csdn.net/u012921921/article/details/107557880](https://blog.csdn.net/u012921921/article/details/107557880)


```




1. 
2. 
3. 
![]()