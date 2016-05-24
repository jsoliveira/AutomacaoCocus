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
public class PontoPk implements Serializable {

    @Column(name = "cd_modulo")
    private Long cdModulo;

    @Column(name = "nr_seq_ponto")
    private Long sequenciaPonto;
    
    @Column(name = "cd_modelo")
    private Long cdModelo;
    
    @Column(name = "nr_seq_modelo")
    private Long sequencia;
    
    

    public PontoPk() {
    }

    public PontoPk(Long cdModulo, Long sequenciaPonto, Long cdModelo, Long sequencia) {
        this.cdModulo = cdModulo;
        this.sequenciaPonto = sequenciaPonto;
        this.cdModelo = cdModelo;
        this.sequencia = sequencia;
    }

    public Long getCdModulo() {
        return cdModulo;
    }

    public void setCdModulo(Long cdModulo) {
        this.cdModulo = cdModulo;
    }

    public Long getSequenciaPonto() {
        return sequenciaPonto;
    }

    public void setSequenciaPonto(Long sequenciaPonto) {
        this.sequenciaPonto = sequenciaPonto;
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
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.cdModulo);
        hash = 19 * hash + Objects.hashCode(this.sequenciaPonto);
        hash = 19 * hash + Objects.hashCode(this.cdModelo);
        hash = 19 * hash + Objects.hashCode(this.sequencia);
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
        final PontoPk other = (PontoPk) obj;
        if (!Objects.equals(this.cdModulo, other.cdModulo)) {
            return false;
        }
        if (!Objects.equals(this.sequenciaPonto, other.sequenciaPonto)) {
            return false;
        }
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
        return "PontoPk{" + "cdModulo=" + cdModulo + ", sequenciaPonto=" + sequenciaPonto + ", cdModelo=" + cdModelo + ", sequencia=" + sequencia + '}';
    }


}
