import java.io.*;
import java.util.*;

/**
 * Classe SistemaBiblioteca - classe principal que gerencia toda a biblioteca
 * Esta é a classe mais importante do sistema, pois gerencia todos os dados
 * e funcionalidades da biblioteca
 * 
 * Conceitos demonstrados:
 * - Agregação: contém listas de Usuario, ItemDeAcervo e Emprestimo
 * - Encapsulamento: controla acesso aos dados
 * - Métodos de negócio: implementam as regras do sistema
 * 
 * @author Antonio Neto
 */
public class SistemaBiblioteca {
    
    // Listas principais do sistema
    private List<Usuario> listaUsuarios;          // todos os usuários da biblioteca
    private List<ItemDeAcervo> acervo;            // todos os itens do acervo
    private List<Emprestimo> historicoEmprestimos; // histórico completo de empréstimos
    private static int proximoIdEmprestimo;       // contador para gerar IDs únicos
    
    /**
     * Construtor da classe SistemaBiblioteca
     * Inicializa todas as listas e variáveis necessárias
     */
    public SistemaBiblioteca() {
        // Inicializa as listas como ArrayLists vazias
        this.listaUsuarios = new ArrayList<>();
        this.acervo = new ArrayList<>();
        this.historicoEmprestimos = new ArrayList<>();
        this.proximoIdEmprestimo = 1;  // começa com ID 1
    }
    
    /**
     * Método getter para obter a lista de usuários
     * @return lista de usuários
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    /**
     * Método getter para obter o acervo
     * @return lista de itens do acervo
     */
    public List<ItemDeAcervo> getAcervo() {
        return acervo;
    }
    
    /**
     * Método getter para obter o histórico de empréstimos
     * @return lista de empréstimos
     */
    public List<Emprestimo> getHistoricoEmprestimos() {
        return historicoEmprestimos;
    }
    
    /**
     * Método getter para obter o próximo ID de empréstimo
     * @return próximo ID disponível
     */
    public static int getProximoIdEmprestimo() {
        return proximoIdEmprestimo;
    }
    
    /**
     * Método setter para alterar o próximo ID de empréstimo
     * @param proximoIdEmprestimo novo valor do contador
     */
    public static void setProximoIdEmprestimo(int proximoIdEmprestimo) {
        SistemaBiblioteca.proximoIdEmprestimo = proximoIdEmprestimo;
    }
    
    // =============== MÉTODOS DE CADASTRO ===============
    
