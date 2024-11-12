# language: pt

Funcionalidade: Restaurante
  Cenário: Cadastrar restaurante
    Quando cadastrar um novo restaurante
    Então o restaurante é registrado com sucesso
    E o restaurante deve ser apresentado

  Cenario: Buscar restaurante
    Dado que um restaurante já está cadastrado
    Quando efetuar a busca de restaurante
    Então o restaurante é exibido com sucesso

  Cenario: Alterar restaurante
    Dado que um restaurante já está cadastrado
    Então efeturar a requisição para atualizar o restaurante
    Então o restaurante é atualizado com sucesso
    E o restaurante deve ser apresentado

  Cenario: Remover restaurante
    Dado que um restaurante já está cadastrado
    Quando requisitar a remoção do restaurante
    Então o restaurante é removido com sucesso
    E deve apresentar o resultado da remoção do restaurante
