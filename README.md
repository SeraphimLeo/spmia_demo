# spmia_demo


> 《Spring 微服务实战》课程练习项目。


## 模块结构


- **authentication-service** 认证服务
- **confsvr** spring 配置服务
- **docker** docker-compose 构建 Docker 镜像
- **eurekasvr** 服务发现服务器
- **licensing-service** 许可证服务（业务模块）
- **organization-service** 组织服务（业务模块）
- **zuulsvr** 服务路由

项目中另外用到了 kafka、redis、zipkin，用了 Docker 启动，可以直接从 Docker Hub 拉取：


- docker pull johnnypark/kafka-zookeeper
- docker pull openzipkin/zipkin
- docker pull redis
> 书中使用过 spotify/kafka 镜像，其中 kafka 的版本太低所以换成了上面的

## 启动方式
本地可以用 mvn spring:run 启动

docker 启动先构建 docker 镜像：`mvn clean package docker:build`，然后使用  `docker-compose -f docker/common/docker-compose.yml up`