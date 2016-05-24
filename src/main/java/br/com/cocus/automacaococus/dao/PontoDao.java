package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.Ponto;
import br.com.cocus.automacaococus.model.pk.PontoPk;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;

/**
 *
 * @author jsoliveira
 */
@Stateless
public class PontoDao extends DAOImplementado<PontoPk, Ponto> implements Serializable {

    public PontoDao() {
        super(Ponto.class);
    }

    public List<SelectItem> getPontosEntrada() {

        try {

            UaiCriteria uaiCriteria = UaiCriteriaFactory.createQueryCriteria(entityManager, Ponto.class);
            uaiCriteria.andEquals("tpPonto", "E");
            uaiCriteria.andEquals("inAtivo", "A");
            uaiCriteria.orderByAsc("id");

            return listToSelectItens(uaiCriteria.getResultList());

        } catch (Exception e) {

            return null;

        }

    }

    public List<SelectItem> getPontosSaida() {

        try {

            UaiCriteria uaiCriteria = UaiCriteriaFactory.createQueryCriteria(entityManager, Ponto.class);
            uaiCriteria.andEquals("tpPonto", "S");
            uaiCriteria.andEquals("inAtivo", "A");
            uaiCriteria.orderByAsc("id");

            return listToSelectItens(uaiCriteria.getResultList());

        } catch (Exception e) {

            return null;

        }

    }
    
    public List<Ponto> getPontosSaida(Long idArea) {

        try {

            UaiCriteria uaiCriteria = UaiCriteriaFactory.createQueryCriteria(entityManager, Ponto.class);
            uaiCriteria.innerJoin("modulo.area");
            uaiCriteria.andEquals("tpPonto", "S");
            uaiCriteria.andEquals("inAtivo", "A");
            uaiCriteria.andEquals("modulo.area.id", idArea);
          
            uaiCriteria.orderByAsc("id");

            return uaiCriteria.getResultList();

        } catch (Exception e) {

            return null;

        }

    }

}
