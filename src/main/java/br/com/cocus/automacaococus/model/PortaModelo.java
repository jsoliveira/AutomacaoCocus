package br.com.cocus.automacaococus.model;

import br.com.cocus.automacaococus.model.pk.PortaModeloPk;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jsoliveira
 */
@Entity
@Table(name = "cad_porta_modelo")
public class PortaModelo implements Serializable {

    @EmbeddedId
    private PortaModeloPk portaModeloPk;

    @JoinColumn(name = "cd_modelo", referencedColumnName = "cd_modelo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "cd_tipo_porta", referencedColumnName = "cd_tipo_porta")
    private TipoPorta tipoPorta;

    @Column(name = "ds_porta_modelo", length = 100)
    private String descricao;

    @Column(name = "in_ativo")
    private char inAtivo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_transacao")
    private Date dataTransacao;

    @OneToMany(mappedBy = "portaModelo" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ponto> pontoList;

    public PortaModelo() {
    }

    public PortaModelo(PortaModeloPk portaModeloPk) {
        this.portaModeloPk = portaModeloPk;
    }

    public PortaModelo(Long cdModelo, Long sequencia) {
        this.portaModeloPk = new PortaModeloPk(cdModelo, sequencia);
    }

    public PortaModeloPk getPortaModeloPk() {
        return portaModeloPk;
    }

    public void setPortaModeloPk(PortaModeloPk portaModeloPk) {
        this.portaModeloPk = portaModeloPk;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public TipoPorta getTipoPorta() {
        return tipoPorta;
    }

    public void setTipoPorta(TipoPorta tipoPorta) {
        this.tipoPorta = tipoPorta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public char getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(char inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.portaModeloPk);
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
        final PortaModelo other = (PortaModelo) obj;
        if (!Objects.equals(this.portaModeloPk, other.portaModeloPk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }

    @XmlTransient
    public List<Ponto> getPontoList() {
        return pontoList;
    }

    public void setPontoList(List<Ponto> pontoList) {
        this.pontoList = pontoList;
    }

}
