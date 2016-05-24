package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.Parametros;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author jsoliveira
 */

@Stateless
public class ParametroDao extends DAOImplementado<Long, Parametros> implements Serializable{

    public ParametroDao() {
        super(Parametros.class);
    }
    
    
    
    
    
}
