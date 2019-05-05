# :golf:NGINX

## 安装NGINX

- 从nginx官网下载最新稳定版nginx源码，[传送门](nginx.org)

nginx配置文件使用vim打开不能根据nginx语法变色，需要将nginx目录下`contrib/vim`提供的所有文件拷贝到家目录的`./vim`文件夹中。

~~~bash
$ cd ~
$ mkdir .vim
$ cd nginx解压后的文件夹
$ cp -r contrib/vim/* ~/.vim/
~~~

- 指定NGINX安装目录为`/developer/nginx`

~~~bash
$ ./configure --prefix=/developer/nginx
~~~

出现错误：

~~~
checking for OS
 + Linux 3.10.0-862.el7.x86_64 x86_64
checking for C compiler ... not found

./configure: error: C compiler cc is not found
~~~

	1. 安装gcc

~~~bash
$ yum -y install gcc
~~~

出现错误：

~~~bash
./configure: error: the HTTP rewrite module requires the PCRE library.
~~~

2. 安装pcre-devel

~~~bash
$ yum install -y pcre-devel
~~~

出现错误：

~~~bash
./configure: error: the HTTP gzip module requires the zlib library.
~~~

3. 安装zlib-devel

~~~bash
yum install -y zlib-devel
~~~

再次执行

成功后出现：`./configure --prefix=/developer/nginx`

~~~bash
Configuration summary
  + using system PCRE library
  + OpenSSL library is not used
  + using system zlib library
  # 下面为nginx运行时目录
  nginx path prefix: "/developer/nginx"
  nginx binary file: "/developer/nginx/sbin/nginx"
  nginx modules path: "/developer/nginx/modules"
  nginx configuration prefix: "/developer/nginx/conf"
  nginx configuration file: "/developer/nginx/conf/nginx.conf"
  nginx pid file: "/developer/nginx/logs/nginx.pid"
  nginx error log file: "/developer/nginx/logs/error.log"
  nginx http access log file: "/developer/nginx/logs/access.log"
  nginx http client request body temporary files: "client_body_temp"
  nginx http proxy temporary files: "proxy_temp"
  nginx http fastcgi temporary files: "fastcgi_temp"
  nginx http uwsgi temporary files: "uwsgi_temp"
  nginx http scgi temporary files: "scgi_temp"
~~~

安装完成后，当前目录下会出现`objs`目录用来存放中间文件。

~~~bash
[root@localhost objs]# cd objs/;ll
-rw-r--r--. 1 root root 17763 5月   5 19:36 autoconf.err
-rw-r--r--. 1 root root 40144 5月   5 19:36 Makefile
-rw-r--r--. 1 root root  6804 5月   5 19:36 ngx_auto_config.h
-rw-r--r--. 1 root root   657 5月   5 19:36 ngx_auto_headers.h
-rw-r--r--. 1 root root  5856 5月   5 19:36 ngx_modules.c
drwxr-xr-x. 9 root root    91 5月   5 19:36 src
~~~

`ngx_moudules.c`决定接下来编译的时候，会有哪些模块会被编译到NGINX中。

- 执行`make`编译

~~~bash
$ make
~~~

编译完成后再次进入`objs`目录中，

C语言编译时生成的中间文件都会存放在`objs/src`目录下

~~~bash
[root@localhost objs]# cd src
[root@localhost src]# ls
core  event  http  mail  misc  os  stream
[root@localhost src]# cd http/
[root@localhost http]# ls
modules                            ngx_http_request.o
ngx_http_copy_filter_module.o      ngx_http_script.o
ngx_http_core_module.o             ngx_http_special_response.o
ngx_http_file_cache.o              ngx_http_upstream.o
ngx_http_header_filter_module.o    ngx_http_upstream_round_robin.o
ngx_http.o                         ngx_http_variables.o
ngx_http_parse.o                   ngx_http_write_filter_module.o
ngx_http_postpone_filter_module.o  v2
ngx_http_request_body.o
~~~

- 执行`make install`

- 测试是否安装成功

  1. 进入刚才`./configure`时指定的安装目录，进入`sbin`指定`./nginx`

  2. nginx默认监听的端口是80,查看端口状态

~~~bash
[root@localhost sbin]# netstat -nplt | grep 80
tcp     0     0 0.0.0.0:80        0.0.0.0:*         LISTEN     7084/nginx: master  
~~~

3. 浏览器访问

![](H:\notebook\src\images\hello.png)

*安装完成！！！老铁没毛病奥~*

























