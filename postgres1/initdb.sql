-- create database connessioni;
-- GRANT ALL PRIVILEGES ON DATABASE connessioni TO 'postgres'@'%' WITH GRANT OPTION;
   CREATE TABLE connessioni( id serial not null, follower varchar (255), followed varchar (255));