package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.PontoDao;
import br.com.cocus.automacaococus.lazy.model.PontoIndex;
import br.com.cocus.automacaococus.model.Area;
import br.com.cocus.automacaococus.model.Ponto;
import br.com.cocus.automacaococus.tools.EnviarMensagemSocket;
import br.com.cocus.automacaococus.tools.Mensagem;
import br.com.cocus.automacaococus.tools.MensagensServidor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class IndexBean implements Serializable {

    @Inject
    private Mensagem men;

    private final String luzLigada = "imagens/luzligada.png";
    private final String luzDesligada = "imagens/luzdesligada.png";

    private PontoIndex pontoSel;
    private Area areaSel;
    private List<PontoIndex> pontosArea;
    private ArrayList<String> menServ;
    private StringBuilder str;

    private Boolean atualizar;
    private Boolean stop;
    private String tmpAtualizar;

    @Inject
    private PontoDao pDao;

    @Inject
    private EnviarMensagemSocket enviarMensagemSocket;

    @PostConstruct
    public void init() {
        menServ = new ArrayList<>();
        str = new StringBuilder();
        this.atualizar = false;
        this.stop = false;
        this.tmpAtualizar = "60";

    }

    public List<PontoIndex> getPontosArea() {

        return this.pontosArea;

    }

    public void testarAtt() {

        this.stop = this.atualizar == false;
    }

    public void setarPontosArea() {

        if (this.areaSel == null) {

            this.pontosArea = new ArrayList<>();
            return;
        }

        this.pontosArea = converter(pDao.getPontosSaida(areaSel.getId()));

    }

    public List<PontoIndex> converter(List<Ponto> pontos) {

        try {
            List<PontoIndex> ret = new ArrayList<>();
            int valorPonto;
            for (Ponto p : pontos) {

                valorPonto = valorPonto(p);

                ret.add(new PontoIndex((valorPonto > 0) ? p.getImgLigado() : p.getImgDesligado(), (valorPonto > 0), 0, p, enviarMensagemSocket));

            }

            return ret;

        } catch (Exception e) {

            return new ArrayList<>();

        }
    }

    public Integer valorPonto(Ponto p) {

        try {

            menServ.clear();
            menServ.add(MensagensServidor.adicionarComando.getMensagem());

            str.setLength(0);
            str.append("GETPORTA;").append(p.getModulo().getId()).append(";").append(p.getPortaModelo().getDescricao());
            menServ.add(str.toString());

            String message = enviarMensagemSocket.receberMensagemSocket(menServ);

            System.out.println(message);

            return Integer.valueOf(message);

        } catch (Exception ex) {

            return 0;

        }

    }

    public PontoIndex getPontoSel() {
        return pontoSel;
    }

    public void setPontoSel(PontoIndex pontoSel) {
        this.pontoSel = pontoSel;
    }

    public Area getAreaSel() {
        return areaSel;
    }

    public void setAreaSel(Area areaSel) {
        this.areaSel = areaSel;
    }

    public Boolean getAtualizar() {
        return atualizar;
    }

    public void setAtualizar(Boolean atualizar) {
        this.atualizar = atualizar;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }

    public String getTmpAtualizar() {
        return tmpAtualizar;
    }

    public void setTmpAtualizar(String tmpAtualizar) {
        this.tmpAtualizar = tmpAtualizar;
    }

}
