---
title: RestTemplate用法
tags: [RestTemplate用法,http]
date: 2019-07-23 10:37:52
categories: http
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

###  引用

``` 
    @Autowired
    private RestTemplate restTemplate;
``` 


###  简单get请求

``` 
Map<String, Object> uriVariables = new HashMap<>();
HttpHeaders requestHeaders = new HttpHeaders();
HttpEntity requestEntity = new HttpEntity<>(requestHeaders);
ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, requestEntity, String.class, uriVariables);
HttpStatus status = response.getStatusCode();
String responseBody = response.getBody();

```

###  简单post请求（不带参数）

```
Map<String, Object> uriVariables = new HashMap<>();
HttpHeaders requestHeaders = new HttpHeaders();
HttpEntity requestEntity = new HttpEntity<>(requestHeaders);
ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, requestEntity, String.class, uriVariables);
HttpStatus status = response.getStatusCode();
String responseBody = response.getBody();

```

###  简单post请求（带参数）

```
Map<String, Object> uriVariables = new HashMap<>();
HttpHeaders requestHeaders = new HttpHeaders();
HttpEntity requestEntity = new HttpEntity<>(requestHeaders);
ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, requestEntity, String.class, uriVariables);
HttpStatus status = response.getStatusCode();
String responseBody = response.getBody();

```

###  设置请求头

```  
 HttpHeaders requestHeaders = new HttpHeaders();
 
 
 requestHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(Authorization.getBytes("utf-8")));
 requestHeaders.add("Accept", "application/json");

```

###  设置form表单提交（请求参数要用MultiValueMap）

```
 HttpHeaders requestHeaders = new HttpHeaders();
 requestHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
 
```
注意请求参数要用MultiValueMap

```
MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
postParameters.add("grant_type", "client_credentials");
HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(postParameters, requestHeaders);

```

###  

```


```
