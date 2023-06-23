# Comentários - Projeto 4 (30/05)

## Nota base: 4,75

### Comentários

- Instruções de uso não preenchido
- Backlog preenchido
- Diagrama desatualizado
- Não fizeram video de apresentacao
- Uma possível solução ao implementar a classe Avaliacao seria definir uma interface de Avaliadores para diferenciar os Clientes
- Utilizem enumeradores quando necessário
- Remover trechos de código comentado
- A classe Cliente não existe, no arquivo Cliente.java possui a classe PlataformaStreaming
- Rever a necessidade da classe Catalogo
- Erros de sintaxe, como falta de ';'
- Problemas do uso da avaliacao de midia chamado em filme pois esse atributo está privado e não protegido. Mesmo assim, verificar comentários acima para o mecanismo de avaliação
- Como possuem herança entre Midia e os elementos Filme e Serie, crie apenas uma lista de Conteúdo, evitando assim criar elementos duplicados e garantindo os benefícios do polimorfismo
- A duracao é uma propriedade específica de um Filme, o mesmo ocorre para a quantidade de episodios para uma Serie
- Não implementaram mecanismo de avaliar e de identificar Cliente como especialista

----
	
- Aderência às classes do diagrama: 1/2 pontos
- Requisitos de corretamente implementados: 0/14 pontos
    - só pode avaliar o que tiver visto		0/2 pontos
    - avaliar, calcular e exibir media 		0/2 pontos
    - cliente não pode avaliar 2x			0/3 pontos
    - especialistas podem comentar			0/4 pontos
    - verificação de especialistas			0/3 pontos
	
- Documentação de código: 2/2 pontos

- Implementação na aula inicial: 1,75/2 pontos (02/05)
    - arquivos JavaDoc  
    - diagrama atualizado 
    - backlog de pendências

----