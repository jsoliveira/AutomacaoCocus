package br.com.cocus.automacaococus.model.JSON;

import java.util.Objects;

/**
 *
 * @author jsoliveira
 */
public class Dispositivo {

    private String Prot;

    private String Modulo;

    private Integer ID;

    private String IP;

    private Boolean inCadastrado;

    public Dispositivo() {
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

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Boolean getInCadastrado() {
        return inCadastrado;
    }

    public void setInCadastrado(Boolean inCadastrado) {
        this.inCadastrado = inCadastrado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.ID);
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
        final Dispositivo other = (Dispositivo) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dispositivo{" + "Prot=" + Prot + ", Modulo=" + Modulo + ", ID=" + ID + ", IP=" + IP + '}';
    }

}
