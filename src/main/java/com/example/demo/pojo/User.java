package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data  //自动生成getter Setter方法
@AllArgsConstructor  //全成员变量的构造方法
@NoArgsConstructor  //空参构造
public class User {

    //对应数据库中的主键（uuid，自增id，雪花算法）
    @TableId(type = IdType.AUTO) //配置主键策略  这是一个枚举类  具体的可以进源码看看
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)  //配置插入时时间自动更新  这里需要在handler中配置他的填充策略
    private Date updateTime;

    @Version  //乐观锁version注解
    private Integer version;  //乐观锁字段

    @TableLogic
    private Integer logicDelete;

}
