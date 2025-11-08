/**
 * Classe Professor - representa um professor da biblioteca
 * Esta classe herda da classe Usuario e adiciona características específicas do professor
 * Professores podem pegar até 5 livros emprestados e têm prazo de 15 dias para devolver
 * 
 * @author Antonio Neto
 */
public class Professor extends Usuario {
    
    // Atributos da classe Professor
    private String siape;  // número SIAPE do professor
    private String departamento;  // departamento onde o professor trabalha
    private int limiteDeEmprestimos;  // limite de livros que professor pode pegar (5)
    
    /**
     * Construtor da classe Professor
     * Cria um novo professor com os dados fornecidos
     * 
     * @param id - ID único do professor
     * @param nome - nome completo do professor
     * @param endereco - endereço do professor
     * @param siape - número SIAPE
     * @param departamento - departamento onde trabalha
     */
    public Professor(String id, String nome, String endereco, String siape, String departamento) {
        // Chama o construtor da classe pai (Usuario)
        super(id, nome, endereco);
        
        // Inicializa os atributos específicos do professor
        this.siape = siape;
        this.departamento = departamento;
        this.limiteDeEmprestimos = 5;  // professor pode pegar máximo 5 livros
    }
    
    /**
     * Método para obter o SIAPE do professor
     * @return SIAPE do professor
     */
    public String getSiape() {
        return siape;
    }
    
    /**
     * Método para alterar o SIAPE do professor
     * @param siape novo SIAPE
     */
    public void setSiape(String siape) {
        this.siape = siape;
    }
    
    /**
     * Método para obter o departamento do professor
     * @return departamento do professor
     */
    public String getDepartamento() {
        return departamento;
    }
    
    /**
     * Método para alterar o departamento do professor
     * @param departamento novo departamento
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    /**
     * Método para obter o limite de empréstimos do professor
     * @return limite de empréstimos (5)
     */
    public int getLimiteDeEmprestimos() {
        return limiteDeEmprestimos;
    }
    
    /**
     * Método para alterar o limite de empréstimos (não deve ser alterado normalmente)
     * @param limiteDeEmprestimos novo limite
     */
    public void setLimiteDeEmprestimos(int limiteDeEmprestimos) {
        this.limiteDeEmprestimos = limiteDeEmprestimos;
    }
    
    /**
     * Método sobrescrito para verificar se o professor pode fazer empréstimo
     * Um professor pode fazer empréstimo se:
     * - Estiver ativo
     * - Não tiver multas pendentes
     * - Tiver menos de 5 livros emprestados
     * 
     * @return true se pode fazer empréstimo, false caso contrário
     */
    @Override
    public boolean isAptoParaEmprestimo() {
        // Primeiro chama o método da classe pai para verificar status e multas
        boolean statusOk = super.isAptoParaEmprestimo();
        
        if (!statusOk) {
            return false;  // se não passou na verificação da classe pai, já retorna false
        }
        
        // Verifica se o professor já pegou o máximo de livros
        int quantidadeEmprestada = this.getItensEmprestados().size();
        
        if (quantidadeEmprestada >= limiteDeEmprestimos) {
            return false;  // já atingiu o limite
        }
        
        return true;  // pode fazer empréstimo
    }
    
    /**
     * Método sobrescrito para calcular quando o professor deve devolver o livro
     * Professores têm 15 dias para devolver (mais que os alunos)
     * 
     * @return data limite para devolução
     */
    @Override
    public java.util.Date calcularPrazoDevolucao() {
        // Pega a data atual
        java.util.Calendar calendario = java.util.Calendar.getInstance();
        
        // Adiciona 15 dias (mais que os alunos)
        calendario.add(java.util.Calendar.DAY_OF_MONTH, 15);
        
        // Retorna a nova data
        return calendario.getTime();
    }
    
    /**
     * Método para converter os dados do professor em texto
     * Usado para exibir informações do professor
     * 
     * @return string com informações do professor
     */
    @Override
    public String toString() {
        // Usa formatação simples com concatenação de strings
        String resultado = "Professor: " + super.toString();
        resultado = resultado + " | SIAPE: " + this.siape;
        resultado = resultado + " | Departamento: " + this.departamento;
        
        return resultado;
    }
    
    /**
     * Método específico para mostrar informações detalhadas do professor
     * Útil para relatórios
     */
    public void mostrarDetalhes() {
        System.out.println("=== DETALHES DO PROFESSOR ===");
        System.out.println("ID: " + this.getId());
        System.out.println("Nome: " + this.getNome());
        System.out.println("Endereço: " + this.getEndereco());
        System.out.println("SIAPE: " + this.siape);
        System.out.println("Departamento: " + this.departamento);
        System.out.println("Status: " + this.getStatus());
        System.out.println("Limite de empréstimos: " + this.limiteDeEmprestimos);
        System.out.println("Livros emprestados: " + this.getItensEmprestados().size());
        
        if (this.getItensEmprestados().size() > 0) {
            System.out.println("Itens emprestados:");
            for (int i = 0; i < this.getItensEmprestados().size(); i++) {
                Emprestimo emp = this.getItensEmprestados().get(i);
                System.out.println("  " + (i+1) + ". " + emp.getItem().getTitulo() + " (Prazo: " + emp.getDataDevolucaoPrevista() + ")");
            }
        }
    }
    
    /**
     * Método para verificar se o professor é do departamento de informática
     * Útil para alguns relatórios específicos
     * 
     * @return true se é do departamento de informática, false caso contrário
     */
    public boolean éDoDepartamentoInformatica() {
        String dept = this.departamento.toLowerCase();
        return dept.contains("informática") || dept.contains("informatica") || dept.contains("computação") || dept.contains("computacao");
    }
    
    /**
     * Método para obter o nome completo do professor com título
     * Útil para relatórios oficiais
     * 
     * @return nome com título "Prof. Dr."
     */
    public String getNomeComTitulo() {
        return "Prof. Dr. " + this.getNome();
    }
}