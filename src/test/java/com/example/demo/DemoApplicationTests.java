package com.example.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.IUserMapper;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    IUserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setAge(18);
        user.setEmail("614015759@qq.com");
        user.setName("超超6nmb663");
        int insert = userMapper.insert(user);
        System.out.println(insert);

        // users.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1278541387704303620l);
        user.setName("超皇6*3556");
        user.setAge(16);
        user.setEmail("ssada@dassd.com");
        userMapper.updateById(user);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        User user = userMapper.selectById(1l);
        user.setName("超超之觉醒");
        user.setAge(27);
        userMapper.updateById(user);
    }

    @Test
    public void testOptimisticLocker2(){
        //线程1
        User user = userMapper.selectById(1l);
        user.setName("超超之觉醒111");
        user.setAge(27);


        User user2 = userMapper.selectById(1l);
        user.setName("超超之觉醒222");
        user.setAge(27);
        //模拟第二个线程执行了插队操作

        userMapper.updateById(user2);
        userMapper.updateById(user);

    }

    //测试查询
    @Test
    public void testSelectById(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }


    //测试批量查询
    @Test
    public void testSelectByBatchId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        for (User user : users) {
            System.out.println(user);
        }
    }

    //测试条件查询
    @Test
    public void testSelectBy(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Tom");
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }


    //测试分页查询
    @Test
    public void testPage(){
        //参数1  当前页
        //参数2  页面大小
        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }


    //删除操作
    @Test
    public void testDelete(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",1278541387704303619l);

        userMapper.deleteByMap(map);

    }



}
