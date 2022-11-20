INSERT INTO estado (id, nome, sigla, ibge, pais, ddd) VALUES
(1, 'Acre', 'AC', 12, 1, '68'),
(2, 'Alagoas', 'AL', 27, 1, '82'),
(3, 'Amazonas', 'AM', 13, 1, '97,92'),
(4, 'Amapá', 'AP', 16, 1, '96'),
(5, 'Bahia', 'BA', 29, 1, '77,75,73,74,71'),
(6, 'Ceará', 'CE', 23, 1, '88,85'),
(7, 'Distrito Federal', 'DF', 53, 1, '61'),
(8, 'Espírito Santo', 'ES', 32, 1, '28,27'),
(9, 'Goiás', 'GO', 52, 1, '62,64,61'),
(10, 'Maranhão', 'MA', 21, 1, '99,98'),
(11, 'Minas Gerais', 'MG', 31, 1, '34,37,31,33,35,38,32'),
(12, 'Mato Grosso do Sul', 'MS', 50, 1, '67'),
(13, 'Mato Grosso', 'MT', 51, 1, '65,66'),
(14, 'Pará', 'PA', 15, 1, '91,94,93'),
(15, 'Paraíba', 'PB', 25, 1, '83'),
(16, 'Pernambuco', 'PE', 26, 1, '81,87'),
(17, 'Piauí', 'PI', 22, 1, '89,86'),
(18, 'Paraná', 'PR', 41, 1, '43,41,42,44,45,46'),
(19, 'Rio de Janeiro', 'RJ', 33, 1, '24,22,21'),
(20, 'Rio Grande do Norte', 'RN', 24, 1, '84'),
(21, 'Rondônia', 'RO', 11, 1, '69'),
(22, 'Roraima', 'RR', 14, 1, '95'),
(23, 'Rio Grande do Sul', 'RS', 43, 1, '53,54,55,51'),
(24, 'Santa Catarina', 'SC', 42, 1, '47,48,49'),
(25, 'Sergipe', 'SE', 28, 1, '79'),
(26, 'São Paulo', 'SP', 35, 1, '11,12,13,14,15,16,17,18,19'),
(27, 'Tocantins', 'TO', 17, 1, '63');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('PIX');

insert into cozinha (nome) values ('Quitandas');
insert into cozinha (nome) values ('Hamburguerias');

insert into restaurante (cnpj, nome, descricao, cozinha_id) values ('07490497000113', 'fatinha', 'empadas x', 1);
insert into restaurante (cnpj, nome, descricao, cozinha_id) values ('07490497000112', 'galpao', 'galpao do hamburguer', 2);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 1), (2, 2), (2, 3), (2, 4);

insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('11111111111', 'Josimar Viana', '61998287070', 'josimar@gmail.com', '1997-11-28T09:00:00-03:00');
insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('22222222222', 'Maria Silva', '22555554445', 'maria@gmail.com', '2000-11-28T09:00:00-03:00');
insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('33333333333', 'Jose Pereira', '22555554446', 'jose@gmail.com', '2000-11-28T09:00:00-03:00');

insert into produto (nome, descricao, preco, restaurante_id) values ('Empada Pimenta', 'Empada Pimenta', 30, 1);
insert into produto (nome, descricao, preco, restaurante_id) values ('Sanduiche Ixe', 'Sanduiche Ixe', 20, 2);
insert into produto (nome, descricao, preco, restaurante_id) values ('Sanduiche Promo', 'Sadcuiche Promo', 10, 2);

insert into pedido (data_solicitacao, data_atualizacao, valor_total, cliente_id, restaurante_id ,status_pedido) values ('2020-11-11', '2020-11-11', 20.00, 1, 1, 'PENDENTE');
insert into pedido (data_solicitacao, data_atualizacao, valor_total, cliente_id, restaurante_id ,status_pedido) values ('2020-11-11', '2020-11-11', 20.00, 2, 2, 'PENDENTE');

insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total, pedido_id) values (1, 1, 20, 20, 1);
insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total, pedido_id) values (2, 1, 10, 10, 2);
insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total, pedido_id) values (3, 1, 30, 30, 2);

insert into ocorrencia_entrega (pedido_id, descricao, data_registro) values (1, 'Tentativa sem sucesso', '2020-11-11');
--insert into ocorrencia_entrega (pedido_id, descricao, data_registro) values (2, 'Entrega realizada', '2020-11-11');

update cliente set logradouro='x', numero='71', bairro='centro', cidade_id=1, cep='38600000' where id = 1;
update cliente set logradouro='y', numero='72', bairro='centro', cidade_id=2, cep='38600000' where id = 2;
update cliente set logradouro='z', numero='73', bairro='centro', cidade_id=3, cep='38600000' where id = 3;

