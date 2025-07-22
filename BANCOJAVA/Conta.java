import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class Conta {
    private static int contadorContas = 1000;
    
    protected String numero;
    protected String titular;
    protected String cpf;
    protected double saldo;
    protected List<Transacao> historico;
    protected LocalDateTime dataCriacao;
    
    public Conta(String titular, String cpf) {
        this.numero = String.valueOf(++contadorContas);
        this.titular = titular;
        this.cpf = cpf;
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
        this.dataCriacao = LocalDateTime.now();
    }
    
    public abstract String getTipo();
    
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            adicionarTransacao(new Transacao(TipoTransacao.DEPOSITO, valor, "Depósito"));
        }
    }
    
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            this.saldo -= valor;
            adicionarTransacao(new Transacao(TipoTransacao.SAQUE, valor, "Saque"));
            return true;
        }
        return false;
    }
    
    public void adicionarTransacao(Transacao transacao) {
        this.historico.add(transacao);
    }
    
    public String getNumero() {
        return numero;
    }
    
    public String getTitular() {
        return titular;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public List<Transacao> getHistorico() {
        return Collections.unmodifiableList(historico);
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    @Override
    public String toString() {
        return String.format("Conta: %s | Tipo: %s | Titular: %s | CPF: %s | Saldo: R$ %.2f | Criada em: %s",
                numero, getTipo(), titular, cpf, saldo, 
                dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Conta conta = (Conta) obj;
        return Objects.equals(numero, conta.numero);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}