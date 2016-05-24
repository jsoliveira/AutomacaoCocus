package br.com.cocus.automacaococus.model.pk;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jsoliveira
 */
@Embeddable
public class LigacaoEntradaSaidaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_ponto_entrada")
    private long cdPontoEntrada;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_ponto_saida")
    private long cdPontoSaida;

    public LigacaoEntradaSaidaPK() {
    }

    public LigacaoEntradaSaidaPK(long cdPontoEntrada, long cdPontoSaida) {
        this.cdPontoEntrada = cdPontoEntrada;
        this.cdPontoSaida = cdPontoSaida;
    }

    public long getCdPontoEntrada() {
        return cdPontoEntrada;
    }

    public void setCdPontoEntrada(long cdPontoEntrada) {
        this.cdPontoEntrada = cdPontoEntrada;
    }

    public long getCdPontoSaida() {
        return cdPontoSaida;
    }

    public void setCdPontoSaida(long cdPontoSaida) {
        this.cdPontoSaida = cdPontoSaida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cdPontoEntrada;
        hash += (int) cdPontoSaida;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigacaoEntradaSaidaPK)) {
            return false;
        }
        LigacaoEntradaSaidaPK other = (LigacaoEntradaSaidaPK) object;
        if (this.cdPontoEntrada != other.cdPontoEntrada) {
            return false;
        }
        if (this.cdPontoSaida != other.cdPontoSaida) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cocus.automacaococus.model.LigacaoEntradaSaidaPK[ cdPontoEntrada=" + cdPontoEntrada + ", cdPontoSaida=" + cdPontoSaida + " ]";
    }
    
}
