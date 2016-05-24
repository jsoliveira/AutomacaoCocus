package br.com.cocus.automacaococus.model;

import br.com.cocus.automacaococus.model.pk.LigacaoEntradaSaidaPK;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jsoliveira
 */
@Entity
@Table(name = "ligacao_entrada_saida")
public class LigacaoEntradaSaida implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected LigacaoEntradaSaidaPK ligacaoEntradaSaidaPK;

    @JoinColumn(name = "cd_ponto_entrada", referencedColumnName = "cd_ponto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ponto pontoEntrada;

    @JoinColumn(name = "cd_ponto_saida", referencedColumnName = "cd_ponto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ponto pontoSaida;

    @Column(name = "vl_ligado")
    private Integer vlLigado;

    @Column(name = "vl_desligado")
    private Integer vlDesligado;

    @Column(name = "in_ativo")
    private Character inAtivo;

    @Column(name = "dt_transacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTransacao;

    public LigacaoEntradaSaida() {
    }

    public LigacaoEntradaSaida(LigacaoEntradaSaidaPK ligacaoEntradaSaidaPK) {
        this.ligacaoEntradaSaidaPK = ligacaoEntradaSaidaPK;
    }

    public LigacaoEntradaSaida(long cdPontoEntrada, long cdPontoSaida) {
        this.ligacaoEntradaSaidaPK = new LigacaoEntradaSaidaPK(cdPontoEntrada, cdPontoSaida);
    }

    public LigacaoEntradaSaidaPK getLigacaoEntradaSaidaPK() {
        return ligacaoEntradaSaidaPK;
    }

    public void setLigacaoEntradaSaidaPK(LigacaoEntradaSaidaPK ligacaoEntradaSaidaPK) {
        this.ligacaoEntradaSaidaPK = ligacaoEntradaSaidaPK;
    }

    public Character getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Character inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Integer getVlLigado() {
        return vlLigado;
    }

    public void setVlLigado(Integer vlLigado) {
        this.vlLigado = vlLigado;
    }

    public Integer getVlDesligado() {
        return vlDesligado;
    }

    public void setVlDesligado(Integer vlDesligado) {
        this.vlDesligado = vlDesligado;
    }

    public Date getDtTransacao() {
        return dtTransacao;
    }

    public void setDtTransacao(Date dtTransacao) {
        this.dtTransacao = dtTransacao;
    }

    public Ponto getPontoEntrada() {
        return pontoEntrada;
    }

    public void setPontoEntrada(Ponto pontoEntrada) {
        this.pontoEntrada = pontoEntrada;
    }

    public Ponto getPontoSaida() {
        return pontoSaida;
    }

    public void setPontoSaida(Ponto pontoSaida) {
        this.pontoSaida = pontoSaida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ligacaoEntradaSaidaPK != null ? ligacaoEntradaSaidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigacaoEntradaSaida)) {
            return false;
        }
        LigacaoEntradaSaida other = (LigacaoEntradaSaida) object;
        if ((this.ligacaoEntradaSaidaPK == null && other.ligacaoEntradaSaidaPK != null) || (this.ligacaoEntradaSaidaPK != null && !this.ligacaoEntradaSaidaPK.equals(other.ligacaoEntradaSaidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cocus.automacaococus.model.LigacaoEntradaSaida[ ligacaoEntradaSaidaPK=" + ligacaoEntradaSaidaPK + " ]";
    }

}
