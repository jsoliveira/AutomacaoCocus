package br.com.cocus.automacaococus.tools;

/**
 *
 * @author jsoliveira
 */
public enum MensagensServidor {

    atualizarLigacoes("ATTLIGACOES;"),
    adicionarPainel("ADDPANEL;"),
    adicionarComando("ADDCOMMAND;"),
    dispositivosOnline("GETONLINE;");

    private final String mensagem;

    private MensagensServidor(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
    
 

}
