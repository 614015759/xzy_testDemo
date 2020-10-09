# Mybatis_Plus学习笔记

<a id = "_top"></a>

   <a href="https://mybatis.plus/guide/quick-start.html#添加依赖" rel="nofollow" target="_self"> · mybatis_plus官方文档参考</a>

   <a href="https://github.com/614015759/xzy_testDemo" rel="nofollow" target="_self"> · mybatis_plus参考源码下载</a>
   
## 目录： 
### 1.<a href="#_1" rel="nofollow" target="_self">配置相关</a>
1.1 <a href="#_1.1" rel="nofollow" target="_self">相关依赖(pom.xml)</a>
1.2 <a href="#_1.2" rel="nofollow" target="_self">配置类(MyBatisPlusConfig)</a>
1.3 <a href="#_1.3" rel="nofollow" target="_self">application.properties</a>
1.4 <a href="#_1.4" rel="nofollow" target="_self">Sql和实体类和自动填充策略处理程序</a>

### 2.<a href="#_2" rel="nofollow" target="_self">增删改查基本及Wrapper操作代码</a>


###  3.<a href="#_3" rel="nofollow" target="_self">逆向工程实现</a>

<a id="_1">配置入门</a>
---
   · <a href="#_1" rel="nofollow" target="_self">返回目录</a>
   ---
   1.<a id="_1.1" rel="nofollow" target="_self">pom.xml </a>
```java
        <dependencies>
            <dependency>
                <groupId>com.ibeetl</groupId>
                <artifactId>beetl</artifactId>
                <version>3.1.8.RELEASE</version>
            </dependency>
            <dependency>
                <groupId>org.freemarker</groupId>
                <artifactId>freemarker</artifactId>
                <version>2.3.30</version>
            </dependency>
            <dependency>
                <groupId>org.apache.velocity</groupId>
                <artifactId>velocity-engine-core</artifactId>
                <version>2.2</version>
            </dependency>
    <!--        //代码生成器依赖-->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-generator</artifactId>
                <version>3.3.2</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/p6spy/p6spy -->
    <!--       p6spy 第三方sql性能分析工具  官方推荐的  好像在3.2.0之后就不能用自己的那个性能分析插件了-->
            <dependency>
                <groupId>p6spy</groupId>
                <artifactId>p6spy</artifactId>
                <version>3.9.0</version>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
    
    <!--        注意我这里用的是3.3.1-->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>3.3.1</version>
            </dependency>
    
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
                <exclusions>
                    <exclusion>
                        <groupId>org.junit.vintage</groupId>
                        <artifactId>junit-vintage-engine</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>io.swagger</groupId>
                <artifactId>swagger-annotations</artifactId>
                <version>1.5.13</version>
            </dependency>
    
        </dependencies>
```
 2.<a id="_1.2" rel="nofollow" target="_self">MybatisPlusConfig </a>
 

 配置清单
 :  乐观锁插件
 :  分页查询

 
 ```java
    package com.example.demo.config;
    
    import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
    import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
    import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
    import org.mybatis.spring.annotation.MapperScan;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.Profile;
    import org.springframework.transaction.annotation.EnableTransactionManagement;
    
    @MapperScan("com.example.demo.mapper")
    @EnableTransactionManagement
    @Configuration  //配置类
    public class MybatisPlusConfig {
    
            //注册乐观锁插件
            @Bean
            public OptimisticLockerInterceptor optimisticLockerInterceptor() {
                return new OptimisticLockerInterceptor();
            }
    
            //分页插件
        @Bean
        public PaginationInterceptor paginationInterceptor() {
            PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
            return paginationInterceptor;
        }
    }
```

 3.<a id="_1.3" rel="nofollow" target="_self">application.properties </a>
 ```java
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.url=jdbc:p6spy:mysql:///mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
#因为使用了p6spy作为第三方性能分析插件，所以驱动和url都要进行改变  这些在官方文档里的链接都有
spring.datasource.driver-class-name=com.p6spy.engine.spy.P6SpyDriver


# 配置日志
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
#0表示已删除（数据查不到）
mybatis-plus.global-config.db-config.logic-delete-value=0
#1表示未删除（数据可查）
mybatis-plus.global-config.db-config.logic-not-delete-value=1
#开启逻辑删除
mybatis-plus.global-config.db-config.logic-delete-field=true
```

