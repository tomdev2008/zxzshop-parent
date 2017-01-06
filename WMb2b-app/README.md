## 工程描述
   wmb2b的APP端接口工程，给移动端提供接口和鉴权服务，通过motan从service获取数据

## 部署方式
   1. mvn clean package -P{xxx}   xxx: dev（开发环境） stage（预发布环境） prod（产品环境）
   2. 忽略junit test  
             mvn clean package -Dmaven.test.skip=true -P{xxx}