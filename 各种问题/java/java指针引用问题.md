---
title: java指针引用问题
tags: [问题]
date: 2019-07-17 14:05:08
categories: 各种问题
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1、java指针引用问题 
 
如下图：分析此代码有啥问题

```
   TParkingCommentConfig tParkingCommentConfig = tParkingCommentConfigMapper.selectOne(new QueryWrapper<TParkingCommentConfig>()
                  .lambda().eq(TParkingCommentConfig::getChannelId, channelId));
          TParkingCommentConfig oldParkingCommentConfig =tParkingCommentConfig;
  
          if (tParkingCommentConfig == null) {
              tParkingCommentConfig = new TParkingCommentConfig();
              tParkingCommentConfig.setId(ObjectId.id());
              tParkingCommentConfig.setDefaultCommentLevel(query.getDefaultCommentLevel());
              tParkingCommentConfig.setOverTime(query.getOverTime());
              tParkingCommentConfig.setStatus(query.getStatus());
              tParkingCommentConfig.setCreateTime(LocalDateTime.now());
              tParkingCommentConfig.setCreator(user.getName());
              tParkingCommentConfig.setChannelId(channelId);
              tParkingCommentConfigMapper.insert(tParkingCommentConfig);
              type = "新增";
          } else {
              Integer oldOverTime = tParkingCommentConfig.getOverTime();
              if(CommentEnum.STATUS.OFF.getCode().equals(tParkingCommentConfig.getParkingCommentStatus()) &&
                      CommentEnum.STATUS.OPEN.getCode().equals(query.getStatus())){
                  throw new BusinessException("停车评价关闭中，不能开启自动评价");
              }
              tParkingCommentConfig.setDefaultCommentLevel(query.getDefaultCommentLevel());
              tParkingCommentConfig.setOverTime(query.getOverTime());
              tParkingCommentConfig.setStatus(query.getStatus());
              tParkingCommentConfig.setUpdater(user.getName());
              tParkingCommentConfig.setUpdateTime(LocalDateTime.now());
              tParkingCommentConfigMapper.updateById(tParkingCommentConfig);
  
              if((query.getOverTime()-oldOverTime)<0){
                  //异步自动评价
                  asyncAutoComment(query,oldParkingCommentConfig,channelId);
              }
          }      


 private void asyncAutoComment(ParkingCommentConfigQuery query,TParkingCommentConfig tParkingCommentConfig, String channelId) {
        try {
            Thread t1 = new Thread(()->{
                    List<TParkingCommentConfigLinkParking> linkList = configLinkParkingMapper.selectList(new QueryWrapper<TParkingCommentConfigLinkParking>().eq("comment_config_id",tParkingCommentConfig.getId()));
                    if (CollectionUtils.isEmpty(linkList)){
                        return;
                    }
                    linkList.forEach(link ->{
                        LocalDateTime dateTime = LocalDateTime.now();
                        dateTime = dateTime.minus(tParkingCommentConfig.getOverTime(), ChronoUnit.DAYS);
                        String commentDateStart = DateUtil.getFormatLocalDateTime(DateUtil.YMD, dateTime);
                        String startDate=commentDateStart;

                        LocalDateTime dateTime2 = LocalDateTime.now();
                        dateTime2 = dateTime2.minus(query.getOverTime(), ChronoUnit.DAYS);
                        String commentDateEnd = DateUtil.getFormatLocalDateTime(DateUtil.YMD, dateTime2);
                        String endDate=commentDateEnd;
                        findNeedCommentCarLog(link.getParkingId(), startDate,endDate, tParkingCommentConfig.getDefaultCommentLevel());
                    });
            });
            t1.setName("asyncAutoComment");
            t1.start();
        } catch (Exception e) {
           log.info("异步保存自动生成评论失败:",e);
        }

    }
```
解答：

```
     TParkingCommentConfig oldParkingCommentConfig =tParkingCommentConfig;

```
这行代码赋值到旧的对象是没法赋值的，只要tParkingCommentConfig变了，oldParkingCommentConfig就会变，
所以要新建个新的TParkingCommentConfig对象，然后把内容复制过去.


