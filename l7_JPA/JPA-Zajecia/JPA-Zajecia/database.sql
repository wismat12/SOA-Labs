create database paw_hibernate_zajecia;
use  paw_hibernate_zajecia;
create table student (
	id int auto_increment not null primary key,
    imie varchar(20) not null,
    nazwisko varchar(30) not null,
    created_at timestamp not null,
    updated_at timestamp)engine=innodb;
