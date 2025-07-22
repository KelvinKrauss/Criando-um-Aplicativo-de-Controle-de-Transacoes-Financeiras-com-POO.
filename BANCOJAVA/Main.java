import java.util.Scanner;

public class Main {
    private static final SistemaBancario sistema = new SistemaBancario();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA BANCARIO ===");
        
        while (true) {
            exibirMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> criarInvestimento();
                case 3 -> fazerInvestimento();
                case 4 -> depositar();
                case 5 -> sacar();
                case 6 -> transferir();
                case 7 -> sacarInvestimento();
                case 8 -> listarContas();
                case 9 -> listarInvestimentos();
                case 10 -> listarCarteiras();
                case 11 -> atualizarInvestimentos();
                case 12 -> exibirHistorico();
                case 13 -> {
                    System.out.println("Obrigado por usar o Sistema Bancario!");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
            
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        }
    }
    
    private static void exibirMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("MENU PRINCIPAL DO BANCO BROTHERSCO");
        System.out.println("=".repeat(40));
        System.out.println("1  - Criar conta");
        System.out.println("2  - Criar investimento");
        System.out.println("3  - Fazer investimento");
        System.out.println("4  - Depositar na conta");
        System.out.println("5  - Sacar da conta");
        System.out.println("6  - Transferência entre contas");
        System.out.println("7  - Sacar investimentos");
        System.out.println("8  - Listar contas criadas");
        System.out.println("9  - Listar investimentos");
        System.out.println("10 - Listar carteiras de investimento");
        System.out.println("11 - Atualizar investimentos");
        System.out.println("12 - Histórico da conta");
        System.out.println("13 - Sair");
        System.out.println("=".repeat(40));
        System.out.print("Escolha uma opção: ");
    }
    
    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void criarConta() {
        System.out.println("\n--- CRIAR CONTA ---");
        System.out.print("Nome do titular: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.println("Tipo de conta:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        System.out.print("Escolha: ");
        
        int tipo = lerOpcao();
        boolean sucesso = sistema.criarConta(nome, cpf, tipo);
        
        if (sucesso) {
            System.out.println("Conta criada com sucesso!");
        } else {
            System.out.println("Erro ao criar conta. Verifique os dados.");
        }
    }
    
    private static void criarInvestimento() {
        System.out.println("\n--- CRIAR INVESTIMENTO ---");
        System.out.print("Nome do investimento: ");
        String nome = scanner.nextLine();
        
        System.out.print("Taxa de rendimento (%): ");
        double taxa = Double.parseDouble(scanner.nextLine());
        
        boolean sucesso = sistema.criarInvestimento(nome, taxa);
        
        if (sucesso) {
            System.out.println("Investimento criado com sucesso!");
        } else {
            System.out.println("Erro ao criar investimento.");
        }
    }
    
    private static void fazerInvestimento() {
        System.out.println("\n--- FAZER INVESTIMENTO ---");
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        System.out.print("ID do investimento: ");
        String idInvestimento = scanner.nextLine();
        
        System.out.print("Valor a investir: R$ ");
        double valor = Double.parseDouble(scanner.nextLine());
        
        boolean sucesso = sistema.fazerInvestimento(numeroConta, idInvestimento, valor);
        
        if (sucesso) {
            System.out.println("Investimento realizado com sucesso!");
        } else {
            System.out.println("Erro ao realizar investimento. Verifique os dados.");
        }
    }
    
    private static void depositar() {
        System.out.println("\n--- DEPOSITAR ---");
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        System.out.print("Valor do depósito: R$ ");
        double valor = Double.parseDouble(scanner.nextLine());
        
        boolean sucesso = sistema.depositar(numeroConta, valor);
        
        if (sucesso) {
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Erro ao realizar depósito.");
        }
    }
    
    private static void sacar() {
        System.out.println("\n--- SACAR ---");
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        System.out.print("Valor do saque: R$ ");
        double valor = Double.parseDouble(scanner.nextLine());
        
        boolean sucesso = sistema.sacar(numeroConta, valor);
        
        if (sucesso) {
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Erro ao realizar saque. Verifique saldo e dados.");
        }
    }
    
    private static void transferir() {
        System.out.println("\n--- TRANSFERIR (PIX) ---");
        System.out.print("Conta origem: ");
        String contaOrigem = scanner.nextLine();
        
        System.out.print("Conta destino: ");
        String contaDestino = scanner.nextLine();
        
        System.out.print("Valor da transferência: R$ ");
        double valor = Double.parseDouble(scanner.nextLine());
        
        boolean sucesso = sistema.transferir(contaOrigem, contaDestino, valor);
        
        if (sucesso) {
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Erro na transferência. Verifique os dados.");
        }
    }
    
    private static void sacarInvestimento() {
        System.out.println("\n--- SACAR INVESTIMENTO ---");
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        System.out.print("ID do investimento: ");
        String idInvestimento = scanner.nextLine();
        
        System.out.print("Valor a sacar: R$ ");
        double valor = Double.parseDouble(scanner.nextLine());
        
        boolean sucesso = sistema.sacarInvestimento(numeroConta, idInvestimento, valor);
        
        if (sucesso) {
            System.out.println("Saque de investimento realizado com sucesso!");
        } else {
            System.out.println("Erro ao sacar investimento.");
        }
    }
    
    private static void listarContas() {
        System.out.println("\n--- CONTAS CRIADAS ---");
        sistema.listarContas();
    }
    
    private static void listarInvestimentos() {
        System.out.println("\n--- INVESTIMENTOS DISPONÍVEIS ---");
        sistema.listarInvestimentos();
    }
    
    private static void listarCarteiras() {
        System.out.println("\n--- CARTEIRAS DE INVESTIMENTO ---");
        sistema.listarCarteiras();
    }
    
    private static void atualizarInvestimentos() {
        System.out.println("\n--- ATUALIZAR INVESTIMENTOS ---");
        sistema.atualizarInvestimentos();
        System.out.println("Investimentos atualizados com sucesso!");
    }
    
    private static void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DA CONTA ---");
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        
        sistema.exibirHistorico(numeroConta);
    }
}