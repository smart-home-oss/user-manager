#!/usr/bin/env bash

# https://dev.mysql.com/doc/refman/8.0/en/connecting.html

mysql -u root -proot -h localhost -P 8306 --protocol=TCP -e "CREATE DATABASE smart_home;"