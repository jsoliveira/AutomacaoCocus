package br.com.cocus.automacaococus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.model.SelectItem;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cad_modelo")
public class Modelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_modelo")
    private Long id;

    @Column(name = "ds_modelo", length = 100)
    @Size(max = 100, message = "ds_descricao")
    private String descricao;

    @Column(name = "in_ativo")
    private char inAtivo;

    @Column(name = "dt_transacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTransacao;

    @OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Modulo> modulos;

    @OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PortaModelo> portaModelos;

    public Modelo() {
    }

    public Modelo(Long id, String descricao, char inAtivo, Date dataTransacao) {
        this.id = id;
        this.descricao = descricao;
        this.inAtivo = inAtivo;
        this.dataTransacao = dataTransacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<PortaModelo> getPortaModelos() {
        return portaModelos;
    }

    public List<SelectItem> getPortaModelosConverter() {

        List<SelectItem> ret = new ArrayList<>();
        ret.add(new SelectItem(null, "Selecione"));
        for (PortaModelo p : getPortaModelos()) {

            ret.add(new SelectItem(p));

        }

        return ret;

    }

    public void setPortaModelos(List<PortaModelo> portaModelos) {
        this.portaModelos = portaModelos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Modelo other = (Modelo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo{" + "id=" + id + ", descricao=" + descricao + ", dataTransacao=" + dataTransacao + '}';
    }



}
