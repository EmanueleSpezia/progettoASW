-- create database ricetteseguite;
-- GRANT ALL PRIVILEGES ON DATABASE ricetteseguite TO 'postgres'@'%' WITH GRANT OPTION;
   CREATE TABLE ricette( id serial not null, autore varchar (255), titolo varchar (255), preparazione varchar (255));
   CREATE TABLE connessioni( id serial not null, follower varchar (255), followed varchar (255));