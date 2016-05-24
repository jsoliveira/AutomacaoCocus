package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.LigacaoEntradaSaida;
import br.com.cocus.automacaococus.model.pk.LigacaoEntradaSaidaPK;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author jsoliveira
 */

@Stateless
public class LigacaoEntradaSaidaDao extends DAOImplementado<LigacaoEntradaSaidaPK, LigacaoEntradaSaida> implements Serializable {

    public LigacaoEntradaSaidaDao() {
        super(LigacaoEntradaSaida.class);
    }

}
