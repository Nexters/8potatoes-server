CREATE USER 'master'@'%' IDENTIFIED BY 'master';
GRANT ALL PRIVILEGES ON *.* TO 'master'@'%';
FLUSH PRIVILEGES;

--TODO: table setting
drop table IF EXISTS test;
