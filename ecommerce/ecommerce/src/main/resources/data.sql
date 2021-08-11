 insert into usuario (email, senha) values ('caio@email.com', '$2a$10$QhciAyk/pdM3IYE.t9Y.ROaBLkkjdQy7mqZxZF8CPgHDxQkYyiEIG')
 insert into usuario (email, senha) values ('jose@email.com', '$2a$10$QhciAyk/pdM3IYE.t9Y.ROaBLkkjdQy7mqZxZF8CPgHDxQkYyiEIG')

insert into categoria (nome, categoria_mae_id) values ('Tecnologia', null), ('Smartphone', 1), ('Andorid', 2), ('IOS', 2), ('Notebook', 1)

insert into produto (descricao, instante, nome, quantidade, valor, categoria_id, usuario_id) values ('Um belo redmi note 10', '2021-04-09 13:01:39.527087', 'Redmi note 10', 250, 1100.00, 2, 1 )

insert into caracteristicas (descricao, nome, produto_id) values ('OLED', 'Tela', 1)
insert into caracteristicas (descricao, nome, produto_id) values ('Preto', 'Cor', 1)
insert into caracteristicas (descricao, nome, produto_id) values ('128GB', 'Armazenamento', 1)

insert into opiniao (nota, titulo, produto_id, usuario_id, descricao) values (5, 'Muito bom', 1, 1, 'Produto excelente, supriu minhas expectativas')
insert into opiniao (nota, titulo, produto_id, usuario_id, descricao) values (4, 'Bom', 1, 1, 'Produto vale a pena, não me arrependi. So não dei nota 5 pq demorou pra chegar')

insert into pergunta (instante, titulo, produto_id, usuario_id) values ('2021-04-12 12:17:43.630804', 'Qual o tamanho da tela?', 1, 1)
insert into pergunta (instante, titulo, produto_id, usuario_id) values ('2021-04-11 12:17:43.630804', 'É cobrado frete para entregar?', 1, 1)

insert into imagem (link, produto_id) values ('https://aws.com/ml/1652604838', 1)
insert into imagem (link, produto_id) values ('https://aws.com/ml/918422736', 1)

insert into produto (descricao, instante, nome, quantidade, valor, categoria_id, usuario_id) values ('Um belo redmi note 10', '2021-04-09 13:01:39.527087', 'Redmi note 10', 250, 1100.00, 2, 1 )

insert into caracteristicas (descricao, nome, produto_id) values ('OLED', 'Tela', 1)
insert into caracteristicas (descricao, nome, produto_id) values ('Preto', 'Cor', 1)

insert into opiniao (nota, titulo, produto_id, usuario_id, descricao) values (5, 'Muito bom', 1, 1, 'Produto excelente, supriu minhas expectativas')
insert into opiniao (nota, titulo, produto_id, usuario_id, descricao) values (4, 'Bom', 1, 1, 'Produto vale a pena, não me arrependi. So não dei nota 5 pq demorou pra chegar')

insert into pergunta (instante, titulo, produto_id, usuario_id) values ('2021-04-12 12:17:43.630804', 'Qual o tamanho da tela?', 1, 1)
insert into pergunta (instante, titulo, produto_id, usuario_id) values ('2021-04-11 12:17:43.630804', 'É cobrado frete para entregar?', 1, 1)

insert into imagem (link, produto_id) values ('https://aws.com/ml/1652604838', 1)
insert into imagem (link, produto_id) values ('https://aws.com/ml/918422736', 1)

insert into pedido (metodo_pagamento, quantidade, status, valor_unitario, usuario_id) values ('PAGSEGURO', 52, 'INICIADO', 1100, 1)
insert into pedido (metodo_pagamento, quantidade, status, valor_unitario, usuario_id) values ('PAYPAL', 12, 'INICIADO', 1100, 1)

insert into pedido_produtos (pedido_id, produtos_id) values (1, 1)
insert into pedido_produtos (pedido_id, produtos_id) values (2, 1)