public class ContaCorrente extends Conta {
    private static final double TAXA_MANUTENCAO = 10.0;
    
    public ContaCorrente(String titular, String cpf) {
        super(titular, cpf);
    }
    
    @Override
    public String getTipo() {
        return "Conta Corrente";
    }
    
    public void cobrarTaxaManutencao() {
        if (saldo >= TAXA_MANUTENCAO) {
            saldo -= TAXA_MANUTENCAO;
            adicionarTransacao(new Transacao(TipoTransacao.TAXA, TAXA_MANUTENCAO, 
                "Taxa de manutenção mensal"));
        }
    }
    
    @Override
    public boolean sacar(double valor) {
        // conta corrente permite saque mesmo com saldo insuficiente pra emular cheque especial
        double limite = 0; // limite do cheque especial
        // cancelei o esquema de cheque especial porque nao tem sistema pra ver quanto ta devendo ainda
        
        if (valor > 0 && (saldo + limite) >= valor) {
            this.saldo -= valor;
            adicionarTransacao(new Transacao(TipoTransacao.SAQUE, valor, "Saque"));
            return true;
        }
        return false;
    }
}