# 使用RocketMQ实现最终一致性

## 引言

原理部分参考[https://blog.csdn.net/qq_41124175/article/details/136200431](https://blog.csdn.net/qq_41124175/article/details/136200431)进行学习!

## 环境部署

> 创建项目所需文件夹

`./init.sh`

> 编辑.env文件

修改.env文件中的HOME变量为项目路径

> 编辑docker/conf/broker.conf

修改namesrvAddr和brokerIP1为本地IP

```yml
# namesrv地址
namesrvAddr = 172.16.7.1:9876
# 设置broker节点所在服务器的ip地址 
brokerIP1 = 172.16.7.1
```

> 使用docker-compose运行

```shell
cd docker && source .env && docker-compose up -d
```

> 检查环境是否正常

注意：MySQL的端口为本地的3307

```shell
NAME         IMAGE                           COMMAND                  SERVICE            CREATED         STATUS         PORTS
mysql        mysql:5.7                       "docker-entrypoint.s…"   mysql              6 minutes ago   Up 6 minutes   33060/tcp, 0.0.0.0:3307->3306/tcp
nacos        nacos/nacos-server:1.1.4        "bin/docker-startup.…"   nacos              6 minutes ago   Up 6 minutes   0.0.0.0:8848->8848/tcp
rmqadmin     styletang/rocketmq-console-ng   "sh -c 'java $JAVA_O…"   rocketmq-console   6 minutes ago   Up 6 minutes   0.0.0.0:8080->8080/tcp
rmqbroker    rocketmqinc/rocketmq:4.4.0      "sh mqbroker -c /opt…"   rocketmq_broker    6 minutes ago   Up 6 minutes   0.0.0.0:10909->10909/tcp, 9876/tcp, 0.0.0.0:10911->10911/tcp
rmqnamesrv   rocketmqinc/rocketmq:4.4.0      "sh mqnamesrv"           rocketmq_namesrv   6 minutes ago   Up 6 minutes   10909/tcp, 0.0.0.0:9876->9876/tcp, 10911/tcp
```

> 接口测试

需求: 创建订单(order-api),增加用户余额(account-api)

运行`account-api,order-api,mq-gateway`

```shell
localhost:9999/order/create

body:
{
    "accountCode": "hjl",
    "productCode": "P002",
    "count": 1,
    "amount": 1000.00
}
```
