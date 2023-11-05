# Grover

A best practice for Spring Boot Web.

## 技术栈及依赖

- SpringBoot
- [MyBatis-Plus](https://github.com/baomidou/mybatis-plus): MyBatis增强工具包
- [dynamic-datasource-spring-boot-starter](https://github.com/baomidou/dynamic-datasource): 基于 SpringBoot 的多数据源组件
- Lombok: 简化Java代码
- Maven: 依赖管理
- JUnit
- Jackson: JSON序列化
- Guava: 工具库（集合、缓存、位图、原语等等）
- Javassist: 动态编辑Java字节码
- [OkHttp](https://github.com/square/okhttp): HTTP客户端

## 环境

- MySQL 5.7+
- Redis 5.0+

## 配置

### Spring Boot Banner Print

ASCII 图案在线生成工具:

- https://www.bootschool.net/ascii
- http://www.network-science.de/ascii/

代码实现及可用配置: `org.springframework.boot.SpringApplicationBannerPrinter`

## Maven

- 打包：`mvn package`

- 打包（跳过测试）：`mvn package -Dmaven.test.skip=true`

- 运行 `jar` 文件

```shell
java -jar xxx.jar
```

## MyBatis-Plus

- 注解：简单查询
- XML：共用配置段、复杂查询等，并提供给注解使用

