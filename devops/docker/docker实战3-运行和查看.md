---
title: docker实战3-运行和查看
date: 2018-06-06 13:35:37
tags: [docker,devOps]
devops: docker
categories: docker
---

** {{ title }} ** <Excerpt in index | 首页摘要>
<!-- more -->

#### Dockerfile


```
FROM java:8-jre
MAINTAINER tobe <tobe@tobe.com>

ADD pc-admin-web.jar /usr/local/tobe/pc/
CMD ["java", "-Xms500m", "-Xmx1024m", "-jar", "/usr/local/tobe/pc/pc-admin-web.jar"]

EXPOSE 8080
```

#### 运行docker步骤
```
cd /opt/zch
docker build . --tag="tobe/pc-admin-web:v1.0.0"
docker run -d -p 8080:8080 -v /usr/local/logs/:/opt/ --name="pc-admin-web-1.0.0" --net=host tobe/pc-admin-web:v1.0.0

注：经实战，发现必须配置成8080:8080，而且Dockfile文件里也是8080才能访问
```
问题1：-v是挂载宿主主机的目录，实战发现那个目录下没有日志文件，
问题2：删除容器和重新docker run都发现项目接口没有更新，是不是只能重新build个镜像


docker run -d -p 8080:8080 -v /usr/local/logs/:/var/lib/docker/containers/ --name="pc-admin-web-1.0.0" --net=host tobe/pc-admin-web:v1.0.0  

docker run -d -p 8080:8080 -v /usr/local/logs/:/opt/ --name="pc-admin-web-1.0.0" --net=host tobe/pc-admin-web:v1.0.0  

##### 开放防火墙
```
firewall-cmd --zone=public --add-port=9096/tcp --permanent
firewall-cmd --reload
```

##### 查看容器
```
docker ps -a
```

##### 查看日志
```
docker logs -f 容器名，用docker ps查看，比如
docker logs -f pc-admin-web-1.0.0
docker logs -f fullfilment --tail=500
```

##### 访问：
```
curl -v http://172.16.17.250:8080/swagger-ui.html
```

##### 删除容器
```
删除容器
docker rmi 容器id

停止所有容器（删除容器才能删除images）：
docker stop $(docker ps -a -q)

删除所有容器：
docker rm $(docker ps -a -q)
```

##### 删除镜像
```
删除images
docker rmi <image id>

删除untagged images，也就是那些id为<None>的image
docker rmi $(docker images | grep "^<none>" | awk "{print $3}")

删除全部image
docker rmi $(docker images -q)
```



#### Linux上tobe项目做的操作
```
vi pc-admin-web/src/main/resources/application.yaml 

 cp pc-admin-web/target/pc-admin-web.jar /opt/zch
```


---

下面的是实战，可以不看

#### docker build

```
[root@localhost zch]# pwd
/opt/zch
[root@localhost zch]# docker images
REPOSITORY                      TAG                 IMAGE ID            CREATED             SIZE
hello-world                     latest              05a3bd381fc2        7 weeks ago         1.84kB
192.168.1.1/tobe/java   8-jre               e44d62cf8862        9 months ago        311MB
[root@localhost zch]# docker build . --tag="tobe/pc-admin-web:v1.0.0"
Sending build context to Docker daemon  32.74MB
Step 1/5 : FROM java:8-jre
8-jre: Pulling from library/java
Digest: sha256:b91008e234402fc87e7889d6af1f36b6ece844c05989236d83d1f658a6f329b0
Status: Downloaded newer image for java:8-jre
 ---> e44d62cf8862
Step 2/5 : MAINTAINER tobe <tobe@tobe.com>
 ---> Running in bd1e3b4e96c1
 ---> 77598a1df94a
Removing intermediate container bd1e3b4e96c1
Step 3/5 : ADD pc-admin-web.jar /usr/local/tobe/pc/
 ---> 31ce18cf19af
Step 4/5 : CMD java -Xms500m -Xmx1024m -jar /usr/local/tobe/pc/pc-admin-web.jar
 ---> Running in d68f92bec092
 ---> 75c9a968da39
Removing intermediate container d68f92bec092
Step 5/5 : EXPOSE 9096
 ---> Running in 219782caa18b
 ---> 1bd7a778ba13
Removing intermediate container 219782caa18b
Successfully built 1bd7a778ba13
Successfully tagged tobe/pc-admin-web:v1.0.0
[root@localhost zch]# 
```



看下面的，看到最终构建的是上面运行docker中的

```
docker build . --tag="tobe/pc-admin-web:v1.0.0"
```
这个命令打的tag标签,如果上面命令报错，尝试把逗号放最后  
:分号前面对应REPOSITORY，分号后面对应TAG

```
[root@localhost zch]# docker images
REPOSITORY                      TAG                 IMAGE ID            CREATED             SIZE
tobe/pc-admin-web                v1.0.0              1bd7a778ba13        4 minutes ago       344MB
hello-world                     latest              05a3bd381fc2        7 weeks ago         1.84kB
192.168.1.1/tobe/java   8-jre               e44d62cf8862        9 months ago        311MB
java                            8-jre               e44d62cf8862        9 months ago        311MB
[root@localhost zch]# 
```



#### docker run：

```
[root@localhost zch]# docker run -d -p 9096:9096 -v /usr/local/logs/:/opt/ --name="pc-admin-web-1.0.0" --net=host tobe/pc-admin-web:v1.0.0
f9a08bb82e2650bebed3eb3185a383ec121114ce3ef79ee2166d7a46329ced85
[root@localhost zch]# 
```

经实战，发现必须配置成8080:8080，而且Dockfile文件里也是8080才能访问


```
docker run -d -p 8080:8080 -v /usr/local/logs/:/opt/ --name="pc-admin-web-1.0.0" --net=host tobe/pc-admin-web:v1.0.0
```


```
[root@localhost zch]# docker ps
CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS              PORTS               NAMES
f9a08bb82e26        tobe/pc-admin-web:v1.0.0   "java -Xms500m -Xm..."   2 minutes ago       Up 2 minutes                            pc-admin-web-1.0.0
[root@localhost zch]# 

```



```
[root@localhost zch]# firewall-cmd --zone=public --add-port=9096/tcp --permanent
FirewallD is not running
[root@localhost zch]# firewall-cmd --reload
FirewallD is not running
[root@localhost zch]# 

```






#### 移除容器和镜像

http://www.cnblogs.com/q4486233/p/6482711.html

```
1.停止所有的container，这样才能够删除其中的images：

docker stop $(docker ps -a -q)

如果想要删除所有container的话再加一个指令：

docker rm $(docker ps -a -q)

2.查看当前有些什么images

docker images

3.删除images，通过image的id来指定删除谁

docker rmi <image id>

想要删除untagged images，也就是那些id为<None>的image的话可以用

docker rmi $(docker images | grep "^<none>" | awk "{print $3}")

要删除全部image的话

docker rmi $(docker images -q)
```

实战  

命令必须加-a才会显示容器出来 ，

```
docker ps -a
```
查询出来用下面命令删除某个容器

```
docker rm 容器id
```

```
[root@localhost zch]# docker ps -a
CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS                       PORTS               NAMES
11b8d5b0a241        tobe/pc-admin-web:v1.0.0   "java -Xms500m -Xm..."   2 days ago          Exited (143) 5 minutes ago                       pc-admin-web-1.0.0
[root@localhost zch]# docker rm 11b8d5b0a241
11b8d5b0a241
[root@localhost zch]# docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES

```




#### 查看日志
```
docker logs -f pc-admin-web-1.0.0
```




