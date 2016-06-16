package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.TipoUsuarioDAO;
import br.com.cocus.automacaococus.model.TipoUsuario;
import br.com.cocus.automacaococus.tools.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class TipoUsuarioBean implements Serializable {
    
    @Inject
    private Mensagem men;
    
    @Inject
    private TipoUsuarioDAO tDao;
    
    private TipoUsuario t;
    
    private List<TipoUsuario> tipos = new ArrayList<>();
    private List<SelectItem> tiposConverter = new ArrayList<>();
    
    @Inject
    private LoginBean loginBean;
    
    public void save() {
        
        try {
            this.t.setDataTransacao(new Date());
            if (this.t.getId() == null) {
                
                tDao.save(this.t);
                men.info("Tipo de Usuário Salva com Sucesso!!");
                
            } else {
                
                tDao.update(this.t);
                men.info("Tipo de Usuário Alterada com Sucesso!!");
                
            }
            limpar();
            
        } catch (Exception e) {
            
            men.error("Erro ao Salvar Tipo de Usuário");
            
        }
        
    }
    
    public void del() {
        
        try {
            tDao.remove(this.t);
            limpar();
            men.info("Tipo de Usuário Excluida com Sucesso!!");
            
        } catch (Exception e) {
            
            men.error("Erro ao Excluir Tipo de Usuário");
        }
        
    }
    
    public void limpar() {
        
        this.t = new TipoUsuario();
        this.tipos = null;
        this.tipos = getTipos();
        
    }
    
    public void onRowEdit(RowEditEvent event) {
        
        this.t = (TipoUsuario) event.getObject();
        save();
        
    }
    
    public TipoUsuario getT() {
        return t;
    }
    
    public void setT(TipoUsuario t) {
        this.t = t;
    }
    
    public List<TipoUsuario> getTipos() {
        
        if (this.tipos == null || this.tipos.isEmpty()) {
            
            this.tipos = tDao.findAll("id", Boolean.TRUE);
        }
        return this.tipos;
        
    }
    
    public void setTipos(List<TipoUsuario> tipos) {
        this.tipos = tipos;
    }
    
    public List<SelectItem> getTiposConverter() {
        
        if (this.tiposConverter == null || this.tipos.isEmpty()) {
            
            this.tiposConverter = tDao.getSelectItens("id", Boolean.TRUE);
            this.tiposConverter.add(0, new SelectItem(null, "Selecione"));
            
        }
        return tiposConverter;
    }
    
}
