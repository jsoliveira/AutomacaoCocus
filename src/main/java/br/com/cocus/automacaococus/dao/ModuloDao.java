package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.Modulo;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 *
 * @author jsoliveira
 */

@Stateless
public class ModuloDao extends DAOImplementado<Long, Modulo> implements Serializable {

    public ModuloDao() {
        super(Modulo.class);
    }

}
