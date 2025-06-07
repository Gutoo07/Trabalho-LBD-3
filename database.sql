create database TrabalhoLabBd3
go
use TrabalhoLabBd3
go
------------------------------
use master
go
drop database TrabalhoLabBd3
go
create database TrabalhoLabBd3
go
use TrabalhoLabBd3


insert into ingrediente (apresentacao, nome) values
('ap1' , 'ing1')
go
insert into tipo (nome) values
('tipo 1')
go
insert into prato values
('P0001', 'prato1', 1, 10.0, 1)
go
insert into prato_ingrediente values
(1, 1, 'P0001')
go
delete from prato

select * from cliente
select * from ingrediente
select * from pedido
select * from pedido_prato
select * from prato
select * from prato_ingrediente
select * from tipo

