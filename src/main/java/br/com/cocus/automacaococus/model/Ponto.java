package br.com.cocus.automacaococus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author jsoliveira
 */
@Entity
@Table(name = "cad_ponto")

public class Ponto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_ponto")
    private Long id;

    @JoinColumn(name = "cd_modulo", referencedColumnName = "cd_modulo")
    @ManyToOne(optional = false)
    private Modulo modulo;

    @JoinColumns(value = {
        @JoinColumn(name = "cd_modelo", referencedColumnName = "cd_modelo"),
        @JoinColumn(name = "nr_seq_modelo", referencedColumnName = "nr_sequencia")})
    @ManyToOne(optional = false)
    private PortaModelo portaModelo;

    @Column(name = "ds_rotulo", length = 20)
    @Size(max = 20, message = "O rotulo não podem conter mais que 20 caracteres")
    private String rotulo;

    @Column(name = "ds_img_ligado", length = 80)
    private String imgLigado;

    @Column(name = "ds_img_desligado", length = 80)
    private String imgDesligado;

    @Column(name = "in_ativo", length = 1)
    private char inAtivo;

    @Column(name = "tp_ponto", length = 1)
    private char tpPonto;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_transacao")
    private Date dataTransacao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pontoEntrada")
    private List<LigacaoEntradaSaida> ligacaoEntradaSaidaEntradas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pontoSaida")
    private List<LigacaoEntradaSaida> ligacaoEntradaSaidaSaidas;

    public Ponto() {
    }

    public Ponto(Long id, Modulo modulo, PortaModelo portaModelo, String rotulo, char inAtivo, char tpPonto, Date dataTransacao) {
        this.id = id;
        this.modulo = modulo;
        this.portaModelo = portaModelo;
        this.rotulo = rotulo;
        this.inAtivo = inAtivo;
        this.tpPonto = tpPonto;
        this.dataTransacao = dataTransacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public PortaModelo getPortaModelo() {
        return portaModelo;
    }

    public void setPortaModelo(PortaModelo portaModelo) {
        this.portaModelo = portaModelo;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public char getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(char inAtivo) {
        this.inAtivo = inAtivo;
    }

    public char getTpPonto() {
        return tpPonto;
    }

    public void setTpPonto(char tpPonto) {
        this.tpPonto = tpPonto;
    }

    public String getImgLigado() {
        return imgLigado;
    }

    public void setImgLigado(String imgLigado) {
        this.imgLigado = imgLigado;
    }

    public String getImgDesligado() {
        return imgDesligado;
    }

    public void setImgDesligado(String imgDesligado) {
        this.imgDesligado = imgDesligado;
    }

    public List<LigacaoEntradaSaida> getLigacaoEntradaSaidaEntradas() {
        return ligacaoEntradaSaidaEntradas;
    }

    public void setLigacaoEntradaSaidaEntradas(List<LigacaoEntradaSaida> ligacaoEntradaSaidaEntradas) {
        this.ligacaoEntradaSaidaEntradas = ligacaoEntradaSaidaEntradas;
    }

    public List<LigacaoEntradaSaida> getLigacaoEntradaSaidaSaidas() {
        return ligacaoEntradaSaidaSaidas;
    }

    public void setLigacaoEntradaSaidaSaidas(List<LigacaoEntradaSaida> ligacaoEntradaSaidaSaidas) {
        this.ligacaoEntradaSaidaSaidas = ligacaoEntradaSaidaSaidas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Ponto other = (Ponto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return rotulo;
    }

}
