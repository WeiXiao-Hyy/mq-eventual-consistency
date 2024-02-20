# 使用RocketMQ实现最终一致性

## 引言

原理部分参考[https://blog.csdn.net/qq_41124175/article/details/136200431](https://blog.csdn.net/qq_41124175/article/details/136200431)进行学习

## 环境部署

### 创建项目所需文件夹

```shell
./init.sh
```

### 创建nacos_config数据库

连接mysql数据库，导入nacos-mysql.sql

### 编辑.env文件

修改.env文件中的HOME变量为项目路径

### 编辑docker/conf/broker.conf

修改namesrvAddr和brokerIP1为本地IP

```yml
# namesrv地址
namesrvAddr = 172.16.7.1:9876
# 设置broker节点所在服务器的ip地址 
brokerIP1 = 172.16.7.1
```

### 使用docker-compose运行

```shell
cd docker && docker-compose up -d
```

### 测试

PS:虽然不符合逻辑,但是也能学习使用了

需求: 创建订单(order-api),增加用户余额(account-api)

```shell
mvn install 编译项目

使用postman等工具 -> localhost:8013/order/create 

body:
{
    "accountCode": "hjl",
    "productCode": "P002",
    "count": 1,
    "amount": 1000.00
}
```
