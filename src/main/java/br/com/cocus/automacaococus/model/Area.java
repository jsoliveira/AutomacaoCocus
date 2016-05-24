package br.com.cocus.automacaococus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jsoliveira
 */
@Entity
@Table(name = "cad_area")
public class Area implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_area")
    private Long id;

    @Column(name = "ds_area", length = 100)
    @NotNull(message = "Campo Obrigatório")
    @Size(max = 100, message = "A descrição não pode ultrapassar mais que 100 caracteres")
    private String descricao;

    @Column(name = "ds_local_imagem", length = 150)
    private String localImagem;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "dt_transacao")
    private Date dataTransacao;

    @Column(name = "in_ativo")
    private char inAtivo;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Modulo> modulos;

    public Area() {
    }

    public Area(Long id, String descricao, String localImagem, char inAtivo, Date dataTransacao, List<Modulo> modulos) {
        this.id = id;
        this.descricao = descricao;
        this.localImagem = localImagem;
        this.inAtivo = inAtivo;
        this.dataTransacao = dataTransacao;
        this.modulos = modulos;
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

    public String getLocalImagem() {
        return localImagem;
    }

    public void setLocalImagem(String localImagem) {
        this.localImagem = localImagem;
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

    @Override
    public String toString() {
        return descricao;
    }

}
