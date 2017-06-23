--Empresas

insert into empresa (id,descripcion,nombre,numeroempleados) values (1,'Esta es una descripcion de prueba','empresa TFG',1);
insert into empresa (id,descripcion,nombre,numeroempleados) values (2,'Esta es la segunda empresa de prueba que dificilmente usare','empresa UAH',3);

--empleados

insert into empleado (id,apellidos,cargo,nombre,empresa_id) values(1,'rospigliosi beltran','creador','piero',1);
insert into empleado (id,apellidos,cargo,nombre,empresa_id) values(2,'sanchez','comercial','pepe',1);
insert into empleado (id,apellidos,cargo,nombre,empresa_id) values(3,'lopez','soporte','juan',1);

--usuarios

insert into usuario (id,esadmin,password,username,empresa_id) values (1,true,'1234','piero',1);
insert into usuario (id,esadmin,password,username,empresa_id) values (2,true,'1234','admin',1);
insert into usuario (id,esadmin,password,username,empresa_id) values (3,true,'1234','comercial',1);

--role

insert into roleusuario (id,issuperadmin,nombre) values (1,true,'ROLE_ADMIN');
insert into roleusuario (id,issuperadmin,nombre) values (2,false,'ROLE_COMERCIAL');

--usuario_roles

insert into usuario_roles (usuario_id,roles_id) values (1,1);
insert into usuario_roles (usuario_id,roles_id) values (2,1);
insert into usuario_roles (usuario_id,roles_id) values (3,2);
