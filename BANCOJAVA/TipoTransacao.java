public enum TipoTransacao {
    DEPOSITO("Depósito"),
    SAQUE("Saque"),
    PIX_ENVIADO("PIX Enviado"),
    PIX_RECEBIDO("PIX Recebido"),
    INVESTIMENTO("Investimento"),
    SAQUE_INVESTIMENTO("Saque Investimento"),
    RENDIMENTO("Rendimento"),
    TAXA("Taxa");
    
    private final String descricao;
    
    TipoTransacao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}