FROM mysql:5.7.25
MAINTAINER medolia

ENV MYSQL_ALLOW_EMPTY_PASSWORD yes
COPY conf.sh /mysql/conf.sh
COPY privileges.sql /mysql/privileges.sql
COPY schema.sql /mysql/schema.sql

CMD ["sh", "/mysql/conf.sh"]

# docker build -t medolia/mysql5.7:1.0 .
# docker run --name medoliasql -p 3306:3306 -e MYSQL_ROOT_PASSWORD="1234567" -e MYSQL_REPLICATION_USER="medolia" \
# -e MYSQL_REPLICATION_PASSWORD="1234567" -d medolia/mysql5.7:1.0
# 本地客户端连接 localhost:13306 账号/密码 repl/1234567

