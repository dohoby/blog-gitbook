---
title: docker实战4-挂载目录
date: 2018-06-06 13:35:37
tags: [docker,devOps]
devops: docker
categories: docker
---

** {{ title }} ** <Excerpt in index | 首页摘要>
<!-- more -->

#### 查看容器的详细信息  


```
docker inspect 容器id
```


```
[root@localhost zch]# docker inspect 65db9b511573
[
    {
        "Id": "65db9b511573294c25d5ab42e7f9f14c44b12d562037d111b6a385d43c2f0fa1",
        "Created": "2017-11-06T01:32:39.592582918Z",
        "Path": "java",
        "Args": [
            "-Xms500m",
            "-Xmx1024m",
            "-jar",
            "/usr/local/tobe/pc/pc-admin-web.jar"
        ],
        "State": {
            "Status": "running",
            "Running": true,
            "Paused": false,
            "Restarting": false,
            "OOMKilled": false,
            "Dead": false,
            "Pid": 4042,
            "ExitCode": 0,
            "Error": "",
            "StartedAt": "2017-11-06T01:32:39.729399639Z",
            "FinishedAt": "0001-01-01T00:00:00Z"
        },
        "Image": "sha256:d7f0891abbd7a77169fa646ab1da8778ee15643a64322b982bf512b01fd080d0",
        "ResolvConfPath": "/var/lib/docker/containers/65db9b511573294c25d5ab42e7f9f14c44b12d562037d111b6a385d43c2f0fa1/resolv.conf",
        "HostnamePath": "/var/lib/docker/containers/65db9b511573294c25d5ab42e7f9f14c44b12d562037d111b6a385d43c2f0fa1/hostname",
        "HostsPath": "/var/lib/docker/containers/65db9b511573294c25d5ab42e7f9f14c44b12d562037d111b6a385d43c2f0fa1/hosts",
        "LogPath": "/var/lib/docker/containers/65db9b511573294c25d5ab42e7f9f14c44b12d562037d111b6a385d43c2f0fa1/65db9b511573294c25d5ab42e7f9f14c44b12d562037d111b6a385d43c2f0fa1-json.log",
        "Name": "/pc-admin-web-1.0.0",
        "RestartCount": 0,
        "Driver": "overlay",
        "Platform": "linux",
        "MountLabel": "",
        "ProcessLabel": "",
        "AppArmorProfile": "",
        "ExecIDs": null,
        "HostConfig": {
            "Binds": [
                "/usr/local/logs/:/opt/"
            ],
            "ContainerIDFile": "",
            "LogConfig": {
                "Type": "json-file",
                "Config": {}
            },
            "NetworkMode": "host",
            "PortBindings": {
                "8080/tcp": [
                    {
                        "HostIp": "",
                        "HostPort": "8080"
                    }
                ]
            },
            "RestartPolicy": {
                "Name": "no",
                "MaximumRetryCount": 0
            },
            "AutoRemove": false,
            "VolumeDriver": "",
            "VolumesFrom": null,
            "CapAdd": null,
            "CapDrop": null,
            "Dns": [],
            "DnsOptions": [],
            "DnsSearch": [],
            "ExtraHosts": null,
            "GroupAdd": null,
            "IpcMode": "",
            "Cgroup": "",
            "Links": null,
            "OomScoreAdj": 0,
            "PidMode": "",
            "Privileged": false,
            "PublishAllPorts": false,
            "ReadonlyRootfs": false,
            "SecurityOpt": null,
            "UTSMode": "",
            "UsernsMode": "",
            "ShmSize": 67108864,
            "Runtime": "runc",
            "ConsoleSize": [
                0,
                0
            ],
            "Isolation": "",
            "CpuShares": 0,
            "Memory": 0,
            "NanoCpus": 0,
            "CgroupParent": "",
            "BlkioWeight": 0,
            "BlkioWeightDevice": [],
            "BlkioDeviceReadBps": null,
            "BlkioDeviceWriteBps": null,
            "BlkioDeviceReadIOps": null,
            "BlkioDeviceWriteIOps": null,
            "CpuPeriod": 0,
            "CpuQuota": 0,
            "CpuRealtimePeriod": 0,
            "CpuRealtimeRuntime": 0,
            "CpusetCpus": "",
            "CpusetMems": "",
            "Devices": [],
            "DeviceCgroupRules": null,
            "DiskQuota": 0,
            "KernelMemory": 0,
            "MemoryReservation": 0,
            "MemorySwap": 0,
            "MemorySwappiness": null,
            "OomKillDisable": false,
            "PidsLimit": 0,
            "Ulimits": null,
            "CpuCount": 0,
            "CpuPercent": 0,
            "IOMaximumIOps": 0,
            "IOMaximumBandwidth": 0
        },
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay/259ae4941ced45fa1397474685a29b680143b2017c59079aa4cc79360f2974f2/root",
                "MergedDir": "/var/lib/docker/overlay/f6fc30704f20c0538c159a9e388ddbde79696b0c841d2cf6ee3a57ec92b4210a/merged",
                "UpperDir": "/var/lib/docker/overlay/f6fc30704f20c0538c159a9e388ddbde79696b0c841d2cf6ee3a57ec92b4210a/upper",
                "WorkDir": "/var/lib/docker/overlay/f6fc30704f20c0538c159a9e388ddbde79696b0c841d2cf6ee3a57ec92b4210a/work"
            },
            "Name": "overlay"
        },
        "Mounts": [
            {
                "Type": "bind",
                "Source": "/usr/local/logs",
                "Destination": "/opt",
                "Mode": "",
                "RW": true,
                "Propagation": "rprivate"
            }
        ],
        "Config": {
            "Hostname": "localhost.localdomain",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "ExposedPorts": {
                "8080/tcp": {}
            },
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin",
                "LANG=C.UTF-8",
                "JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre",
                "JAVA_VERSION=8u111",
                "JAVA_DEBIAN_VERSION=8u111-b14-2~bpo8+1",
                "CA_CERTIFICATES_JAVA_VERSION=20140324"
            ],
            "Cmd": [
                "java",
                "-Xms500m",
                "-Xmx1024m",
                "-jar",
                "/usr/local/tobe/pc/pc-admin-web.jar"
            ],
            "ArgsEscaped": true,
            "Image": "tobe/pc-admin-web:v1.0.0",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": {}
        },
        "NetworkSettings": {
            "Bridge": "",
            "SandboxID": "8ec994d4adf3a820ccfff026d220506e4fe28a1542f72321eac09319da4ec386",
            "HairpinMode": false,
            "LinkLocalIPv6Address": "",
            "LinkLocalIPv6PrefixLen": 0,
            "Ports": {},
            "SandboxKey": "/var/run/docker/netns/default",
            "SecondaryIPAddresses": null,
            "SecondaryIPv6Addresses": null,
            "EndpointID": "",
            "Gateway": "",
            "GlobalIPv6Address": "",
            "GlobalIPv6PrefixLen": 0,
            "IPAddress": "",
            "IPPrefixLen": 0,
            "IPv6Gateway": "",
            "MacAddress": "",
            "Networks": {
                "host": {
                    "IPAMConfig": null,
                    "Links": null,
                    "Aliases": null,
                    "NetworkID": "dac528ad522ee4f3d29f73d3c8e25bf85891cedfd4d21e8a9f2cd6d573268b23",
                    "EndpointID": "d8545b90b5730af9e28649144d3774a23a0b1d7d0a6f9b3fb14aab66b75bf795",
                    "Gateway": "",
                    "IPAddress": "",
                    "IPPrefixLen": 0,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "",
                    "DriverOpts": null
                }
            }
        }
    }
]
[root@localhost zch]# 

```

