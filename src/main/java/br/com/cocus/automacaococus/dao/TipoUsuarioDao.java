package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.TipoUsuario;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 *
 * @author jsoliveira
 */
@Stateless
public class TipoUsuarioDao extends DAOImplementado<Long, TipoUsuario> implements Serializable{

    public TipoUsuarioDao() {
        super(TipoUsuario.class);
    }
    
}
