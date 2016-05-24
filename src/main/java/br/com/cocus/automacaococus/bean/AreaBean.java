package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.AreaDao;
import br.com.cocus.automacaococus.interfaces.Beans;
import br.com.cocus.automacaococus.model.Area;
import br.com.cocus.automacaococus.tools.Mensagem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jsoliveira
 */
@SessionScoped
@Named
public class AreaBean implements Serializable, Beans {
    
    @Inject
    private Mensagem men;
    
    @Inject
    private AreaDao aDao;
    
    private Area a;
    
    private List<Area> areas = new ArrayList<>();
    
    private List<SelectItem> areasConverter = new ArrayList<>();
    
    @Inject
    private LoginBean loginBean;
    
    private final String pastaUpload = "upload/";
    
    private final int BUFFER_SIZE = 8000000;
    
    @Override
    public void save() {
        
        try {
            
            this.a.setDataTransacao(new Date());
            if (this.a.getId() == null) {
                
                aDao.save(this.a);
                men.info("Aréa Salva com Sucesso!!");
                
            } else {
                
                aDao.update(this.a);
                men.info("Aréa Alterada com Sucesso!!");
                
            }
            clear();
            
        } catch (Exception e) {
            
            men.error("Erro ao Salvar Aréa");
            
        }
        
    }
    
    @Override
    public void del() {
        
        try {
            aDao.remove(a);
            clear();
            men.info("Aréa Excluida com Sucesso!!");
            
        } catch (Exception e) {
            
            men.error("Erro ao Excluir Aréa");
        }
        
    }
    
    @Override
    public void clear() {
        
        this.a = new Area();
        this.a.setInAtivo('A');
        this.areas = null;
        this.areasConverter = null;
        
    }
    
    public void onRowEdit(RowEditEvent event) {
        
        this.a = (Area) event.getObject();
        save();
        
    }
    
    public void fileUploadAction(FileUploadEvent event) {
        
        try {
            
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            
            FacesContext aFacesContext = FacesContext.getCurrentInstance();
            ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
            
            String caminho = pastaUpload + loginBean.getUsuario().getLogin() + "/";
            
            String realPath = context.getRealPath("/");
            
            String newDiretory = context.getRealPath(realPath + caminho);
            
            System.out.println(realPath + caminho);
            
            if (newDiretory == null || !new File(newDiretory).exists()) {
                
                new File(realPath + caminho).mkdirs();
                
            }
            
            File result = new File(realPath + caminho + event.getFile().getFileName());
            
            String s = result.getAbsolutePath();
            try {
                InputStream inputStream;
                try (FileOutputStream fileOutputStream = new FileOutputStream(result)) {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bulk;
                    inputStream = event.getFile().getInputstream();
                    while (true) {
                        bulk = inputStream.read(buffer);
                        if (bulk < 0) {
                            break;
                        }
                        fileOutputStream.write(buffer, 0, bulk);
                        fileOutputStream.flush();
                    }
                    
                }
                inputStream.close();
                
                this.a.setLocalImagem(s);
                this.save();
                
                men.info("O arquivo: " + event.getFile().getFileName() + ", foi adicionado com sucesso!");
                
            } catch (IOException e) {
                men.error("Ocorreu um erro ao tentar adicionar o arquivo!");
            }
            
        } catch (Exception e) {
            men.error("Ocorreu um erro ao tentar adicionar o arquivo!");
        }
    }
    
    public Area getA() {
        return a;
    }
    
    public void setA(Area a) {
        this.a = a;
    }
    
    public List<Area> getAreas() {
        
        if (this.areas == null || this.areas.isEmpty()) {
            
            this.areas = this.aDao.findAll("id", Boolean.TRUE);
        }
        return this.areas;
    }
    
    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
    
    public List<SelectItem> getAreasConverter() {
        
        if (this.areasConverter == null || this.areasConverter.isEmpty()) {
            
            this.areasConverter = aDao.getSelectItens("id", Boolean.TRUE);
            this.areasConverter.add(0, new SelectItem(null, "Selecione"));
        }
        
        return this.areasConverter;
        
    }
    
}
