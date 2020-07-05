package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.IUserMapper;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

//条件查询器测试
@SpringBootTest
public class WrapperTest {

    @Autowired
    private IUserMapper userMapper;

    @Test
    public void testSelect(){
        //查询name不为空的用户，并且邮箱不为空的用户，年龄>=12岁的
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",20);
        userMapper.selectList(userQueryWrapper).forEach(System.out::println);
    }

    @Test
    void testWrapper2(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        User user = userMapper.selectOne(userQueryWrapper);
        System.out.println(user);

    }

    @Test
    void testWrapper3(){
        //查询年龄在20-30岁之间的用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.between("age",20,25);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    //模糊查询
    @Test
    void testWrapper4(){
        //查询名字中不带e的
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //左和右  %e  e%
        userQueryWrapper.notLike("name","超")
                .likeLeft("name","y");
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void testWrapper5(){
        //id 在子查询中查出
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.inSql("id","select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(userQueryWrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void testWrapper6(){
        //通过id进行排序
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByDesc("id");

    }
}
