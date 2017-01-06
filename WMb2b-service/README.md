## 工程描述
   wmb2b的核心服务工程，包括平台的所有功能性和非功能性服务；通过motan对外提供rpc服务

## 运行环境
   1. zookeeper3.4.9，可以使用测试环境的zk(192.165.4.14:2181)，也可以本地安装zk
   2. memcached，可以使用测试环境的memcached(192.165.4.13:11211)，也可以本地安装memcached

## 部署方式
   1. mvn spring-boot:run
   2. mvn clean package -P{xxx}   xxx: dev（开发环境） stage（预发布环境） prod（产品环境）
      java -jar WMb2b-service.jar