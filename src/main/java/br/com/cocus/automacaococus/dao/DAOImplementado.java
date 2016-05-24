package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.interfaces.DAO;
import br.com.cocus.automacaococus.lazy.filter.LazyFilter;
import br.com.cocus.automacaococus.tools.OperadoresJPQL;
import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jsoliveira
 * @param <T>
 * @param <I>
 */
public abstract class DAOImplementado<T, I extends Serializable> implements DAO<T, I> {

    @PersistenceContext(unitName = "Persistence")
    public EntityManager entityManager;

    private final Class<I> type;

    public UaiCriteria criteria;

    public DAOImplementado(Class<I> type) {
        this.type = type;

    }

    public void initCriteria() {

        this.criteria = null;

        this.criteria = UaiCriteriaFactory.createQueryCriteria(this.entityManager, this.type);

        //this.criteria.
    }

    public List<I> filtrados(LazyFilter filter) {

        createEasyCriteria(filter);

        this.criteria.setFirstResult(filter.getPrimeiroRegistro());
        this.criteria.setMaxResults(filter.getQuantidadeRegistros());

        if (filter.isAscendente() && filter.getPropriedadeOrdenacao() != null && !filter.getPropriedadeOrdenacao().isEmpty()) {
            this.criteria.orderByAsc(filter.getPropriedadeOrdenacao());
        } else if (filter.getPropriedadeOrdenacao() != null && !filter.getPropriedadeOrdenacao().isEmpty()) {
            this.criteria.orderByDesc(filter.getPropriedadeOrdenacao());
        } else {

            this.criteria.orderByAsc(filter.getPropriedadePadraoOrdenacao());
        }

        return this.criteria.getResultList();

    }

    public Long quantidadeFiltrados(LazyFilter filter) {
        createEasyCriteria(filter);

        return this.criteria.countRegularCriteria();
    }

