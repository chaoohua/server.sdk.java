server-sdk-java
=================

Rong Cloud Server SDK in Java.

# 版本说明
为方便开发者的接入使用，更好的对融云 Server SDK 进行维护管理，融云 Server SDK 2.0 统一规范了命名及调用方式，结构更加清晰。老版本的 Server SDK 已经切入v1分支，仍然可以使用，但不会再做新的功能更新。
如果您是新接入的开发者，建议您使用 Server SDK 2.0 版本。 对已集成使用老版本 Server SDK 的开发者，不能直接升级使用，强烈建议您重新测试后使用
# API文档
- 官方文档(http://www.rongcloud.cn/docs/server.html)

# 使用教程
* 请参考 Example 里面提供了所有的 API 接口的调用用例。

## 高级API接口

### User
- getToken  获取 Token 
- refresh  刷新用户信息
  #### black
  * add 添加用户到黑名单
  * remove 从黑名单中移除用户
  * getList 获取某用户的黑名单列表
  #### block
  * add 封禁用户
  * remove 解除用户封禁
  * getList 获取被封禁用户
  #### online-status
  * check 检查用户在线状态 

### Message
- 消息发送
  #### private
   - publish  发送单聊消息
   - publishTemplate  发送单聊模板消息
  #### system
    - publish  发送系统消息
    - publishTemplate  发送系统模板消息
  #### group
    - publish  发送群组消息
  #### discussion 
    - publish  发送讨论组消息
  #### chatroom
    - publish  发送聊天室消息
  #### broadcast
    - publish  发送广播消息
  #### history
    - get  消息历史记录下载地址获取 消息历史记录下载地址获取。获取 APP 内指定某天某小时内的所有会话消息记录的下载地址
    - remove  消息历史记录删除

### SensitiveWord
- add  添加敏感词
- delete  移除敏感词
- batchDelete 批量移除敏感词
- getList  查询敏感词列表

### Group
- create  创建群组
- sync  同步用户所属群组
- refresh  刷新群组信息
- join  将用户加入指定群组，用户将可以收到该群的消息，同一用户最多可加入 500 个群，每个群最大至 3000 人
- query  查询群成员
- quit  退出群组
- dismiss  解散群组。
  #### gag
  * addGagUser  添加禁言群成员
  * lisGagUser  查询被禁言群成员
  * rollBackGagUser  移除禁言群成员


### Chatroom
- create  创建聊天室
- join  加入聊天室
- query  查询聊天室信息
- getMembers  查询聊天室内用户
- destroy  销毁聊天室
- stopDistribution  聊天室消息停止分发
- resumeDistribution  聊天室消息恢复分发
  #### Gag
  - 局部禁言与全局禁言
    ##### Member
    - add
    - remove
    - getList
    ##### Global
    - add  添加禁言聊天室成员
    - getList  查询被禁言聊天室成员
    - remove  移除禁言聊天室成员
  #### Block
    - add  添加封禁聊天室成员
    - remove  移除封禁聊天室成员
    - getList  查询被封禁聊天室成员
  #### WhiteList
    - add  添加聊天室白名单成员
    - remove
    - getList 
  #### Priority
    - add
    - remove
    - getList
  #### Keeplive
    - add
    - remove
    - getList
### Push
- setUserTag  添加 Push 标签
- send  广播消息

### SMS
- 验证码和通知类短信
  ##### Code
  - getImage  获取图片验证码
  - send  发送短信验证码
  - verify  验证码验证
  ##### Notify
  - send  发送通知类短信
