/**
 * Classe Aluno - representa um aluno da biblioteca
 * Esta classe herda da classe Usuario e adiciona características específicas do aluno
 * Alunos podem pegar até 3 livros emprestados e têm prazo de 7 dias para devolver
 * 
 * @author Antonio Neto
 */
public class Aluno extends Usuario {
    
    // Atributos da classe Aluno
    private String matricula;  // número de matrícula do aluno
    private String curso;      // curso que o aluno está cursando
    private int limiteDeEmprestimos;  // limite de livros que aluno pode pegar (3)
    
    /**
     * Construtor da classe Aluno
     * Cria um novo aluno com os dados fornecidos
     * 
     * @param id - ID único do aluno
     * @param nome - nome completo do aluno
     * @param endereco - endereço do aluno
     * @param matricula - número de matrícula
     * @param curso - nome do curso
     */
    public Aluno(String id, String nome, String endereco, String matricula, String curso) {
        // Chama o construtor da classe pai (Usuario)
        super(id, nome, endereco);
        
        // Inicializa os atributos específicos do aluno
        this.matricula = matricula;
        this.curso = curso;
        this.limiteDeEmprestimos = 3;  // aluno pode pegar máximo 3 livros
    }
    
    /**
     * Método para obter a matrícula do aluno
     * @return matrícula do aluno
     */
    public String getMatricula() {
        return matricula;
    }
    
    /**
     * Método para alterar a matrícula do aluno
     * @param matricula nova matrícula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    /**
     * Método para obter o curso do aluno
     * @return curso do aluno
     */
    public String getCurso() {
        return curso;
    }
    
    /**
     * Método para alterar o curso do aluno
     * @param curso novo curso
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    /**
     * Método para obter o limite de empréstimos do aluno
     * @return limite de empréstimos (3)
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
     * Método sobrescrito para verificar se o aluno pode fazer empréstimo
     * Um aluno pode fazer empréstimo se:
     * - Estiver ativo
     * - Não tiver multas pendentes
     * - Tiver menos de 3 livros emprestados
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
        
        // Verifica se o aluno já pegou o máximo de livros
        int quantidadeEmprestada = this.getItensEmprestados().size();
        
        if (quantidadeEmprestada >= limiteDeEmprestimos) {
            return false;  // já atingiu o limite
        }
        
        return true;  // pode fazer empréstimo
    }
    
    /**
     * Método sobrescrito para calcular quando o aluno deve devolver o livro
     * Alunos têm 7 dias para devolver
     * 
     * @return data limite para devolução
     */
    @Override
    public java.util.Date calcularPrazoDevolucao() {
        // Pega a data atual
        java.util.Calendar calendario = java.util.Calendar.getInstance();
        
        // Adiciona 7 dias
        calendario.add(java.util.Calendar.DAY_OF_MONTH, 7);
        
        // Retorna a nova data
        return calendario.getTime();
    }
    
    /**
     * Método para converter os dados do aluno em texto
     * Usado para exibir informações do aluno
     * 
     * @return string com informações do aluno
     */
    @Override
    public String toString() {
        // Usa formatação simples com concatenação de strings
        String resultado = "Aluno: " + super.toString();
        resultado = resultado + " | Matrícula: " + this.matricula;
        resultado = resultado + " | Curso: " + this.curso;
        
        return resultado;
    }
    
    /**
     * Método específico para mostrar informações detalhadas do aluno
     * Útil para relatórios
     */
    public void mostrarDetalhes() {
        System.out.println("=== DETALHES DO ALUNO ===");
        System.out.println("ID: " + this.getId());
        System.out.println("Nome: " + this.getNome());
        System.out.println("Endereço: " + this.getEndereco());
        System.out.println("Matrícula: " + this.matricula);
        System.out.println("Curso: " + this.curso);
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
}