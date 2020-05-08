# stuck-link

#### 介绍
stuck-link是一个以生产者、管道、消费者为模型，支持高并发和易于二次开发的短链转发、匿名访问、访问统计框架，提供了灵活的默认配置，支持二维码生成，易于部署。

demo网站：[http://stuck.top](http://stuck.top/)

<p align="center">
    <a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
        <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" />
    </a>
    <a target="_blank" href="https://gitee.com/Ddull/stuck-link">
        <img src="https://img.shields.io/badge/Docs-latest-blue.svg"/>
    </a>
    <a target="_blank" href="LICENSE">
        <img src="https://img.shields.io/:license-MIT-blue.svg">
    </a>
</p>

#### 软件架构

- ├─ db 数据库版本管理
- ├─ stuck-link-admin 前端管理系统
- ├─ stuck-link-charts 图形接口模块
- ├─ stuck-link-core 核心模块
- ├─ stuck-link-redirect 短链接口执行器模块（主体功能）


#### 安装教程

1.  源码编译部署
    1.  修改stuck-link-redirect下配置文件application.yml中，数据源，缓存源。
    2.  编译stuck-link-redirect模块：mvn clean package
    3.  将stuck-link-redirect模块target目录下stuck-link-redirect.jar放至服务器，运行jar包，启动服务：nohup java -jar stuck-link-redirect.jar &
2.  docker部署stuck-link-redirect服务
3.  已编译jar包部署
    1.  

#### 使用说明

1.  短链功能
    1.  短链重定向：http://stuck.top/zaeJ9t
    2.  生成短链：http://stuck.top/short?www.taobao.com 或 http://stuck.top/short?http://www.taobao.com
    3.  短链还原真实地址：http://stuck.top/restore?http://stuck.top/zaeJ9t或http://stuck.top/restore?stuck.top/zaeJ9t
2.  短链二维码
    1.  二维码接口：http://link.stuck.top/img/zaeJ9t
3.  匿名访问
    1.  http://link.stuck.top?www.baidu.com 或 http://link.stuck.top?http://www.baidu.com
4.  访问统计
5.  配置调整
    - 默认禁用访问统计图形API接口，通过设置参数link.charts.api-enabled进行启用
    - 默认禁用异步任务接口，通过设置参数link.async.enabled进行启用
    - 默认采用内存缓存管理器，通过设置参数link.cache.type进行设置，现支持memory（不支持缓存有效时间）和redis两类缓存管理器

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request

#### 问题排查




#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
