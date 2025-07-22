public class ContaPoupanca extends Conta {
    private static final double TAXA_RENDIMENTO = 0.5; 
    
    public ContaPoupanca(String titular, String cpf) {
        super(titular, cpf);
    }
    
    @Override
    public String getTipo() {
        return "Conta Poupança";
    }
    
    public void aplicarRendimento() {
        if (saldo > 0) {
            double rendimento = saldo * (TAXA_RENDIMENTO / 100);
            saldo += rendimento;
            adicionarTransacao(new Transacao(TipoTransacao.RENDIMENTO, rendimento, 
                "Rendimento da poupança"));
        }
    }
    
    @Override
    public boolean sacar(double valor) {
        // pra nao permitir saldo negativo na poupança
        if (valor > 0 && valor <= saldo) {
            this.saldo -= valor;
            adicionarTransacao(new Transacao(TipoTransacao.SAQUE, valor, "Saque"));
            return true;
        }
        return false;
    }
}