# language: pt

Funcionalidade: Mesa
  Cenario: Cadastrar mesa
    Dado que um restaurante já está cadastrado
    Quando cadastrar uma mesa para o restaurante
    Então a mesa é registrada com sucesso
    E a mesa deve ser apresentada

  Cenario: Buscar mesa
    Dado que a mesa já está cadastrada
    Quando efetuar a busca da mesa de um restaurante
    Então as mesas são exibida

  Cenario: Alterar mesa
    Dado que a mesa já está cadastrada
    Então efeturar a requisição para atualizar a mesa
    Então a mesa é atualizada com sucesso
    E a mesa deve ser apresentada

  Cenario: Remover mesa
    Dado que uma mesa já está cadastrada
    Quando requisitar a remoção da mesa
    Então a mesa é removida com sucesso
    E deve apresentar o resultado da remoção da mesa
