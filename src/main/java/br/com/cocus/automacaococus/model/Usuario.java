package br.com.cocus.automacaococus.model;

import br.com.cocus.automacaococus.tools.ConverterDate;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cad_usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cd_tipo_usuario",referencedColumnName = "cd_tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Column(name = "nm_usuario", length = 100)
    private String nome;

    @Column(name = "nm_login", length = 20)
    @Size(max = 20, min = 4, message = "A login deve conter nom minimo 4 e no maximo 20 caracteres")
    @NotNull(message = "Campo Obrigatório")
    private String login;

    @Column(name = "pw_login", length = 10)
    @Size(max = 20, min = 4, message = "A senha deve conter no minimo 4 carcteres e no máximo 20 caracteres")
    @NotNull(message = "Campo Obrigatório")
    private String password;

    @Column(name = "in_ativo")
    private char inAtivo;

    @Column(name = "dt_transacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTransacao;

    @Column(name = "dt_ult_acesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimoAcesso;

    public Usuario() {
    }

    public Usuario(Long id, TipoUsuario tipoUsuario, String nome, String login, String password, char inAtivo, Date dataTransacao, Date dataUltimoAcesso) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.inAtivo = inAtivo;
        this.dataTransacao = dataTransacao;
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(Date dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }



    public String getUltimoAcesso() {

        return ConverterDate.dateTimeToStr(this.dataUltimoAcesso);
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", tipoUsuario=" + tipoUsuario + ", nome=" + nome + ", login=" + login + ", password=" + password + ", inAtivo=" + inAtivo + ", dataTransacao=" + dataTransacao + ", dataUltimoAcesso=" + dataUltimoAcesso + '}';
    }

    
    
}
