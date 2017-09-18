--Empresas

insert into empresa (id,descripcion,nombre,numeroempleados,actividad,fechaalta,ficticia,nombrecomercial,sector) values (1,'Esta es una descripcion de prueba','Empresa TFG',1,'Informatica','06/07/2017',true,'TFG S.A.','Educación');
insert into empresa (id,descripcion,nombre,numeroempleados,actividad,fechaalta,ficticia,nombrecomercial,sector) values (2,'Esta es la segunda empresa de prueba que dificilmente usaré','Empresa UAH',3,'Gestion','06/07/2017',true,'UAH S.A','Sector educacion');

--empleados

insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(1,'Rospigliosi Beltran','creador','Piero',1,0,'06/07/2017','25/12/1992',2000,90);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(2,'Sanchez','comercial','Pepe',1,1,'04/04/2017','28/07/1998',1500,80);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(3,'Admin','admin','Super',1,0,'04/04/2017','13/11/1994',3000,100);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(4,'Gonzales','oficinista','Raul',1,0,'02/05/2017','17/10/1990',30000,80);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(5,'Perez','oficinista','David',1,1,'04/03/2017','13/11/1992',3300,70);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(6,'Loranca','oficinista','Daniel',1,0,'04/07/2017','18/11/1993',31000,60);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(7,'Badillo','oficinista','Mario',1,1,'04/08/2017','13/10/1994',30300,50);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(8,'Toledano','oficinista','Alfredo',1,1,'09/09/2017','20/11/1998',6000,40);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(9,'Sanchez','oficinista','Blanca',1,1,'01/01/2017','07/11/1992',2000,10);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(10,'Diaz','oficinista','Beatriz',1,0,'04/02/2017','04/02/1994',5000,100);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(11,'Lanchipa','oficinista','Dora',1,0,'04/03/2017','11/09/1992',1000,99);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(12,'Hernandez','oficinista','Angello',1,1,'04/06/2017','13/11/1992',2000,70);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(13,'Cool','oficinista','Mister',1,0,'07/07/2017','13/10/1997',1500,50);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(14,'Yion','oficinista','Lee',1,1,'04/08/2017','12/11/1994',3000,35);
insert into empleado (id,apellidos,cargo,nombre,empresa_id,tipoempleado,fechaalta,fechanacimiento,salario,rentabilidad) values(15,'Advincula','oficinista','Diego',1,0,'04/09/2017','13/11/1994',3000,100);
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
