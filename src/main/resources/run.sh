#!/usr/bin/env sh

export JAVA_HOME=/home/soft/jdk1.8
export JRE_HOME=$JAVA_HOME/jre

## API_NAME 打包文件的名字 不带.jar
API_NAME=open
## 项目路径
SERVICE_DIR=/home/services/open
JAR_NAME=$API_NAME\.jar

#PID 进程文件
PID=$API_NAME\.pid

cd $SERVICE_DIR

# 提示使用方法
usage(){
  echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
  exit 1
}

#检查程序是否在运行
is_exist(){
  pid=`ps -ef | grep $JAR_NAME | grep -v grep | awk '{print $2}' `
  # 如果不存在返回1，存在返回0
  if [ -z "${pid}" ]; then
      return 1
  else
      return 0
  fi
}

#启动命令
start(){
  is_exist
  if [ $? -eq "0" ]; then
      echo ">>>${JAR_NAME} is already running PID=${pid} <<<"
  else
      nohup $JRE_HOME/bin/java -Xms128m -Xmx512m -Dloader.path=resources,lib -jar $JAR_NAME > /dev/null 2>&1 &
      echo $! > $PID
      echo ">>> start $JAR_NAME successed PID=$! <<<"
  fi
}

#停止方法
stop(){
  #is_exit
  pidf=$(cat $PID)
  #echo "$pidf"
  echo ">>> api PID = $pidf begin kill $pidf <<<"
  kill $pidf
  rm -rf $PID
  sleep 2
  is_exist
  if [ $? -eq "0" ]; then
      echo ">>> api 2 PID = $pid begin kill -9 $pid <<<"
      kill -9 $pid
      sleep 2
      echo ">>> $JAR_NAME process stopped <<<"
  fi
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
      echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
      echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#查看日志
log(){
  currDate=$(date "+%Y-%m-%d")
  tail -300f logs/$API_NAME/console.log
}

#重启
restart(){
  stop
  start
  log
}

# 根据输入的参数，选择执行对应的方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  "log")
    log
    ;;
  *)
    usage
    ;;
esac
exit 0