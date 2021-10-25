#!/bin/bash

set -e

echo '1. set server_id....'
sed -i '/\[mysqld\]/a server-id=1\nlog-bin=/var/log/mysql/mysql-bin\ngtid-mode=ON\nenforce-gtid-consistency=ON' /etc/mysql/mysql.conf.d/mysqld.cnf

echo '2. start mysql...'
service mysql start

echo '3. setting password...'
sed -i 's/MYSQLROOTPASSWORD/'$MYSQL_ROOT_PASSWORD'/' /mysql/privileges.sql
sed -i 's/MYSQLREPLICATIONUSER/'$MYSQL_REPLICATION_USER'/' /mysql/privileges.sql
sed -i 's/MYSQLREPLICATIONPASSWORD/'$MYSQL_REPLICATION_PASSWORD'/' /mysql/privileges.sql
mysql < /mysql/privileges.sql


echo '4. service mysql status'
echo 'mysql for tigerfive if ready...'

tail -f /dev/null