package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.ModeloDao;
import br.com.cocus.automacaococus.model.Modelo;
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
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author jsoliveira
 */
@SessionScoped
@Named
public class ModeloBean implements Serializable {

    @Inject
    private Mensagem men;

    @Inject
    private ModeloDao mDao;

    private Modelo m;

    private List<Modelo> modelos = new ArrayList<>();
    private List<SelectItem> modelosConverter = new ArrayList<>();

    @Inject
    private LoginBean loginBean;

    public void save() {

        try {

            this.m.setDataTransacao(new Date());
            if (this.m.getId() == null) {

                mDao.save(this.m);
                men.info("Modelo Salva com Sucesso!!");

            } else {

                mDao.update(this.m);
                men.info("Modelo Alterada com Sucesso!!");

            }
            limpar();

        } catch (Exception e) {

            men.error("Erro ao Salvar Modelo");

        }

    }

    public void del() {

        try {
            mDao.remove(this.m);
            limpar();
            men.info("Modelo Excluida com Sucesso!!");

        } catch (Exception e) {

            men.error("Erro ao Excluir Modelo");
        }

    }

    public Modelo getDesc(String desc) {

        return mDao.buscarPorAtributoUnicoRet("descricao", desc, OperadoresJPQL.like.getOperador());

    }

    public void limpar() {

        this.m = new Modelo();
        this.m.setInAtivo('A');
        this.modelos = null;
        this.modelosConverter =null;

    }

    public void onRowEdit(RowEditEvent event) {

        this.m = (Modelo) event.getObject();
        save();

    }

    public void onRowToggle(ToggleEvent event) {

        this.m = (Modelo) event.getData();

    }

    public Modelo getM() {
        return m;
    }

    public void setM(Modelo m) {
        this.m = m;
    }

    public List<Modelo> getModelos() {

        if (this.modelos == null || this.modelos.isEmpty()) {

            this.modelos = mDao.findAll("id", Boolean.TRUE);
        }
        return this.modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public List<SelectItem> getModelosConverter() {

        if (this.modelosConverter == null || this.modelosConverter.isEmpty()) {

            this.modelosConverter = mDao.getSelectItens("id", Boolean.TRUE);
            this.modelosConverter.add(0, new SelectItem(null, "Selecione"));

        }

        return modelosConverter;
    }

    public void setModelosConverter(List<SelectItem> modelosConverter) {
        this.modelosConverter = modelosConverter;
    }

}
