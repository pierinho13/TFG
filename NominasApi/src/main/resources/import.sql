--Empresas

insert into empresa (id,descripcion,nombre,numeroempleados,actividad,fechaalta,ficticia,nombrecomercial,sector) values (1,'Esta es una descripcion de prueba','Empresa TFG',1,'Informatica','06/07/2017',true,'TFG S.A.','Educación');
insert into empresa (id,descripcion,nombre,numeroempleados,actividad,fechaalta,ficticia,nombrecomercial,sector) values (2,'Esta es la segunda empresa de prueba que dificilmente usaré','Empresa UAH',3,'Gestion','06/07/2017',true,'UAH S.A','Sector educacion');

--empleados

insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento) values(1,'Rospigliosi Beltran','creador','Piero',1,0,'06/07/2017','25/12/1992');
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento) values(2,'Sanchez','comercial','Pepe',1,1,'04/04/2017','28/07/1998');
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento) values(3,'Admin','admin','Super',1,0,'04/04/2017','13/11/1994');

--usuarios

insert into usuario (id,esadmin,password,username,empresa_id,empleado_id,fechaalta,escomercial) values (1,true,'1234','piero',1,1,'06/07/2017',false);
insert into usuario (id,esadmin,password,username,empresa_id,empleado_id,fechaalta,escomercial) values (2,true,'1234','admin',1,3,'04/04/2017',false);
insert into usuario (id,esadmin,password,username,empresa_id,empleado_id,fechaalta,escomercial) values (3,false,'1234','comercial',1,2,'04/04/2017',true);

--role

insert into roleusuario (id,issuperadmin,nombre) values (1,true,'ROLE_ADMIN');
insert into roleusuario (id,issuperadmin,nombre) values (2,false,'ROLE_COMERCIAL');

--usuario_roles

insert into usuario_roles (usuario_id,roles_id) values (1,1);
insert into usuario_roles (usuario_id,roles_id) values (2,1);
insert into usuario_roles (usuario_id,roles_id) values (3,2);
