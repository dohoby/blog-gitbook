---
title: oracle常见更新操作
tags: []
date: 2024-02-23 15:46:57
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### Oracle 在原有内容上继续添加内容

SET DTL.REMARK = DTL.REMARK||'无权限操作;'
注意只能用||，不能用+，否则更新不成功的

```java
    <update id="updateRemarkWhenNoAuthority">
        UPDATE T_SMS_TASK_DETAIL DTL SET DTL.REMARK = DTL.REMARK||'无权限操作;',DTL.MODIFY_TIME=SYSTIMESTAMP
        WHERE  DTL.TASK_NO = #{taskNo}
          AND  EXISTS(SELECT 1 FROM T_CARD CARD WHERE CARD.CARD_NO=DTL.CARD_NO AND   CARD.channel_id!=#{channelId})
    </update>
```

```java

```
[]()

#### 
```java

    <update id="throwReInventoryWhenCheckCardNo">
        UPDATE T_RE_INVENTORY_IN_DETAIL d SET d.REMARK = '卡号不存在或卡处于预约销户'
        WHERE d.ORDER_NO = #{orderNo}
        AND (SELECT COUNT(1) FROM T_CARD c WHERE c.CARD_NO = d.CARD_NO AND c.CARD_STATE != '5') = 0
    </update>
	
	
	    <update id="updateRemarkWhenCardStateInReservedNumCancel">
        UPDATE T_RENEWAL_DETAIL DTL SET DTL.REMARK = '卡处于预约销户状态' ,DTL.STATE='3',
     DTL.MODIFIER=#{userName},DTL.MODIFY_TIME=SYSTIMESTAMP
     WHERE  DTL.ORDER_NO = #{orderNo}
		 AND  EXISTS(SELECT 1 FROM T_CARD CARD WHERE CARD.CARD_NO=DTL.CARD_NO AND   CARD.CUSTOMER_STATE='5')
		 AND DTL.REMARK IS NULL
    </update>
	
	
	    <update id="throwRechargeWhenCheckMerchantId">
        UPDATE T_CHARGE_ORDER co SET co.REMARK = '充值卡存在不属于当前商户的卡', co.STATE = '1', co.MODIFIER = #{userName}, MODIFY_TIME = SYSDATE
        WHERE co.ORDER_NO = #{orderNo}
        AND (
            SELECT COUNT(1)
            FROM T_CARD c
                LEFT JOIN T_PORTAL_DISTRIBUTE pd ON pd.CARD_ID = c.ID
                LEFT JOIN T_PORTAL_MERCHANT pm ON pd.MERCHANT_ID = pm.ID
            WHERE c.CARD_NO = co.CARD_NO AND pm.ID = #{merchantId}
        ) = 0
    </update>
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