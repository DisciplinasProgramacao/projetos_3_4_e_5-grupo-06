## Correção Projeto 3 (branch de 03/05)

### Nota base: 13,5

### Comentários

- Não preencheram backlog
- Não preencheram instruções de uso
- Enviaram video de apresentação
- A carga dos dados pode ser realizada automaticamente ao iniciar o sistema
- Não estão realizando a carga correta dos dados de espectadores
- Estão salvando arquivos diferentes do que foram especificados. Deveriam salvar os dados de Serie, Filme e Cliente
- Repensar a classe Catalogo. A situação ideal seria consederar herança existente para as especializações (Filme e Serie). A lista dessa generalização deve ser utilizada para o Cliente e para a PlataformaStreaming, garantindo assim o polimorfismo necessário para continuidade das funcionalidades
- A recomendação do uso de herança deve-se ao fato de ambos (Filme e Serie) compartilharem das mesmas informações e operações, mesmo o grupo não tendo preenchido todos os dados do Filme conforme especificado
- Dessa forma, o Cliente e a PlataformaStreaming possuiriam listas de Midia, que garantiria que estas podem ser Series e Filmes
- Isso irá impactar também no processo de filtragem, por exemplo, evitando duplicação
- Rever metodos de carregar espectadores e audiencia
- As informações de genero e idioma não são fornecidas na carga de dados, você devem adotar uma política para informar esses dados
- Não estão exercitando as filtragens em virtude dos comentários anteriores, inviabilizando o mesmo
- Não implementaram todas as filtragens solicitadas (nome)
- Diagrama desatualizado


1. Aderência às classes do diagrama: 1,25/2 pontos
  - Diagramas

2. Requisitos de corretamente implementados: 7/12 pontos
  - Carga de dados					1,25/2 pontos
  - Cadastro + salvar dados			1,25/2 pontos
  - Robustez básica					0,75/1 ponto
  - Clientes							1,25/2 pontos
	Listas, audiência sem repet
  - Séries							0,5/1 ponto
	 - audiência
  - Filme/Herança de mídia			0,5/1 ponto
  - Buscas 							1,5/3 pontos
	 - nome, gênero, idioma

3. Documentação de código: 4/4 pontos

4. Implementação na aula inicial: 1,25/2 pontos (cliente e série testados)
- Teste de serie pouco explorados
