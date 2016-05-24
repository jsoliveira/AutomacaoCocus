package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.PortaModeloDao;
import br.com.cocus.automacaococus.model.PortaModelo;
import br.com.cocus.automacaococus.model.pk.PortaModeloPk;
import br.com.cocus.automacaococus.tools.Mensagem;
import br.com.cocus.automacaococus.tools.OperadoresJPQL;
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
public class PortaModeloBean implements Serializable {

    @Inject
    private Mensagem men;

    @Inject
    private PortaModeloDao pModDao;

    private PortaModelo pm;

    private List<PortaModelo> portasModelo = new ArrayList<>();

    private List<SelectItem> portasModeloConverter = new ArrayList<>();

    @Inject
    private ModeloBean modeloBean;

    public void save() {

        this.pm.setDataTransacao(new Date());
        try {

            Long nrSeq = this.pm.getPortaModeloPk().getSequencia();

            pModDao.update(this.pm);
            men.info("Porta Alterada com Sucesso!!");
            limpar();

        } catch (Exception e) {

            try {
                pm.setPortaModeloPk(new PortaModeloPk(this.pm.getModelo().getId(), pModDao.ultSeq(this.pm.getModelo().getId())));
                pModDao.save(this.pm);
                men.info("Porta Salva com Sucesso!!");
               

            } catch (Exception ee) {
                men.error("Erro ao Salvar Porta");
            }

        }
        
         limpar();
   

    }

    public void del() {

        try {
            pModDao.remove(this.pm);
            limpar();
            men.info("Porta Excluida com Sucesso!!");

        } catch (Exception e) {

            men.error("Erro ao Excluir Porta");
        }

    }

    public void limpar() {

        this.pm = new PortaModelo();
        this.pm.setInAtivo('A');
        this.portasModelo = null;
        this.portasModelo = getPortasModelo();
        this.pm.setModelo(this.modeloBean.getM());
        
        modeloBean.setModelos(null);

    }

    public void onRowEdit(RowEditEvent event) {

        this.pm = (PortaModelo) event.getObject();
        save();

    }

    public PortaModelo getPm() {
        return pm;
    }

    public void setPm(PortaModelo pm) {
        this.pm = pm;
    }

    public List<PortaModelo> getPortasModelo() {
        return (this.portasModelo == null || this.portasModelo.isEmpty()) ? pModDao.findAll("portaModeloPk.sequencia", Boolean.TRUE) : this.portasModelo;
    }

    public List<SelectItem> getPortasModeloConverter() {

        if (this.portasModeloConverter == null || this.portasModeloConverter.isEmpty()) {

            this.portasModeloConverter = this.pModDao.getSelectItens("portaModeloPk.sequencia", Boolean.TRUE);
        }
        return this.portasModeloConverter;

    }

    public List<SelectItem> getPortasModeloConverter(Long cdModelo) {

        if (this.portasModeloConverter == null || this.portasModeloConverter.isEmpty()) {

            this.portasModeloConverter = this.pModDao.getSelectItens("portaModeloPk.cdModelo", cdModelo, OperadoresJPQL.equals.getOperador(), "portaModeloPk.sequencia", Boolean.TRUE);
        }

        return this.portasModeloConverter;

    }

}
