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
select * from prato order by id asc
select * from prato_ingrediente
select * from tipo

delete from prato

-- Insert Valores
INSERT INTO tipo (nome) VALUES 
('Lanches'),
('Bebidas');

INSERT INTO ingrediente (nome, apresentacao) VALUES 
('Pão de hambúrguer', 'Unidade'),
('Carne bovina', '150g'),
('Queijo cheddar', 'Fatia'),
('Pão de hot dog', 'Unidade'),
('Salsicha', 'Unidade'),
('Molho especial', 'Colher de sopa');

INSERT INTO prato (id, nome, tamanho_porcao, valor, tipo_id) VALUES 
('P0001', 'Hambúrguer Artesanal', 2, 22.50, 2),
('P0002', 'Hot Dog Tradicional', 1, 14.00, 2);

INSERT INTO prato_ingrediente (prato_id, ingrediente_id, qtd) VALUES 
('P0001', 2, 1),  -- pão hambúrguer
('P0001', 3, 1),  -- carne
('P0001', 4, 2),  -- cheddar

('P0002', 5, 1),  -- pão hot dog
('P0002', 6, 1),  -- salsicha
('P0002', 7, 1);  -- molho

INSERT INTO cliente (cpf, nome, telefone, end_log, end_num, end_cep, end_ponto_ref, senha) VALUES 
('98765432100', 'Maria Oliveira', '11988887777', 'Av. Central', '456', '04567000', 'Ao lado da padaria' , 40028922);


INSERT INTO pedido (dia_hora, valor_total, cliente_id) VALUES 
(GETDATE(), 36.50, '98765432100');

INSERT INTO pedido_prato (pedido_id, prato_id) VALUES 
(1, 'P0001'),
(1, 'P0002');




