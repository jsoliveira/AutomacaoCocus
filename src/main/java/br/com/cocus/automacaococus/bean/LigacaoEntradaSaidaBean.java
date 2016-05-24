package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.LigacaoEntradaSaidaDao;
import br.com.cocus.automacaococus.model.LigacaoEntradaSaida;
import br.com.cocus.automacaococus.model.pk.LigacaoEntradaSaidaPK;
import br.com.cocus.automacaococus.tools.EnviarMensagemSocket;
import br.com.cocus.automacaococus.tools.Mensagem;
import br.com.cocus.automacaococus.tools.MensagensServidor;
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
public class LigacaoEntradaSaidaBean implements Serializable {

    @Inject
    private Mensagem men;

    @Inject
    private LigacaoEntradaSaidaDao lDao;

    private LigacaoEntradaSaida l;

    private List<LigacaoEntradaSaida> ligacoes;

    private List<SelectItem> ligacaoEntradaSaidaConverter = new ArrayList<>();

    @Inject
    private ModuloBean moduloBen;

    @Inject
    private PortaModeloBean portaModBean;

    @Inject
    private EnviarMensagemSocket enviarMensagemSocket;

    public void save() {

        try {

            this.l.setDtTransacao(new Date());
            if (this.l.getLigacaoEntradaSaidaPK() == null) {

                this.l.setLigacaoEntradaSaidaPK(new LigacaoEntradaSaidaPK(this.l.getPontoEntrada().getId(), this.l.getPontoSaida().getId()));
                this.l.setInAtivo('A');
                lDao.save(this.l);
                men.info("Ligação de Entrada e Saída Adicionado com Sucesso!!");

            } else {

                lDao.update(this.l);
                men.info("Ligação de Entrada e Saída Alterada com Sucesso!!");

            }
            limpar();
            atualizarServidor();

        } catch (Exception e) {

            men.error("Erro ao Salvar Ligação de Entrada e Saída");

        }

    }

    public void del() {

        try {
            lDao.remove(l);
            limpar();
            atualizarServidor();
            men.info("Ligação de Entrada e Saída Excluida com Sucesso!!");
            

        } catch (Exception e) {

            men.error("Erro ao Excluir Ligação de Entrada e Saída");
        }

    }

    public void limpar() {

        this.l = new LigacaoEntradaSaida();
        this.l.setInAtivo('A');

        this.ligacaoEntradaSaidaConverter = null;
        this.ligacoes = null;

    }

    public void onRowEdit(RowEditEvent event) {

        this.l = (LigacaoEntradaSaida) event.getObject();
        save();

    }

    public void atualizarServidor() {

        if (enviarMensagemSocket.enviarMsgSocket(MensagensServidor.atualizarLigacoes.getMensagem())) {

            men.info("Servidor Atualizado com Sucesso!!!");
        } else {

            men.error("Erro ao Atualizar Servidor");
        }
    }

    public LigacaoEntradaSaida getL() {
        return l;
    }

    public void setL(LigacaoEntradaSaida l) {
        this.l = l;
    }

    public List<LigacaoEntradaSaida> getLigacoes() {
        if (this.ligacoes == null || this.ligacoes.isEmpty()) {

            this.ligacoes = lDao.findAll("dtTransacao", Boolean.TRUE);
        }

        return this.ligacoes;
    }

    public void setLigacoes(List<LigacaoEntradaSaida> ligacoes) {
        this.ligacoes = ligacoes;
    }

    public List<SelectItem> getLigacaoEntradaSaidaConverter() {

        if (this.ligacaoEntradaSaidaConverter == null || this.ligacaoEntradaSaidaConverter.isEmpty()) {

            this.ligacaoEntradaSaidaConverter = this.lDao.getSelectItens("dtTransacao", Boolean.TRUE);
        }
        return this.ligacaoEntradaSaidaConverter;
    }

    public void setLigacaoEntradaSaidaConverter(List<SelectItem> ligacaoEntradaSaidaConverter) {
        this.ligacaoEntradaSaidaConverter = ligacaoEntradaSaidaConverter;
    }

}
