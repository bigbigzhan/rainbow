## 平台简介

rainbow分布式配置中心服务端
rainbow分布式配置中心简单省心

####项目描述：
能够集中化管理应用不同环境、不同集群的配置，配置修改后能够实时推送到应用端，并且具备规范的权限、流程治理等特性，适用于微服务配置管理场景，使用rainbow可以让集群中的服务进程动态感知数据的变化，无需重启服务就可以实现配置数据的更新。

* 统一管理不同环境的配置

> Rainbow提供了一个统一界面集中式管理不同环境（evn）、不同组（groups）、不同空间（cells）的配置。

>同一份代码部署在不同的集群，可以有不同的配置，比如zookeeper/mysql的地址等

>通过命名空间（cells）可以很方便地支持多个不同应用共享同一份配置，同时还允许应用对共享的配置进行覆盖
>
* 配置修改实时生效（热发布）
>用户在Apollo修改完配置并发布后，客户端能实时（1秒）接收到最新的配置，并通知到应用程序
>
* 权限管理、发布审核、操作审计、操作日志
>应用和配置的管理都有完善的权限管理机制，对配置的管理还分为了编辑和发布两个环节，从而减少人为的错误。

>所有的操作都有审计日志，可以方便地追踪问题

* 部署简单配
>置中心作为基础服务，可用性要求非常高，这就要求Rainbow对外部依赖尽可能地少

>目前唯一的外部依赖是MySQL，所以部署非常简单，只要安装好Java和MySQL就可以让Apollo跑起来

* 高可用架构设计

>当客户端启动成功则把配置项缓存到本地磁盘中,如果服务端挂掉则从本地缓存中读取配置项,作为兜底策略。



##
- admin/admin123  

## 演示图
<table>
    <tr>
       <td><img src="https://github.com/bigbigzhan/rainbow/raw/master/rainbow-server/images-folder/login.png"/></td>
       <td><img src="https://github.com/bigbigzhan/rainbow/raw/master/rainbow-server/images-folder/homepage.png"/></td>
    </tr>
    <tr>
           <td><img src="https://github.com/bigbigzhan/rainbow/raw/master/rainbow-server/images-folder/env.png"/></td>
           <td><img src="https://github.com/bigbigzhan/rainbow/raw/master/rainbow-server/images-folder/cells.png"/></td>
    </tr>
</table>
 
 
 快速使用  
 1.搭建服务端rainbow-server是服务端代码执行rainbow-server/sql并修改application-druid.yml数据库链接地址即可成功启动
 ****
 2.客户端引入pom
 ```xml
     <dependency>
             <groupId>com.github.bigbigzhan</groupId>
             <artifactId>rainbow-client</artifactId>
             <version>1.0.0.Final</version>
     </dependency>
 ```
****
3.客户端启动类添加注解
```java
@EnableRainbowConfigCenter
```
****

4.在配置文件中配置已下配置项  
   
 配置服务端地址   
  rainbow.address=127.0.0.1:9009  
 配置环境信息  
  rainbow.env=dev  
 配置引用的配置组  
  rainbow.groupsName=eureka-config,redis-config,common  
 配置缓存文件地址  
  rainbow.config.local.path=C://code
