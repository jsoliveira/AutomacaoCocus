package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.PortaModelo;
import br.com.cocus.automacaococus.model.pk.PortaModeloPk;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.Query;

/**
 *
 * @author jsoliveira
 */
@Stateless
public class PortaModeloDao extends DAOImplementado<PortaModeloPk, PortaModelo> implements Serializable {

    public PortaModeloDao() {
        super(PortaModelo.class);
    }

    public Long ultSeq(Long cdModelo) {

        try {

            Query q = entityManager.createQuery("SELECT COALESCE(MAX(p.portaModeloPk.sequencia),0)+1 FROM PortaModelo p WHERE p.portaModeloPk.cdModelo= :cdModelo");
            q.setParameter("cdModelo", cdModelo);
            return (long) q.getSingleResult();

        } catch (Exception e) {

            return null;
        }

    }

}
