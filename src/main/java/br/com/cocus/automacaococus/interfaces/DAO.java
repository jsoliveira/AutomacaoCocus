package br.com.cocus.automacaococus.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jsoliveira
 * @param <T>
 * @param <I>
 */
public interface DAO<T, I extends Serializable> {

    public void save(I entity);

    public void remove(I entity);

    public void update(I entity);

    public I getById(T pk);

    public List<I> getAll();

    public List<I> findAll(String orderBy, Boolean asc);

    public List<I> executeJPQL(String jpql);

    public Object executeJPQLUnicoValor(String jpql);

    public List<I> buscarPorAtributo(String atributo, Object valorPesq, Integer operador, String orderBy, Boolean asc);

    public I buscarPorAtributoUnicoRet(String atributo, Object valorPesq, Integer operador);

}
