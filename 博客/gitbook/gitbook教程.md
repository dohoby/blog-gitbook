---
title: gitbook教程
tags: [gitbook]
date: 2019-07-18 19:05:08
categories: gitbook
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 基本步骤


```
#1、安装gitbook客户端
npm install gitbook-cli -g 


# 2、初始化一个仓库，会生成README.md和SUMMARY.md文件
gitbook init 
 
# 3、新建book.json，然后执行下面命令会安装里面的插件 
gitbook install

# 4、启动服务器
gitbook serve
gitbook serve -p 8080

生产上去掉热部署，要不首页加载会很慢
gitbook serve --no-live
 
```
其他命令
```
gitbook -V

# 生成html
gitbook build
 
```

### book.json文件


```
{
  

  "title": "Webpack 中文指南",
  "description": "Webpack 是当下最热门的前端资源模块化管理和打包工具，本书大部分内容翻译自 Webpack 官网。",
  "language": "zh",
  "plugins": [
    "disqus",
    "github",
    "editlink",
    "prism",
    "-highlight",
    "baidu",
    "splitter",
    "sitemap",
    "summary"
  ],
  "pluginsConfig": {
    "disqus": {
      "shortName": "webpack-handbook"
    },
    "github": {
      "url": "https://github.com/zhaoda/webpack-handbook"
    },
    "editlink": {
      "base": "https://github.com/zhaoda/webpack-handbook/blob/master/content",
      "label": "编辑本页"
    },
    "baidu": {
        "token": "a9787f0ab45d5e237bab522431d0a7ec"
    },
    "sitemap": {
        "hostname": "http://zhaoda.net/"
    }
  }
}
```


### 自动生成summary目录

安装插件
```
npm install -g gitbook-summary
```


```
 book sm -c _posts
```

```
 book sm -c source/_posts 
```
-c参数-c，即--catalog，是指全部要显示的目录，表示要生成html的文件夹  
缺陷：source/_posts表示该目录是需要生成html的目录,貌似这样没用，只能到source目录,不能具体到_posts目录

-i 是参数 --ignores的缩写形式，意思是忽略该参数提供的目录  
-i 用法像下面那样,有效果,注意是文件夹的名字(注意大小写)，

```
 book sm -c source/_posts -i about,tags,friends,categories
```
在book.json里添加也可以，如下：
```
	"ignores": ["friends","tags","categories","about"],
```
缺陷：若要忽略的文件夹，存在要生成的文件夹_posts的子文件夹。这个子文件夹也无法生成，这种应该改成指定路径下的文件夹的，  
例如devops既存在source下，又存在_posts下，我只想忽略source下的devops，它却连_posts下的也忽略了


注意：在哪个目录下执行book命令就生成SUMMARY.md在哪个目录下，测试发现在content目录下(非根目录下)生成的文件开头没法
取到book.json定义的title


参考：https://blog.csdn.net/ds19991999/article/details/81275458


### 生成pdf文件

```
gitbook pdf . book.pdf
```
### 添加章节编号

在pluginsConfig加上,注意不是在plugin里加


```

"theme-default": {
        "showLevel": true
    }
    
```
https://www.crifan.com/gitbook_add_chapter_index_number/


### 修改indroduction首页指定为另一个文件

```
	"structure": {
		"readme": "SUMMARY.md"
	}
	
```
### 修改根目录，即打包生成html目录

```
		"root": "./content",	
```
### 部署到coding

特别注意  
1、自定义的gitbbook发布时不能在根目录下新建scripts文件夹，否则会影响hexo的生成和发布
2、自定义的deploy-gh-pages.js会改变当前git仓库的分支，特别注意这个

新建gitbook_deploy文件夹，然后新建deploy-gh-pages.js文件，文件内容大概如下   
注意修改repo为你自己的git仓库,_book是你生成的gitbook的html输出目录

```

'use strict';

var ghpages = require('gh-pages');

main();

function main() {
	  const defaults = {
    dest: '.',
    add: false,
    git: 'git',
    depth: 1,
    dotfiles: false,
    branch: 'gh-pages',
    src: '**/*',
    only: '.',
    push: true,
    message: 'Updates',
    silent: false,
	repo: '你的git仓库地址'
  };
    ghpages.publish('./_book',defaults, console.error.bind(console));
}

```
参考：https://github.com/tschaub/gh-pages


### 自动化
特别注意不能ignore掉content文件夹，否则命令找不到文件的
```
	 npm run gautopublish
		 	
```
nodejs拷贝文件  
https://www.cnblogs.com/coding4/p/7495968.html


### 发布到gitbook

http://www.chengweiyang.cn/gitbook/gitbook.com/newbook.html


### 统计插件

```
{
    "plugins": [
        "pageview-count"
    ]
}
```

### 个性化配置

http://www.chengweiyang.cn/gitbook/customize/book.json.html


### 修改样式
https://blog.tedxiong.com/how_to_remove_Published_with_GitBook_in_GitBook.html

### 其他

插件

https://blog.csdn.net/weixin_37865166/article/details/91899788


https://yanhaijing.com/tool/2015/09/12/my-gitbook-note/




