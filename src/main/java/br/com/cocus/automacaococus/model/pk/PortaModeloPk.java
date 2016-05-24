package br.com.cocus.automacaococus.model.pk;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jsoliveira
 */
@Embeddable
public class PortaModeloPk implements Serializable {

    @Column(name = "cd_modelo")
    private Long cdModelo;
    
    @Column(name = "nr_sequencia")
    private Long sequencia;

    public PortaModeloPk() {
    }

    public PortaModeloPk(Long cdModelo, Long sequencia) {
        this.cdModelo = cdModelo;
        this.sequencia = sequencia;
    }

    public Long getCdModelo() {
        return cdModelo;
    }

    public void setCdModelo(Long cdModelo) {
        this.cdModelo = cdModelo;
    }

    public Long getSequencia() {
        return sequencia;
    }

    public void setSequencia(Long sequencia) {
        this.sequencia = sequencia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.cdModelo);
        hash = 67 * hash + Objects.hashCode(this.sequencia);
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
        final PortaModeloPk other = (PortaModeloPk) obj;
        if (!Objects.equals(this.cdModelo, other.cdModelo)) {
            return false;
        }
        if (!Objects.equals(this.sequencia, other.sequencia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PortaModeloPk{" + "cdModelo=" + cdModelo + ", sequencia=" + sequencia + '}';
    }
    
    
    

}
