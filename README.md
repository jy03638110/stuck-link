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
    1.  源码基础上修改配置文件application.yml
    2.  在stuck-link目录编译整个项目：mvn clean package
    3.  运行docker命令：docker build，构建docker镜像
3.  已编译jar包部署
    1.  下载最新稳定运行发布包：[stuck-link-redirect.jar](https://gitee.com/Ddull/stuck-link/attach_files/387989/download)
    2.  调整配置文件application.yml
    3.  部署运行

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
    1.  稍后完善统计功能

#### 配置说明

1. stuck.address为本开源项目gitee地址，也是默认短链不存在跳转地址
2. stuck.server-path为短链部署服务器地址，用于生成短链长地址，配置错误无法通过短链还原真实地址！
3. 默认禁用访问统计图形API接口，通过设置参数：link.charts.api-enabled进行启用
4. 默认禁用异步任务接口，通过设置参数：link.async.enabled进行启用
5. 默认采用内存缓存管理器，通过设置参数：link.cache.type进行设置，现支持memory（不支持缓存有效时间）和redis两类缓存管理器
6. 默认短链code长度为6位，通过设置参数：link.code-length调整参数长度
7. link.accessToken配置用于接口暴露验证访问权限，默认为空

#### 开发说明

项目采用传统生产者、管道、消费者为模型，另提供灵活的缓存管理器

1. 构建接口：Builder，用于将用户HttpServletRequest请求构建成传输对象
   1. 默认实现：MessageBuilder，将HttpServletRequest构造成消息
2. 生产者接口：Producer，需要绑定对应的Builder，用于将传输对象写入管道
   1. 默认实现：MessageProducer，将消息写入管道
3. 管道接口：Pipeline，管道采用队列的结构，提供入队和出队的方法，另支持获取管道中对象长度
   1. 默认实现：MessagePipline，消息管道抽象类，提供对消息的入队、出队方法
   2. 实现类：ArrayBlockingQueuePipeline，底层采用ArrayBlockingQueue实现管道操作，采用相同读写锁，性能较差
   3. 实现类：LinkedBlockingQueuePipeline，底层采用LinkedBlockingQueue实现管道操作，采用单独的读写锁，性能较好
4. 消费者接口：Consumer，用于传输对象
   1. 默认实现：MessageConsumer，用于消费消息对象
   2. 实现类：SimpleMessageConsumer，将消息对象System.out.println输出
   3. 实现类：DataBaseMessageConsumer，将消息对象写入数据库

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request

#### 问题排查

欢迎多多提问，项目持续更新，你的提问和支持是对我们的最大鼓励！