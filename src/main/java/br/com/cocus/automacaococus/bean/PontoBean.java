package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.PontoDao;
import br.com.cocus.automacaococus.model.Ponto;
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
public class PontoBean implements Serializable {

    @Inject
    private Mensagem men;

    @Inject
    private PontoDao pDao;

    private Ponto p;

    private List<Ponto> pontos;
    
    private List<Ponto> pontosSaida;
    
    private List<Ponto> pontosEntrada;

    private List<SelectItem> portasModeloConverter = new ArrayList<>();

    private List<SelectItem> pontosConverter = new ArrayList<>();

    private List<SelectItem> pontosEntradaConverter = new ArrayList<>();

    private List<SelectItem> pontosSaidaConverter = new ArrayList<>();
    
    

    @Inject
    private ModuloBean moduloBen;

    @Inject
    private PortaModeloBean portaModBean;

    public void save() {

        try {

            this.p.setDataTransacao(new Date());
            this.p.setTpPonto(this.p.getPortaModelo().getTipoPorta().getTpPorta());
            if (this.p.getId() == null) {

                pDao.save(this.p);
                men.info("Ponto Adicionado com Sucesso!!");

            } else {

                pDao.update(this.p);
                men.info("Ponto Alterada com Sucesso!!");

            }
            limpar();

        } catch (Exception e) {

            men.error("Erro ao Salvar Ponto");

        }

    }

    public void setPortaModelo() {

        this.portasModeloConverter = portaModBean.getPortasModeloConverter(this.p.getModulo().getModelo().getId());

    }

    public void del() {

        try {
            pDao.remove(p);
            limpar();
            men.info("Ponto Excluida com Sucesso!!");

        } catch (Exception e) {

            men.error("Erro ao Excluir Ponto");
        }

    }

    public void limpar() {

        this.p = new Ponto();
        this.p.setInAtivo('A');

        this.pontos = null;
        this.pontosConverter = null;
        this.pontosEntradaConverter = null;
        this.pontosSaidaConverter = null;

    }

    public void onRowEdit(RowEditEvent event) {

        this.p = (Ponto) event.getObject();
        save();

    }

    public Ponto getP() {
        return p;
    }

    public void setP(Ponto p) {
        this.p = p;
    }

    public List<SelectItem> getPontosConverter() {

        if (this.pontosConverter == null || this.pontosConverter.isEmpty()) {

            this.pontosConverter = this.pDao.getSelectItens("id", Boolean.TRUE);

        }

        return pontosConverter;

    }

    public List<SelectItem> getPontosEntradaConverter() {

        if (this.pontosEntradaConverter == null || this.pontosEntradaConverter.isEmpty()) {

            this.pontosEntradaConverter = this.pDao.getPontosEntrada();
            this.pontosEntradaConverter.add(0, new SelectItem(null, "Selecione"));

        }

        return pontosEntradaConverter;
    }

    public List<SelectItem> getPontosSaidaConverter() {

        if (this.pontosSaidaConverter == null || this.pontosSaidaConverter.isEmpty()) {

            this.pontosSaidaConverter = this.pDao.getPontosSaida();
            this.pontosSaidaConverter.add(0, new SelectItem(null, "Selecione"));

        }

        return pontosSaidaConverter;
    }

    public void setPontosConverter(List<SelectItem> pontosConverter) {
        this.pontosConverter = pontosConverter;
    }
    
    

    public List<Ponto> getPontos() {

        if (this.pontos == null || this.pontos.isEmpty()) {

            this.pontos = this.pDao.findAll("id", Boolean.TRUE);

        }
        return this.pontos;
    }

    public List<Ponto> getPontosSaida() {
    
        
        if (this.pontosSaida == null || this.pontosSaida.isEmpty()) {

            this.pontosSaida = this.pDao.buscarPorAtributo("tpPonto", "S", OperadoresJPQL.equals.getOperador(), "id", Boolean.TRUE);

        }
           
        return pontosSaida;
    }

    public void setPontosSaida(List<Ponto> pontosSaida) {
        this.pontosSaida = pontosSaida;
    }

    public List<Ponto> getPontosEntrada() {
        
        if (this.pontosEntrada == null || this.pontosEntrada.isEmpty()) {

            this.pontosEntrada = this.pDao.buscarPorAtributo("tpPonto", "E", OperadoresJPQL.equals.getOperador(), "id", Boolean.TRUE);

        }
        
        return pontosEntrada;
    }

    public void setPontosEntrada(List<Ponto> pontosEntrada) {
        this.pontosEntrada = pontosEntrada;
    }

    
    
    public void setPontos(List<Ponto> pontos) {
        this.pontos = pontos;
    }

    public List<SelectItem> getPortasModeloConverter() {
        return portasModeloConverter;
    }

    public void setPortasModeloConverter(List<SelectItem> portasModeloConverter) {
        this.portasModeloConverter = portasModeloConverter;
    }

}
