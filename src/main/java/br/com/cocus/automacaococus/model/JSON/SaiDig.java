package br.com.cocus.automacaococus.model.JSON;

/**
 *
 * @author jsoliveira
 */
public class SaiDig {
    
    private Integer SD0;
    private Integer SD1;
    private Integer SD2;
    private Integer SD3;
    private Integer SD4;
    private Integer SD5;

    public SaiDig() {
    }

    public SaiDig(Integer SD0, Integer SD1, Integer SD2, Integer SD3, Integer SD4, Integer SD5) {
        this.SD0 = SD0;
        this.SD1 = SD1;
        this.SD2 = SD2;
        this.SD3 = SD3;
        this.SD4 = SD4;
        this.SD5 = SD5;
    }

    public Integer getSD0() {
        return SD0;
    }

    public void setSD0(Integer SD0) {
        this.SD0 = SD0;
    }

    public Integer getSD1() {
        return SD1;
    }

    public void setSD1(Integer SD1) {
        this.SD1 = SD1;
    }

    public Integer getSD2() {
        return SD2;
    }

    public void setSD2(Integer SD2) {
        this.SD2 = SD2;
    }

    public Integer getSD3() {
        return SD3;
    }

    public void setSD3(Integer SD3) {
        this.SD3 = SD3;
    }

    public Integer getSD4() {
        return SD4;
    }

    public void setSD4(Integer SD4) {
        this.SD4 = SD4;
    }

    public Integer getSD5() {
        return SD5;
    }

    public void setSD5(Integer SD5) {
        this.SD5 = SD5;
    }

    @Override
    public String toString() {
        return "SaiDig{" + "SD0=" + SD0 + ", SD1=" + SD1 + ", SD2=" + SD2 + ", SD3=" + SD3 + ", SD4=" + SD4 + ", SD5=" + SD5 + '}';
    }
    
    
}
