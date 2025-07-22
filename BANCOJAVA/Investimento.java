import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Investimento {
    private final String id;
    private final String nome;
    private final double taxaRendimento;
    private final LocalDateTime dataCriacao;
    
    public Investimento(String nome, double taxaRendimento) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.nome = nome;
        this.taxaRendimento = taxaRendimento;
        this.dataCriacao = LocalDateTime.now();
    }
    
    public double calcularRendimento(double valorInvestido) {
        return valorInvestido * (1 + taxaRendimento / 100);
    }
    
    public String getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public double getTaxaRendimento() {
        return taxaRendimento;
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Nome: %s | Taxa: %.2f%% | Criado em: %s",
                id, nome, taxaRendimento,
                dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Investimento that = (Investimento) obj;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
