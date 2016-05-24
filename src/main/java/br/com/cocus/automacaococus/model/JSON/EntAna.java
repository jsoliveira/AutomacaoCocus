package br.com.cocus.automacaococus.model.JSON;

/**
 *
 * @author jsoliveira
 */
public class EntAna {

    private Integer EA0;
    private Integer EA1;

    public EntAna() {
    }

    public EntAna(Integer EA0, Integer EA1) {
        this.EA0 = EA0;
        this.EA1 = EA1;
    }

    public Integer getEA0() {
        return EA0;
    }

    public void setEA0(Integer EA0) {
        this.EA0 = EA0;
    }

    public Integer getEA1() {
        return EA1;
    }

    public void setEA1(Integer EA1) {
        this.EA1 = EA1;
    }

    @Override
    public String toString() {
        return "EntAna{" + "EA0=" + EA0 + ", EA1=" + EA1 + '}';
    }

   

 
    
    
    
}
