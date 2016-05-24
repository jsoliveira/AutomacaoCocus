package br.com.cocus.automacaococus.model.JSON;

import java.util.Objects;

/**
 *
 * @author jsoliveira
 */
public class Status {

    private String Tipo;

    private String Prot;

    private String Modulo;

    private Integer ID;

    private Integer SWifi;

    private EntDig EntDig;

    private EntAna EntAna;

    private SaiDig SaiDig;

    private SaiTr SaiTr;

    private Integer SW;

    public Status() {
    }

    public Status(String Tipo, String Prot, String Modulo, Integer ID, Integer SWifi, EntDig EntDig, EntAna EntAna, SaiDig SaiDig, SaiTr SaiTr, Integer SW) {
        this.Tipo = Tipo;
        this.Prot = Prot;
        this.Modulo = Modulo;
        this.ID = ID;
        this.SWifi = SWifi;
        this.EntDig = EntDig;
        this.EntAna = EntAna;
        this.SaiDig = SaiDig;
        this.SaiTr = SaiTr;
        this.SW = SW;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getProt() {
        return Prot;
    }

    public void setProt(String Prot) {
        this.Prot = Prot;
    }

    public String getModulo() {
        return Modulo;
    }

    public void setModulo(String Modulo) {
        this.Modulo = Modulo;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSWifi() {
        return SWifi;
    }

    public void setSWifi(Integer SWifi) {
        this.SWifi = SWifi;
    }

    public EntDig getEntDig() {
        return EntDig;
    }

    public void setEntDig(EntDig EntDig) {
        this.EntDig = EntDig;
    }

    public EntAna getEntAna() {
        return EntAna;
    }

    public void setEntAna(EntAna EntAna) {
        this.EntAna = EntAna;
    }

    public SaiDig getSaiDig() {
        return SaiDig;
    }

    public void setSaiDig(SaiDig SaiDig) {
        this.SaiDig = SaiDig;
    }

    public SaiTr getSaiTr() {
        return SaiTr;
    }

    public void setSaiTr(SaiTr SaiTr) {
        this.SaiTr = SaiTr;
    }

    public Integer getSW() {
        return SW;
    }

    public void setSW(Integer SW) {
        this.SW = SW;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Status other = (Status) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }



    
    

}
