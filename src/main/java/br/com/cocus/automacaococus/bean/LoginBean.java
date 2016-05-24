package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.ParametroDao;
import br.com.cocus.automacaococus.dao.UsuarioDao;
import br.com.cocus.automacaococus.model.Parametros;
import br.com.cocus.automacaococus.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jsoliveira
 */
@Named
@SessionScoped
public class LoginBean extends javax.servlet.http.HttpServlet implements Serializable {

    private String email;

    private String senha;

    @Inject
    private UsuarioDao uDao;

    private Usuario usuario;

    @Inject
    private ParametroDao pDao;

    private Parametros parametros;

    public void doLogin() {
        try {
            this.usuario = uDao.findUser(email, senha);

            if (this.usuario != null) {

                this.parametros = pDao.getById(Long.valueOf(1));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/AutomacaoCocus/interno/index.xhtml");
            }

        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", "Erro ao redirecionar página. Consulte o Administrador do sistema."));
        }
    }

    public void doLogout() {
        try {

            usuario.setDataUltimoAcesso(new Date());
            uDao.update(usuario);

            this.usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

            FacesContext.getCurrentInstance().getExternalContext().redirect("/AutomacaoCocus/interno/index.xhtml");

        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", "Erro ao redirecionar página. Consulte o Administrador do sistema."));
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha.toUpperCase();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    
    
}
