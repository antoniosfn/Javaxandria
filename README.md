# Javaxandria - Sistema de Gestão de Biblioteca Universitária

**Projeto acadêmico desenvolvido por:** Antonio Neto

**Características:** Este projeto foi desenvolvido seguindo um estilo mais simples, demonstrando características de código feito por desenvolvedor junior.

## Como Executar no IntelliJ IDEA

### Pré-requisitos
- Java JDK 8 ou superior instalado
- IntelliJ IDEA instalado

### Passo a Passo

1. **Abrir o Projeto:**
   - Abra o IntelliJ IDEA
   - Selecione "Open" (não "New Project")
   - Navegue até a pasta `code` e selecione-a
   - O IntelliJ IDEA irá reconhecer como um projeto Java

2. **Configurar o JDK:**
   - Vá em `File` → `Project Structure` (ou `Project Settings`)
   - Em `Project`, selecione o JDK apropriado
   - Clique em `OK`

3. **Executar o Sistema:**
   - No painel `Project`, localize o arquivo `Main.java`
   - Clique com botão direito no arquivo `Main.java`
   - Selecione `Run 'Main.main()'`
   - O sistema irá iniciar no console do IntelliJ

### Estrutura do Projeto

```
code/
├── Main.java                    # Classe principal para execução no IntelliJ
├── Usuario.java                 # Classe abstrata base para usuários
├── Aluno.java                   # Classe para alunos (herda de Usuario)
├── Professor.java               # Classe para professores (herda de Usuario)
├── ItemDeAcervo.java            # Classe abstrata base para itens do acervo
├── Livro.java                   # Classe para livros (herda de ItemDeAcervo)
├── Revista.java                 # Classe para revistas (herda de ItemDeAcervo)
├── Emprestimo.java              # Classe para gerenciar empréstimos
├── SistemaBiblioteca.java       # Classe principal com toda a lógica de negócio
└── dados_biblioteca.txt         # Arquivo de dados (criado automaticamente)
```

### Conceitos de POO Demonstrados

#### 1. **Abstração**
- Classes `Usuario` e `ItemDeAcervo` são abstratas
- Não podem ser instanciadas diretamente
- Definem comportamentos que serão implementados nas subclasses

#### 2. **Encapsulamento**
- Todos os atributos são privados
- Acesso controlado através de métodos getter/setter
- Lógica de negócio protegida dentro das classes

#### 3. **Herança**
- `Aluno` e `Professor` herdam de `Usuario`
- `Livro` e `Revista` herdam de `ItemDeAcervo`
- Reutilização de código e especialização

#### 4. **Polimorfismo**
- Métodos `calcularPrazoDevolucao()` implementados diferente em cada subclasse
- Método `getIdentificacao()` retorna ISBN para livros e ISSN para revistas
- Variáveis do tipo pai podem referenciar objetos filhos

### Regras de Negócio Implementadas

#### RN1 - Disponibilidade
- Sistema verifica se o item está disponível antes de emprestar
- Item não pode ser emprestado se já estiver emprestado

#### RN2 - Limites de Usuário
- **Alunos:** máximo 3 itens emprestados, prazo de 7 dias
- **Professores:** máximo 5 itens emprestados, prazo de 15 dias

#### RN3 - Multas
- Multa de R$ 1,00 por dia de atraso
- Cálculo automático na devolução

#### RN4 - Bloqueio
- Usuário bloqueado se tiver multas pendentes
- Usuário bloqueado se tiver empréstimos vencidos

### Funcionalidades do Sistema

1. **Cadastros**
   - Cadastro de alunos e professores
   - Cadastro de livros e revistas

2. **Consultas**
   - Busca por título
   - Busca por ISBN/ISSN
   - Listagem completa do acervo

3. **Empréstimos**
   - Realização de empréstimos
   - Controle de prazos automático
   - Validação de regras de negócio

4. **Devoluções**
   - Devolução de itens
   - Cálculo automático de multas
   - Liberação automática do item

5. **Relatórios**
   - Lista de usuários
   - Lista do acervo
   - Empréstimos ativos
   - Estatísticas do sistema

6. **Persistência**
   - Salvamento automático em arquivo
   - Carregamento automático ao iniciar
   - Dados preservados entre execuções

### Dados de Exemplo

O sistema inclui dados de exemplo no arquivo `dados_biblioteca.txt`:
- 2 alunos cadastrados
- 1 professor cadastrado
- 2 livros no acervo
- 1 revista no acervo

### Características do Código Junior

Este projeto foi estruturado para demonstrar:

- **Métodos mais longos** com comentários explicativos
- **Validações manuais** em vez de usar bibliotecas avançadas
- **Verificações explícitas** de condições
- **Estruturas simples** e diretas
- **Comentarios detalhados** explicando cada passo
- **Nomes de variáveis descritivos**
- **Organização básica** das funcionalidades

### Solução de Problemas

#### Erro: "Cannot resolve symbol"
- Verifique se o JDK está configurado corretamente
- Certifique-se de que todas as classes estão no mesmo pacote (ou sem pacote)

#### Erro: "FileNotFoundException"
- O arquivo `dados_biblioteca.txt` será criado automaticamente
- Certifique-se de que o IntelliJ IDEA tem permissão de escrita na pasta

#### Sistema não salva dados
- Verifique se o diretório do projeto tem permissão de escrita
- O arquivo será criado na pasta `code/`

### Próximos Passos

Para desenvolvedores que queiram melhorar o projeto:

1. **Melhorar validações** usando expressões regulares
2. **Adicionar tratamento de exceções** mais robusto
3. **Implementar interface gráfica** usando JavaFX ou Swing
4. **Adicionar mais tipos de usuários** (funcionários, visitantes)
5. **Implementar busca avançada** com filtros
6. **Adicionar relatórios em PDF**
7. **Implementar sistema de reservas**

---

**Desenvolvido como projeto acadêmico para demonstração dos conceitos de Programação Orientada a Objetos em Java.**