package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.Usuario;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author jsoliveira
 */
@Stateless
public class UsuarioDao extends DAOImplementado<Long, Usuario> implements Serializable {

    public UsuarioDao() {
        super(Usuario.class);
    }
    
     public Usuario findUser(String email, String senha) {

        try {

            UaiCriteria uaiCriteria = UaiCriteriaFactory.createQueryCriteria(entityManager, Usuario.class);
            uaiCriteria.andEquals("login", email.toUpperCase());
            uaiCriteria.andEquals("password", senha.toUpperCase());
            uaiCriteria.andEquals("inAtivo", "A");

            return (Usuario) uaiCriteria.getSingleResult();

        } catch (Exception e) {

            return null;

        }

    }

}
