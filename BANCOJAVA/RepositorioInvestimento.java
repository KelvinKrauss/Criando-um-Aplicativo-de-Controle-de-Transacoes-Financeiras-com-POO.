import java.util.*;

public class RepositorioInvestimento {
    private final Map<String, Investimento> investimentos;
    
    public RepositorioInvestimento() {
        this.investimentos = new HashMap<>();
    }
    
    public void salvar(Investimento investimento) {
        if (investimento != null && investimento.getId() != null) {
            investimentos.put(investimento.getId(), investimento);
        }
    }
    
    public Investimento buscarPorId(String id) {
        return investimentos.get(id);
    }
    
    public List<Investimento> listarTodos() {
        return new ArrayList<>(investimentos.values());
    }
    
    public boolean existe(String id) {
        return investimentos.containsKey(id);
    }
    
    public void remover(String id) {
        investimentos.remove(id);
    }
    
    public int total() {
        return investimentos.size();
    }
    
    public List<Investimento> buscarPorNome(String nome) {
        return investimentos.values().stream()
                .filter(inv -> inv.getNome().toLowerCase().contains(nome.toLowerCase()))
                .toList();
    }
    
    public List<Investimento> buscarPorTaxaMinima(double taxaMinima) {
        return investimentos.values().stream()
                .filter(inv -> inv.getTaxaRendimento() >= taxaMinima)
                .toList();
    }
}