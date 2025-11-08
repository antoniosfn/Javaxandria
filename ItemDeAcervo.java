import java.util.Date;

/**
 * Classe ItemDeAcervo - classe base para todos os itens da biblioteca
 * Esta é uma classe abstrata, ou seja, não pode ser instanciada diretamente
 * Pode ser herdada por outras classes como Livro, Revista, etc.
 * 
 * Conceitos demonstrados:
 * - Abstração: classe abstrata com métodos abstratos
 * - Herança: pode ser herdada por outras classes
 * - Encapsulamento: atributos privados com métodos de acesso
 * 
 * @author Antonio Neto
 */
public abstract class ItemDeAcervo {
    
    // Atributos privados da classe ItemDeAcervo
    private String codigo;  // código único do item
    private String titulo;  // título do item
    private int anoPublicacao;  // ano de publicação
    private boolean isEmprestado;  // indica se está emprestado
    private Date dataCadastro;  // data quando foi cadastrado no sistema
    
    /**
     * Construtor da classe ItemDeAcervo
     * Este método é chamado quando se cria um novo item
     * 
     * @param codigo - código único do item
     * @param titulo - título do item
     * @param anoPublicacao - ano de publicação
     */
    public ItemDeAcervo(String codigo, String titulo, int anoPublicacao) {
        // Inicializa todos os atributos
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.isEmprestado = false;  // novo item sempre começa como disponível
        this.dataCadastro = new Date();  // registra a data de cadastro
    }
    
    /**
     * Método getter para obter o código do item
     * @return o código do item
     */
    public String getCodigo() {
        return codigo;
    }
    
    /**
     * Método setter para alterar o código do item
     * @param codigo novo código
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Método getter para obter o título do item
     * @return o título do item
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Método setter para alterar o título do item
     * @param titulo novo título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Método getter para obter o ano de publicação
     * @return o ano de publicação
     */
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    
    /**
     * Método setter para alterar o ano de publicação
     * @param anoPublicacao novo ano de publicação
     */
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    
    /**
     * Método getter para verificar se está emprestado
     * @return true se está emprestado, false caso contrário
     */
    public boolean isEmprestado() {
        return isEmprestado;
    }
    
    /**
     * Método setter para alterar o status de empréstimo
     * @param emprestado novo status
     */
    public void setIsEmprestado(boolean emprestado) {
        this.isEmprestado = emprestado;
    }
    
    /**
     * Método getter para obter a data de cadastro
     * @return data de cadastro do item
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }
    
    /**
     * Método para verificar se o item está disponível para empréstimo
     * @return true se disponível, false caso contrário
     */
    public boolean isDisponivel() {
        return !this.isEmprestado;
    }
    
    /**
     * Método abstrato para obter a identificação específica do item
     * Cada tipo de item (livro, revista) tem sua própria identificação
     * Por isso, este método é abstrato e deve ser implementado nas subclasses
     * 
     * @return identificação do item (ISBN para livros, ISSN para revistas, etc.)
     */
    public abstract String getIdentificacao();
    
    /**
     * Método abstrato para obter o tipo do item
     * Cada tipo de item deve retornar seu próprio tipo
     * 
     * @return tipo do item ("Livro", "Revista", etc.)
     */
    public abstract String getTipo();
    
    /**
     * Método para realizar empréstimo do item
     * Marca o item como emprestado
     * 
     * @throws IllegalStateException se o item já estiver emprestado
     */
    public void emprestar() {
        // Verifica se o item já está emprestado
        if (this.isEmprestado) {
            throw new IllegalStateException("Item já está emprestado! Não pode ser emprestado novamente.");
        }
        
        // Marca o item como emprestado
        this.isEmprestado = true;
    }
    
    /**
     * Método para devolver o item
     * Marca o item como disponível novamente
     * 
     * @throws IllegalStateException se o item não estiver emprestado
     */
    public void devolver() {
        // Verifica se o item realmente está emprestado
        if (!this.isEmprestado) {
            throw new IllegalStateException("Item não está emprestado! Não pode ser devolvido.");
        }
        
        // Marca o item como disponível
        this.isEmprestado = false;
    }
    
    /**
     * Método para calcular a idade do item em anos
     * Útil para relatórios de acervos
     * 
     * @return idade em anos
     */
    public int calcularIdade() {
        Date dataAtual = new Date();
        int anoAtual = dataAtual.getYear() + 1900;  // getYear() retorna anos desde 1900
        
        return anoAtual - this.anoPublicacao;
    }
    
    /**
     * Método para verificar se o item é antigo (mais de 10 anos)
     * 
     * @return true se é antigo, false caso contrário
     */
    public boolean éAntigo() {
        return this.calcularIdade() > 10;
    }
    
    /**
     * Método para verificar se o item é muito antigo (mais de 20 anos)
     * 
     * @return true se é muito antigo, false caso contrário
     */
    public boolean éMuitoAntigo() {
        return this.calcularIdade() > 20;
    }
    
    /**
     * Método para obter informações resumidas do item
     * 
     * @return string com resumo do item
     */
    public String getResumo() {
        return "[" + this.getTipo() + "] " + this.titulo + " (" + this.anoPublicacao + ")";
    }
    
    /**
     * Método para converter os dados do item em string
     * Útil para exibir informações do item
     * 
     * @return string com as principais informações do item
     */
    @Override
    public String toString() {
        String resultado = "Código: " + this.codigo;
        resultado = resultado + ", Título: " + this.titulo;
        resultado = resultado + ", Ano: " + this.anoPublicacao;
        resultado = resultado + ", Emprestado: ";
        
        if (this.isEmprestado) {
            resultado = resultado + "Sim";
        } else {
            resultado = resultado + "Não";
        }
        
        return resultado;
    }
    
    /**
     * Método equals para comparar itens pelo código
     * 
     * @param obj objeto a ser comparado
     * @return true se são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        // Verifica se é o mesmo objeto
        if (this == obj) {
            return true;
        }
        
        // Verifica se o objeto é null ou de classe diferente
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        // Faz o cast e compara os códigos
        ItemDeAcervo that = (ItemDeAcervo) obj;
        return this.codigo.equals(that.codigo);
    }
    
    /**
     * Método hashCode baseado no código
     * 
     * @return hash code do item
     */
    @Override
    public int hashCode() {
        return this.codigo.hashCode();
    }
    
    /**
     * Método para mostrar informações detalhadas do item
     * Pode ser usado para relatórios ou exibição de dados
     */
    public void mostrarInformacoesCompletas() {
        System.out.println("=== INFORMAÇÕES DO ITEM ===");
        System.out.println("Tipo: " + this.getTipo());
        System.out.println("Código: " + this.codigo);
        System.out.println("Título: " + this.titulo);
        System.out.println("Ano de publicação: " + this.anoPublicacao);
        System.out.println("Idade: " + this.calcularIdade() + " anos");
        System.out.println("Status: ");
        
        if (this.isEmprestado) {
            System.out.println("  Emprestado");
        } else {
            System.out.println("  Disponível");
        }
        
        System.out.println("Data de cadastro: " + this.dataCadastro);
        System.out.println("Identificação: " + this.getIdentificacao());
        
        if (this.éAntigo()) {
            System.out.println("⚠️ Item antigo (mais de 10 anos)");
        }
        
        if (this.éMuitoAntigo()) {
            System.out.println("⚠️ Item muito antigo (mais de 20 anos)");
        }
    }
}