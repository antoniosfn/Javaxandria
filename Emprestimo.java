import java.util.Date;

/**
 * Classe Emprestimo - representa um empréstimo de item da biblioteca
 * Esta classe faz a ligação entre um usuário e um item do acervo
 * Armazena todas as informações do empréstimo: datas, multas, etc.
 * 
 * Conceitos demonstrados:
 * - Associação: liga Usuario com ItemDeAcervo
 * - Encapsulamento: controla acesso aos dados
 * - Métodos de negócio: calcular multas, verificar atrasos
 * 
 * @author Antonio Neto
 */
public class Emprestimo {
    
    // Atributos da classe Emprestimo
    private String idEmprestimo;  // ID único do empréstimo
    private Usuario usuario;  // usuário que fez o empréstimo
    private ItemDeAcervo item;  // item emprestado
    private Date dataEmprestimo;  // data quando foi feito o empréstimo
    private Date dataDevolucaoPrevista;  // data limite para devolução
    private Date dataDevolucaoReal;  // data real da devolução (null se ainda não devolvido)
    private double multaCobrada;  // valor da multa (0 se não houve atraso)
    private static final double VALOR_MULTA_POR_DIA = 1.00;  // valor da multa por dia de atraso
    
    /**
     * Construtor da classe Emprestimo
     * Cria um novo empréstimo com os dados fornecidos
     * 
     * @param idEmprestimo - ID único do empréstimo
     * @param usuario - usuário que está emprestando
     * @param item - item que está sendo emprestado
     * @param dataEmprestimo - data do empréstimo
     * @param dataDevolucaoPrevista - data limite para devolução
     */
    public Emprestimo(String idEmprestimo, Usuario usuario, ItemDeAcervo item, 
                     Date dataEmprestimo, Date dataDevolucaoPrevista) {
        
        // Inicializa todos os atributos
        this.idEmprestimo = idEmprestimo;
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = null;  // ainda não foi devolvido
        this.multaCobrada = 0.0;  // sem multa inicialmente
    }
    
    /**
     * Método getter para obter o ID do empréstimo
     * @return ID do empréstimo
     */
    public String getIdEmprestimo() {
        return idEmprestimo;
    }
    
    /**
     * Método setter para alterar o ID do empréstimo
     * @param idEmprestimo novo ID
     */
    public void setIdEmprestimo(String idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }
    
    /**
     * Método getter para obter o usuário
     * @return usuário que fez o empréstimo
     */
    public Usuario getUsuario() {
        return usuario;
    }
    
    /**
     * Método getter para obter o item
     * @return item emprestado
     */
    public ItemDeAcervo getItem() {
        return item;
    }
    
