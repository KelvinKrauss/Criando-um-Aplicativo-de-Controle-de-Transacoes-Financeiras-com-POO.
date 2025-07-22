import java.util.*;

public class RepositorioConta {
    private final Map<String, Conta> contas;
    
    public RepositorioConta() {
        this.contas = new HashMap<>();
    }
    
    public void salvar(Conta conta) {
        if (conta != null && conta.getNumero() != null) {
            contas.put(conta.getNumero(), conta);
        }
    }
    
    public Conta buscarPorNumero(String numero) {
        return contas.get(numero);
    }
    
    public Conta buscarPorCpf(String cpf) {
        return contas.values().stream()
                .filter(conta -> conta.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
    
    public List<Conta> listarTodos() {
        return new ArrayList<>(contas.values());
    }
    
    public boolean existe(String numero) {
        return contas.containsKey(numero);
    }
    
    public void remover(String numero) {
        contas.remove(numero);
    }
    
    public int total() {
        return contas.size();
    }
    
    public List<Conta> buscarPorTitular(String titular) {
        return contas.values().stream()
                .filter(conta -> conta.getTitular().toLowerCase().contains(titular.toLowerCase()))
                .toList();
    }
}