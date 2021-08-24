# JavaTest
Teste de seleção para vaga de Java
## Faça um fork desse projeto e siga as instruções a seguir utilizando esse projeto.

Pré-requisitos

Deve ser implementado apenas a API (Backend)
Versão Java +8 (caso seja Java 8, verificar compatibilidade da sua IDE)
Versão Spring Boot >= 2.4
Banco de dados fica a seu critério (Sql, NoSql)
Seu projeto deve obrigatoriamente ter as anotações: @Repository, @Entity e @Controller
Documentação mínima da API (Swagger ou documento PDF)

Deve ser implementado para empresa de transporte de cargas SigaBem o endpoint para o cálculo do preço do frete:

Considerar regras para calcular o valor do frete:

Você deve calcular o valor total do frete e a data prevista da entrega
CEPs com DDDs iguais tem 50% de desconto no valor do frete e entrega prevista de 1 dia
CEPs de estados iguais tem 75% de desconto no valor do frete e entrega prevista de 3 dias
CEPs de estados diferentes não deve ser aplicado o desconto no valor do frete
O valor do frete é cobrado pelo peso da encomenda, o valor para cada KG é R$1,00

Seu input de entrada deve ser “peso”, “cepOrigem”, “cepDestino” e “nomeDestinatario“

Você pode utilizar a API gratuita de consulta de CEP abaixo: 
Documentação da API: https://viacep.com.br/
Exemplo do GET: https://viacep.com.br/ws/<CEP_A_CONSULTAR>/json/

Endpoint pode ser público
Response/Output deve possuir: “vlTotalFrete” e “dataPrevistaEntrega”, “cepOrigem” e “cepDestino”
Deve ser persistido no banco os valores da cotação os valores consultados: “peso”, “cepOrigem”, “cepDestino”, “nomeDestinatario”, “vlTotalFrete”, “dataPrevistaEntrega” e “dataConsulta”



Critérios de avaliação:

Implementação das regras de negócios para o cálculo do frete
Boas práticas de programação, arquitetura  e padrões de projetos

Entrega: 

Deve ser disponibilizado um link do repositório no GitHub.
