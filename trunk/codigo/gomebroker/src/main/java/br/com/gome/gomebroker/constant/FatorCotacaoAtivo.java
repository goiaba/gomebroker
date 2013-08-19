package br.com.gome.gomebroker.constant;

public enum FatorCotacaoAtivo {

    UNITARIA(1),
    LOTE_MIL(1000);
    
    private Integer codigo;

    private FatorCotacaoAtivo(Integer codigo) {

        this.codigo = codigo;

    }

    public Integer getCodigo() {

        return this.codigo;

    }
    
    public static FatorCotacaoAtivo getFromCodigo(Integer codigo) {

        for (FatorCotacaoAtivo fCA : FatorCotacaoAtivo.values()) {
            
            if (fCA.getCodigo().equals(codigo)) {
                
                return fCA;
                
            }
            
        }
        
        throw new EnumConstantNotPresentException(FatorCotacaoAtivo.class, codigo.toString());

    }
    
}