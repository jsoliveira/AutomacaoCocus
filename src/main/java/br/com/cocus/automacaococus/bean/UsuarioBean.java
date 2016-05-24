package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.UsuarioDao;
import br.com.cocus.automacaococus.model.Usuario;
import br.com.cocus.automacaococus.tools.Mensagem;
import br.com.cocus.automacaococus.tools.OperadoresJPQL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jsoliveira
 */
@SessionScoped
@Named
public class UsuarioBean implements Serializable {

    @Inject
    private Mensagem men;

    @Inject
    private UsuarioDao uDao;

    private Usuario u;

    private String confPwd;
    private String comf;

    private List<Usuario> usuarios = new ArrayList<>();
    private List<SelectItem> usuariosConverter = new ArrayList<>();

    @Inject
    private LoginBean loginBean;

    @PostConstruct
    public void init() {

        this.u = new Usuario();
    }

    public void save() {

        try {

            if (!this.confPwd.equals(this.u.getPassword())) {

                men.warn("Senhas não conferem");
                this.confPwd = "";
                this.u.setPassword("");

                return;
            }

            this.u.setDataTransacao(new Date());
            if (this.u.getId() == null) {

                uDao.save(this.u);
                men.info("Usuário Salva com Sucesso!!");

            } else {

                uDao.update(this.u);
                men.info("Usuário Alterada com Sucesso!!");

            }
            limpar();

        } catch (Exception e) {

            men.error("Erro ao Salvar Usuário");

        }

    }

    public void comfirma() {

        this.comf = (u.getPassword().equals(confPwd)) ? "Ok" : "Incorreta";

    }

    public void validaUsuario() {

        try {

            if (this.uDao.buscarPorAtributoUnicoRet("login", this.u.getLogin(), OperadoresJPQL.equals.getOperador()) != null) {

                men.warn("Usuário ja existe");
                this.u.setLogin("");

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void del() {

        try {
            uDao.remove(this.u);
            limpar();
            men.info("Usuário Excluida com Sucesso!!");

        } catch (Exception e) {

            men.error("Erro ao Excluir Usuário");
        }

    }

    public void limpar() {

        this.u = new Usuario();
        this.u.setInAtivo('A');
        this.usuarios = null;

    }

    public void onRowEdit(RowEditEvent event) {

        this.u = (Usuario) event.getObject();
        save();

    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public List<Usuario> getUsuarios() {

        if (this.usuarios == null || this.usuarios.isEmpty()) {

            this.usuarios = uDao.findAll("id", Boolean.TRUE);
        }
        return this.usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<SelectItem> getUsuariosConverter() {

        if (this.usuariosConverter == null || this.usuariosConverter.isEmpty()) {

            this.usuariosConverter = uDao.getSelectItens("id", Boolean.TRUE);
            this.usuariosConverter.add(0, new SelectItem(null, "Selecione"));

        }
        return usuariosConverter;
    }

    public void setUsuariosConverter(List<SelectItem> usuariosConverter) {
        this.usuariosConverter = usuariosConverter;
    }

    public String getConfPwd() {
        return confPwd;
    }

    public void setConfPwd(String confPwd) {
        this.confPwd = confPwd;
    }

    public String getComf() {
        return comf;
    }

    public void setComf(String comf) {
        this.comf = comf;
    }
    
    

}
