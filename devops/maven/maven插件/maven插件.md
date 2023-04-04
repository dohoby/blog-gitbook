---
title: maven插件
tags: []
date: 2022-01-07 10:27:21
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### Maven中maven-source-plugin,maven-javadoc-plugin插件的使用

把项目通过maven生产源码包和文档包并发布到自己的私服上，经过查看mavne官网发现有两个maven插件可以做到这些工作，一个是maven-source-plugin，另一个是maven-javadoc-plugin

一：首先在你的项目的pom.xml文件中加入如下配置：

```java
<!-- 生成javadoc文档包的插件 -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-javadoc-plugin</artifactId>
				<version>2.10.2</version>
				<configuration>
					<aggregate>true</aggregate>
				</configuration>
				<executions>
					<execution>
						<id>attach-javadocs</id>
						<goals>
							<goal>jar</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
			<!-- 生成sources源码包的插件 -->
			<plugin>
				<artifactId>maven-source-plugin</artifactId>
				<version>2.4</version>
				<configuration>
					<attach>true</attach>
				</configuration>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>jar-no-fork</goal>
						</goals>
					</execution>
				</executions>
			</plugin>


```

```java

```
![]()

#### 
```java

```

```java

```
![]()

#### 


```java

```

```java

```
![]()
```




1. 
2. 
3. 
![]()