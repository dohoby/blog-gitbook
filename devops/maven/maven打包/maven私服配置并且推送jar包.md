---
title: maven私服配置并且推送jar包
tags: []
date: 2020-12-23 14:48:51
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### maven settings.xml修改

C:\Users\hoby\.m2/settings.xml

```java
    <!--设置 Nexus 认证信息-->
    <servers>
		<server>
		   <id>thirdparty</id>
		   <username>admin</username>
		   <password>Admin!@#</password>
		</server>
		<server>
		  <id>releases</id>
		   <username>admin</username>
		   <password>Admin!@#</password>
		</server>
		<server>
		  <id>snapshots</id>
		   <username>admin</username>
		   <password>Admin!@#</password>
		</server>
    </servers>
```

#### 在需要上传jar包到私服的模块的父模块(父模块就可以，不用子模块)下添加下面的

```java
   <distributionManagement>
        <repository>
            <id>releases</id>
            <url>http://10.20.150.115:8081/nexus/content/repositories/releases</url>
        </repository>
        <snapshotRepository>
            <id>snapshots</id>
            <url>http://10.20.150.115:8081/nexus/content/repositories/snapshots</url>
        </snapshotRepository>
    </distributionManagement>
```

其中distributionManagement在project下


#### 点击deploy上传

![](maven私服配置并且推送jar包/maven-jar-1.png)