    /**
     * Método para cadastrar um novo aluno
     * Verifica se o ID já existe e cria um novo objeto Aluno
     * 
     * @param id - ID único do aluno
     * @param nome - nome completo do aluno
     * @param endereco - endereço do aluno
     * @param matricula - número de matrícula
     * @param curso - nome do curso
     * @return true se cadastrou com sucesso, false caso contrário
     */
    public boolean cadastrarAluno(String id, String nome, String endereco, 
                                 String matricula, String curso) {
        try {
            // Verifica se os dados não são nulos ou vazios
            if (id == null || nome == null || endereco == null || matricula == null || curso == null) {
                System.out.println("Erro: Todos os campos são obrigatórios!");
                return false;
            }
            
            if (id.trim().isEmpty() || nome.trim().isEmpty() || endereco.trim().isEmpty() || 
                matricula.trim().isEmpty() || curso.trim().isEmpty()) {
                System.out.println("Erro: Nenhum campo pode estar vazio!");
                return false;
            }
            
            // Verifica se já existe um usuário com esse ID
            Usuario usuarioExistente = buscarUsuarioPorId(id);
            if (usuarioExistente != null) {
                System.out.println("Erro: Já existe um usuário cadastrado com o ID: " + id);
                return false;
            }
            
            // Cria um novo objeto Aluno
            Aluno novoAluno = new Aluno(id, nome, endereco, matricula, curso);
            
            // Adiciona na lista de usuários
            listaUsuarios.add(novoAluno);
            
            System.out.println("Sucesso! Aluno cadastrado com ID: " + id);
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar aluno: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método para cadastrar um novo professor
     * Verifica se o ID já existe e cria um novo objeto Professor
     * 
     * @param id - ID único do professor
     * @param nome - nome completo do professor
     * @param endereco - endereço do professor
     * @param siape - número SIAPE
     * @param departamento - departamento onde trabalha
     * @return true se cadastrou com sucesso, false caso contrário
     */
    public boolean cadastrarProfessor(String id, String nome, String endereco,
                                    String siape, String departamento) {
        try {
            // Verifica se os dados não são nulos ou vazios
            if (id == null || nome == null || endereco == null || siape == null || departamento == null) {
                System.out.println("Erro: Todos os campos são obrigatórios!");
                return false;
            }
            
            if (id.trim().isEmpty() || nome.trim().isEmpty() || endereco.trim().isEmpty() || 
                siape.trim().isEmpty() || departamento.trim().isEmpty()) {
                System.out.println("Erro: Nenhum campo pode estar vazio!");
                return false;
            }
            
            // Verifica se já existe um usuário com esse ID
            Usuario usuarioExistente = buscarUsuarioPorId(id);
            if (usuarioExistente != null) {
                System.out.println("Erro: Já existe um usuário cadastrado com o ID: " + id);
                return false;
            }
            
            // Cria um novo objeto Professor
            Professor novoProfessor = new Professor(id, nome, endereco, siape, departamento);
            
            // Adiciona na lista de usuários
            listaUsuarios.add(novoProfessor);
            
            System.out.println("Sucesso! Professor cadastrado com ID: " + id);
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar professor: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método para cadastrar um novo livro
     * Verifica se o código já existe e cria um novo objeto Livro
     * 
     * @param codigo - código único do livro
     * @param titulo - título do livro
     * @param anoPublicacao - ano de publicação
     * @param autor - nome do autor
     * @param isbn - código ISBN
     * @param edicao - edição do livro
     * @return true se cadastrou com sucesso, false caso contrário
     */
    public boolean cadastrarLivro(String codigo, String titulo, int anoPublicacao,
                                String autor, String isbn, int edicao) {
        try {
            // Verifica se os dados não são nulos ou vazios
            if (codigo == null || titulo == null || autor == null || isbn == null) {
                System.out.println("Erro: Todos os campos são obrigatórios!");
                return false;
            }
            
            if (codigo.trim().isEmpty() || titulo.trim().isEmpty() || autor.trim().isEmpty() || 
                isbn.trim().isEmpty()) {
                System.out.println("Erro: Nenhum campo pode estar vazio!");
                return false;
            }
            
            // Verifica se o ano é válido
            if (anoPublicacao <= 0 || anoPublicacao > 2025) {
                System.out.println("Erro: Ano de publicação inválido!");
                return false;
            }
            
            // Verifica se a edição é válida
            if (edicao <= 0) {
                System.out.println("Erro: Edição deve ser maior que zero!");
                return false;
            }
            
            // Verifica se já existe um item com esse código
            ItemDeAcervo itemExistente = buscarItemPorCodigo(codigo);
            if (itemExistente != null) {
                System.out.println("Erro: Já existe um item cadastrado com o código: " + codigo);
                return false;
            }
            
            // Cria um novo objeto Livro
            Livro novoLivro = new Livro(codigo, titulo, anoPublicacao, autor, isbn, edicao);
            
            // Adiciona no acervo
            acervo.add(novoLivro);
            
            System.out.println("Sucesso! Livro cadastrado com código: " + codigo);
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar livro: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método para cadastrar uma nova revista
     * Verifica se o código já existe e cria um novo objeto Revista
     * 
     * @param codigo - código único da revista
     * @param titulo - título da revista
     * @param anoPublicacao - ano de publicação
     * @param editora - nome da editora
     * @param volume - número do volume
     * @param issn - código ISSN
     * @return true se cadastrou com sucesso, false caso contrário
     */
    public boolean cadastrarRevista(String codigo, String titulo, int anoPublicacao,
                                  String editora, int volume, String issn) {
        try {
            // Verifica se os dados não são nulos ou vazios
            if (codigo == null || titulo == null || editora == null || issn == null) {
                System.out.println("Erro: Todos os campos são obrigatórios!");
                return false;
            }
            
            if (codigo.trim().isEmpty() || titulo.trim().isEmpty() || 
                editora.trim().isEmpty() || issn.trim().isEmpty()) {
                System.out.println("Erro: Nenhum campo pode estar vazio!");
                return false;
            }
            
            // Verifica se o ano é válido
            if (anoPublicacao <= 0 || anoPublicacao > 2025) {
                System.out.println("Erro: Ano de publicação inválido!");
                return false;
            }
            
            // Verifica se o volume é válido
            if (volume <= 0) {
                System.out.println("Erro: Volume deve ser maior que zero!");
                return false;
            }
            
            // Verifica se já existe um item com esse código
            ItemDeAcervo itemExistente = buscarItemPorCodigo(codigo);
            if (itemExistente != null) {
                System.out.println("Erro: Já existe um item cadastrado com o código: " + codigo);
                return false;
            }
            
            // Cria um novo objeto Revista
            Revista novaRevista = new Revista(codigo, titulo, anoPublicacao, editora, volume, issn);
            
            // Adiciona no acervo
            acervo.add(novaRevista);
            
            System.out.println("Sucesso! Revista cadastrada com código: " + codigo);
            return true;
            
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar revista: " + e.getMessage());
            return false;
        }
    }
    
    // =============== MÉTODOS DE CONSULTA ===============
    
    /**
     * Método para buscar um usuário pelo ID
     * Procura na lista de usuários por um ID específico
     * 
     * @param id - ID do usuário a ser buscado
     * @return usuário encontrado ou null se não encontrado
     */
    public Usuario buscarUsuarioPorId(String id) {
        // Verifica se o ID é válido
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        
        // Percorre toda a lista de usuários
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuario = listaUsuarios.get(i);
            
            // Verifica se encontrou o usuário pelo ID
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        
        // Se chegou até aqui, não encontrou
        return null;
    }
    
    /**
     * Método para buscar um item pelo código
     * Procura no acervo por um código específico
     * 
     * @param codigo - código do item a ser buscado
     * @return item encontrado ou null se não encontrado
     */
    public ItemDeAcervo buscarItemPorCodigo(String codigo) {
        // Verifica se o código é válido
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        
        // Percorre todo o acervo
        for (int i = 0; i < acervo.size(); i++) {
            ItemDeAcervo item = acervo.get(i);
            
            // Verifica se encontrou o item pelo código
            if (item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        
        // Se chegou até aqui, não encontrou
        return null;
    }
    
    /**
     * Método para buscar itens por título
     * Procura no acervo por itens que contenham o título especificado
     * 
     * @param titulo - título ou parte do título a ser buscado
     * @return lista de itens encontrados
     */
    public List<ItemDeAcervo> buscarPorTitulo(String titulo) {
        List<ItemDeAcervo> resultados = new ArrayList<>();
        
        // Verifica se o título é válido
        if (titulo == null || titulo.trim().isEmpty()) {
            return resultados;  // retorna lista vazia
        }
        
        // Converte para minúscula para busca case-insensitive
        String tituloBusca = titulo.toLowerCase();
        
        // Percorre todo o acervo
        for (int i = 0; i < acervo.size(); i++) {
            ItemDeAcervo item = acervo.get(i);
            String tituloItem = item.getTitulo().toLowerCase();
            
            // Verifica se o título do item contém o texto buscado
            if (tituloItem.contains(tituloBusca)) {
                resultados.add(item);
            }
        }
        
        return resultados;
    }
    
    /**
     * Método para buscar itens por identificação (ISBN/ISSN)
     * Procura no acervo por itens que contenham a identificação especificada
     * 
     * @param identificacao - ISBN/ISSN ou parte dele a ser buscado
     * @return lista de itens encontrados
     */
    public List<ItemDeAcervo> buscarPorIdentificacao(String identificacao) {
        List<ItemDeAcervo> resultados = new ArrayList<>();
        
        // Verifica se a identificação é válida
        if (identificacao == null || identificacao.trim().isEmpty()) {
            return resultados;  // retorna lista vazia
        }
        
        // Converte para minúscula para busca case-insensitive
        String identBusca = identificacao.toLowerCase();
        
        // Percorre todo o acervo
        for (int i = 0; i < acervo.size(); i++) {
            ItemDeAcervo item = acervo.get(i);
            String identItem = item.getIdentificacao().toLowerCase();
            
            // Verifica se a identificação do item contém o texto buscado
            if (identItem.contains(identBusca)) {
                resultados.add(item);
            }
        }
        
        return resultados;
    }
    
    /**
     * Método para buscar um empréstimo pelo ID
     * Procura no histórico por um ID específico
     * 
     * @param idEmprestimo - ID do empréstimo a ser buscado
     * @return empréstimo encontrado ou null se não encontrado
     */
    public Emprestimo buscarEmprestimoPorId(String idEmprestimo) {
        // Verifica se o ID é válido
        if (idEmprestimo == null || idEmprestimo.trim().isEmpty()) {
            return null;
        }
        
        // Percorre todo o histórico de empréstimos
        for (int i = 0; i < historicoEmprestimos.size(); i++) {
            Emprestimo emprestimo = historicoEmprestimos.get(i);
            
            // Verifica se encontrou o empréstimo pelo ID
            if (emprestimo.getIdEmprestimo().equals(idEmprestimo)) {
                return emprestimo;
            }
        }
        
        // Se chegou até aqui, não encontrou
        return null;
    }
    
    // =============== MÉTODOS DE EMPRÉSTIMO ===============
    
    /**
     * Método principal para realizar um empréstimo
     * Implementa as regras de negócio RN1 e RN2
     * RN1: Verifica se o item está disponível
     * RN2: Verifica se o usuário está apto para empréstimo
     * 
     * @param idUsuario - ID do usuário que vai fazer o empréstimo
     * @param codItem - código do item a ser emprestado
     * @return objeto Emprestimo se bem-sucedido, null caso contrário
     */
    public Emprestimo realizarEmprestimo(String idUsuario, String codItem) {
        try {
            // Validação dos parâmetros de entrada
            if (idUsuario == null || codItem == null) {
                System.out.println("Erro: ID do usuário e código do item são obrigatórios!");
                return null;
            }
            
            if (idUsuario.trim().isEmpty() || codItem.trim().isEmpty()) {
                System.out.println("Erro: ID do usuário e código do item não podem estar vazios!");
                return null;
            }
            
            // Busca o usuário
            Usuario usuario = buscarUsuarioPorId(idUsuario);
            if (usuario == null) {
                System.out.println("Erro: Usuário com ID '" + idUsuario + "' não encontrado!");
                System.out.println("Verifique se o usuário está cadastrado no sistema.");
                return null;
            }
            
            // Busca o item
            ItemDeAcervo item = buscarItemPorCodigo(codItem);
            if (item == null) {
                System.out.println("Erro: Item com código '" + codItem + "' não encontrado!");
                System.out.println("Verifique se o item está cadastrado no acervo.");
                return null;
            }
            
            // RN1: Verifica se o item está disponível para empréstimo
            if (item.isEmprestado()) {
                System.out.println("Erro RN1: Item '" + item.getTitulo() + "' já está emprestado!");
                System.out.println("Não é possível realizar o empréstimo.");
                return null;
            }
            
            // RN2: Verifica se o usuário está apto para empréstimo
            if (!usuario.isAptoParaEmprestimo()) {
                System.out.println("Erro RN2: Usuário '" + usuario.getNome() + "' não está apto para empréstimo!");
                System.out.println("Possíveis motivos:");
                
                // Verifica o status
                if (!usuario.getStatus().equals("Ativo")) {
                    System.out.println("- Status não está ativo");
                }
                
                // Verifica multas
                double totalMultas = usuario.calcularTotalMultas();
                if (totalMultas > 0) {
                    System.out.println("- Tem multas pendentes: R$ " + totalMultas);
                }
                
                // Verifica empréstimos vencidos
                if (usuario.temEmprestimosVencidos()) {
                    System.out.println("- Tem empréstimos com prazo vencido");
                }
                
                System.out.println("Resolva essas pendências antes de realizar novo empréstimo.");
                return null;
            }
            
            // Se chegou até aqui, pode fazer o empréstimo
            // Gera ID único para o empréstimo
            String idEmprestimo = "EMP" + proximoIdEmprestimo;
            proximoIdEmprestimo = proximoIdEmprestimo + 1;
            
            // Pega a data atual
            Date dataEmprestimo = new Date();
            
            // Calcula a data de devolução baseada no tipo de usuário
            Date dataDevolucaoPrevista = usuario.calcularPrazoDevolucao();
            
            // Cria o objeto Empréstimo
            Emprestimo novoEmprestimo = new Emprestimo(idEmprestimo, usuario, item, 
                                                      dataEmprestimo, dataDevolucaoPrevista);
            
            // Atualiza o status do item
            item.emprestar();
            
            // Adiciona o empréstimo na lista do usuário
            usuario.adicionarEmprestimo(novoEmprestimo);
            
            // Adiciona no histórico geral
            historicoEmprestimos.add(novoEmprestimo);
            
            // Exibe confirmação
            System.out.println("=== EMPRÉSTIMO REALIZADO COM SUCESSO! ===");
            System.out.println("ID do empréstimo: " + idEmprestimo);
            System.out.println("Usuário: " + usuario.getNome());
            System.out.println("Item: " + item.getTitulo());
            System.out.println("Data do empréstimo: " + dataEmprestimo);
            System.out.println("Data de devolução: " + dataDevolucaoPrevista);
            
            // Mostra quantos dias o usuário tem para devolver
            long diasDisponiveis = (dataDevolucaoPrevista.getTime() - dataEmprestimo.getTime()) / (24 * 60 * 60 * 1000);
            System.out.println("Prazo: " + diasDisponiveis + " dias");
            
            return novoEmprestimo;
            
        } catch (Exception e) {
            System.out.println("Erro inesperado ao realizar empréstimo: " + e.getMessage());
            e.printStackTrace();  // para debug
            return null;
        }
    }
    
    // =============== MÉTODOS DE DEVOLUÇÃO ===============
    
    /**
     * Método principal para realizar uma devolução
     * Finaliza o empréstimo e calcula multa se houver atraso
     * 
     * @param idEmprestimo - ID do empréstimo a ser devolvido
     */
    public void realizarDevolucao(String idEmprestimo) {
        try {
            // Validação do parâmetro de entrada
            if (idEmprestimo == null || idEmprestimo.trim().isEmpty()) {
                System.out.println("Erro: ID do empréstimo é obrigatório!");
                return;
            }
            
            // Busca o empréstimo
            Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);
            if (emprestimo == null) {
                System.out.println("Erro: Empréstimo com ID '" + idEmprestimo + "' não encontrado!");
                System.out.println("Verifique se o ID está correto.");
                return;
            }
            
            // Verifica se já foi devolvido
            if (emprestimo.getDataDevolucaoReal() != null) {
                System.out.println("Erro: Este empréstimo já foi devolvido!");
                System.out.println("Data da devolução: " + emprestimo.getDataDevolucaoReal());
                return;
            }
            
            // Obtém informações antes de modificar
            Usuario usuario = emprestimo.getUsuario();
            ItemDeAcervo item = emprestimo.getItem();
            Date dataDevolucaoReal = new Date();
            
            // Finaliza o empréstimo (calcula multa automaticamente)
            emprestimo.finalizarEmprestimo(dataDevolucaoReal);
            
            // Atualiza o status do item (marca como disponível)
            item.devolver();
            
            // Remove o empréstimo da lista do usuário
            usuario.removerEmprestimo(emprestimo);
            
            // Exibe confirmação
            System.out.println("=== DEVOLUÇÃO REALIZADA COM SUCESSO! ===");
            System.out.println("ID do empréstimo: " + idEmprestimo);
            System.out.println("Usuário: " + usuario.getNome());
            System.out.println("Item: " + item.getTitulo());
            System.out.println("Data da devolução: " + dataDevolucaoReal);
            
            // Verifica se houve multa
            double multa = emprestimo.getMultaCobrada();
            if (multa > 0) {
                System.out.println("⚠️  MULTA APLICADA: R$ " + String.format("%.2f", multa));
                System.out.println("Motivo: Devolução após o prazo limite");
                
                // Calcula quantos dias de atraso
                long diasAtraso = multa;  // como é R$ 1,00 por dia, o valor da multa = dias de atraso
                System.out.println("Dias de atraso: " + (int)diasAtraso);
                
            } else {
                System.out.println("✓ Devolvido no prazo! Sem multa.");
            }
            
        } catch (Exception e) {
            System.out.println("Erro inesperado ao realizar devolução: " + e.getMessage());
            e.printStackTrace();  // para debug
        }
    }
    
    // =============== MÉTODOS DE RELATÓRIOS ===============
    
    /**
     * Método para listar todos os usuários cadastrados
     */
    public void listarUsuarios() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         RELATÓRIO: LISTA DE USUÁRIOS");
        System.out.println("=".repeat(50));
        
        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado no sistema.");
            return;
        }
        
        System.out.println("Total de usuários: " + listaUsuarios.size());
        System.out.println();
        
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuario = listaUsuarios.get(i);
            
            System.out.println("--- USUÁRIO " + (i + 1) + " ---");
            System.out.println(usuario.toString());
            System.out.println("Itens emprestados: " + usuario.getItensEmprestados().size());
            
            // Mostra multas se houver
            double multas = usuario.calcularTotalMultas();
            if (multas > 0) {
                System.out.println("⚠️  Multas pendentes: R$ " + String.format("%.2f", multas));
            }
            
            // Mostra empréstimos ativos
            if (usuario.getItensEmprestados().size() > 0) {
                System.out.println("Itens emprestados:");
                for (int j = 0; j < usuario.getItensEmprestados().size(); j++) {
                    Emprestimo emp = usuario.getItensEmprestados().get(j);
                    System.out.println("  " + (j + 1) + ". " + emp.getItem().getTitulo());
                    System.out.println("     Prazo: " + emp.getDataDevolucaoPrevista());
                    if (emp.isEmAtraso()) {
                        System.out.println("     ⚠️  EM ATRASO!");
                    }
                }
            }
            
            System.out.println();
        }
    }
    
    /**
     * Método para listar todo o acervo da biblioteca
     */
    public void listarAcervo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         RELATÓRIO: ACERVO DA BIBLIOTECA");
        System.out.println("=".repeat(50));
        
        if (acervo.isEmpty()) {
            System.out.println("Nenhum item cadastrado no acervo.");
            return;
        }
        
        System.out.println("Total de itens no acervo: " + acervo.size());
        System.out.println();
        
        for (int i = 0; i < acervo.size(); i++) {
            ItemDeAcervo item = acervo.get(i);
            
            System.out.println("--- ITEM " + (i + 1) + " ---");
            System.out.println(item.toString());
            System.out.println("Tipo: " + item.getTipo());
            System.out.println("Identificação: " + item.getIdentificacao());
            System.out.println("Idade: " + item.calcularIdade() + " anos");
            
            // Mostra status específico
            if (item.isEmprestado()) {
                System.out.println("Status: EMPRESTADO");
            } else {
                System.out.println("Status: DISPONÍVEL");
            }
            
            System.out.println();
        }
    }
    
    /**
     * Método para listar todos os empréstimos ativos (não devolvidos)
     */
    public void listarEmprestimosAtivos() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         RELATÓRIO: EMPRÉSTIMOS ATIVOS");
        System.out.println("=".repeat(50));
        
        int quantidadeAtivos = 0;
        
        for (int i = 0; i < historicoEmprestimos.size(); i++) {
            Emprestimo emprestimo = historicoEmprestimos.get(i);
            
            // Só mostra empréstimos que ainda não foram devolvidos
            if (emprestimo.getDataDevolucaoReal() == null) {
                quantidadeAtivos++;
                
                System.out.println("--- EMPRÉSTIMO ATIVO " + quantidadeAtivos + " ---");
                System.out.println(emprestimo.toString());
                
                // Mostra informações adicionais se estiver em atraso
                if (emprestimo.isEmAtraso()) {
                    int diasAtraso = emprestimo.calcularDiasDeAtraso();
                    System.out.println("⚠️  EM ATRASO: " + diasAtraso + " dias");
                }
                
                System.out.println();
            }
        }
        
        if (quantidadeAtivos == 0) {
            System.out.println("Nenhum empréstimo ativo no momento.");
        } else {
            System.out.println("Total de empréstimos ativos: " + quantidadeAtivos);
        }
    }
    
    // =============== MÉTODOS DE PERSISTÊNCIA ===============
    
    /**
     * Método para salvar todos os dados do sistema em arquivo
     * Os dados são salvos no arquivo "dados_biblioteca.txt"
     */
    public void salvarDados() {
        try {
            // Cria o arquivo para escrita
            PrintWriter writer = new PrintWriter("dados_biblioteca.txt");
            
            // Seção 1: Salva usuários
            writer.println("# USUARIOS");
            for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuario usuario = listaUsuarios.get(i);
                
                if (usuario instanceof Aluno) {
                    // É um aluno
                    Aluno aluno = (Aluno) usuario;
                    writer.println("ALUNO|" + aluno.getId() + "|" + aluno.getNome() + "|" + 
                                 aluno.getEndereco() + "|" + aluno.getMatricula() + "|" + 
                                 aluno.getCurso() + "|" + aluno.getStatus());
                                 
                } else if (usuario instanceof Professor) {
                    // É um professor
                    Professor professor = (Professor) usuario;
                    writer.println("PROFESSOR|" + professor.getId() + "|" + professor.getNome() + "|" + 
                                 professor.getEndereco() + "|" + professor.getSiape() + "|" + 
                                 professor.getDepartamento() + "|" + professor.getStatus());
                }
            }
            
            // Seção 2: Salva acervo
            writer.println("# ACERVO");
            for (int i = 0; i < acervo.size(); i++) {
                ItemDeAcervo item = acervo.get(i);
                
                if (item instanceof Livro) {
                    // É um livro
                    Livro livro = (Livro) item;
                    writer.println("LIVRO|" + livro.getCodigo() + "|" + livro.getTitulo() + "|" + 
                                 livro.getAnoPublicacao() + "|" + livro.getAutor() + "|" + 
                                 livro.getIsbn() + "|" + livro.getEdicao() + "|" + 
                                 (livro.isEmprestado() ? "1" : "0"));
                                 
                } else if (item instanceof Revista) {
                    // É uma revista
                    Revista revista = (Revista) item;
                    writer.println("REVISTA|" + revista.getCodigo() + "|" + revista.getTitulo() + "|" + 
                                 revista.getAnoPublicacao() + "|" + revista.getEditora() + "|" + 
                                 revista.getVolume() + "|" + revista.getIssn() + "|" + 
                                 (revista.isEmprestado() ? "1" : "0"));
                }
            }
            
            // Seção 3: Salva histórico de empréstimos
            writer.println("# EMPRESTIMOS");
            for (int i = 0; i < historicoEmprestimos.size(); i++) {
                Emprestimo emprestimo = historicoEmprestimos.get(i);
                
                String dataDevolucaoReal = "0";  // "0" significa que ainda não foi devolvido
                if (emprestimo.getDataDevolucaoReal() != null) {
                    dataDevolucaoReal = String.valueOf(emprestimo.getDataDevolucaoReal().getTime());
                }
                
                writer.println(emprestimo.getIdEmprestimo() + "|" + 
                             emprestimo.getUsuario().getId() + "|" + 
                             emprestimo.getItem().getCodigo() + "|" + 
                             emprestimo.getDataEmprestimo().getTime() + "|" + 
                             emprestimo.getDataDevolucaoPrevista().getTime() + "|" + 
                             dataDevolucaoReal + "|" + 
                             emprestimo.getMultaCobrada());
            }
            
            // Fecha o arquivo
            writer.close();
            
            System.out.println("✓ Dados salvos com sucesso no arquivo 'dados_biblioteca.txt'!");
            
        } catch (IOException e) {
            System.out.println("✗ Erro ao salvar dados: " + e.getMessage());
            System.out.println("Verifique se o sistema tem permissão para criar arquivos.");
        } catch (Exception e) {
            System.out.println("✗ Erro inesperado ao salvar dados: " + e.getMessage());
        }
    }
    
    /**
     * Método para carregar todos os dados do sistema do arquivo
     * Os dados são carregados do arquivo "dados_biblioteca.txt"
     */
    public void carregarDados() {
        try {
            // Verifica se o arquivo existe
            File arquivo = new File("dados_biblioteca.txt");
            if (!arquivo.exists()) {
                System.out.println("ℹ️  Arquivo 'dados_biblioteca.txt' não encontrado.");
                System.out.println("Sistema iniciado com dados vazios.");
                return;
            }
            
            // Cria o scanner para leitura
            Scanner scanner = new Scanner(arquivo);
            
            // Variável para controlar em que seção estamos
            String secaoAtual = "";
            
            // Reinicia o contador de IDs de empréstimo
            proximoIdEmprestimo = 1;
            
            // Lê linha por linha
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                
                // Pula linhas vazias
                if (linha.isEmpty()) {
                    continue;
                }
                
                // Verifica se é uma seção (começa com #)
                if (linha.startsWith("#")) {
                    secaoAtual = linha.substring(1);  // remove o #
                    continue;
                }
                
                // Divide a linha pelos pipes (|)
                String[] dados = linha.split("\\|");
                
                // Processa conforme a seção atual
                if (secaoAtual.equals("USUARIOS")) {
                    processarUsuario(dados);
                    
                } else if (secaoAtual.equals("ACERVO")) {
                    processarItem(dados);
                    
                } else if (secaoAtual.equals("EMPRESTIMOS")) {
                    processarEmprestimo(dados);
                }
            }
            
            // Fecha o scanner
            scanner.close();
            
            System.out.println("✓ Dados carregados com sucesso!");
            
        } catch (FileNotFoundException e) {
            System.out.println("ℹ️  Arquivo 'dados_biblioteca.txt' não encontrado.");
            System.out.println("Sistema iniciado com dados vazios.");
            
        } catch (Exception e) {
            System.out.println("✗ Erro ao carregar dados: " + e.getMessage());
            System.out.println("Verifique se o formato do arquivo está correto.");
        }
    }
    
