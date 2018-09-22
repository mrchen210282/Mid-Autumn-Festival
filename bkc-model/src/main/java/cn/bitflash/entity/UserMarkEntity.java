package cn.bitflash.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
@TableName("user_mark")
public class UserMarkEntity implements Serializable {
    @TableId
    private String id;

    private String uid;

    private BigDecimal quantity;

    private BigDecimal price;

    private String state;

    private String purchaseUid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;
}
