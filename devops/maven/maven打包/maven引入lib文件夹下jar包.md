---
title: maven引入lib文件夹下jar包
tags: [maven]
date: 2020-09-08 11:57:18
categories: maven
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### pom文件添加依赖
其中${pom.basedir}是指根目录，将外部jar包添加到根目录下的lib文件夹下  
pom文件添加依赖  
```
        <dependency>
            <groupId>pentaho-kettle</groupId>
            <artifactId>kettle-jdbc</artifactId>
            <version>5.0.0</version>
            <scope>system</scope>
            <systemPath>${pom.basedir}/lib/kettle-jdbc-5.0.0.jar</systemPath>
        </dependency>
```



#### 打包时或idea中启动Tomcat时发现没添加lib文件夹下的jar包解决办法

springboot下
```
<plugins>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <includeSystemScope>true</includeSystemScope>
        </configuration>
    </plugin>
</plugins>
```
[https://www.cnblogs.com/xiang--liu/p/11451521.html](https://www.cnblogs.com/xiang--liu/p/11451521.html)

旧的打war包情况下

```
<!--设置maven-war-plugins插件，否则外部依赖无法打进war包-->
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-war-plugin</artifactId>
  <configuration>
    <webResources>
              <resource>
                      <directory>${project.basedir}/src/lib</directory>
                      <targetPath>WEB-INF/lib/</targetPath>
                      <includes>
                          <include>**/*.jar</include>
                      </includes>
              </resource>
    </webResources>
  </configuration>
</plugin> 
```

实际案例：
```
		<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.1.1</version>
				<configuration>
					<warName>fzsiotcard</warName>
					<webResources>
						<resource>
							<filtering>true</filtering>
							<!-- 这里是刚刚创建的目录 -->
							<directory>src/main/profile</directory>
							<!-- 目标目录为WEB-INF -->
							<targetPath>WEB-INF</targetPath>
							<!-- <directory>src/main/webapp</directory> -->
							<includes>
								<include>**/web.xml</include>
							</includes>
						</resource>
						<resource>
							<directory>${pom.basedir}/lib</directory>
							<targetPath>WEB-INF/lib/</targetPath>
							<includes>
								<include>**/*.jar</include>
							</includes>
						</resource>
					</webResources>
					<warSourceDirectory>src/main/webapp</warSourceDirectory>
					<webXml>src/main/webapp/WEB-INF/web.xml</webXml>
					<failOnMissingWebXml>false</failOnMissingWebXml>
				</configuration>
			</plugin>
```

[]()