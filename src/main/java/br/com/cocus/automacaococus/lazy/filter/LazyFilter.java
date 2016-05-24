package br.com.cocus.automacaococus.lazy.filter;

import java.io.Serializable;

/**
 *
 * @author jsoliveira
 */
public class LazyFilter implements Serializable {

    private String propriedadePesq;
    private String descricao;
    private int tipoFiltro;

    private int primeiroRegistro;
    private int quantidadeRegistros;
    private String propriedadeOrdenacao;
    private String propriedadePadraoOrdenacao;
    private boolean ascendente;

    public LazyFilter(String propriedadePadraoOrdenacao) {
        this.propriedadePadraoOrdenacao = propriedadePadraoOrdenacao;
    }

    public String getPropriedadePadraoOrdenacao() {
        return propriedadePadraoOrdenacao;
    }

    public void setPropriedadePadraoOrdenacao(String propriedadePadraoOrdenacao) {
        this.propriedadePadraoOrdenacao = propriedadePadraoOrdenacao;
    }

    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrimeiroRegistro() {
        return primeiroRegistro;
    }

    public void setPrimeiroRegistro(int primeiroRegistro) {
        this.primeiroRegistro = primeiroRegistro;
    }

    public int getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(int quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
    }

    public String getPropriedadeOrdenacao() {
        return propriedadeOrdenacao;
    }

    public void setPropriedadeOrdenacao(String propriedadeOrdenacao) {
        this.propriedadeOrdenacao = propriedadeOrdenacao;
    }

    public boolean isAscendente() {
        return ascendente;
    }

    public void setAscendente(boolean ascendente) {
        this.ascendente = ascendente;
    }

    public String getPropriedadePesq() {
        return propriedadePesq;
    }

    public void setPropriedadePesq(String propriedadePesq) {
        this.propriedadePesq = propriedadePesq;
    }

    public int getTipoFiltro() {
        return tipoFiltro;
    }

    public void setTipoFiltro(int tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    
}