    private void createEasyCriteria(LazyFilter filter) {

        initCriteria();

        if (filter.getPropriedadePesq() == null || filter.getPropriedadePesq().isEmpty()) {

            return;
        }

        try {

            Field f = type.getDeclaredField(filter.getPropriedadePesq());
            switch (f.getType().getName()) {

                case ("java.util.Date"): {

                    try {

                        if (f.isAnnotationPresent(Temporal.class)) {

                            if (f.getAnnotation(Temporal.class).value().equals(TemporalType.TIMESTAMP)) {

                                //            f.set(obj, new Date(r.getTimestamp(f.getAnnotation(Column.class).name().trim()).getTime()));
                            }

                            if (f.getAnnotation(Temporal.class).value().equals(TemporalType.DATE)) {

                                //           f.set(obj, new Date(r.getDate(f.getAnnotation(Column.class).name().trim()).getTime()));
                            }

                        }

                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                }
                break;

                case ("java.lang.String"): {

                    this.criteria.andStringLike(false, filter.getPropriedadePesq(), "%" + filter.getDescricao() + "%");

                }
                break;

                case ("java.lang.Integer"): {

                    this.criteria.andEquals(filter.getPropriedadePesq(), filter.getDescricao());

                }
                break;

                case ("java.lang.Long"): {

                    this.criteria.andEquals(filter.getPropriedadePesq(), filter.getDescricao());
                }
                break;

                case ("char"): {

                    this.criteria.andEquals(filter.getPropriedadePesq(), filter.getDescricao());

                }
                break;

            }

        } catch (NoSuchFieldException ex) {
            Logger.getLogger(DAOImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DAOImplementado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param entity Objeto a ser persistido
     */
    @Override
    public void save(I entity) {
        this.entityManager.persist(entity);
    }

    /**
     *
     * @param entity Objeto a ser removido
     */
    @Override
    public void remove(I entity) {

        this.entityManager.remove(this.entityManager.merge(entity));
    }

    /**
     *
     * @param entity Objeto a ser Atualizado
     */
    @Override
    public void update(I entity) {
        this.entityManager.merge(entity);
    }

    /**
     *
     * @param pk Valor da chave primaria a ser pesquisa
     * @return retorna objeto do tipo
     *
     */
    @Override
    public I getById(T pk) {
        return (I) this.entityManager.find(this.type, pk);
    }

    /**
     *
     * @return Retorna todos os objetos do tipoo
     *
     */
    @Override
    public List<I> getAll() {

        initCriteria();
        return this.criteria.getResultList();
    }

    /**
     *
     *
     * @param orderBy Campo de ordenação
     * @param asc True- Ascendente False-Descendente
     * @return retorna uma de entidades pesquisada
     */
    @Override
    public List<I> findAll(String orderBy, Boolean asc) {

        try {

            initCriteria();
            if (asc) {
                this.criteria.orderByAsc(orderBy);
            } else {
                this.criteria.orderByDesc(orderBy);
            }

            return this.criteria.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao consultar todos =>" + e);
            return new ArrayList<>();
        }
    }

    public List<SelectItem> getSelectItens(String orderBy, Boolean asc) {

        try {

            List<SelectItem> ret = new ArrayList<>();

            for (I i : findAll(orderBy, asc)) {

                ret.add(new SelectItem(i));

            }
            return ret;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public List<SelectItem> getSelectItens(String atributo, Object valorPesq, Integer operador, String orderBy, Boolean asc) {

        try {

            List<SelectItem> ret = new ArrayList<>();

            for (I i : buscarPorAtributo(atributo, valorPesq, operador, orderBy, asc)) {

                ret.add(new SelectItem(i));

            }
            return ret;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public List<SelectItem> getSelectItens(List<String> atributos, List<Object> valoresPesq, List<Integer> operadores, String orderBy, Boolean asc) {

        try {

            List<SelectItem> ret = new ArrayList<>();

            for (I i : buscarPorAtributo(atributos, valoresPesq, operadores, orderBy, asc)) {

                ret.add(new SelectItem(i));

            }
            return ret;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public List<SelectItem> listToSelectItens(List<I> list) {

        try {

            List<SelectItem> ret = new ArrayList<>();

            for (I i : list) {
                ret.add(new SelectItem(i));
            }
            return ret;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    /**
     *
     * @param jpql Consulta JPQL
     * @return retorna uma lita de entidades pesquisadas
     */
    @Override
    public List<I> executeJPQL(String jpql) {

        try {

            return this.entityManager.createQuery(jpql).getResultList();

        } catch (Exception e) {

            System.out.println("Erro ao executar jpql =>" + e);
            return new ArrayList<>();

        }
    }

    /**
     *
     * @param jpql -Executa o jpql retornando um unico valor que deve ser feito
     * o cast
     * @return -Retorna Objeto que foi pesquisado
     */
    @Override
    public Object executeJPQLUnicoValor(String jpql) {

        try {

            return this.entityManager.createQuery(jpql).getSingleResult();

        } catch (Exception e) {

            System.out.println("Erro ao executar jpql =>" + e);
            return null;

        }
    }

    /**
     *
     * @param atributo Atributo a ser pesquisado
     * @param valorPesq Valor a ser pesquisado no atributo
     * @param operador Operador logico da consulta
     * @param orderBy Campo de ordenação
     * @param asc True- Ascendente False-Descendente
     * @return -Retorna uma lista de entidades pesquisada
     */
    @Override
    public List<I> buscarPorAtributo(String atributo, Object valorPesq, Integer operador, String orderBy, Boolean asc) {

        try {
            initCriteria();

            if (operador.equals(OperadoresJPQL.equals.getOperador())) {

                this.criteria.andEquals(atributo, valorPesq);

            }

            if (operador.equals(OperadoresJPQL.notEquals.getOperador())) {

                this.criteria.andNotEquals(atributo, valorPesq);

            }

            if (operador.equals(OperadoresJPQL.like.getOperador())) {

                this.criteria.andStringLike(atributo, "%" + (String) valorPesq + "%");

            }

            if (operador.equals(OperadoresJPQL.notLike.getOperador())) {

                this.criteria.andStringNotLike(atributo, "%" + (String) valorPesq + "%");

            }

            if (asc) {
                this.criteria.orderByAsc(orderBy);
            } else {
                this.criteria.orderByDesc(orderBy);
            }

            return this.criteria.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao consultar todos =>" + e);
            return new ArrayList<>();
        }
    }

    public List<I> buscarPorAtributo(List<String> atributos, List<Object> valoresPesq, List<Integer> operadores, String orderBy, Boolean asc) {

        try {
            initCriteria();

            for (int i = 0; i <= atributos.size(); i++) {

                if (operadores.get(i).equals(OperadoresJPQL.equals.getOperador())) {

                    this.criteria.andEquals(atributos.get(i), valoresPesq.get(i));

                }

                if (operadores.get(i).equals(OperadoresJPQL.notEquals.getOperador())) {

                    this.criteria.andNotEquals(atributos.get(i), valoresPesq.get(i));

                }

                if (operadores.get(i).equals(OperadoresJPQL.like.getOperador())) {

                    this.criteria.andStringLike(atributos.get(i), "%" + (String) valoresPesq.get(i) + "%");

                }

                if (operadores.get(i).equals(OperadoresJPQL.notLike.getOperador())) {

                    this.criteria.andStringNotLike(atributos.get(i), "%" + (String) valoresPesq.get(i) + "%");

                }
            }

            if (asc) {
                this.criteria.orderByAsc(orderBy);
            } else {
                this.criteria.orderByDesc(orderBy);
            }

            return this.criteria.getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao consultar todos =>" + e);
            return new ArrayList<>();
        }
    }

    @Override
    public I buscarPorAtributoUnicoRet(String atributo, Object valorPesq, Integer operador) {

        try {
            initCriteria();

            if (operador.equals(OperadoresJPQL.equals.getOperador())) {

                this.criteria.andEquals(atributo, valorPesq);

            }

            if (operador.equals(OperadoresJPQL.notEquals.getOperador())) {

                this.criteria.andNotEquals(atributo, valorPesq);

            }

            if (operador.equals(OperadoresJPQL.like.getOperador())) {

                this.criteria.andStringLike(atributo, "%" + (String) valorPesq + "%");

            }

            if (operador.equals(OperadoresJPQL.notLike.getOperador())) {

                this.criteria.andStringNotLike(atributo, "%" + (String) valorPesq + "%");

            }

            return (I) this.criteria.getSingleResult();
        } catch (Exception e) {
            System.out.println("Erro ao consultar todos =>" + e);
            return null;
        }

    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
