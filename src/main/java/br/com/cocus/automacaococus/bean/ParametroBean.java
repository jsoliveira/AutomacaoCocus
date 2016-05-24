package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.ParametroDao;
import br.com.cocus.automacaococus.model.Parametros;
import br.com.cocus.automacaococus.tools.Mensagem;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jsoliveira
 */
@SessionScoped
@Named
public class ParametroBean implements Serializable {
    
    @Inject
    private ParametroDao pDao;
    
    @Inject
    private Mensagem m;
    
    @Inject
    private LoginBean lBean;
    
    private Parametros p;
    
    @PostConstruct
    public void init() {
        
        this.p = pDao.getById(Long.valueOf(1));
    }
    
    public void save() {
        
        try {
            this.pDao.update(p);
            this.lBean.setParametros(p);
            m.info("Parâmetros Alterados com Sucesso!!!");
            
        } catch (Exception e) {
            
            m.error("Erro ao Alterar Parâmetros");
            
            e.printStackTrace();
        }
        
    }
    
    public Parametros getP() {
        return p;
    }
    
    public void setP(Parametros p) {
        this.p = p;
    }
    
}
