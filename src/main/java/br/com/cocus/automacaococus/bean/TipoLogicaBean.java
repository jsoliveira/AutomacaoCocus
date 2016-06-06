package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.TipoLogicaDAO;
import br.com.cocus.automacaococus.interfaces.Beans;
import br.com.cocus.automacaococus.model.TipoLogica;
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
 * @author junior
 */
@SessionScoped
@Named
public class TipoLogicaBean implements Serializable, Beans{
    
    @Inject
    private Mensagem men;
    
    @Inject
    private TipoLogicaDAO tipoLogicaDAO;
    
    private TipoLogica tl;
    
    private List<TipoLogica> tiposLogicas = new ArrayList<>();
    
    private List<SelectItem> tipoLogicaConverter = new ArrayList<>();
    
    
    @Override
    public void save() {
        
        try {
            
            this.tl.setDtTransacao(new Date());
            if (this.tl.getId() == null) {
                
                tipoLogicaDAO.save(this.tl);
                men.info("Tipo de Lógica Salva com Sucesso!!");
                
            } else {
                
                tipoLogicaDAO.update(this.tl);
                men.info("Tipo de Lógica Alterada com Sucesso!!");
                
            }
            clear();
            
        } catch (Exception e) {
            e.printStackTrace();
            men.error("Erro ao Salvar Tipo Lógica");
            
        }
        
    }
    
    @Override
    public void del() {
        
        try {
            tipoLogicaDAO.remove(tl);
            clear();
            men.info("Tipo Lógica Excluída com Sucesso!!");
            
        } catch (Exception e) {
            
            men.error("Erro ao Excluir Tipo Lógica");
        }
        
    }
    
    @Override
    public void clear() {
        
        this.tl = new TipoLogica();
        this.tiposLogicas = null;
        this.tipoLogicaConverter = null;
        
    }
    
    public void onRowEdit(RowEditEvent event) {
        
        this.tl = (TipoLogica) event.getObject();
        save();
        
    }
    
    public TipoLogica getTl() {
        return tl;
    }
    
    public void setTl(TipoLogica tl) {
        this.tl = tl;
    }
    
    public List<TipoLogica> getTipoLogicas() {
        
        if (this.tiposLogicas == null || this.tiposLogicas.isEmpty()) {
            
            this.tiposLogicas = this.tipoLogicaDAO.findAll("id", Boolean.TRUE);
        }
        return this.tiposLogicas;
    }
    
    public void setTipoLogicas(List<TipoLogica> areas) {
        this.tiposLogicas = areas;
    }
    
    public List<SelectItem> getTipoLogicasConverter() {
        
        if (this.tipoLogicaConverter == null || this.tipoLogicaConverter.isEmpty()) {
            
            this.tipoLogicaConverter = tipoLogicaDAO.getSelectItens("id", Boolean.TRUE);
            this.tipoLogicaConverter.add(0, new SelectItem(null, "Selecione"));
        }
        
        return this.tipoLogicaConverter;
        
    }
    
}
