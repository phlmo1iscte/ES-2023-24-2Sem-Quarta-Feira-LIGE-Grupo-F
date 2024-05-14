# Unidade Curricular de Engenharia de Software
## ES-2023-24-2Sem-Quarta-Feira-LIGE-Grupo-F


### Elementos do Grupo:
| Nome            | N. Estudante | UserNm GitHub     |
|-----------------|--------------|-------------------|
| Aires Oliveira  |    90323     | aproaiscte        |
| Bruna Pioris    |    99550     | BrunaPioris       |
| Duarte Reforço  |    104923    | dapro-iscte       |
| Pedro Martinho  |    104692    | phlmo1iscte       |
| Ricardo Ribeiro |    98805     | ricardo-mar-iscte |
| Tiago Longle    |    104684    | taleo-iscte       |



### Descrição do Projeto:
Este projeto deverá permitir a leitura de dados sobre os horários do ISCTE, representados em ficheiros CSV.

- Carregar um horário a partir de um ficheiro CSV;
- Visualizar e navegar no horário;
- Visualizar e navegar no cadastro das salas do ISCTE;
- Opção de gravar em CSV e JSON os horários alterados;
- Opção de sugestão de slots para uma aula de substituição da UC, mediante limitações ao bom funcionamento da faculdade;
- Visualizar sob a forma gráfica a relação de conflitualidade entre aulas;
- Visualizar sob a forma gráfica o mapa de ocupaçãodas salas.


### Descrição de erros
-Implementações até ao ponto 4 completas e sem erros


### Funcionalidades não implementadas ou incompletas

- Opção de sugestão de slots para uma aula de substituição da UC, mediante limitações ao bom funcionamento da faculdade;
- Visualizar sob a forma gráfica a relação de conflitualidade entre aulas;
- Visualizar sob a forma gráfica o mapa de ocupaçãodas salas.
- Classe de testes JUnit TipoSala.java
- Classe de testes JUnit LancaBrowser.java
- Classe de testes JUnit submitePage.java

### Funcionalidades  implementadas 

- Carregar um horário a partir de um ficheiro CSV local ou remoto;
- Visualizar e navegar no horário;
- Carregar a Caracterização das salas a partir de um ficheiro CSV;
- Visualizar e navegar no cadastro das salas do ISCTE;
- Opção de gravar em CSV e JSON os horários alterados;
- Adicionadas à tabela as colunas Semana do ano (1 a 52) e Semana do semestre (1 a 15) correspondentes às semanas em que as aulas ocorrem;
- Classe de testes JUnit Horario.java
- Classe de testes JUnit Sala.java
- Classe de testes JUnit HorarioToJson.java
- Classe de testes JUnit HorarioToCsv.java
- Classe de testes JUnit CreatHTML.java
- Classe de testes JUnit CreateSalaHTML.java
