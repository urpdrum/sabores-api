# language: pt

Funcionalidade: Avaliação
  Cenario: Cadastrar avaliação
    Dado que um restaurante já está cadastrado
    E que um usuario ja esta cadastrado
    Quando enviar uma avaliação para um Restaurante
    Então a avaliação é registrada com sucesso
    E a avaliacao deve ser apresentada

  Cenario: Buscar avaliação por Restaurante
    Dado que uma avaliacao já está cadastrada
    Quando efetuar a busca de avaliação de restaurante
    Então o restaurante e a avaliação são exibidos

  Cenario: Alterar avaliação
    Dado que uma avaliacao já está cadastrada
    Então efeturar a requisição para atualizar a avaliação
    E a avaliação é atualizada com sucesso
    E a avaliacao deve ser apresentada

  Cenario: Remover avaliação
    Dado que uma avaliacao já está cadastrada
    Quando requisitar a remoção da avaliação
    Então a avaliação é removida com sucesso
    E deve apresentar o resultado da remoção da avaliacao