/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.LogicaDAO;
import br.com.cocus.automacaococus.dao.TipoLogicaDAO;
import br.com.cocus.automacaococus.interfaces.Beans;
import br.com.cocus.automacaococus.model.Logica;
import br.com.cocus.automacaococus.model.TipoLogica;
import br.com.cocus.automacaococus.tools.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author junio
 */
@SessionScoped
@Named
public class LogicaBean implements Serializable, Beans{

    @Inject
    private Mensagem men;
    
    @Inject
    private LogicaDAO logicaDAO;
    
    @Inject
    private TipoLogicaDAO tipoLogicaDAO;
    
    private Logica log;
    
    private List<Logica> logicas = new ArrayList<>();
    
    private List<SelectItem> logicaConverter = new ArrayList<>();
    private List<SelectItem> tipoLogicasConverter = new ArrayList<>();
    
    
    @Override
    public void save() {
        
        try {
            
            this.log.setDtTransacao(new Date());
            if (this.log.getId() == null) {
                logicaDAO.save(this.log);
                men.info("Lógica Salva com Sucesso!!");
                
            } else {
                
                logicaDAO.update(this.log);
                men.info("Lógica Alterada com Sucesso!!");
                
            }
            clear();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
            men.error("Erro ao Salvar Lógica");
            
        }
        
    }
    
    @Override
    public void del() {
        
        try {
            logicaDAO.remove(log);
            clear();
            men.info("Lógica Excluída com Sucesso!!");
            
        } catch (Exception e) {
            
            men.error("Erro ao Excluir Lógica");
        }
        
    }
    
    @Override
    public void clear() {
        
        this.log = new Logica();
        this.logicas = null;
        this.logicaConverter = null;
        
    }
    
    public void onRowEdit(RowEditEvent event) {
        
        this.log = (Logica) event.getObject();
        save();
        
    }

    public Logica getLog() {
        return log;
    }

    public void setLog(Logica log) {
        this.log = log;
    }
    
    
    public List<Logica> getLogicas() {
        
        if (this.logicas == null || this.logicas.isEmpty()) {
            
            this.logicas = this.logicaDAO.findAll("id", Boolean.TRUE);
        }
        return this.logicas;
    }
    
    public void setLogicas(List<Logica> logicas) {
        this.logicas = logicas;
    }
    
    public List<SelectItem> getLogicasConverter() {
        
        if (this.logicaConverter == null || this.logicaConverter.isEmpty()) {
            
            this.logicaConverter = logicaDAO.getSelectItens("id", Boolean.TRUE);
            this.logicaConverter.add(0, new SelectItem(null, "Selecione"));
        }
        
        return this.logicaConverter;
        
    }
    
    public List<SelectItem> getTiposLogicasConverter() {
        
        if (this.tipoLogicasConverter == null || this.tipoLogicasConverter.isEmpty()) {
            
            this.tipoLogicasConverter = tipoLogicaDAO.getSelectItens("id", Boolean.TRUE);
            this.tipoLogicasConverter.add(0, new SelectItem(null, "Selecione"));
        }
        
        return this.tipoLogicasConverter;
        
    }
    
}
