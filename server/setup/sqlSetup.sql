CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
CREATE DATABASE 'database1';
GRANT ALL PRIVILEGES ON database1 . * TO 'admin'@'localhost';

