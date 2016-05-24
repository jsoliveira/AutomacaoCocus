package br.com.cocus.automacaococus.model.JSON;

/**
 *
 * @author jsoliveira
 */
public class SaiTr {

    private Integer ST0;
    private Integer ST1;

    public SaiTr() {
    }

    public SaiTr(Integer ST0, Integer ST1) {
        this.ST0 = ST0;
        this.ST1 = ST1;
    }

    public Integer getST0() {
        return ST0;
    }

    public void setST0(Integer ST0) {
        this.ST0 = ST0;
    }

    public Integer getST1() {
        return ST1;
    }

    public void setST1(Integer ST1) {
        this.ST1 = ST1;
    }

    @Override
    public String toString() {
        return "SaiTr{" + "ST0=" + ST0 + ", ST1=" + ST1 + '}';
    }
    
    
    
    
}
