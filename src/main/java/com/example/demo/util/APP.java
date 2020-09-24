package com.example.demo.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class APP {
    private static final AutoGenerator mpg = new AutoGenerator();   //代码生成器
    private static final GlobalConfig gc = new GlobalConfig();      //全局配置
    private static final String Author = "xiaowei";                 //作者
    /**
     * 如果要Swagger Api的自动配置 可以去策略配置那里把我的取消注释就行
     * 如果为ture贼执行tables选中的表名,如果为false戝一次性创建指定数据库的所有表
     */
    private static final Boolean Include = false;       //true则输入数据库你要生成的表名
    private static final String[] tables = {"表名"};    //选的数据库表名
    //填写需要自动填充的字段(创建时间,和更新时间) 需要对应表名
    private static final String TableFillName = "createDate";      //自动填充的注解 可以按自己想要的更改
    private static final String TableFillName1 = "updateDate";
    /**
     * 项目生成根路径
     * 可以使用System.getProperty("user.dir")
     * 获取到你的项目路径
     * 不过建议直接绝对路径好点,因为那方法会有变动随着你项目的变动而变动
     * 数据源配置
     */
    private static final String projectPath = "G:/demo"; //输入你要生成在哪个项目路径
    private static final String URL = "jdbc:mysql://IP地址:3306/数据库名?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";   //5.7以下的版本把CJ去掉
    private static final String USER_NAME = "用户名";
    private static final String PASSWORD = "密码";
    //启动
    public static void main(String[] args) {
        {
            //全局设置
            setPropertyConfig();
            //数据源配置
            setDataSourceConfig();
            //策略配置
            setStrategyConfig(Include);
            //包配置
            setPackageConfig();
        }
    }
    //策略配置
    private static void setStrategyConfig(boolean Include) {
        StrategyConfig strategy = new StrategyConfig();
        ArrayList<TableFill> fillArrayList = new ArrayList<>();
        if (Include){
            strategy.setInclude(tables);
        }
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        fillArrayList.add(new TableFill(TableFillName, FieldFill.INSERT_UPDATE));
        fillArrayList.add(new TableFill(TableFillName1,FieldFill.INSERT_UPDATE));
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // strategy.setSuperEntityColumns("id");
        strategy.setTablePrefix("t_");
        strategy.setTableFillList(fillArrayList);
        mpg.setStrategy(strategy);
    }
    //全局配置
    private static void setPropertyConfig() {
        // String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("I%sServiceImpl");
        gc.setControllerName("%sController");
        gc.setIdType(IdType.AUTO);
        gc.setAuthor(Author);
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        // gc.setSwagger2(true);   //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
    }
    //数据源配置
    private static void setDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);
    }
    //包配置
    private static void setPackageConfig() {
        PackageConfig pc = new PackageConfig();
        // pc.setModuleName(scanner("模块名"));
        pc.setParent("com.example.demo"); // 自定义包路径
        pc.setController("controller"); // 这里是控制器包名，默认 web
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        // 配置模板
        // TemplateConfig templateConfig = new TemplateConfig();
        // templateConfig.setXml(null);
        // mpg.setTemplate(templateConfig);
    }
}

