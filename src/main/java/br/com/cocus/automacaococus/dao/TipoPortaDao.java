package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.TipoPorta;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author jsoliveira
 */

@Stateless
public class TipoPortaDao extends DAOImplementado<Long, TipoPorta> implements Serializable{

    public TipoPortaDao() {
        super(TipoPorta.class);
    }
    
}
