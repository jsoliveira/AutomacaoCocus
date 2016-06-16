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
public class TipoUsuarioDAO extends DAOImplementado<Long, TipoUsuario> implements Serializable{

    public TipoUsuarioDAO() {
        super(TipoUsuario.class);
    }
    
}
