package br.com.cocus.automacaococus.model.JSON;

import java.util.Objects;

/**
 *
 * @author jsoliveira
 */
public class Dispositivo_1 {

    private String Prot;

    private String Modulo;

    private Integer ID;
    
    private String IP;

    public Dispositivo_1() {
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
        final Dispositivo_1 other = (Dispositivo_1) obj;
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
