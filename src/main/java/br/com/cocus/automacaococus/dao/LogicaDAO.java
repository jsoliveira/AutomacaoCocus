/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cocus.automacaococus.dao;

import br.com.cocus.automacaococus.model.LigacaoEntradaSaida;
import br.com.cocus.automacaococus.model.Logica;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author junio
 */
@Stateless
public class LogicaDAO extends DAOImplementado<Long, Logica> implements Serializable{

    public LogicaDAO() {
        super(Logica.class);

    }
    
}
