# version: 0.0.1
FROM ubuntu:14.04
MAINTAINER Medolia
RUN ["apt-get", "update"]
RUN ["apt-get", "install", "-y", "nginx"]
RUN ["echo", "Hi, I am in your container", ">","/usr/share/ngix/html/index.html"]
# ENTRYPOINT 容器 RUN 时的参数会传递给这里指定的命令里
ENTRYPOINT ["/usr/sbin/nginx", "-g", "daemon off;"]
EXPOSE 80
