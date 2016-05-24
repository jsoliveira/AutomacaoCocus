package br.com.cocus.automacaococus.lazy.model;

import br.com.cocus.automacaococus.model.Ponto;
import br.com.cocus.automacaococus.tools.EnviarMensagemSocket;

/**
 *
 * @author jsoliveira
 */
public class PontoIndex {

    private String img;
    private boolean ligado;
    private Integer itensidade;
    private Ponto ponto;
    private final StringBuilder str;
    private final EnviarMensagemSocket enviarMensagemSocket;

    public PontoIndex(String img, boolean ligado, Integer itensidade, Ponto ponto, EnviarMensagemSocket enviarMensagemSocket) {
        this.img = img;
        this.ligado = ligado;
        this.itensidade = itensidade;
        this.ponto = ponto;
        this.enviarMensagemSocket = enviarMensagemSocket;
        this.str = new StringBuilder();

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    public Integer getItensidade() {
        return itensidade;
    }

    public void setItensidade(Integer itensidade) {
        this.itensidade = itensidade;
    }

    public Ponto getPonto() {
        return ponto;
    }

    public void setPonto(Ponto ponto) {
        this.ponto = ponto;
    }

    public void ligar() {

        try {

            str.setLength(0);
            str.append("SETPORTA;").append(getPonto().getModulo().getId()).append(";").append(getPonto().getPortaModelo().getDescricao());

            enviarMensagemSocket.enviarMsgSocket(str.toString());

           setImg((isLigado()) ? getPonto().getImgLigado() : getPonto().getImgDesligado());

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}
