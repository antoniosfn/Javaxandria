/**
 * Classe Revista - representa uma revista da biblioteca
 * Esta classe herda da classe ItemDeAcervo e adiciona características específicas da revista
 * 
 * Conceitos demonstrados:
 * - Herança: herda de ItemDeAcervo
 * - Polimorfismo: implementa métodos abstratos da classe pai
 * - Encapsulamento: controla acesso aos dados específicos da revista
 * 
 * @author Antonio Neto
 */
public class Revista extends ItemDeAcervo {
    
    // Atributos específicos da classe Revista
    private String editora;  // nome da editora da revista
    private int volume;  // número do volume
    private String issn;  // código ISSN da revista
    
    /**
     * Construtor da classe Revista
     * Cria uma nova revista com os dados fornecidos
     * 
     * @param codigo - código único da revista
     * @param titulo - título da revista
     * @param anoPublicacao - ano de publicação
     * @param editora - nome da editora
     * @param volume - número do volume
     * @param issn - código ISSN
     */
    public Revista(String codigo, String titulo, int anoPublicacao,
                  String editora, int volume, String issn) {
        
        // Chama o construtor da classe pai (ItemDeAcervo)
        super(codigo, titulo, anoPublicacao);
        
        // Inicializa os atributos específicos da revista
        this.editora = editora;
        this.volume = volume;
        this.issn = issn;
    }
    
    /**
     * Método getter para obter a editora da revista
     * @return nome da editora
     */
    public String getEditora() {
        return editora;
    }
    
    /**
     * Método setter para alterar a editora da revista
     * @param editora nova editora
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }
    
    /**
     * Método getter para obter o volume da revista
     * @return número do volume
     */
    public int getVolume() {
        return volume;
    }
    
    /**
     * Método setter para alterar o volume da revista
     * @param volume novo volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    /**
     * Método getter para obter o ISSN da revista
     * @return código ISSN
     */
    public String getIssn() {
        return issn;
    }
    
    /**
     * Método setter para alterar o ISSN da revista
     * @param issn novo ISSN
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }
    
    /**
     * Método sobrescrito para obter a identificação específica da revista
     * Para revistas, a identificação é o ISSN
     * Este método implementa o método abstrato da classe pai
     * 
     * @return ISSN da revista
     */
    @Override
    public String getIdentificacao() {
        return this.issn;
    }
    
    /**
     * Método sobrescrito para obter o tipo do item
     * Retorna "Revista" para todos os objetos desta classe
     * 
     * @return tipo do item
     */
    @Override
    public String getTipo() {
        return "Revista";
    }
    
    /**
     * Método para verificar se a revista é um volume especial
     * Considera especial se o volume for 1 ou múltiplo de 10
     * 
     * @return true se é volume especial, false caso contrário
     */
    public boolean éVolumeEspecial() {
        return this.volume == 1 || this.volume % 10 == 0;
    }
    
    /**
     * Método para obter informações completas da editora
     * Inclui formatação e validação básica
     * 
     * @return string com informações da editora
     */
    public String getInformacaoEditora() {
        if (this.editora != null && !this.editora.isEmpty()) {
            return "Editora: " + this.editora;
        } else {
            return "Editora: Não informado";
        }
    }
    
    /**
     * Método para obter informações completas do ISSN
     * Inclui formatação e validação básica
     * 
     * @return string com informações do ISSN
     */
    public String getInformacaoISSN() {
        if (this.issn != null && !this.issn.isEmpty()) {
            return "ISSN: " + this.issn;
        } else {
            return "ISSN: Não informado";
        }
    }
    
    /**
     * Método para obter o volume formatado
     * Adiciona "Vol." antes do número
     * 
     * @return volume formatado
     */
    public String getVolumeFormatado() {
        return "Vol. " + this.volume;
    }
    
    /**
     * Método para obter uma referência bibliográfica simples
     * Útil para citações e referências
     * 
     * @return referência bibliográfica
     */
    public String getReferenciaBibliografica() {
        String referencia = this.getTitulo() + ". ";
        referencia = referencia + this.getVolumeFormatado() + ", ";
        referencia = referencia + this.getAnoPublicacao() + ", ";
        referencia = referencia + this.editora + ".";
        
        return referencia;
    }
    
    /**
     * Método para verificar se a revista é recente (menos de 2 anos)
     * 
     * @return true se é recente, false caso contrário
     */
    public boolean éRecente() {
        return this.calcularIdade() <= 2;
    }
    
    /**
     * Método para obter o volume anterior
     * Assume que os volumes são sequenciais
     * 
     * @return número do volume anterior (0 se este é o primeiro)
     */
    public int getVolumeAnterior() {
        if (this.volume > 1) {
            return this.volume - 1;
        } else {
            return 0; // não há volume anterior
        }
    }
    
    /**
     * Método para obter o próximo volume
     * Assume que os volumes são sequenciais
     * 
     * @return número do próximo volume
     */
    public int getProximoVolume() {
        return this.volume + 1;
    }
    
    /**
     * Método para converter os dados da revista em string
     * Útil para exibir informações da revista
     * 
     * @return string com informações da revista
     */
    @Override
    public String toString() {
        // Usa formatação simples com concatenação de strings
        String resultado = "REVISTA - " + super.toString();
        resultado = resultado + " | Editora: " + this.editora;
        resultado = resultado + " | Volume: " + this.volume;
        resultado = resultado + " | ISSN: " + this.issn;
        
        return resultado;
    }
    
    /**
     * Método para mostrar informações detalhadas da revista
     * Útil para relatórios e consultas
     */
    @Override
    public void mostrarInformacoesCompletas() {
        System.out.println("=== INFORMAÇÕES DA REVISTA ===");
        
        // Chama o método da classe pai para mostrar informações básicas
        super.mostrarInformacoesCompletas();
        
        // Adiciona informações específicas da revista
        System.out.println("Editora: " + this.editora);
        System.out.println("Volume: " + this.getVolumeFormatado());
        System.out.println("ISSN: " + this.issn);
        
        if (this.éVolumeEspecial()) {
            System.out.println("✓ Volume especial");
        }
        
        if (this.éRecente()) {
            System.out.println("✓ Revista recente (menos de 2 anos)");
        }
        
        System.out.println("Referência bibliográfica:");
        System.out.println(this.getReferenciaBibliografica());
        
        if (this.getVolumeAnterior() > 0) {
            System.out.println("Volume anterior: " + this.getVolumeAnterior());
        }
        System.out.println("Próximo volume: " + this.getProximoVolume());
    }
    
    /**
     * Método para obter um resumo completo da revista
     * Combina informações básicas e específicas
     * 
     * @return resumo completo
     */
    public String getResumoCompleto() {
        String resumo = "[REVISTA] " + this.getTitulo();
        resumo = resumo + " - " + this.getVolumeFormatado();
        resumo = resumo + " (" + this.getAnoPublicacao() + ")";
        resumo = resumo + " - " + this.editora;
        resumo = resumo + " - ISSN: " + this.issn;
        
        return resumo;
    }
    
    /**
     * Método para verificar se é uma edição temática especial
     * Considera especial se o volume for um múltiplo de 5
     * 
     * @return true se é edição especial, false caso contrário
     */
    public boolean éEdicaoEspecial() {
        return this.volume % 5 == 0;
    }
}