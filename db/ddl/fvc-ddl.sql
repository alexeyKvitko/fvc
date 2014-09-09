
DROP DATABASE IF EXISTS fvcDB;
CREATE DATABASE fvcDB;

CREATE TABLE modified_file (
	id serial NOT NULL,
    file_name varchar(150) NOT NULL,
    crc32 bigint,
    author varchar(150),
    revision int,
    commit_date timestamp 
    
);

CREATE TABLE history (
	id serial NOT NULL,
    file_name varchar(150) NOT NULL,
    revision int,
    author varchar(150),
    mod_date varchar(50),
    comment varchar(1000),
    cls_mtd varchar(300)
);



