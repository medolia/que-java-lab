#!/bin/bash

# 若命令不返回 0 则立即结束
set -e

echo '1. set server_id....'
# mysqld.cnf 文件插入新内容
sed -i '/\[mysqld\]/a server-id=1\nlog-bin=/var/log/mysql/mysql-bin\ngtid-mode=ON\nenforce-gtid-consistency=ON' /etc/mysql/mysql.conf.d/mysqld.cnf

echo '2. start mysql...'
service mysql start

echo '3. setting password...'
# privileges.sql 替换字符串内容
sed -i 's/MYSQLROOTPASSWORD/'$MYSQL_ROOT_PASSWORD'/' /mysql/privileges.sql
sed -i 's/MYSQLREPLICATIONUSER/'$MYSQL_REPLICATION_USER'/' /mysql/privileges.sql
sed -i 's/MYSQLREPLICATIONPASSWORD/'$MYSQL_REPLICATION_PASSWORD'/' /mysql/privileges.sql
mysql < /mysql/privileges.sql

echo '3.1 database & table creat'
# 新建库，插入表
mysql < /mysql/schema.sql


echo '4. service mysql status'
echo 'mysql for medoliasql if ready...'

tail -f /dev/null
