---
title: oracle的mergeinto更新用法
tags: []
date: 2022-04-21 11:42:45
categories: oracle,mergeinto
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 

```java


    <update id="updateInventoryStateByOrerNo">
        MERGE INTO T_INVENTORY i
        USING T_RE_INVENTORY_IN_DETAIL d ON (d.ORDER_NO = #{orderNo} AND i.CARD_NO = d.CARD_NO)
        WHEN MATCHED THEN UPDATE SET i.INVENTORY_STATE = '1'
    </update>
	
	
	
	    <update id="updateNoDiscountMark">
        merge into t_agent_transact_record tr using t_card c
        on (tr.card_no = c.card_no and tr.order_no = #{orderNo} and c.agent_discount_id is null)
        when matched then update set tr.remark = #{remark}
    </update>
	
	
	
	<update id="modifyCardState">
        merge into T_CARD c using T_CARD_STATE_UPDATE_RECORD r
        on (r.state = '1' and r.order_no = #{record.orderNo} and r.import_no =
        <choose>
            <when test='importType == "1"'>c.CARD_NO</when>
            <when test='importType == "2"'>c.ICCID</when>
            <when test='importType == "3"'>c.CARD_IMSI</when>
            <when test='importType == "4"'>c.CARD_IMEI</when>
        </choose>
        ) when matched then update set
        c.card_state = #{record.modifyType},c.modifier = #{record.creator},c.modify_time = SYSTIMESTAMP
    </update>
	
	
	    <!--批量更新卡流量销售折扣-->
    <update id="dealSaleDiscountUpdateBatchRenew" parameterType="java.lang.String">
    	MERGE INTO T_CARD A USING (
				 SELECT CARD.CARD_NO,
					MAX(DISCOUNT.ID) AS "FLOW_SALE_DISCOUNT_ID"
					FROM  T_CARD CARD JOIN (
							SELECT DTL.CARD_NO,DTL.DISCOUNT_CODE FROM T_RENEWAL_DETAIL DTL WHERE DTL.ORDER_NO=#{orderNo,jdbcType=VARCHAR}
							) DTL  ON CARD.CARD_NO = DTL.CARD_NO
					JOIN T_CHANNEL_PRODUCT_DISCOUNT DISCOUNT ON DISCOUNT.DISCOUNT_CODE=DTL.DISCOUNT_CODE
					GROUP BY CARD.CARD_NO
		) C ON (A.CARD_NO=C.CARD_NO)
		WHEN MATCHED THEN UPDATE SET A.FLOW_SALE_DISCOUNT_ID=C.FLOW_SALE_DISCOUNT_ID
    </update>
	


https://blog.csdn.net/qq_35606010/article/details/120213562


	
	
```

```java

```
![]()

#### 
```java

```

```java

```
![]()

#### 


```java

```

```java

```
![]()
```




1. 
2. 
3. 
![]()