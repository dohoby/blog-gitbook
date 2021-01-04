---
title: japidoc文档管理
tags: []
date: 2020-12-25 21:17:29
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### pom引入

```java
        <dependency>
            <groupId>io.github.yedaxia</groupId>
            <artifactId>japidocs</artifactId>
            <version>1.4.4</version>
        </dependency>
```

#### 新建个测试类来执行就可以

```java
package com.tobe.apidoc;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;

public class JapiDocTest {
    public static void main(String args[]) {
        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath("D:\\idea-workspace-blog\\lqx-project-demo-github\\basic-server");
        // 项目名称
        config.setProjectName("japi-docs");
        // 声明该API的版本
        config.setApiVersion("V1.2");
        // 生成API 文档所在目录
        config.setDocsPath("d:\\japi");
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);

        //导出markdown
        config.addPlugin(new MarkdownDocPlugin());

        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }
}

```

#### 查看结果
![](japidoc文档管理/japi-1.png)