4.<a id="_1.4" rel="nofollow" target="_self">Sql和实体类和自动填充策略处理程序 </a>
 
 注：这里其实是有数据库实现和mybatis_plus实现两种方法的，我们这里只讲Mybatis_Plus中的配置方法
 
 <img src=""></img>
 
 数据库建表语句
 ```java
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybatis_plus` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mybatis_plus`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `logic_delete` int DEFAULT '1' COMMENT '逻辑删除',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1278541387704303621 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`logic_delete`,`version`,`create_time`,`update_time`,`id`,`name`,`age`,`email`) values 
(1,4,'2020-07-02 12:06:04','2020-07-02 13:13:10',1,'超超之觉醒222',27,'test1@baomidou.com'),
(1,1,'2020-07-02 12:06:04','2020-07-02 12:06:04',2,'Jack',20,'test2@baomidou.com'),
(1,1,'2020-07-02 12:06:04','2020-07-02 12:06:04',3,'Tom',28,'test3@baomidou.com'),
(1,1,'2020-07-02 12:06:04','2020-07-02 12:06:04',4,'Sandy',21,'test4@baomidou.com'),
(1,1,'2020-07-02 12:06:04','2020-07-02 12:26:38',5,'超皇6*3525',16,'ssada@dassd.com'),
(0,1,NULL,'2020-07-02 12:43:13',1278541387704303619,'超皇6*3556',16,'ssada@dassd.com'),
(1,1,'2020-07-02 12:44:19','2020-07-02 12:45:37',1278541387704303620,'超皇6*3556',16,'ssada@dassd.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
```

pojo实体类配置
    
```java
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

```
MyMetaObjectHandler(主键相关策略配置类)
```java

package com.example.demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j      //日志输出
@Component  //放入容器
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill");

        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}

```

##2.<a id="_2" rel="nofollow" target="_self">Sql和实体类和自动填充策略处理程序 </a>
   增删改查demoTest代码（需要的可以把环境搭建好后自行测试）
1 普通版
```java
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
```

2  Wrapper版
```java
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

```

3.<a id="_3" rel="nofollow" target="_self">代码生成器配置测试 </a>

```java
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class MyGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        /**
         * 配置你整体的包属性(service\controller\mapper)
         */
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 当前项目根目录的获取
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("YeZhiyue");
        // 设置是否打开资源管理器中的目录
        gc.setOpen(false);
        // 配置是否覆盖原文件
        gc.setFileOverride(false);
        // 各层文件名称方式，例如： %sAction 生成 UserAction
        gc.setServiceName("%sService");
       // id的自增策略
        gc.setIdType(IdType.ID_WORKER);
        // 配置日期类型
        gc.setDateType(DateType.ONLY_DATE);
        // 用于前后端分离,实体属性 Swagger2 注解
//        gc.setSwagger2(true);
        // 将这个配置扔到我们的全局配置中
        mpg.setGlobalConfig(gc);


        /**
         * 配置你的数据源
         */
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        /**
         * 这里配置你的存放不同层次的bao(Controller\Service\Mapper)
         */
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("innermodle");
        pc.setParent("com.example.demo");
        // 设置存放包的名称
        pc.setEntity("proj");
        pc.setService("service");
        pc.setController("controller");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);


        /**
         * 配置一些mybatispluse的特殊性质
         *  自动填充
         *  乐观锁
         *  逻辑删除
         */
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 设置需要进行映射的表名
        // TODO 2020/6/24 这里是需要根据需求添加表
        strategy.setInclude("user","person");
        // 这里直接可以配置将名称中的下划线 _ 转化为 驼峰命名
        // 表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 你的实体类的父类
//        strategy.setSuperEntityClass("Object");
        // 自动生成lombok
        strategy.setEntityLombokModel(true);
        // 设置自动逻辑删除设置 deleted
        strategy.setLogicDeleteFieldName("deleted");
        // 自动填充策略 create_time update_time 的自动填充策略
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        // 乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        // 配置Rest的使用风格
        strategy.setRestControllerStyle(true);
        // 这里是对Controller进行配置命名的下划线风格
        strategy.setControllerMappingHyphenStyle(true);// localhost:8080/hello_id_2
        // 配置总的
        mpg.setStrategy(strategy);

        // 最后执行所有配置
        mpg.execute();
    }
}

```
 
