# language: pt

Funcionalidade: Usuario
  Cenario: Cadastrar usuario
    Quando registrar um novo usuario
    Então o usuario é registrado com sucesso
    E o usuario deve ser apresentado

  Cenario: Buscar usuario
    Dado que um usuario ja esta cadastrado
    Quando efetuar a busca de usuario
    Então o usuario é exibido com sucesso

  Cenario: Alterar usuario
    Dado que um usuario ja esta cadastrado
    Então efeturar a requisição para atualizar o usuario
    Então o usuario é atualizado com sucesso
    E o usuario deve ser apresentado

  Cenario: Remover usuario
    Dado que um usuario ja esta cadastrado
    Quando requisitar a remoção do usuario
    Então o usuario é removido com sucesso
    E deve apresentar o resultado da remoção
