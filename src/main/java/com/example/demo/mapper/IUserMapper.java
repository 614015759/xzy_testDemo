package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//在对应的mapper上面继承基本的类BaseMapper
@Repository     //代表持久层
public interface IUserMapper extends BaseMapper<User> {
        //所有的CRUD操作都已经完成了
    //不需要编写一大堆配置文件
}
