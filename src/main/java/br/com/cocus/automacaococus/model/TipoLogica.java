/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cocus.automacaococus.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author junio
 */
@Entity
@Table(name = "cad_tipo_logica")
public class TipoLogica implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_tipo_logica")
    private Long id;
    
    @Column(name = "ds_tipo_logica", length = 45)
    @Size(max = 100, message = "A descrição não pode conter mais que 45 caracteres")
    @NotNull(message = "Campo Obrigatório")
    private String descricao;
    
    @Column(name = "ds_operador", length = 4)
    @NotNull(message = "Campo Obrigatório")
    private String operador;
    
    @Column(name = "dt_transacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTransacao;

    public TipoLogica() {
    }

    public TipoLogica(Long cdTipoLogica) {
        this.id = cdTipoLogica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long cdTipoLogica) {
        this.id = cdTipoLogica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Date getDtTransacao() {
        return dtTransacao;
    }

    public void setDtTransacao(Date dtTransacao) {
        this.dtTransacao = dtTransacao;
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
        if (!(object instanceof TipoLogica)) {
            return false;
        }
        TipoLogica other = (TipoLogica) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return id + " - " + descricao;
    }
    
}
