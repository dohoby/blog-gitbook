---
title: docker实战1
date: 2018-06-06 13:35:37
tags: [docker,devOps]
devops: docker
categories: docker
---

** {{ title }} ** <Excerpt in index | 首页摘要>
<!-- more -->


#### 运行docker


```
cd /opt/zch
docker build . --tag="tobe/pc-admin-web:v1.0.0"
docker run -d -p 9096:9096 -v /usr/local/logs/:/opt/ --name="pc-admin-web-1.0.0" --net=host tobe/pc-admin-web:v1.0.0



firewall-cmd --zone=public --add-port=9096/tcp --permanent
firewall-cmd --reload

docker ps


```




#### 拉取镜像

```
命令格式为：
docker pull [选项] [Docker Registry地址]<仓库名>:<标签>
Docker Registry地址：地址的格式一般是 <域名/IP>[:端口号]。默认地址是 Docker Hub。
例如：
docker pull ubuntu:14.04


公司的:
docker pull 192.168.1.1/tobe/java:8-jre
```

#### 镜像构建

```
docker build 命令进行镜像构建。其格式为：
docker build [选项] <上下文路径/URL/->

$ docker build -t nginx:v3 .

在这里我们指定了最终镜像的名称 -t nginx:v3 ，构建成功后，我们可以像之前运行
nginx:v2 那样来运行这个镜像，其结果会和 nginx:v2 一样。
```

#### 镜像运行

```
docker run --name web2 -d -p 81:80 nginx:v2
```
这里我们命名为新的服务为 web2 ，并且映射到 81 端口。如果是 Docker for Mac/Windows
或 Linux 桌面的话，我们就可以直接访问 http://localhost:81 看到结果，其内容应该和之前修
改后的 webserver 一样。


```
docker run --name webserver -d -p 80:80 nginx
```
这条命令会用 nginx 镜像启动一个容器，命名为 webserver ，并且映射了 80 端口，这样我 们可以用浏览器去访问这个 nginx 服务器。



#### Docker重命名镜像名称和TAG

```
# docker tag IMAGEID(镜像id) REPOSITORY:TAG（仓库：标签）
```

```
[root@localhost hoby]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
dohoby/kubernetes   3.1.12              0c14773001c8        16 minutes ago      193MB
[root@localhost hoby]# docker tag 0c14773001c8 k8s.gcr.io/etcd-amd64:3.1.12
[root@localhost hoby]# docker images
REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
dohoby/kubernetes       3.1.12              0c14773001c8        16 minutes ago      193MB
k8s.gcr.io/etcd-amd64   3.1.12              0c14773001c8        16 minutes ago      193MB

```
