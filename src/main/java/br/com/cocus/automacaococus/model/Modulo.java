package br.com.cocus.automacaococus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "cad_modulo")
public class Modulo implements Serializable {

    @Id
    @Column(name = "cd_modulo")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cd_modelo", referencedColumnName = "cd_modelo")
    private Modelo modelo;

    @Column(name = "ds_modulo", length = 100)
    @Size(max = 100, message = "A descrição do modulo não pode ultrapassar a 100 caracteres")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cd_area", referencedColumnName = "cd_area")
    private Area area;

    @Column(name = "in_ativo")
    private char inAtivo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_transacao")
    private Date dataTransacao;

    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ponto> pontos;

    public Modulo() {
    }

    public Modulo(Long id, Modelo modelo, String descricao, Area area, Date dataTransacao, char inAtivo, List<Ponto> pontos) {

        this.id = id;
        this.modelo = modelo;
        this.descricao = descricao;
        this.area = area;
        this.dataTransacao = dataTransacao;
        this.inAtivo = inAtivo;
        this.pontos = pontos;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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

    public List<Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(List<Ponto> pontos) {
        this.pontos = pontos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Modulo other = (Modulo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