在book.json中定义，这样README.md就可以用作项目的简介
```
{
    "structure": {
        "readme": "SUMMARY.md"
    }
}
```
注意：
1. 这个一般和"root": ".deploy/content",这个一起用
2. 还有注意readme这里只能定义文件，比如SUMMARY.md,而不能带目录如.deploy/content/SUMMARY.md,
而root则可以定义成目录如.deploy/content
```
	"root": ".deploy/content",
{
    "structure": {
        "readme": "SUMMARY.md"
    }
}
```
3. 上面表示根目录在.deploy/content目录下,描述文件为SUMMARY.md，
这时gitbook build编译命令为
```
gitbook build . .deploy/_book  --no-live
```
1. 第一个.表示在项目的根目录下(比如lqx-gitbook-deploy),注意这里必须用. 不要用.deploy/content,否则gitbook build会报README.md找不到的
2. 第二个.deploy/_book表示要生成的html文件输出的目录.


https://www.cnblogs.com/luoheng23/p/11197922.html


https://baijiahao.baidu.com/s?id=1590626161534963215&wfr=spider&for=pc

https://gitbook.zhangjikai.com/structure.html


https://www.jianshu.com/p/4e109a1113b2

插件：
https://www.jianshu.com/p/427b8bb066e6

热加载：
https://blog.csdn.net/weixin_38171180/article/details/89975512

去掉热加载 
http://stackmirror.caup.cn/page/s1b4bt53rg3a


### 生成页内目录(要自己在文章加toc标签)

```
npm i gitbook-plugin-toc2  --save

    plugins: ["toc2"],
     "pluginsConfig": {
		"toc": {
			"addClass": true,
			"className": "toc"
		}
}
```
https://www.npmjs.com/package/gitbook-plugin-toc2
https://cnodejs.org/topic/575229332420978970d4a5f0

### 生成页内目录2(要自己在文章加toc标签)

```
npm i gitbook-plugin-page-toc-button  --save

{
    ...
    "plugins": [
        "page-toc-button",
    ],
    "pluginsConfig": {
        "page-toc-button": {
            "maxTocDepth": 2,
            "minTocSize": 2
        }
    }
    ...
}
```
https://www.gitdig.com/gitbook/plugin/toc.html

https://github.com/jonschlinkert/markdown-toc
https://github.com/stuebersystems/gitbook-plugin-simple-page-toc
https://github.com/dohoby/gitbook-plugin-page-toc-button

### 可扩展导航章节(有问题,无法展开)

```
 npm i gitbook-plugin-expandable-chapters-small --save

```
https://www.npmjs.com/package/gitbook-plugin-expandable-chapters-small


### TODO 待研究
1、给html,js等加上版本号，要不每次更改内容时，浏览器还是有缓存

http://xszhao.science/cheatsheet/content/web/gitbook.html

2、toc目录移动到右边
<!-- toc -->

### gitbook官方文档

https://github.com/GitbookIO/gitbook/tree/master/docs

源码：C:\Users\Administrator\.gitbook\versions\3.2.3

### 问题

1. 若遇到下面的提示，很可能是没有权限访问，但是在命令行用ssh又可以拼得通连接git是可以的

```

 at ChildProcess.emit (events.js:191:7)
    at maybeClose (internal/child_process.js:877:16)
    at Process.ChildProcess._handle.onexit (internal/child_process.js:226:5)
  code: 128,
  message: 'Cloning into \'node_modules\\gh-pages\\.cache\\git@git.\'...\nHost key verification failed.\r\nfatal:
 Could not read from remote repository.\n\nPlease make sure you have the correct access rights\nand the repository exists.\n',
  name: 'ProcessError' }
```

解决办法：
在网上搜索了好久都没找到相应办法，后来在命令行下执行下面的,也就是手动clone下仓库的地址，
看下面的提示The authenticity of host 'git.dev.tencent.com (118.25.166.124)' can't be established.
这个和上面的报错Host key verification failed对应,
命令停止在Are you sure you want to continue connecting (yes/no)? yes输入yes后，再重新执行上面的部署命令即可解决了

```
  λ git clone 你的git仓库的ssh地址
Cloning into 'gitbook'...
The authenticity of host 'git.dev.tencent.com (118.25.166.124)' can't be established.
RSA key fingerprint is SHA256:xxxxxxxxxx.
Are you sure you want to continue connecting (yes/no)? yes
Warning: Permanently added 'git.dev.tencent.com' (RSA) to the list of known hosts.
remote: Enumerating objects: 3734, done.
remote: Counting objects: 100% (3734/3734), done.
remote: Compressing objects: 100% (3464/3464), done.
remote: Total 3734 (delta 2449), reused 297 (delta 137)
Receiving objects: 100% (3734/3734), 3.09 MiB | 1020.00 KiB/s, done.
Resolving deltas: 100% (2449/2449), done.
Checking connectivity... done.

```


2. git 不是内部或外部命令，也不是可运行的程序  

注意git安装的要把git的两个路径设置到path环境变量，否则在idea里没法跑git命令
    a:找到git安装路径中bin的位置：D:\Program Files\Git\bin
    b:找到git安装路径中libexec\git-core的位置，如：D:\Program Files\Git\mingw64\libexec\git-core

其中git安装路径为D:\Program Files\Git\

参考https://blog.csdn.net/qq_27911459/article/details/98967901