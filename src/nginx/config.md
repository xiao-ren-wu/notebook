## NGINX配置

### 规则

1. 配置文件由指令与指令块构成
2. 每条指令以;号结尾，指令与参数之间以空格符号分隔
3. 指令以{ }大括号将多条指令组织在一起
4. include语句允许组合多个配置文件以提高可维护性
5. 使用#符号添加注释
6. 使用$符号使用变量
7. 部分指令的参数支持正则表达式

### 参数

#### 时间

|  ms  | milliseconds |  d   |     days     |
| :--: | :----------: | :--: | :----------: |
|  s   |   seconds    |  w   |    weeks     |
|  m   |   minutes    |  M   | month/30days |
|  h   |    hours     |  y   | year/365days |

#### 空间

| 默认 |   bytes   | k/K  | kilobytes |
| :--: | :-------: | ---- | :-------: |
| m/M  | megabytes | g/G  | gigabytes |

#### http配置的指令块

![](H:\notebook\src\images\blocks.png)



1. 在http大括号中包含的所有指令都代表有http协议去解析执行。
2. upstream表示上游服务，一般与tomcat，jetty等服务器交互使用。
3. server对应一个或者一组域名
4. location对应一个URL表达式

### 命令行

1. 格式：nginx -s reload
2. 帮助：-？ -h
3. 使用指定配置文件：-c
4. 指定配置命令：-g
5. 指定运行目录：-p
6. 发送信号：-s 
   1. 立刻停止服务：stop
   2. 优雅的停止服务：quit
   3. 重载配置文件：reload
   4. 重新开始记录日志文件：reopen
7. 测试配置文件是否有语法错误：-t -T
8. 打印NGINX的版本信息，编译信息等：-v -V

#### 日志切割

1. 手动切割

~~~bash
root@localhost nginx]# cd logs/
[root@localhost logs]# ls
access.log  error.log  nginx.pid
#1.复制源日志文件
[root@localhost logs]# mv access.log access.log.bak
#2.生成新的日志文件
[root@localhost logs]# ../sbin/nginx -s reopen
[root@localhost logs]# ls
access.log  access.log.bak  error.log  nginx.pid
~~~

2. 使用脚本自定完成定时切割日志文件

~~~bash
 #/bin/bash
 #Rotate the Nginx logs to prevent a single logfile from consuming too much disk space
  LOGS_PATH=/developer/nginx/logs/history
  CUR_LOGS_PATH=/developer/nginx/logs
  YESTERDAY=${date -d "yesterday" +%Y-%m-%d}
  mv ${CUR_LOGS_PATH}/access.log ${LOGS_PATH}/access_${YESTERDAY}.log
  kill -USR1 ${cat /usr/local/openrestry/nginx/logs/nginx.pid}
~~~

