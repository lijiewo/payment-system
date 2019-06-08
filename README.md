

## 基于区块链的支付系统

2019北航软工大作业

# 0.项目简介:

本项目基于区块链数据共识且不可篡改的特点，将支付交易记录在链上，主要包含用户注册，支付，查询，用户查询等功能

# 1.开发环境:

(1)Intellij Idea 2019

(2)Geth1.9.0

(3)apache tomcat 9.0.20

(4)CentOS Linux release 7.1.1503 

# 2.部署节点

(1) 启动geth
```
geth --allow-insecure-unlock --rpc --rpcapi personal,db,eth,net,web3 --networkid 666666 console
```
(2) 配置自己的钱包

(3) 部署合约(contract.sol)

(4) 挖矿
```
miner.start()
```



