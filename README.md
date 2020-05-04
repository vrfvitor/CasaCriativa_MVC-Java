# Resumo
Nesse projeto aproveito boa parte do front desenvolvido no projeto [WorkShop_OmniStack_11](https://github.com/vrfvitor/WorkShop_OmniStack_11), mas agora com o backend todo em Java.
Utilizando o framework Spring, o padrão MVC, Maven no controle das dependencias, Tomcat como Servlet Container, Hibernate como implementação do JPA e MySQL sendo o banco de dados.
O objetivo foi colocar em prática os conhecimentos dessas tecnologias implementando um CRUD com as Ideias, que são os objetos trabalhados aqui.
# Front
HTML e CSS puros nos JSP's e um pouco de JS para validações. Conta com elementos como drop-down, botões e links interativos. Uso de template próprio do JSP com o uso do arquivo "pageTemplate.tag" para evitar repetição de elementos HTML nas diferentes views, e tags do Spring e JSTL para build de url e execução de controle de fluxo dentro dos JSP's, respectivamente, por exemplo.

# Back
## Geral
Projeto construído no padrão MVC com o Spring framework. Utilizei o Apache Tomcat como implementação de Serlvet e o Maven no controle de dependencias. A lógica de negócio e controle de requisições estão na camada de controle do projeto. Devida utilização dos métodos HTTP GET e POST. O projeto também conta com a implementação de cache provida pelo Google Guava Cache.

## Pesistência e Consulta
No projeto a configuração da persistência de dados está na classe JPAConfig, administrada pelo Spring e não mais num arquivo '.xml'. Banco MySQL e implementação do JPA ficou por conta do Hibernate. A lógica de acesso aos dados está contida nos DAO's nos quais o Spring injeta o EntityManager e permite transações. As queries simples são escritas em JPQL e a mais complexa montada com a API Criteria. 

