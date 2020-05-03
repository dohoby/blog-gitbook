---
title: python小牛试刀-获取微信关注用户信息
date: 2017-02-16 10:35:37
tags: [python,微信公众号]
categories: python
---
** {{ title }} ** <Excerpt in index | 首页摘要>


> 本文内容介绍：  
> 1、背景和需求  
> 2、解决思路  
> 3、解决语言  
> 4、python-requests和python-redis  
> 5、获取关注公众号的所有用户的openid  
> 6、根据openid获取用户详细的用户信息    
> 7、总结  
<!-- more -->


## 1、背景和需求
  
&emsp;&emsp;现在要统计还有多少用户关注橙医生,导出微信上用户的基本信息和注册信息，但是存在以下问题：  
1、数据库现在只记录用户关注的记录，没有记录用户取消关注的记录，  
2、用户基本信息比如所在地区和用户年龄没有记录到数据库中，需要调用微信api去重新获取  
3、导出关注记录的时候，同时要导出该用户的基本注册信息



## 2、解决思路
1、从数据库导出现在所有关注过的记录(openid),现在统计到有80多万记录  
2、调用微信api获取现在所有关注的记录(openid)，统计到有50万条记录  
3、将步骤2中获取到openid，先调用微信api去获取所有用户具体的基本用户信息    
4、对比数据库中的openid和步骤3获取到的用户信息中的openid，然后查询数据库查询出该用户的注册信息,导出到excel，完。


## 3、解决语言
打算用python，因为python是脚本语言，方便对导出数据进行处理，同时还由于调用微信api需要access_token，而这个token在我们服务器由其他java项目生成的，放在redis里面。

## 4、python-requests和python-redis基础模块知识 

##### python-requests模块  
先下载安装
```python
git clone git://github.com/kennethreitz/requests.git    

python setup.py install  
```

  
导入Requests模块,发起get请求,响应文本内容用r.text获取,若要变成json，直接 r.json()即可
```python
import requests  

r = requests.get('https://github.com/timeline.json')  
d=r.json()  
```


##### python-redis模块

编译安装：
```python
wget https://pypi.python.org/packages/source/r/redis/redis-2.9.1.tar.gz  
tar xvzf redis-2.9.1.tar.gz  
cd redis-2.9.1  
python setup.py install  
```
用法：  

```python
import redis
r=redis.Redis(host='localhost',port=6379,db=0,password='密码')  
token=r.get('wxtoken:PATIENT_TOKEN')
```


## 5、获取关注我们公众号的所有用户的openid
详细代码如下：涉及调用微信api，还有从redis里面获取token等知识


```python
#! /usr/bin/python      
# encoding=utf-8    
import requests  
import demjson  
import json  
import sys  
reload(sys)  
sys.setdefaultencoding( "utf-8" )  
import time  
import socket  
import redis  
import traceback  

token='your token'    
baseurl='http://api.weixin.qq.com/cgi-bin/user/get?access_token='   
redis_host='your redis_host'    
redis_password='your redis_password'    

#调用微信api获取关注的所有openid函数  
def getFollowOpenids( baseurl , token   ,nextopenid):  
   if nextopenid:  
      geturl=baseurl + token +   '&next_openid='+nextopenid  
   else:  
      geturl=baseurl + token  
   print "geturl=%s"%(geturl)  
   r=requests.get(geturl,timeout=10)  
   d=r.json()  
   return d  

#获取redis里面的微信access_token  
def getToken():  
  r=redis.Redis(host=redis_host,port=6379,db=0,password=redis_password)  
  token=r.get('wxtoken:PATIENT_TOKEN')  
  while not token:  
    print "再次取到的token是空的token=%s"%token
    time.sleep(30)  
    token=getToken()  
  return token  

fo=open("wx-openid.csv","a+");

def writeToCsv( d ):
  for x in d['data']['openid']:
    fo.write("%s\n"%x)

token=getToken()
nextopenid=''
d=getFollowOpenids( baseurl , token ,nextopenid )

#将第一次获取到的所有关注记录写入wx-openid.csv文件中
writeToCsv( d )

#获取下一次调用需要开始的nextopenid
nextopenid=d['next_openid']
print "第一次获取到的nextopenid=%s"%nextopenid

#统计需要调用多少次微信api，打印所有的记录数
total_time=d['total']/10000
print "total_time=%s,total=%d"%(total_time,d['total'])

#循环调用微信api去获取关注的openid
while total_time > 0:
   d=getFollowOpenids( baseurl , token ,nextopenid )
   writeToCsv( d )
   nextopenid=d['next_openid']
   print "获取到下次next_openid=%s"%nextopenid
   total_time=total_time-1
   print "\n"


fo.close();

```

## 6、根据openid获取用户详细的用户信息  


```python
#! /usr/bin/python
# encoding=utf-8
import requests
import demjson
import json
import sys
reload(sys)
sys.setdefaultencoding( "utf-8" )
import time
import socket
import redis
import traceback

token='your token'
baseurl='http://api.weixin.qq.com/cgi-bin/user/info?access_token='
redis_host='your redis_host'
redis_password='your redis_password'

def getUserInfo( baseurl , token ,openid ):
   geturl=baseurl + token+'&openid='+openid
   r=requests.get(geturl,timeout=10)
   d=r.json()
   print r.text
   return d

def getToken():
  r=redis.Redis(host=redis_host,port=6379,db=0,password=redis_password)
  token=r.get('wxtoken:PATIENT_TOKEN')
  while not token:
    print "再次取到的token是空的token=%s"%token
    time.sleep(30)
    token=getToken()
  return token


fo=open("common-userinfo.csv","wb");

with open("wx-openid.csv","r") as wxf:
          for strs in wxf.readlines():
                try:
                  d=getUserInfo(baseurl,token,strs.strip())
                  if 'errcode' in d and d['errcode']==42001:
                     print "token=%s过期了"%token
                     token=getToken()
                     print "新token=%s"%token
                     d=getUserInfo(baseurl,token,strs.strip())
                  if d['subscribe']==1:
                     subtime=d['subscribe_time']
                     timeArray=time.localtime(subtime)
                     otherStyleTime=time.strftime("%Y-%m-%d %H:%M-%S",timeArray)
                     info=d['openid']+","+d['nickname']+","+otherStyleTime+","+d['city']+","+str(d['sex'])+"\n"
                  else:
                     info=strs.strip()+",,,,"
                  fo.write(info)
                except:
                   info=sys.exc_info()
                   print info[0],":",info[1]
                   traceback.print_exc(file=sys.stdout)  
                   print "异常json:%s"%strs
                   #pass  

fo.close();                   
                   
                   
```

## 7、总结
&emsp;&emsp;上面的python代码都是刚学刚用的，由于时间比较匆忙，需要及时完成需求，所以上面代码还没进行很好封装，后面连接数据库查询注册信息方面的也没写。本文只提供获取微信关注用户信息的例子参考，如有问题，可联系我。  
可参考的知识链接：  
1、python连接redis  
http://debugo.com/python-redis/  
2、http请求框架python-requests  
http://docs.python-requests.org/zh_CN/latest/user/quickstart.html












