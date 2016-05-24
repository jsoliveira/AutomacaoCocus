package br.com.cocus.automacaococus.model.JSON;

import java.util.Objects;

/**
 *
 * @author jsoliveira
 */
public class Configuracao {

    private String Tipo;
    private String Prot;
    private String Modulo;
    private Integer ID;
    private SaiDig SaiDig;
    private SaiTr SaiTr;

    public Configuracao() {
    }

    public Configuracao(String Tipo, String Prot, String Modulo, Integer ID, SaiDig SaiDig, SaiTr SaiTr) {
        this.Tipo = Tipo;
        this.Prot = Prot;
        this.Modulo = Modulo;
        this.ID = ID;
        this.SaiDig = SaiDig;
        this.SaiTr = SaiTr;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.ID);
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
        final Configuracao other = (Configuracao) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Configuracao{" + "Tipo=" + Tipo + ", Prot=" + Prot + ", Modulo=" + Modulo + ", ID=" + ID + ", SaiDig=" + SaiDig + ", SaiTr=" + SaiTr + '}';
    }
    
    
}
