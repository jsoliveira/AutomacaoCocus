package br.com.cocus.automacaococus.tools;

/**
 *
 * @author jsoliveira
 */
public enum  OperadoresJPQL {
    
    
    equals(1),notEquals(2),like(3),notLike(4);
    
    private final Integer Operador;

    private OperadoresJPQL(Integer Operador) {
        this.Operador = Operador;
    }

    public Integer getOperador() {
        return Operador;
    }
    
    
    

    
    
}
