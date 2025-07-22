import java.util.*;

public class RepositorioCarteira {
    private final Map<String, CarteiraInvestimento> carteiras;
    
    public RepositorioCarteira() {
        this.carteiras = new HashMap<>();
    }
    
    public void salvar(CarteiraInvestimento carteira) {
        if (carteira != null && carteira.getNumeroConta() != null) {
            carteiras.put(carteira.getNumeroConta(), carteira);
        }
    }
    
    public CarteiraInvestimento buscarPorConta(String numeroConta) {
        return carteiras.get(numeroConta);
    }
    
    public List<CarteiraInvestimento> listarTodos() {
        return new ArrayList<>(carteiras.values());
    }
    
    public boolean existe(String numeroConta) {
        return carteiras.containsKey(numeroConta);
    }
    
    public void remover(String numeroConta) {
        carteiras.remove(numeroConta);
    }
    
    public int total() {
        return carteiras.size();
    }
    
    public List<CarteiraInvestimento> buscarComInvestimentos() {
        return carteiras.values().stream()
                .filter(CarteiraInvestimento::temInvestimentos)
                .toList();
    }
    
    public double calcularPatrimonioTotal() {
        return carteiras.values().stream()
                .mapToDouble(CarteiraInvestimento::getValorTotal)
                .sum();
    }
}