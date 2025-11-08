import java.util.Scanner;

/**
 * Classe principal para executar o Sistema Javaxandria
 * Esta classe foi criada especificamente para execução no IntelliJ IDEA
 * 
 * Para executar:
 * 1. Abra o projeto no IntelliJ IDEA
 * 2. Clique com botão direito neste arquivo
 * 3. Selecione "Run 'Main.main()'"
 * 
 * @author Antonio Neto
 */
public class Main {
    
    public static void main(String[] args) {
        // Cria o objeto do sistema
        SistemaBiblioteca sistema = new SistemaBiblioteca();
        
        // Cria o scanner para ler entrada do usuário
        Scanner scanner = new Scanner(System.in);
        
        // Carrega dados salvos anteriormente
        sistema.carregarDados();
        
        // Exibe tela de boas-vindas
        System.out.println("====================================================");
        System.out.println("        JAVAXANDRIA - Sistema de Biblioteca        ");
        System.out.println("          Desenvolvido por Antonio Neto           ");
        System.out.println("====================================================");
        System.out.println();
        
        // Loop principal do sistema - continua até usuário escolher sair
        boolean continuar = true;
        while (continuar) {
            
            // Mostra o menu principal
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Acervo e Consultas");
            System.out.println("3 - Empréstimos");
            System.out.println("4 - Devoluções");
            System.out.println("5 - Relatórios");
            System.out.println("6 - Persistência");
            System.out.println("7 - Sair");
            System.out.println("--------------------");
            
            // Lê a opção do usuário
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha
            
            // Processa a opção escolhida
            switch (opcao) {
                case 1:
                    executarMenuCadastros(sistema, scanner);
                    break;
                case 2:
                    executarMenuAcervo(sistema, scanner);
                    break;
                case 3:
                    executarMenuEmprestimos(sistema, scanner);
                    break;
                case 4:
                    executarMenuDevolucoes(sistema, scanner);
                    break;
                case 5:
                    executarMenuRelatorios(sistema, scanner);
                    break;
                case 6:
                    executarMenuPersistencia(sistema, scanner);
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Obrigado por usar o Javaxandria!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
            System.out.println(); // Linha em branco para separar
        }
        
        // Salva os dados antes de sair
        sistema.salvarDados();
        scanner.close();
    }
    
    // Método para executar o menu de cadastros
    private static void executarMenuCadastros(SistemaBiblioteca sistema, Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== MENU CADASTROS ===");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Cadastrar Professor");
            System.out.println("3 - Cadastrar Livro");
            System.out.println("4 - Cadastrar Revista");
            System.out.println("5 - Voltar");
            
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarAlunoSimples(sistema, scanner);
                    break;
                case 2:
                    cadastrarProfessorSimples(sistema, scanner);
                    break;
                case 3:
                    cadastrarLivroSimples(sistema, scanner);
                    break;
                case 4:
                    cadastrarRevistaSimples(sistema, scanner);
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // Método simples para cadastrar aluno
    private static void cadastrarAlunoSimples(SistemaBiblioteca sistema, Scanner scanner) {
        System.out.println("\n--- CADASTRAR ALUNO ---");
        
        System.out.print("ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        
        sistema.cadastrarAluno(id, nome, endereco, matricula, curso);
        
        System.out.println("Aluno cadastrado com sucesso!");
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    // Método simples para cadastrar professor
    private static void cadastrarProfessorSimples(SistemaBiblioteca sistema, Scanner scanner) {
        System.out.println("\n--- CADASTRAR PROFESSOR ---");
        
        System.out.print("ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        System.out.print("SIAPE: ");
        String siape = scanner.nextLine();
        
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        
        sistema.cadastrarProfessor(id, nome, endereco, siape, departamento);
        
        System.out.println("Professor cadastrado com sucesso!");
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    // Método simples para cadastrar livro
    private static void cadastrarLivroSimples(SistemaBiblioteca sistema, Scanner scanner) {
        System.out.println("\n--- CADASTRAR LIVRO ---");
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Edição: ");
        int edicao = scanner.nextInt();
        scanner.nextLine();
        
        sistema.cadastrarLivro(codigo, titulo, ano, autor, isbn, edicao);
        
        System.out.println("Livro cadastrado com sucesso!");
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    // Método simples para cadastrar revista
    private static void cadastrarRevistaSimples(SistemaBiblioteca sistema, Scanner scanner) {
        System.out.println("\n--- CADASTRAR REVISTA ---");
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        
        System.out.print("Volume: ");
        int volume = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("ISSN: ");
        String issn = scanner.nextLine();
        
        sistema.cadastrarRevista(codigo, titulo, ano, editora, volume, issn);
        
        System.out.println("Revista cadastrada com sucesso!");
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    // Método para executar o menu de acervo
    private static void executarMenuAcervo(SistemaBiblioteca sistema, Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== ACERVO E CONSULTAS ===");
            System.out.println("1 - Buscar por título");
            System.out.println("2 - Buscar por ISBN/ISSN");
            System.out.println("3 - Listar acervo completo");
            System.out.println("4 - Voltar");
            
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o título: ");
                    String titulo = scanner.nextLine();
                    var resultados1 = sistema.buscarPorTitulo(titulo);
                    if (resultados1.isEmpty()) {
                        System.out.println("Nenhum item encontrado.");
                    } else {
                        System.out.println("Itens encontrados:");
                        for (ItemDeAcervo item : resultados1) {
                            System.out.println(item.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.print("\nDigite ISBN/ISSN: ");
                    String ident = scanner.nextLine();
                    var resultados2 = sistema.buscarPorIdentificacao(ident);
                    if (resultados2.isEmpty()) {
                        System.out.println("Nenhum item encontrado.");
                    } else {
                        System.out.println("Itens encontrados:");
                        for (ItemDeAcervo item : resultados2) {
                            System.out.println(item.toString());
                        }
                    }
                    break;
                case 3:
                    sistema.listarAcervo();
                    break;
                case 4:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            if (opcao != 4) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        }
    }
    
    // Método para executar o menu de empréstimos
    private static void executarMenuEmprestimos(SistemaBiblioteca sistema, Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== EMPRÉSTIMOS ===");
            System.out.println("1 - Realizar empréstimo");
            System.out.println("2 - Listar empréstimos ativos");
            System.out.println("3 - Voltar");
            
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    System.out.println("\n--- REALIZAR EMPRÉSTIMO ---");
                    System.out.print("ID do usuário: ");
                    String idUser = scanner.nextLine();
                    System.out.print("Código do item: ");
                    String codItem = scanner.nextLine();
                    sistema.realizarEmprestimo(idUser, codItem);
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    break;
                case 2:
                    sistema.listarEmprestimosAtivos();
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    break;
                case 3:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // Método para executar o menu de devoluções
    private static void executarMenuDevolucoes(SistemaBiblioteca sistema, Scanner scanner) {
        System.out.println("\n--- DEVOLUÇÕES ---");
        System.out.print("ID do empréstimo: ");
        String idEmp = scanner.nextLine();
        sistema.realizarDevolucao(idEmp);
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
    
    // Método para executar o menu de relatórios
    private static void executarMenuRelatorios(SistemaBiblioteca sistema, Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== RELATÓRIOS ===");
            System.out.println("1 - Listar usuários");
            System.out.println("2 - Listar acervo");
            System.out.println("3 - Listar empréstimos");
            System.out.println("4 - Voltar");
            
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    sistema.listarUsuarios();
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    break;
                case 2:
                    sistema.listarAcervo();
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    break;
                case 3:
                    sistema.listarEmprestimosAtivos();
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    break;
                case 4:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // Método para executar o menu de persistência
    private static void executarMenuPersistencia(SistemaBiblioteca sistema, Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== PERSISTÊNCIA ===");
            System.out.println("1 - Salvar dados");
            System.out.println("2 - Carregar dados");
            System.out.println("3 - Voltar");
            
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    sistema.salvarDados();
                    System.out.println("Dados salvos com sucesso!");
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    break;
                case 2:
                    sistema.carregarDados();
                    System.out.println("Dados carregados com sucesso!");
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    break;
                case 3:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}