#### 进入容器
-it 命令是以交互方式进入容器  
```
[root@localhost zch]# docker run -it -p 8080:8080 -v /usr/local/logs/:/opt/ --name="pc-admin-web-1.0.0" --net=host tobe/pc-admin-web:v1.0.0 /bin/bash

```

#### 列出容器的目录

```
root@localhost:/# ls
bin  boot  dev	etc  home  lib	lib64  media  mnt  opt	proc  root  run  sbin  srv  sys  tmp  usr  var
root@localhost:/# 

```

#### 查看Dockerfile文件复制进去的jar包
Dockerfile文件中有句：
```
ADD pc-admin-web.jar /usr/local/tobe/pc/
```

查看
```
root@localhost:/# ls /usr/local/tobe/pc/
pc-admin-web.jar
```


#### 检查挂载是否成功
docker run命令中有个参数，冒号前面是宿主主机的目录，冒号后面是容器的目录，是将宿主主机的目录挂载到容器的/opt/目录下，
```
-v /usr/local/logs/:/opt/
```
检查，在容器的/opt/目录下新建个a文件，然后到宿主主机上发现有个a文件,同样，在宿主主机新建个b文件，然后在容器里发现也有b文件  




#### 后台进程运行容器，如何进入容器

```
 docker exec -i -t ea0928cfcad2 /bin/bash
```

#### 从容器拷贝文件到宿主机

拷贝方式为：

docker cp 容器名：容器中要拷贝的文件名及其路径 要拷贝到宿主机里面对应的路径
```
docker cp sp-comm:/logs/192.168.1.1mon/192.168.1.1mon.log .

docker cp sp-comm:/logs/192.168.1.1mon/20180907/192.168.1.1mon.0.log.zip .

```

参考：https://blog.csdn.net/dongdong9223/article/details/71425077

