# Atividade - Integração de sistemas

Este projeto contém uma implementação em Java de um webservice simples e um script Python para consumi-lo.  
v2 - Dados são transmitidos utilizando JSON como linguagem de interoperabilidade.

## Estrutura

O projeto contém a seguinte estrutura:

- `PrimeiroWebservice`: Código fonte do webservice em Java
- `client.py`: Script Python para consumir o webservice

## Webservice Java

O webservice Java disponibiliza uma lista de turmas codificada diretamente no código.

Ele abre um socket na porta 5000 e fica aguardando conexões. Ao receber uma conexão, envia um JSON com a lista de turmas, em seguida, recebe os líderes de cada turma, printa na tela e depois fecha a conexão.

## Cliente Python

O script `client.py` conecta-se ao webservice Java, recebe a lista de turmas, sorteia um lider para cada turma, imprime o resultado e envia o indice dos lideres para o servidor.

## Execução

Para executar o projeto:

1. Execute o webservice Java com a IDE de sua preferência.
2. Execute o cliente Python: `python client.py`

O cliente irá se conectar ao webservice e imprimir a lista de turmas com seus líderes.  
Após conexão estabelecida com o cliente, o servidor irá imprimir o nome e líder de cada disciplina.
