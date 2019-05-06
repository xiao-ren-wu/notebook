## NGINX搭建服务器

### 静态资源服务器

1. 在nginx目录下新建一个文件夹用于存放静态资源，这里以创建一个static文件夹为例，将静态资源文件拷贝到该目录。

2. 配置`nginx.conf`文件

   1. server模块

   ~~~bash
   server {
           listen       8080; #配置监听的端口
           server_name  test; #项目名称
   
           #charset koi8-r;
   
           #access_log  logs/host.access.log  main;
   
           location / {
               alias  static/; # 静态资源的路径
               #root   html; #一般情况下不使用root的形式，不安全
               #index  index.html index.htm;
           }
   
           #error_page  404              /404.html;
   
           # redirect server error pages to the static page /50x.html
           #
           error_page   500 502 503 504  /50x.html;
           location = /50x.html {
               root   html;
           }
   }
   ~~~

   2. http模块

      可以在http模块中开启压缩功能，减少发送内容的大小

   ~~~bash
   # 对传输的静态资源进行压缩
   gzip  on;
   #对于大小小于1字节的文件直接发送不进行压缩
   gzip_min_length 1;
   #压缩的级别
   gzip_comp_level 2;
   #压缩的文件类型
   ~~~

![](https://github.com/xiao-ren-wu/notebook/blob/master/src/images/static-web.png)

#### 将指定目录分享给用户

在location开启`autoindex`

~~~bash
location / {
            alias  static/;
            autoindex on;
        }
~~~

访问方法：`${url}`/指定目录

![](https://github.com/xiao-ren-wu/notebook/blob/master/src/images/file.png)

在大量用户访问静态资源服务器的时候，会给服务器的带宽带来较大的压力，我们可以通过限制服务器向客户端发送数据的速度来节约带宽。

~~~bash
location / {
            alias  static/;
            autoindex on;
           #设置向浏览器发送数据的最大带宽
            set $limit_rate 1k;
            #index  index.html index.htm;
        }
~~~

#### Nginx日志配置

http模块中的log_format记录日志配置规则：

~~~bash
log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                       '$status $body_bytes_sent "$http_referer" '
                        '"$http_user_agent" "$http_x_forwarded_for"';
~~~

在server模块中规定日志的存放位置以及名称

~~~bash
access_log  logs/host.access.log  main;
~~~

### 用NGINX搭建一个具备缓存功能的反向代理服务器

1. 首先设置上游服务器不能让公网访问，

   ​	在server节点的listener监听添加指定的ip地址，让该服务只能由特定的ip访问。

   ~~~bash
   listen      127.0.0.1:8080;
   ~~~

2. 重启nginx，访问原来的静态网站。

~~~bash
[root@localhost conf]# ../sbin/nginx -s stop
[root@localhost conf]# ../sbin/nginx 
~~~

![](https://github.com/xiao-ren-wu/notebook/blob/master/src/images/refuse.png)

3. 安装一个openresty。

   安装和nginx一毛一样，不会[戳我](https://www.runoob.com/w3cnote/openresty-intro.html)

本人安装在/developer/openresty目录下。进入他的nginx模块，修改conf下的nginx.conf文件。

~~~bash
	#上游服务器的路由地址
	upstream local{
        server 127.0.0.1:8080;
   }

    server {
        listen       80;#代理服务器监听的端口
        server_name  opentest;#代理服务器名称

        location / {
        	#默认的配置，上有服务器得到请求的ip等信息都是代理服务器的。
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			#设置代理服务器要代理的upstream
            proxy_pass http://local;
        }
	}
~~~

4. 开启openresty------->sbin/nginx

浏览器访问代理服务器地址

![](https://github.com/xiao-ren-wu/notebook/blob/master/src/images/roxy.png)

上面location中的参数详情戳[我](http://nginx.org/en/docs/http/ngx_http_proxy_module.html)









































