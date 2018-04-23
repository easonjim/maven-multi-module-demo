# maven-multi-module-demo
Maven多模块项目样例以及构建教程  
目录结构：  
```
.
├── multi-module-center
│   ├── multi-module-center-app
│   │   ├── pom.xml
│   │   └── src
│   │       ├── main
│   │       │   └── java
│   │       │       └── com
│   │       │           └── jsoft
│   │       │               └── demo
│   │       │                   └── app
│   │       │                       └── Main.java
│   │       └── test
│   │           └── java
│   ├── multi-module-center-biz
│   │   ├── pom.xml
│   │   └── src
│   │       ├── main
│   │       │   └── java
│   │       │       └── com
│   │       │           └── jsoft
│   │       │               └── demo
│   │       │                   └── biz
│   │       │                       └── Service.java
│   │       └── test
│   │           └── java
│   ├── multi-module-center-common
│   │   ├── pom.xml
│   │   └── src
│   │       ├── main
│   │       │   └── java
│   │       │       └── com
│   │       │           └── jsoft
│   │       │               └── demo
│   │       │                   └── center
│   │       │                       └── common
│   │       │                           └── BizTools.java
│   │       └── test
│   │           └── java
│   ├── multi-module-center-webapp
│   │   ├── pom.xml
│   │   └── src
│   │       ├── main
│   │       │   ├── java
│   │       │   │   └── com
│   │       │   │       └── jsoft
│   │       │   │           └── demo
│   │       │   │               └── webapp
│   │       │   │                   └── Main.java
│   │       │   └── webapp
│   │       │       └── WEB-INF
│   │       │           └── web.xml
│   │       └── test
│   │           └── java
│   └── pom.xml
├── multi-module-common
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── com
│       │           └── jsoft
│       │               └── demo
│       │                   └── common
│       │                       └── Tools.java
│       └── test
│           └── java
└── pom.xml
```
##### 比较有意思的多模块项目框架，父POM的子模块可以为POM类型的项目，其余特点：  
1、multi-module-common为公共项目，但去除了parent节点，好处在于引用了这个公共模块的子项目，只需要install这个项目之后，在子项目直接编译打包，而无需install整个parent项目。但是随着而来的缺点也被暴露，比如全局的依赖配置无法使用、全局的参数配置无法使用等，参考：http://www.cnblogs.com/EasonJim/p/8303917.html  
2、针对上面的特点，可以使用Maven提供的命令在父项目下编译指定子模块，但不编译其它项目：  
```java
mvn clean compile -pl multi-module-center/multi-module-biz -am
```
参考：http://www.cnblogs.com/EasonJim/p/8350560.html  
3、如果在Jenkins上进行构建，可能还会触发其它子项目的构建，此时需要这样配置一下：http://www.cnblogs.com/EasonJim/p/8350578.html  
##### Maven多模块项目开发时的规范：
###### 包结构规范
```
xxx-center // 负责具体的业务处理。比如平台业务中心
 – xxx-center-api  // 服务接口及接口依赖的对象
 – xxx-center-biz  // 具体的业务逻辑
   -- xxx-center-biz-xxx // 这个看具体业务复杂度决定是否再细分，通常情况下该层不是必须的
 – xxx-center-common  // biz和deploy公共逻辑依赖的包，例如一些跟本业务非常相关的工具类，支持服务类
 – xxx-center-deploy(webapp/web/app)  // 部署包
xxx-common // 公共模块，不包含业务逻辑
```
###### 设计原则
+ 上层系统可以调用下层系统，下层系统不得调用上层系统  
+ 水平系统之间不能进行调用  