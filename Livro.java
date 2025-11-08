/**
 * Classe Livro - representa um livro da biblioteca
 * Esta classe herda da classe ItemDeAcervo e adiciona características específicas do livro
 * 
 * Conceitos demonstrados:
 * - Herança: herda de ItemDeAcervo
 * - Polimorfismo: implementa métodos abstratos da classe pai
 * - Encapsulamento: controla acesso aos dados específicos do livro
 * 
 * @author Antonio Neto
 */
public class Livro extends ItemDeAcervo {
    
    // Atributos específicos da classe Livro
    private String autor;  // nome do autor do livro
    private String isbn;  // código ISBN do livro
    private int edicao;  // edição do livro
    
    /**
     * Construtor da classe Livro
     * Cria um novo livro com os dados fornecidos
     * 
     * @param codigo - código único do livro
     * @param titulo - título do livro
     * @param anoPublicacao - ano de publicação
     * @param autor - nome do autor
     * @param isbn - código ISBN
     * @param edicao - edição do livro
     */
    public Livro(String codigo, String titulo, int anoPublicacao, 
                String autor, String isbn, int edicao) {
        
        // Chama o construtor da classe pai (ItemDeAcervo)
        super(codigo, titulo, anoPublicacao);
        
        // Inicializa os atributos específicos do livro
        this.autor = autor;
        this.isbn = isbn;
        this.edicao = edicao;
    }
    
    /**
     * Método getter para obter o autor do livro
     * @return nome do autor
     */
    public String getAutor() {
        return autor;
    }
    
    /**
     * Método setter para alterar o autor do livro
     * @param autor novo autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    /**
     * Método getter para obter o ISBN do livro
     * @return código ISBN
     */
    public String getIsbn() {
        return isbn;
    }
    
    /**
     * Método setter para alterar o ISBN do livro
     * @param isbn novo ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    /**
     * Método getter para obter a edição do livro
     * @return edição do livro
     */
    public int getEdicao() {
        return edicao;
    }
    
    /**
     * Método setter para alterar a edição do livro
     * @param edicao nova edição
     */
    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }
    
    /**
     * Método sobrescrito para obter a identificação específica do livro
     * Para livros, a identificação é o ISBN
     * Este método implementa o método abstrato da classe pai
     * 
     * @return ISBN do livro
     */
    @Override
    public String getIdentificacao() {
        return this.isbn;
    }
    
    /**
     * Método sobrescrito para obter o tipo do item
     * Retorna "Livro" para todos os objetos desta classe
     * 
     * @return tipo do item
     */
    @Override
    public String getTipo() {
        return "Livro";
    }
    
    /**
     * Método para verificar se o livro é da primeira edição
     * 
     * @return true se é primeira edição, false caso contrário
     */
    public boolean éPrimeiraEdicao() {
        return this.edicao == 1;
    }
    
    /**
     * Método para obter o nome completo da autoria
     * Útil para relatórios que mostram múltiplos autores
     * 
     * @return string com informações de autoria
     */
    public String getInformacaoAutoria() {
        if (this.autor != null && !this.autor.isEmpty()) {
            return "Autor: " + this.autor;
        } else {
            return "Autor: Não informado";
        }
    }
    
    /**
     * Método para obter informações completas do ISBN
     * Inclui formatação e validação básica
     * 
     * @return string com informações do ISBN
     */
    public String getInformacaoISBN() {
        if (this.isbn != null && !this.isbn.isEmpty()) {
            return "ISBN: " + this.isbn;
        } else {
            return "ISBN: Não informado";
        }
    }
    
    /**
     * Método para obter uma referência bibliográfica simples
     * Útil para citações e referências
     * 
     * @return referência bibliográfica
     */
    public String getReferenciaBibliografica() {
        String referencia = this.autor + ". ";
        referencia = referencia + this.getTitulo() + ". ";
        referencia = referencia + this.edicao + " ed. ";
        referencia = referencia + this.getAnoPublicacao() + ".";
        
        return referencia;
    }
    
    /**
     * Método para converter os dados do livro em string
     * Útil para exibir informações do livro
     * 
     * @return string com informações do livro
     */
    @Override
    public String toString() {
        // Usa formatação simples com concatenação de strings
        String resultado = "LIVRO - " + super.toString();
        resultado = resultado + " | Autor: " + this.autor;
        resultado = resultado + " | ISBN: " + this.isbn;
        resultado = resultado + " | Edição: " + this.edicao;
        
        return resultado;
    }
    
    /**
     * Método para mostrar informações detalhadas do livro
     * Útil para relatórios e consultas
     */
    @Override
    public void mostrarInformacoesCompletas() {
        System.out.println("=== INFORMAÇÕES DO LIVRO ===");
        
        // Chama o método da classe pai para mostrar informações básicas
        super.mostrarInformacoesCompletas();
        
        // Adiciona informações específicas do livro
        System.out.println("Autor: " + this.autor);
        System.out.println("ISBN: " + this.isbn);
        System.out.println("Edição: " + this.edicao);
        
        if (this.éPrimeiraEdicao()) {
            System.out.println("✓ Primeira edição");
        } else {
            System.out.println("✓ Edição: " + this.edicao);
        }
        
        System.out.println("Referência bibliográfica:");
        System.out.println(this.getReferenciaBibliografica());
    }
    
    /**
     * Método para obter um resumo completo do livro
     * Combina informações básicas e específicas
     * 
     * @return resumo completo
     */
    public String getResumoCompleto() {
        String resumo = "[LIVRO] " + this.getTitulo();
        resumo = resumo + " - " + this.autor;
        resumo = resumo + " (" + this.getAnoPublicacao() + ")";
        resumo = resumo + " - ISBN: " + this.isbn;
        resumo = resumo + " - " + this.edicao + "ª edição";
        
        return resumo;
    }
}