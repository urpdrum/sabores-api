INSERT INTO Endereco (logradouro, numero, complemento, bairro, cidade, cep, uf)
VALUES ('logradouro teste', 10, 'apto 1', 'bairro teste', 'cidade teste', '00000-000', 'IT'),
       ('logradouro teste 2', 10, 'apto 2', 'bairro teste 2', 'cidade teste 2', '20000-000', 'IT'),
       ('logradouro teste 3', 10, 'apto 3', 'bairro teste 3', 'cidade teste 3', '30000-000', 'IT'),
       ('logradouro teste 4', 10, 'apto 4', 'bairro teste 4', 'cidade teste 4', '30000-000', 'IT'),
       ('logradouro teste 5', 10, 'apto 5', 'bairro teste 5', 'cidade teste 5', '30000-000', 'IT'),
       ('logradouro teste 6', 10, 'apto 6', 'bairro teste 6', 'cidade teste 6', '30000-000', 'IT'),
       ('logradouro teste 7', 10, 'apto 7', 'bairro teste 7', 'cidade teste 7', '30000-000', 'IT'),
       ('logradouro teste 8', 10, 'apto 8', 'bairro teste 8', 'cidade teste 8', '30000-000', 'IT'),
       ('logradouro teste 9', 10, 'apto 9', 'bairro teste 9', 'cidade teste 9', '30000-000', 'IT');

INSERT INTO Restaurante (nome, endereco_id, tipo_de_cozinha, capacidade, horario_funcionamento)
VALUES ('restaurante teste', 1, 'MEXICANA', 150, '12h às 23h'),
        ('restaurante teste 2', 2, 'MEXICANA', 150, '12h às 23h'),
        ('restaurante teste 3', 3, 'JAPONESA', 150, '12h às 23h'),
        ('restaurante teste 4', 4, 'JAPONESA', 150, '12h às 23h'),
        ('restaurante teste 5', 5, 'JAPONESA', 150, '12h às 23h'),
        ('restaurante teste 6', 6, 'JAPONESA', 150, '12h às 23h'),
        ('restaurante teste 7', 7, 'JAPONESA', 150, '12h às 23h'),
        ('restaurante teste 8', 8, 'JAPONESA', 150, '12h às 23h'),
        ('restaurante teste 9', 9, 'JAPONESA', 150, '12h às 23h');

INSERT INTO Usuario (nome, email, senha, telefone)
VALUES ('usuario teste', 'teste@email.com','Senha@90','99999-9999'),
       ('usuario teste 2', 'teste2@email.com','Senha@92','99999-9999'),
       ('Lucas Franco', 'lucas.franco@email.com', 'Senha@12', '11999999901'),
       ('João Silva', 'joao.silva@email.com', 'Senha@45', '11999999902'),
       ('Maria Souza', 'maria.souza@email.com', 'Senha@78', '11999999903'),
       ('Ana Costa', 'ana.costa@email.com', 'Senha@10', '11999999904'),
       ('Pedro Alves', 'pedro.alves@email.com', 'Senha@20', '11999999905'),
       ('Fernanda Lima', 'fernanda.lima@email.com', 'Senha@30', '11999999906'),
       ('Bruno Pereira', 'bruno.pereira@email.com', 'Senha@40', '11999999907'),
       ('Mariana Rocha', 'mariana.rocha@email.com', 'Senha@50', '11999999908'),
       ('Ricardo Mendes', 'ricardo.mendes@email.com', 'Senha@60', '11999999909'),
       ('Camila Dias', 'camila.dias@email.com', 'Senha@70', '11999999910');

INSERT INTO Avaliacao (restaurante_Id, usuario_Id, nota, comentario, data_avaliacao)
VALUES (1, 1, 5, 'Muito boa a comida!', '2024-09-10T11:47:37.912019300'),
       (2, 1, 1, 'Terrível a comida!', '2024-09-10T11:47:37.912019300'),
       (1, 2, 4, 'Atendimento top!', '2024-09-10T11:47:37.912019300'),
       (2, 2, 3, 'Mais ou menos', '2024-09-10T11:47:37.912019300'),
       (8, 2, 3, 'Mais ou menos', '2024-09-10T11:47:37.912019300'),
       (8, 2, 3, 'Mais ou menos', '2024-09-10T11:47:37.912019300'),
       (8, 2, 3, 'Mais ou menos', '2024-09-10T11:47:37.912019300');

INSERT INTO Mesa (restaurante_id, quantidade_Assentos)
VALUES (1, 4),
       (1, 4),
       (1, 4),
       (1, 4),
       (1, 4),
       (2, 4),
       (2, 4),
       (2, 4),
       (3, 4),
       (3, 4),
       (3, 4),
       (4, 4),
       (4, 4),
       (4, 4),
       (5, 4),
       (5, 4),
       (5, 4),
       (6, 4),
       (6, 4),
       (6, 4),
       (7, 4),
       (7, 4),
       (7, 4),
       (8, 4),
       (8, 4),
       (8, 4),
       (9, 4),
       (9, 4),
       (9, 4);

INSERT INTO reserva (usuario_Id, mesa_Id, status, data_inicio, data_fim)
VALUES (1, 1, 'ATIVA', '2030-09-10T11:47:37', '2030-09-10T12:47:37'),
        (1, 1, 'ATIVA', '2030-09-10T13:47:37', '2030-09-10T14:47:37'),
        (1, 2, 'ATIVA', '2030-09-11T11:47:37', '2030-09-11T12:47:37'),
        (1, 3, 'ATIVA', '2030-09-12T11:47:37', '2030-09-12T12:47:37')

