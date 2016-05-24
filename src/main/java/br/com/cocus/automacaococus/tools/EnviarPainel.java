package br.com.cocus.automacaococus.tools;

import br.com.cocus.automacaococus.lazy.model.PontoIndex;

/**
 *
 * @author jsoliveira
 */
public class EnviarPainel {

    private static final EnviarMensagemSocket men = new EnviarMensagemSocket();
    private static final StringBuilder str = new StringBuilder();

    public static void ligar(PontoIndex p) {

        try {

            str.setLength(0);
            str.append("SETPORTA;").append(p.getPonto().getModulo().getId()).append(";").append(p.getPonto().getPortaModelo().getDescricao());
            

            men.enviarMsgSocket(str.toString());

  
        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }
}
