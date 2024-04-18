---
title: pandoc教程
tags: []
date: 2024-03-13 19:45:21
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 
1. 转换Markdown为HTML
  
```java
 pandoc -s input.md -o output.html
```
   其中“-s input.md”表示将Markdown文件转换为HTML文件，“-o output.html”表示将结果输出到output.html文件中。

我的
 ```java
pandoc -s v7.5-门户短信-技术设计.md -o output.html
```

2. 转换Markdown为PDF
 ```java
  pandoc -s input.md -o output.pdf
```
   和将Markdown转换为HTML的命令类似，只是输出的文件类型不同，需要使用PDF。

我的
 ```java
pandoc -s v7.5-门户短信-技术设计.md -o output.pdf
```
报错：
pdflatex not found. Please select a different --pdf-engine or install pdflatex

替换为：pandoc --pdf-engine=xelatex test.md -o test.pdf
 ```java
pandoc --pdf-engine=xelatex v7.5-门户短信-技术设计.md -o output.pdf
```

3. 转换多个文件
   pandoc -s file1.md file2.md -o output.html
   可以同时将多个Markdown文件转换为同一个格式的文件。
4. 
4. 引入CSS样式
   pandoc -s input.md -o output.html --css=mycss.css
   可以使用--css选项引入自定义的CSS样式。

5. 生成目录
   pandoc -s input.md -o output.html --toc
   可以在生成的HTML文件中自动生成目录。

6. 转换为其他格式
   pandoc -s input.md -o output.docx


[]()

#### 
```java

```

```java

```
[]()

#### 


```java

```

```java

```
[]()
```




1. 
2. 
3. 
![]()