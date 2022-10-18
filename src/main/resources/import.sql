insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('11111111111', 'Josimar Viana', '61998287070', 'josimar@gmail.com', '1997-11-28');
insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('22222222222', 'Maria Silva', '22555554445', 'maria@gmail.com', '2000-11-28');
insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('33333333333', 'Jose Pereira', '22555554446', 'jose@gmail.com', '2000-11-28');
insert into produto (nome, descricao, preco) values ('Sanduiche Ixe', 'Sancuiche Ixe', 20);
insert into produto (nome, descricao, preco) values ('Sanduiche Promo', 'Sancuiche Promo', 10);
insert into produto (nome, descricao, preco) values ('Sanduiche Mega', 'Sancuiche Mega', 30);
insert into pedido (data_criacao, data_atualizacao, valor_total, cliente_id) values (utc_timestamp, utc_timestamp, 20.00, 1);
insert into pedido (data_criacao, data_atualizacao, valor_total, cliente_id) values (utc_timestamp, utc_timestamp, 20.00, 2);
insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total) values (1, 1, 20, 20);
insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total) values (2, 1, 10, 10);
insert into item_pedido (produto_id, quantidade, preco_unitario, preco_total) values (3, 1, 30, 30);
--insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total) values (1, 1, 1, 20, 20);
insert into pedido_item_pedido (pedido_id, item_pedido_id) values (1, 1);
insert into pedido_item_pedido (pedido_id, item_pedido_id) values (1, 2);
insert into pedido_item_pedido (pedido_id, item_pedido_id) values (2, 3);
insert into restaurante (nome, descricao) values ("galpao", "galpao do hamburguer");
update cliente set endereco_logradouro="x", endereco_numero="71", endereco_bairro="centro", endereco_cidade="Paracatu", endereco_estado="MG", endereco_cep="38600000" where id = 1;
update cliente set endereco_logradouro="y", endereco_numero="72", endereco_bairro="centro", endereco_cidade="Paracatu", endereco_estado="MG", endereco_cep="38600000" where id = 2;
update cliente set endereco_logradouro="z", endereco_numero="73", endereco_bairro="centro", endereco_cidade="Paracatu", endereco_estado="MG", endereco_cep="38600000" where id = 3;