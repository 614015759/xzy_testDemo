package com.example.demo;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.lang.reflect.Array;
import java.util.Arrays;

//代码自动生成器
public class lingzunCode {
    public static void main(String[] args) {
        //首先需要构建一个  代码生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //配置策略

        //1. 全局配置
        GlobalConfig gc = new GlobalConfig();
        String property = System.getProperty("user.dir");
        gc.setOutputDir(property+"/src/main/java");
        gc.setAuthor("lingzun");
        gc.setOpen(false);
        gc.setFileOverride(false);//是否覆盖
        gc.setServiceName("%sService");//  去service的I前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        //设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDriverName("com.p6spy.engine.spy.P6SpyDriver");
        dataSourceConfig.setUrl("jdbc:p6spy:mysql:///mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dataSourceConfig.setDbType(DbType.MYSQL);
        mpg.setDataSource(dataSourceConfig);


        //3.包的配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("blog");
        packageConfig.setParent("com.example.demo");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        mpg.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);  //下划线转驼峰命名
        strategy.setInclude("user"); //改表   重点！！！！！！！！！！！！！！！！！！！！！！！！
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);  //是否支持lombok
        //开启restFul的驼峰命名格式
        strategy.setRestControllerStyle(true);

        strategy.setLogicDeleteFieldName("logic_delete");


        //自动填充配置
        TableFill gmt_create = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmt_modified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);

        strategy.setTableFillList(Arrays.asList(gmt_create,gmt_modified));
        //乐观锁
        strategy.setVersionFieldName("version");
        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2  多个字段会这样搞

        mpg.setStrategy(strategy);

        mpg.execute();  //执行代码构造器
    }

}