    /**
     * Método auxiliar para processar dados de usuário durante o carregamento
     */
    private void processarUsuario(String[] dados) {
        try {
            if (dados[0].equals("ALUNO")) {
                // É um aluno
                cadastrarAluno(dados[1], dados[2], dados[3], dados[4], dados[5]);
                Usuario usuario = buscarUsuarioPorId(dados[1]);
                if (usuario != null && dados.length > 6) {
                    usuario.setStatus(dados[6]);
                }
                
            } else if (dados[0].equals("PROFESSOR")) {
                // É um professor
                cadastrarProfessor(dados[1], dados[2], dados[3], dados[4], dados[5]);
                Usuario usuario = buscarUsuarioPorId(dados[1]);
                if (usuario != null && dados.length > 6) {
                    usuario.setStatus(dados[6]);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao processar usuário: " + e.getMessage());
        }
    }
    
    /**
     * Método auxiliar para processar dados de item durante o carregamento
     */
    private void processarItem(String[] dados) {
        try {
            if (dados[0].equals("LIVRO")) {
                // É um livro
                cadastrarLivro(dados[1], dados[2], Integer.parseInt(dados[3]), 
                             dados[4], dados[5], Integer.parseInt(dados[6]));
                // Atualiza status de empréstimo se necessário
                if (dados.length > 7 && dados[7].equals("1")) {
                    ItemDeAcervo item = buscarItemPorCodigo(dados[1]);
                    if (item != null) {
                        item.setIsEmprestado(true);
                    }
                }
                
            } else if (dados[0].equals("REVISTA")) {
                // É uma revista
                cadastrarRevista(dados[1], dados[2], Integer.parseInt(dados[3]), 
                               dados[4], Integer.parseInt(dados[5]), dados[6]);
                // Atualiza status de empréstimo se necessário
                if (dados.length > 7 && dados[7].equals("1")) {
                    ItemDeAcervo item = buscarItemPorCodigo(dados[1]);
                    if (item != null) {
                        item.setIsEmprestado(true);
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao processar item: " + e.getMessage());
        }
    }
    
    /**
     * Método auxiliar para processar dados de empréstimo durante o carregamento
     */
    private void processarEmprestimo(String[] dados) {
        try {
            // Extrai o número do ID para atualizar o contador
            String idTexto = dados[0];
            if (idTexto.startsWith("EMP")) {
                int numero = Integer.parseInt(idTexto.substring(3));
                proximoIdEmprestimo = Math.max(proximoIdEmprestimo, numero + 1);
            }
            
            // Busca usuário e item
            Usuario usuario = buscarUsuarioPorId(dados[1]);
            ItemDeAcervo item = buscarItemPorCodigo(dados[2]);
            
            if (usuario != null && item != null) {
                // Recria o empréstimo
                Emprestimo emprestimo = new Emprestimo(dados[0], usuario, item,
                                                    new Date(Long.parseLong(dados[3])),
                                                    new Date(Long.parseLong(dados[4])));
                
                // Atualiza ID do empréstimo
                emprestimo.setIdEmprestimo(dados[0]);
                
                // Se foi devolvido, atualiza a data e calcula multa
                if (!dados[5].equals("0")) {
                    Date dataDevolucaoReal = new Date(Long.parseLong(dados[5]));
                    emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
                    emprestimo.finalizarEmprestimo(dataDevolucaoReal);
                }
                
                // Adiciona no histórico
                historicoEmprestimos.add(emprestimo);
                
                // Adiciona na lista do usuário se ainda está emprestado
                if (emprestimo.getDataDevolucaoReal() == null) {
                    usuario.adicionarEmprestimo(emprestimo);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao processar empréstimo: " + e.getMessage());
        }
    }
    
    // =============== MÉTODOS ESTATÍSTICOS ===============
    
    /**
     * Método para obter estatísticas básicas do sistema
     */
    public void mostrarEstatisticas() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("         ESTATÍSTICAS DO SISTEMA");
        System.out.println("=".repeat(50));
        
        // Conta usuários por tipo
        int alunos = 0, professores = 0;
        for (Usuario usuario : listaUsuarios) {
            if (usuario instanceof Aluno) {
                alunos++;
            } else if (usuario instanceof Professor) {
                professores++;
            }
        }
        
        // Conta itens por tipo
        int livros = 0, revistas = 0;
        for (ItemDeAcervo item : acervo) {
            if (item instanceof Livro) {
                livros++;
            } else if (item instanceof Revista) {
                revistas++;
            }
        }
        
        // Conta empréstimos
        int emprestimosAtivos = 0, emprestimosFinalizados = 0;
        double totalMultas = 0;
        
        for (Emprestimo emp : historicoEmprestimos) {
            if (emp.getDataDevolucaoReal() == null) {
                emprestimosAtivos++;
            } else {
                emprestimosFinalizados++;
                totalMultas += emp.getMultaCobrada();
            }
        }
        
        // Exibe as estatísticas
        System.out.println("USUÁRIOS:");
        System.out.println("  Total: " + listaUsuarios.size());
        System.out.println("  Alunos: " + alunos);
        System.out.println("  Professores: " + professores);
        
        System.out.println("\nACERVO:");
        System.out.println("  Total de itens: " + acervo.size());
        System.out.println("  Livros: " + livros);
        System.out.println("  Revistas: " + revistas);
        
        System.out.println("\nEMPRÉSTIMOS:");
        System.out.println("  Total: " + historicoEmprestimos.size());
        System.out.println("  Ativos: " + emprestimosAtivos);
        System.out.println("  Finalizados: " + emprestimosFinalizados);
        System.out.println("  Total de multas: R$ " + String.format("%.2f", totalMultas));
    }
}