    /**
     * Método getter para obter a data do empréstimo
     * @return data do empréstimo
     */
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    /**
     * Método getter para obter a data de devolução prevista
     * @return data limite para devolução
     */
    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    
    /**
     * Método getter para obter a data real da devolução
     * @return data real da devolução (null se ainda não devolvido)
     */
    public Date getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }
    
    /**
     * Método setter para alterar a data real da devolução
     * @param dataDevolucaoReal nova data de devolução
     */
    public void setDataDevolucaoReal(Date dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
    
    /**
     * Método getter para obter a multa cobrada
     * @return valor da multa
     */
    public double getMultaCobrada() {
        return multaCobrada;
    }
    
    /**
     * Método para calcular a multa baseada na data de devolução
     * Aplica a regra de negócio: R$ 1,00 por dia de atraso
     * 
     * @param dataDevolucaoReal data real da devolução
     * @return valor da multa a ser cobrada
     */
    public double calcularMulta(Date dataDevolucaoReal) {
        // Se foi devolvido na data certa ou antes, não tem multa
        if (dataDevolucaoReal.equals(this.dataDevolucaoPrevista) || 
            dataDevolucaoReal.before(this.dataDevolucaoPrevista)) {
            return 0.0;
        }
        
        // Se foi devolvido após o prazo, calcula os dias de atraso
        if (dataDevolucaoReal.after(this.dataDevolucaoPrevista)) {
            // Calcula a diferença em milliseconds
            long diferencaEmMillis = dataDevolucaoReal.getTime() - this.dataDevolucaoPrevista.getTime();
            
            // Converte milliseconds para dias
            // 1 dia = 24 horas * 60 minutos * 60 segundos * 1000 milliseconds
            long diasDeAtraso = diferencaEmMillis / (24 * 60 * 60 * 1000);
            
            // Calcula a multa: dias de atraso * valor por dia
            double multa = diasDeAtraso * VALOR_MULTA_POR_DIA;
            
            return multa;
        }
        
        return 0.0;
    }
    
    /**
     * Método para finalizar o empréstimo quando o item é devolvido
     * Registra a data de devolução e calcula a multa
     * 
     * @param dataDevolucaoReal data real da devolução
     */
    public void finalizarEmprestimo(Date dataDevolucaoReal) {
        // Registra a data real da devolução
        this.dataDevolucaoReal = dataDevolucaoReal;
        
        // Calcula e registra a multa
        this.multaCobrada = calcularMulta(dataDevolucaoReal);
    }
    
    /**
     * Método para verificar se o empréstimo está em atraso
     * Considera em atraso se ainda não foi devolvido e a data limite já passou
     * 
     * @return true se está em atraso, false caso contrário
     */
    public boolean isEmAtraso() {
        // Se já foi devolvido, não está em atraso
        if (this.dataDevolucaoReal != null) {
            return false;
        }
        
        // Se ainda não foi devolvido, verifica se o prazo já passou
        Date dataAtual = new Date();
        if (dataAtual.after(this.dataDevolucaoPrevista)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Método para calcular quantos dias o empréstimo está em atraso
     * 
     * @return número de dias de atraso (0 se não estiver em atraso)
     */
    public int calcularDiasDeAtraso() {
        // Se não está em atraso, retorna 0
        if (!this.isEmAtraso()) {
            return 0;
        }
        
        // Calcula a diferença em dias
        Date dataAtual = new Date();
        long diferencaEmMillis = dataAtual.getTime() - this.dataDevolucaoPrevista.getTime();
        long diasDeAtraso = diferencaEmMillis / (24 * 60 * 60 * 1000);
        
        return (int) diasDeAtraso;
    }
    
    /**
     * Método para verificar se o empréstimo foi devolvido
     * 
     * @return true se foi devolvido, false caso contrário
     */
    public boolean foiDevolvido() {
        return this.dataDevolucaoReal != null;
    }
    
    /**
     * Método para obter o status do empréstimo
     * 
     * @return string com o status
     */
    public String getStatus() {
        if (this.foiDevolvido()) {
            if (this.multaCobrada > 0) {
                return "DEVOLVIDO COM MULTA";
            } else {
                return "DEVOLVIDO";
            }
        } else if (this.isEmAtraso()) {
            return "EMPRESTADO - EM ATRASO";
        } else {
            return "EMPRESTADO";
        }
    }
    
    /**
     * Método para converter os dados do empréstimo em string
     * Útil para exibir informações do empréstimo
     * 
     * @return string com informações do empréstimo
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("ID Empréstimo: " + this.idEmprestimo + " | ");
        sb.append("Usuário: " + this.usuario.getNome() + " | ");
        sb.append("Item: " + this.item.getTitulo() + " | ");
        sb.append("Data Empréstimo: " + this.dataEmprestimo + " | ");
        sb.append("Data Devolução Prevista: " + this.dataDevolucaoPrevista + " | ");
        
        if (this.foiDevolvido()) {
            sb.append("Data Devolução Real: " + this.dataDevolucaoReal + " | ");
            sb.append("Status: DEVOLVIDO");
            if (this.multaCobrada > 0) {
                sb.append(" | Multa: R$ " + this.multaCobrada);
            }
        } else {
            sb.append("Status: EMPRESTADO");
            if (this.isEmAtraso()) {
                int diasAtraso = this.calcularDiasDeAtraso();
                sb.append(" | ATRASO: " + diasAtraso + " dias");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Método para mostrar detalhes completos do empréstimo
     * Útil para relatórios detalhados
     */
    public void mostrarDetalhesCompletos() {
        System.out.println("=== DETALHES DO EMPRÉSTIMO ===");
        System.out.println("ID: " + this.idEmprestimo);
        System.out.println("Usuário: " + this.usuario.getNome() + " (" + this.usuario.getId() + ")");
        System.out.println("Item: " + this.item.getTitulo() + " (" + this.item.getCodigo() + ")");
        System.out.println("Data do empréstimo: " + this.dataEmprestimo);
        System.out.println("Data de devolução prevista: " + this.dataDevolucaoPrevista);
        
        if (this.foiDevolvido()) {
            System.out.println("Data de devolução real: " + this.dataDevolucaoReal);
            System.out.println("Multa cobrada: R$ " + this.multaCobrada);
            if (this.multaCobrada > 0) {
                System.out.println("⚠️ MULTA PENDENTE!");
            }
        } else {
            System.out.println("Status: EMPRESTADO");
            if (this.isEmAtraso()) {
                int diasAtraso = this.calcularDiasDeAtraso();
                System.out.println("⚠️ EM ATRASO: " + diasAtraso + " dias");
            }
        }
        
        System.out.println("Status final: " + this.getStatus());
    }
}