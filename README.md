# tiny-url

#### 介绍
也许是能在往上找到的性能最高的开源的短网址服务.
无数据库, 简单配置则可以支持集群部署,提高负载能力,通常单机即可支持上万并发访问.

#### 软件架构
基于spring boot开发, 使用nginx作为负载, 提供高效的短网址服务.


#### 安装教程

1.  安装nginx程序.  
2.  修改application.yml配置文件,修改tiny.file.parh路径为文件存储路径  
3.  通过maven编译程序
4.  部署编译号的jar程序到本地

#### 使用说明

1.  使用http调用/api/tinyurl/create地址,将要生成短连接的url以post的方式传递服务.
    调用成功后会返回对应的json对象.
```json
{
"id": "1sSeqj", //生成的id
"tiny_url": "l.qdjinxin.net/1s/Se/qj", //生成短连接
"sync": true //集群模式是否同步成功
}
```