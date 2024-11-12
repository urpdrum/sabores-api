# language: pt

Funcionalidade: Reserva
  Cenario: Cadastrar reserva
    Dado que um usuario ja esta cadastrado
    E que um restaurante já está cadastrado
    E que a mesa já está cadastrada
    Quando cadastrar uma reserva
    Então a reserva é registrada com sucesso
    E a reserva deve ser apresentada

  Cenario: Buscar reserva
    Dado que um usuario ja esta cadastrado
    E que um restaurante já está cadastrado
    E que a mesa já está cadastrada
    E que a reserva já está cadastrada
    Quando efetuar a busca da reserva
    Então a reserva deve ser apresentada

  Cenario: Alterar reserva
    Dado que um usuario ja esta cadastrado
    E que um restaurante já está cadastrado
    E que a mesa já está cadastrada
    E que a reserva já está cadastrada
    Então efeturar a requisição para atualizar a reserva
    Então a reserva é atualizada com sucesso
    E a reserva deve ser apresentada

  Cenario: Remover reserva
    Dado que um usuario ja esta cadastrado
    E que um restaurante já está cadastrado
    E que a mesa já está cadastrada
    E que a reserva já está cadastrada
    Quando requisitar a remoção da reserva
    Então a reserva é removida com sucesso
    E deve apresentar o resultado da remoção de reserva
