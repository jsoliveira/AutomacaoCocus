package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.TipoLogica;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author junior
 */
@Stateless
public class TipoLogicaDAO extends DAOImplementado<Long, TipoLogica> implements Serializable{

    public TipoLogicaDAO() {
        super(TipoLogica.class);
    }
    
}
