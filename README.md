# open
## 1. 概述

### 基于springboot的后台基础开发框架
### 基础实现功能

  - 登录
  - 权限认证
  - 验证码
  - 采用模板技术代码生成
  - 微信支付V2，V3实现
  - 海康威视API实现摄像头控制，摄像头基础操作，抓图
  - 防止重复提交
  - 日志处理
  - 限流处理
  - 阿里云接入
  - mqtt接入
  - 网易云信接口接入
  - minio接入
  - modbus实现
  - xxxjob接入(待清理)
  - zlmedkit视频处理接入
  - ONVIF协议实现摄像头控制，摄像头基础操作，抓图
  
### 所用技术栈

  - springboot
  - mybatis-plus
  - 阿里云文件存储，消息推送，sms短信发送，语音短信
  - ONVIF协议实现摄像头控制
  - modbus数据获取(待清理)
  - 网易云信接口接入
  - 微信支付，V2，V3
  - xxxJob基于代码的任务控制(待清理)
  - zlmedkit视频处理
  - minio
  - mqtt
  - mybatis-plus模板方式生成dao到controller包括测试代码
  - 引入therapi-runtime-javadoc,javadoc生成swagger
  - 引入alibaba easyexcel完成excel生成
  - satoken进行权限相关认证
  - axis代码引入(待清理)
  
  - 前后端分离
    
## 2. 支持平台

  - unix
  - windows

## 3. 其他

  - 视频相关采用onvif协议，计划加入国标
  - 采用github codeql分析代码
