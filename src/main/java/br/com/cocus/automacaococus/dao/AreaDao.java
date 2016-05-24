package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.Area;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 *
 * @author jsoliveira
 */
@Stateless
public class AreaDao extends DAOImplementado<Long, Area> implements Serializable{

    public AreaDao() {
        super(Area.class);
    }
    
}
