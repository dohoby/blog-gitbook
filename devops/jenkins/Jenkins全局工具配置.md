---
title: Jenkins全局工具配置
date: 2019-05-20 16:35:37
tags: [jenkins,devOps]
categories: jenkins
---
** {{ title }} ** <Excerpt in index | 首页摘要>

因为是在下面配置了全局工具，所以Jenkins能识别java命令，git命令和mvn命令，但是没有配置node相关的npm命令，
所以Jenkins跑node项目时会报错，但是不知道为什么docker的不用配置就可以
<!-- more -->

![jenkins](Jenkins全局工具配置/jenkins-global-1.png)

![jenkins](Jenkins全局工具配置/jenkins-global-2.png)