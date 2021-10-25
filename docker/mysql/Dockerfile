FROM mysql:5.7.25
MAINTAINER tigerfive tigerfive@aliyun.com

ENV MYSQL_ALLOW_EMPTY_PASSWORD yes
COPY conf.sh /mysql/conf.sh
COPY privileges.sql /mysql/privileges.sql

CMD ["sh", "/mysql/conf.sh"]

# docker run  --name medoliasql -e MYSQL_ROOT_PASSWORD="1234567" -e MYSQL_MASTER_SERVICE_HOST="172.17.0.2" -e MYSQL_REPLICATION_USER='medolia' -e MYSQL_REPLICATION_PASSWORD="1234567"   -d medolia/mysql5.7-demo:1.0