package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.TipoPortaDAO;
import br.com.cocus.automacaococus.model.TipoPorta;
import br.com.cocus.automacaococus.tools.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
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
public class TipoPortaBean implements Serializable {

    @Inject
    private Mensagem men;

    @Inject
    private TipoPortaDAO tDao;

    private TipoPorta t;

    private List<TipoPorta> tipos = new ArrayList<>();

    private List<SelectItem> tiposConverter = new ArrayList<>();

    @Inject
    private LoginBean loginBean;

    public void save() {

        try {

            if (this.t.getId() == null) {

                tDao.save(this.t);
                men.info("Tipo de Porta Salva com Sucesso!!");

            } else {

                tDao.update(this.t);
                men.info("Tipo de Porta Alterada com Sucesso!!");

            }
            limpar();

        } catch (Exception e) {

            men.error("Erro ao Salvar Tipo de Porta");

        }

    }

    public void del() {

        try {
            tDao.remove(this.t);
            limpar();
            men.info("Tipo de Porta Excluida com Sucesso!!");

        } catch (Exception e) {

            men.error("Erro ao Excluir Tipo de Porta");
        }

    }

    public void limpar() {

        this.t = new TipoPorta();
        this.t.setInAtivo('A');
        this.t.setTpPorta('E');
        this.tipos = null;
        this.tiposConverter = null;

    }

    public void onRowEdit(RowEditEvent event) {

        this.t = (TipoPorta) event.getObject();
        save();

    }

    public TipoPorta getT() {
        return t;
    }

    public void setT(TipoPorta t) {
        this.t = t;
    }

    public List<TipoPorta> getTipos() {

        if (this.tipos == null || this.tipos.isEmpty()) {

            this.tipos = tDao.findAll("id", Boolean.TRUE);
        }
        return this.tipos;
    }

    public void setTipos(List<TipoPorta> tipos) {
        this.tipos = tipos;
    }

    public List<SelectItem> getTiposConverter() {

        if (this.tiposConverter == null || this.tiposConverter.isEmpty()) {

            this.tiposConverter = tDao.getSelectItens("id", Boolean.TRUE);
            this.tiposConverter.add(0, new SelectItem(null, "Selecione"));

        }

        return tiposConverter;
    }

}
