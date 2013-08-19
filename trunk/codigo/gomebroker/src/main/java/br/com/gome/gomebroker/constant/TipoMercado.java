package br.com.gome.gomebroker.constant;

public enum TipoMercado {

    VISTA(10),
    EXERCICIO_OPCOES_COMPRA(12),
    EXERCICIO_OPCOES_VENDA(13),
    LEILAO(17),
    FRACIONARIO(20),
    TERMO(30),
    FUTURO_COM_RETENCAO_DE_GANHO(50),
    FUTURO_COM_MOVIMENTACAO_CONTINUA(60),
    OPCOES_COMPRA(70),
    OPCOES_VENDA(80);
    
    private Integer codigo;

    private TipoMercado(Integer codigo) {

        this.codigo = codigo;

    }

    public Integer getCodigo() {

        return this.codigo;

    }
    
    public static TipoMercado getFromCodigo(Integer codigo) {

        for (TipoMercado tM : TipoMercado.values()) {
            
            if (tM.getCodigo().equals(codigo)) {
                
                return tM;
                
            }
            
        }
        
        throw new EnumConstantNotPresentException(TipoMercado.class, codigo.toString());
        
    }
    
}
