import java.util.*;

public class SistemaBancario {
    private final RepositorioConta repositorioConta;
    private final RepositorioInvestimento repositorioInvestimento;
    private final RepositorioCarteira repositorioCarteira;
    
    public SistemaBancario() {
        this.repositorioConta = new RepositorioConta();
        this.repositorioInvestimento = new RepositorioInvestimento();
        this.repositorioCarteira = new RepositorioCarteira();
    }
    
    public boolean criarConta(String nome, String cpf, int tipo) {
        try {
            Conta conta;
            if (tipo == 1) {
                conta = new ContaCorrente(nome, cpf);
            } else if (tipo == 2) {
                conta = new ContaPoupanca(nome, cpf);
            } else {
                return false;
            }
            
            repositorioConta.salvar(conta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean criarInvestimento(String nome, double taxa) {
        try {
            Investimento investimento = new Investimento(nome, taxa);
            repositorioInvestimento.salvar(investimento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean fazerInvestimento(String numeroConta, String idInvestimento, double valor) {
        try {
            Conta conta = repositorioConta.buscarPorNumero(numeroConta);
            Investimento investimento = repositorioInvestimento.buscarPorId(idInvestimento);
            
            if (conta == null || investimento == null || valor <= 0) {
                return false;
            }
            
            if (!conta.sacar(valor)) {
                return false;
            }
            
            CarteiraInvestimento carteira = repositorioCarteira.buscarPorConta(numeroConta);
            if (carteira == null) {
                carteira = new CarteiraInvestimento(numeroConta);
                repositorioCarteira.salvar(carteira);
            }
            
            carteira.adicionarInvestimento(idInvestimento, valor);
            conta.adicionarTransacao(new Transacao(TipoTransacao.INVESTIMENTO, valor, 
                "Investimento em " + investimento.getNome()));
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean depositar(String numeroConta, double valor) {
        try {
            Conta conta = repositorioConta.buscarPorNumero(numeroConta);
            if (conta == null || valor <= 0) {
                return false;
            }
            
            conta.depositar(valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean sacar(String numeroConta, double valor) {
        try {
            Conta conta = repositorioConta.buscarPorNumero(numeroConta);
            if (conta == null || valor <= 0) {
                return false;
            }
            
            return conta.sacar(valor);
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean transferir(String contaOrigem, String contaDestino, double valor) {
        try {
            Conta origem = repositorioConta.buscarPorNumero(contaOrigem);
            Conta destino = repositorioConta.buscarPorNumero(contaDestino);
            
            if (origem == null || destino == null || valor <= 0) {
                return false;
            }
            
            if (!origem.sacar(valor)) {
                return false;
            }
            
            destino.depositar(valor);
            
            origem.adicionarTransacao(new Transacao(TipoTransacao.PIX_ENVIADO, valor, 
                "PIX para conta " + contaDestino));
            destino.adicionarTransacao(new Transacao(TipoTransacao.PIX_RECEBIDO, valor, 
                "PIX de conta " + contaOrigem));
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean sacarInvestimento(String numeroConta, String idInvestimento, double valor) {
        try {
            Conta conta = repositorioConta.buscarPorNumero(numeroConta);
            Investimento investimento = repositorioInvestimento.buscarPorId(idInvestimento);
            CarteiraInvestimento carteira = repositorioCarteira.buscarPorConta(numeroConta);
            
            if (conta == null || investimento == null || carteira == null) {
                return false;
            }
            
            if (carteira.sacarInvestimento(idInvestimento, valor)) {
                conta.depositar(valor);
                conta.adicionarTransacao(new Transacao(TipoTransacao.SAQUE_INVESTIMENTO, valor, 
                    "Saque do investimento " + investimento.getNome()));
                return true;
            }
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void listarContas() {
        List<Conta> contas = repositorioConta.listarTodos();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada.");
            return;
        }
        
        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }
    
    public void listarInvestimentos() {
        List<Investimento> investimentos = repositorioInvestimento.listarTodos();
        if (investimentos.isEmpty()) {
            System.out.println("Nenhum investimento encontrado.");
            return;
        }
        
        for (Investimento inv : investimentos) {
            System.out.println(inv);
        }
    }
    
    public void listarCarteiras() {
        List<CarteiraInvestimento> carteiras = repositorioCarteira.listarTodos();
        if (carteiras.isEmpty()) {
            System.out.println("Nenhuma carteira de investimento encontrada.");
            return;
        }
        
        for (CarteiraInvestimento carteira : carteiras) {
            System.out.println(carteira);
        }
    }
    
    public void atualizarInvestimentos() {
        List<CarteiraInvestimento> carteiras = repositorioCarteira.listarTodos();
        
        for (CarteiraInvestimento carteira : carteiras) {
            for (String idInvestimento : carteira.getInvestimentos().keySet()) {
                Investimento investimento = repositorioInvestimento.buscarPorId(idInvestimento);
                if (investimento != null) {
                    double valorAtual = carteira.getInvestimentos().get(idInvestimento);
                    double novoValor = valorAtual * (1 + investimento.getTaxaRendimento() / 100);
                    carteira.atualizarValor(idInvestimento, novoValor);
                }
            }
        }
    }
    
    public void exibirHistorico(String numeroConta) {
        Conta conta = repositorioConta.buscarPorNumero(numeroConta);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        
        System.out.println("Histórico da conta: " + numeroConta);
        System.out.println("Titular: " + conta.getTitular());
        System.out.println("Saldo atual: R$ " + String.format("%.2f", conta.getSaldo()));
        System.out.println("\nTransações:");
        
        if (conta.getHistorico().isEmpty()) {
            System.out.println("Nenhuma transação encontrada.");
        } else {
            for (Transacao transacao : conta.getHistorico()) {
                System.out.println(transacao);
            }
        }
    }
}