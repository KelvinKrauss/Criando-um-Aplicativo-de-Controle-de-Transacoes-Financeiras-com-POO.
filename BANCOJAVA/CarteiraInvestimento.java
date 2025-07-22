import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CarteiraInvestimento {
    private final String numeroConta;
    private final Map<String, Double> investimentos;
    private final LocalDateTime dataCriacao;
    
    public CarteiraInvestimento(String numeroConta) {
        this.numeroConta = numeroConta;
        this.investimentos = new HashMap<>();
        this.dataCriacao = LocalDateTime.now();
    }
    
    public void adicionarInvestimento(String idInvestimento, double valor) {
        investimentos.merge(idInvestimento, valor, Double::sum);
    }
    
    public boolean sacarInvestimento(String idInvestimento, double valor) {
        Double valorAtual = investimentos.get(idInvestimento);
        
        if (valorAtual == null || valor <= 0 || valor > valorAtual) {
            return false;
        }
        
        if (valor == valorAtual) {
            investimentos.remove(idInvestimento);
        } else {
            investimentos.put(idInvestimento, valorAtual - valor);
        }
        
        return true;
    }
    
    public void atualizarValor(String idInvestimento, double novoValor) {
        if (investimentos.containsKey(idInvestimento)) {
            investimentos.put(idInvestimento, novoValor);
        }
    }
    
    public double getValorTotal() {
        return investimentos.values().stream().mapToDouble(Double::doubleValue).sum();
    }
    
    public boolean temInvestimentos() {
        return !investimentos.isEmpty();
    }
    
    public String getNumeroConta() {
        return numeroConta;
    }
    
    public Map<String, Double> getInvestimentos() {
        return Collections.unmodifiableMap(investimentos);
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    @Override
    public String toString() {
        if (investimentos.isEmpty()) {
            return String.format("Carteira da conta %s: Sem investimentos", numeroConta);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Carteira da conta %s:\n", numeroConta));
        sb.append(String.format("Criada em: %s\n", 
                dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        
        for (Map.Entry<String, Double> entry : investimentos.entrySet()) {
            sb.append(String.format("  - Investimento %s: R$ %.2f\n", 
                    entry.getKey(), entry.getValue()));
        }
        
        sb.append(String.format("Valor total investido: R$ %.2f", getValorTotal()));
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CarteiraInvestimento that = (CarteiraInvestimento) obj;
        return Objects.equals(numeroConta, that.numeroConta);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(numeroConta);
    }
}