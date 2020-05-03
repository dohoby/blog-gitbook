---
title: docker实战5-曲线拉取国外镜像
date: 2018-06-06 13:35:37
tags: [docker,devOps]
devops: docker
categories: docker
---


## 创建镜像仓库

安装k8s需要很多镜像，这些镜像，若翻墙不了或者公司禁止上谷歌的镜像仓库，就没法获取，这里就需要曲线拉取镜像了，

<!-- more -->

如

```
[root@k8s-node-2 flannel]# docker images
REPOSITORY                                 TAG                 IMAGE ID            CREATED             SIZE
k8s.gcr.io/kube-proxy-amd64                v1.10.3             4261d315109d        2 weeks ago         97.1 MB
k8s.gcr.io/kube-apiserver-amd64            v1.10.3             e03746fe22c3        2 weeks ago         225 MB
k8s.gcr.io/kube-scheduler-amd64            v1.10.3             353b8f1d102e        2 weeks ago         50.4 MB
k8s.gcr.io/kube-controller-manager-amd64   v1.10.3             40c8d10b2d11        2 weeks ago         148 MB
k8s.gcr.io/etcd-amd64                      3.1.12              52920ad46f5b        2 months ago        193 MB
quay.io/coreos/flannel                     v0.10.0-amd64       f0fad859c909        4 months ago        44.6 MB
k8s.gcr.io/pause-amd64                     3.1                 da86e6ba6ca1        5 months ago        742 kB
docker.io/kubernetes/pause                 latest              f9d5de079539        3 years ago         240 kB
gcr.io/google_containers/pause-amd64       3.0                 f9d5de079539        3 years ago         240 kB

```



根据上面所需要的镜像，制作Dockerfile，并且上传到github仓库，参考我的https://github.com/dohoby/kubernetes




## 制作镜像


https://hub.docker.com
登录此网站，参考：https://blog.csdn.net/sjyu_ustc/article/details/79990858
构建镜像




## 拉取镜像

新建拉取镜像脚本

```
#!/bin/bash  
images=(kube-apiserver-amd64:v1.10.3 kube-controller-manager-amd64:v1.10.3 kube-proxy-amd64:v1.10.3 kube-scheduler-amd64:v1.10.3 pause-amd64:3.1 etcd-amd64:3.1.12)
for image in ${images[@]} ; do  
  docker pull dohoby/$image  
  docker tag dohoby/$image k8s.gcr.io/$image  
  docker rmi dohoby/$image
done 
```


拉取结果：

```
[root@localhost hoby]# docker images
REPOSITORY                                 TAG                 IMAGE ID            CREATED             SIZE
k8s.gcr.io/etcd-amd64                      3.1.12              a63d07bcb093        29 minutes ago      193MB
k8s.gcr.io/pause-amd64                     3.1                 623c4d30c6b4        32 minutes ago      742kB
k8s.gcr.io/kube-scheduler-amd64            v1.10.3             788f5b9849cd        35 minutes ago      50.4MB
k8s.gcr.io/kube-proxy-amd64                v1.10.3             0dce35f14844        39 minutes ago      97.1MB
k8s.gcr.io/kube-controller-manager-amd64   v1.10.3             298bb46456e1        43 minutes ago      148MB
k8s.gcr.io/kube-apiserver-amd64            v1.10.3             d866c9b24ddd        About an hour ago   225MB

```



