insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('PIX');

insert ignore into cozinha (nome) values ('Quitandas');
insert ignore into cozinha (nome) values ('Hamburguerias');

insert into restaurante (nome, descricao, cozinha_id) values ("fatinha", "empadas x", 1);
insert into restaurante (nome, descricao, cozinha_id) values ("galpao", "galpao do hamburguer", 2);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 1), (2, 2), (2, 3), (2, 4);

insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('11111111111', 'Josimar Viana', '61998287070', 'josimar@gmail.com', '1997-11-28T09:00:00-03:00');
insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('22222222222', 'Maria Silva', '22555554445', 'maria@gmail.com', '2000-11-28T09:00:00-03:00');
insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('33333333333', 'Jose Pereira', '22555554446', 'jose@gmail.com', '2000-11-28T09:00:00-03:00');

insert into produto (nome, descricao, preco, restaurante_id) values ('Empada Pimenta', 'Empada Pimenta', 30, 1);
insert into produto (nome, descricao, preco, restaurante_id) values ('Sanduiche Ixe', 'Sanduiche Ixe', 20, 2);
insert into produto (nome, descricao, preco, restaurante_id) values ('Sanduiche Promo', 'Sadcuiche Promo', 10, 2);

insert into pedido (data_solicitacao, data_atualizacao, valor_total, cliente_id, restaurante_id, data_confirmacao) values (utc_timestamp, utc_timestamp, 20.00, 1, 1, utc_timestamp);
insert into pedido (data_solicitacao, data_atualizacao, valor_total, cliente_id, restaurante_id, data_confirmacao) values (utc_timestamp, utc_timestamp, 20.00, 2, 2, utc_timestamp);

insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total, pedido_id) values (1, 1, 20, 20, 1);
insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total, pedido_id) values (2, 1, 10, 10, 2);
insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total, pedido_id) values (3, 1, 30, 30, 2);

insert into ocorrencia_entrega (pedido_id, descricao, data_registro) values (1, "Tentativa sem sucesso", utc_timestamp);
--insert into ocorrencia_entrega (pedido_id, descricao, data_registro) values (2, "Entrega realizada", utc_timestamp);

update cliente set logradouro="x", numero="71", bairro="centro", cidade_id=1, cep="38600000" where id = 1;
update cliente set logradouro="y", numero="72", bairro="centro", cidade_id=2, cep="38600000" where id = 2;
update cliente set logradouro="z", numero="73", bairro="centro", cidade_id=3, cep="38600000" where id = 3;

