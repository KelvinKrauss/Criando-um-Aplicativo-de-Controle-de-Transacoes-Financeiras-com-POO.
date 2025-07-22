import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Transacao {
    private final TipoTransacao tipo;
    private final double valor;
    private final String descricao;
    private final LocalDateTime dataHora;
    
    public Transacao(TipoTransacao tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }
    
    public TipoTransacao getTipo() {
        return tipo;
    }
    
    public double getValor() {
        return valor;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - R$ %.2f - %s",
                dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                tipo.getDescricao(),
                valor,
                descricao);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transacao transacao = (Transacao) obj;
        return Double.compare(transacao.valor, valor) == 0 &&
                tipo == transacao.tipo &&
                Objects.equals(descricao, transacao.descricao) &&
                Objects.equals(dataHora, transacao.dataHora);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(tipo, valor, descricao, dataHora);
    }
}