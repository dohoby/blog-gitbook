---
title: maven命令行推送jar包
tags: [maven]
date: 2020-09-08 10:37:43
categories: maven
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 1、命令格式：
```
mvn deploy:deploy-file -Dfile=<path-to-file> -DgroupId=<group-id> -DartifactId=<artifact-id> -Dversion=<version> -Dpackaging=jar -Durl=file:./maven-repository/ -DrepositoryId=maven-repository -DupdateReleaseInfo=true    

```



### 2、可以（执行命令在D:\1）
```
 mvn deploy:deploy-file -Dfile=d:\1\kettle-core-7.0.0.0-25.jar -DgroupId=pentaho-kettle -DartifactId=kettle-core -Dversion=7.0.0.0-25 -Dpackaging=jar  -Durl=http://192.168.1.73:8081/repository/maven-releases -DrepositoryId=nexus-releases
 mvn clean deploy:deploy-file -Dfile=d:\1\kettle-core-7.0.0.0-25.jar -DgroupId=pentaho-kettle -DartifactId=kettle-core -Dversion=7.0.0.0-25 -Dpackaging=jar  -Durl=http://192.168.1.73:8081/repository/maven-releases -DrepositoryId=nexus-releases

```

上面命令推送过一次后，想再推送，必须改版本后才能推送，因为没法更新release的版本的，会报下面的错误
![](source/_posts/devops/maven/maven打包/maven推送jar包/maven-1.png)


#### 3、也可以（和上面命令不同的地方就是执行命令和jar包所在位置不一样，执行命令在D:\idea-workspace2\ebaysdk）
    
```
      mvn deploy:deploy-file -Dfile=d:\1\kettle-core-7.0.0.0-25.jar -DgroupId=pentaho-kettle -DartifactId=kettle-core -Dversion=7.0.0.0-25 -Dpackaging=jar  -Durl=http://192.168.1.73:8081/repository/maven-releases -DrepositoryId=nexus-releases
```
 报下面错，不能从本地的版本库位置deploy，必须像上面那样将jar包和pom包复制到其他目录下
![](source/_posts/devops/maven/maven打包/maven推送jar包/maven-2.png)


#### 4、未授权错误分析

```
  mvn deploy:deploy-file -Dfile=d:\1\kettle-dbdialog-7.0.0.0-25.jar -DgroupId=pentaho-kettle -DartifactId=kettle-dbdialog -Dversion=7.0.0.0-25 -Dpackaging=jar  -Durl=http://192.168.1.73:8081/repository/maven-releases -DrepositoryId=nexus-releasess
```

 和2的除了jar包名一样，为什么报下面的错，401未授权
 ![](source/_posts/devops/maven/maven打包/maven推送jar包/maven-3.png)

 
注意是-DrepositoryId=nexus-releases这个后面多了个s,
这个-DrepositoryId的值必须和D:\maven\apache-maven-3.5.0\conf\settings.xml中配置的id一样


#### 5、其他jar包  

```
 mvn deploy:deploy-file -Dfile=d:\1\kettle-dbdialog-7.0.0.0-25.jar -DgroupId=pentaho-kettle -DartifactId=kettle-dbdialog -Dversion=7.0.0.0-25 -Dpackaging=jar  -Durl=http://192.168.1.73:8081/repository/maven-releases -DrepositoryId=nexus-releases
 mvn deploy:deploy-file -Dfile=d:\1\kettle-engine-7.0.0.0-25.jar -DgroupId=pentaho-kettle -DartifactId=kettle-engine -Dversion=7.0.0.0-25 -Dpackaging=jar  -Durl=http://192.168.1.73:8081/repository/maven-releases -DrepositoryId=nexus-releases
 mvn deploy:deploy-file -Dfile=d:\1\kettle-jdbc-5.0.0.jar -DgroupId=pentaho-kettle  -DartifactId=kettle-jdbc -Dversion=5.0.0 -Dpackaging=jar  -Durl=http://192.168.1.73:8081/repository/maven-releases -DrepositoryId=nexus-releases
 mvn deploy:deploy-file -Dfile=d:\1\metastore-7.0.0.0-25.jar -DgroupId=pentaho -DartifactId=metastore -Dversion=7.0.0.0-25 -Dpackaging=jar  -Durl=http://192.168.1.73:8081/repository/maven-releases -DrepositoryId=nexus-releases
```


参考：

http://www.trinea.cn/dev-tools/maven-sonatype-nexus-return-401-which-settings-xml-maven-is-using/  

https://blog.csdn.net/zzb5682119/article/details/54137986    
```
mvn deploy:deploy-file -Dfile=C:/Users/zhangzubin/Desktop/EisAPIForHA-2.1.jar -DgroupId=cn.evun -DartifactId=EisAPIForHA -Dversion=2.0 -Durl=http://218.75.72.114:8081/nexus/content/repositories/releases -DrepositoryId=nexus-release 关于安装第三方jar到Artifact, 从Artifact的官方上看到其实有很多种方法(请看这里),最简单的就是从Archiva的web 页面上找到Upload Artifact这个功能. 我使用的方法是maven的 deploy:deploy-file 命令,这种方法时要注意的是如果你要安装的jar和pom是位于本地repository的目录下,这个命令就会出错 (Cannot deploy artifact from the local repository…), 解决方法:将要安装的jar和pom copy到其它目录再安装,只要不在本地仓库目录都应该可以.
```

https://www.jianshu.com/p/515fdbf92656    



