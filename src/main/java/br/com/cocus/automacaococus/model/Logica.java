/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cocus.automacaococus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author junio
 */
@Entity
@Table(name = "logica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logica.findAll", query = "SELECT l FROM Logica l")})
public class Logica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_logica")
    private Long id;
    
    @Size(max = 45)
    @Column(name = "ds_logica")
    private String dsLogica;
    
    @Column(name = "in_ativo")
    private Character inAtivo;
    
    @Column(name = "dt_transacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTransacao;
    
    @ManyToOne
    @JoinColumn(name = "cd_tipo_logica", referencedColumnName = "cd_tipo_logica")
    private TipoLogica tipoLogica;
    
    public Logica() {
    }

    public Logica(Long cdLogica) {
        this.id = cdLogica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsLogica() {
        return dsLogica;
    }

    public void setDsLogica(String dsLogica) {
        this.dsLogica = dsLogica;
    }

    public Character getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Character inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Date getDtTransacao() {
        return dtTransacao;
    }

    public void setDtTransacao(Date dtTransacao) {
        this.dtTransacao = dtTransacao;
    }

    public TipoLogica getTipoLogica() {
        return tipoLogica;
    }

    public void setTipoLogica(TipoLogica tipoLogica) {
        this.tipoLogica = tipoLogica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logica)) {
            return false;
        }
        Logica other = (Logica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cocus.automacaococus.model.Logica[ cdLogica=" + id + " ]";
    }
    
}
