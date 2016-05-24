package br.com.cocus.automacaococus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jsoliveira
 */
@Entity
@Table(name = "cad_tipo_usuario")
public class TipoUsuario  implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_tipo_usuario")
    private Long id;
    
    @Column(name = "ds_tipo_usuario",length = 100)
    @Size(max = 100,message = "A descrição não pode ultrapassar 100 caracteres")
    @NotNull
    private String descricao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_transacao")
    private Date dataTransacao;
    
    @OneToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuarios;

    public TipoUsuario() {
    }

    public TipoUsuario(Long id, String descricao, Date dataTransacao) {
        this.id = id;
        this.descricao = descricao;
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

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final TipoUsuario other = (TipoUsuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao ;
    }
    
    
    
    
    
}
