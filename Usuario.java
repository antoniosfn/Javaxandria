import java.util.ArrayList;
import java.util.List;

/**
 * Classe Usuario - classe base para todos os usuários da biblioteca
 * Esta é uma classe abstrata, ou seja, não pode ser instanciada diretamente
 * Só pode ser herdada por outras classes (Aluno, Professor, etc.)
 * 
 * Conceitos demonstrados:
 * - Abstração: classe abstrata com métodos abstratos
 * - Herança: pode ser herdada por outras classes
 * - Encapsulamento: atributos privados com métodos de acesso
 * 
 * @author Antonio Neto
 */
public abstract class Usuario {
    
    // Atributos privados da classe Usuario
    private String id;  // ID único do usuário
    private String nome;  // nome completo do usuário
    private String endereco;  // endereço do usuário
    private String status;  // status do usuário (Ativo, Inativo, etc.)
    private List<Emprestimo> itensEmprestados;  // lista de itens que o usuário emprestou
    
    /**
     * Construtor da classe Usuario
     * Este método é chamado quando se cria um novo usuário
     * 
     * @param id - ID único do usuário
     * @param nome - nome completo do usuário
     * @param endereco - endereço do usuário
     */
    public Usuario(String id, String nome, String endereco) {
        // Inicializa todos os atributos
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.status = "Ativo";  // novo usuário sempre começa como ativo
        this.itensEmprestados = new ArrayList<>();  // cria lista vazia de empréstimos
    }
    
    /**
     * Método getter para obter o ID do usuário
     * @return o ID do usuário
     */
    public String getId() {
        return id;
    }
    
    /**
     * Método setter para alterar o ID do usuário
     * @param id novo ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Método getter para obter o nome do usuário
     * @return o nome do usuário
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Método setter para alterar o nome do usuário
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Método getter para obter o endereço do usuário
     * @return o endereço do usuário
     */
    public String getEndereco() {
        return endereco;
    }
    
    /**
     * Método setter para alterar o endereço do usuário
     * @param endereco novo endereço
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    /**
     * Método getter para obter o status do usuário
     * @return o status do usuário
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * Método setter para alterar o status do usuário
     * @param status novo status (Ativo, Inativo, etc.)
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Método getter para obter a lista de itens emprestados
     * @return lista de empréstimos do usuário
     */
    public List<Emprestimo> getItensEmprestados() {
        return itensEmprestados;
    }
    
    /**
     * Método setter para alterar a lista de itens emprestados
     * @param itensEmprestados nova lista de empréstimos
     */
    public void setItensEmprestados(List<Emprestimo> itensEmprestados) {
        this.itensEmprestados = itensEmprestados;
    }
    
    /**
     * Método para verificar se o usuário pode fazer empréstimo
     * Este método pode ser sobrescrito nas subclasses para implementar regras específicas
     * Um usuário pode fazer empréstimo se:
     * - Estiver com status "Ativo"
     * - Não tiver multas pendentes
     * - Não tiver empréstimos com prazo vencido
     * 
     * @return true se pode fazer empréstimo, false caso contrário
     */
    public boolean isAptoParaEmprestimo() {
        // Verifica se o usuário está ativo
        if (this.status == null) {
            return false;
        }
        
        if (!this.status.equals("Ativo")) {
            return false;
        }
        
        // Verifica se o usuário tem multa pendente
        // Para isso, verifica todos os empréstimos
        for (int i = 0; i < this.itensEmprestados.size(); i++) {
            Emprestimo emprestimo = this.itensEmprestados.get(i);
            
            // Se tem multa cobrada, não pode fazer novo empréstimo
            if (emprestimo.getMultaCobrada() > 0) {
                return false;
            }
            
            // Se tem empréstimo com prazo vencido e ainda não foi devolvido
            if (emprestimo.getDataDevolucaoReal() == null) {
                java.util.Date dataAtual = new java.util.Date();
                java.util.Date dataPrazo = emprestimo.getDataDevolucaoPrevista();
                
                if (dataPrazo.before(dataAtual)) {
                    // O prazo já passou
                    return false;
                }
            }
        }
        
        // Se chegou até aqui, o usuário pode fazer empréstimo
        return true;
    }
    
