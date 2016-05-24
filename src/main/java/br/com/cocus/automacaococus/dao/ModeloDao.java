package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.Modelo;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 *
 * @author jsoliveira
 */

@Stateless
public class ModeloDao extends DAOImplementado<Long, Modelo> implements Serializable {

    public ModeloDao() {
        super(Modelo.class);
    }


    

}