    /**
     * Método abstrato para calcular o prazo de devolução
     * Cada tipo de usuário (aluno, professor) pode ter prazos diferentes
     * Por isso, este método é abstrato e deve ser implementado nas subclasses
     * 
     * @return data limite para devolução
     */
    public abstract java.util.Date calcularPrazoDevolucao();
    
    /**
     * Método para adicionar um novo empréstimo ao usuário
     * Este método é chamado quando o usuário faz um empréstimo
     * 
     * @param emprestimo o empréstimo a ser adicionado
     */
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        // Adiciona o empréstimo na lista
        this.itensEmprestados.add(emprestimo);
    }
    
    /**
     * Método para remover um empréstimo do usuário
     * Este método é chamado quando o usuário devolve um item
     * 
     * @param emprestimo o empréstimo a ser removido
     */
    public void removerEmprestimo(Emprestimo emprestimo) {
        // Remove o empréstimo da lista
        this.itensEmprestados.remove(emprestimo);
    }
    
    /**
     * Método para obter a quantidade de itens emprestados pelo usuário
     * Útil para verificar limites de empréstimo
     * 
     * @return quantidade de itens emprestados
     */
    public int getQuantidadeEmprestimos() {
        return this.itensEmprestados.size();
    }
    
    /**
     * Método para calcular o total de multas pendentes do usuário
     * 
     * @return valor total das multas pendentes
     */
    public double calcularTotalMultas() {
        double total = 0.0;
        
        for (int i = 0; i < this.itensEmprestados.size(); i++) {
            Emprestimo emp = this.itensEmprestados.get(i);
            total = total + emp.getMultaCobrada();
        }
        
        return total;
    }
    
    /**
     * Método para verificar se o usuário tem empréstimos com prazo vencido
     * 
     * @return true se tem algum empréstimo vencido, false caso contrário
     */
    public boolean temEmprestimosVencidos() {
        java.util.Date dataAtual = new java.util.Date();
        
        for (int i = 0; i < this.itensEmprestados.size(); i++) {
            Emprestimo emp = this.itensEmprestados.get(i);
            
            // Se ainda não foi devolvido e o prazo já passou
            if (emp.getDataDevolucaoReal() == null) {
                if (emp.getDataDevolucaoPrevista().before(dataAtual)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Método para converter os dados do usuário em string
     * Útil para exibir informações do usuário
     * 
     * @return string com as principais informações do usuário
     */
    @Override
    public String toString() {
        String resultado = "ID: " + this.id;
        resultado = resultado + ", Nome: " + this.nome;
        resultado = resultado + ", Status: " + this.status;
        return resultado;
    }
    
    /**
     * Método para mostrar informações detalhadas do usuário
     * Pode ser usado para relatórios ou exibição de dados
     */
    public void mostrarInformacoesCompletas() {
        System.out.println("=== INFORMAÇÕES DO USUÁRIO ===");
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("Endereço: " + this.endereco);
        System.out.println("Status: " + this.status);
        System.out.println("Quantidade de empréstimos: " + this.itensEmprestados.size());
        
        double totalMultas = this.calcularTotalMultas();
        System.out.println("Total de multas: R$ " + totalMultas);
        
        if (this.temEmprestimosVencidos()) {
            System.out.println("⚠️ ATENÇÃO: Tem empréstimos com prazo vencido!");
        }
        
        // Mostra todos os empréstimos
        if (this.itensEmprestados.size() > 0) {
            System.out.println("\nEmpréstimos:");
            for (int i = 0; i < this.itensEmprestados.size(); i++) {
                Emprestimo emp = this.itensEmprestados.get(i);
                System.out.println("  " + (i+1) + ". " + emp.getItem().getTitulo());
                System.out.println("     Prazo: " + emp.getDataDevolucaoPrevista());
                if (emp.getMultaCobrada() > 0) {
                    System.out.println("     Multa: R$ " + emp.getMultaCobrada());
                }
            }
        }
    }
}