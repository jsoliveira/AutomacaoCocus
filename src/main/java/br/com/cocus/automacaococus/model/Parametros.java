package br.com.cocus.automacaococus.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jsoliveira
 */
@Entity
@Table(name = "cad_parametros")
public class Parametros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_parametro")
    private Long id;

    @Column(name = "ds_ip_socket")
    private String ipSocket;

    @Column(name = "nr_porta_socket")
    private Integer portaSocket;
    
    @Column(name = "dt_transacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTransacao;

    public Parametros() {
    }

    public Parametros(Long id, String ipSocket, Integer portaSocket, Date dtTransacao) {
        this.id = id;
        this.ipSocket = ipSocket;
        this.portaSocket = portaSocket;
        this.dtTransacao = dtTransacao;
    }

 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpSocket() {
        return ipSocket;
    }

    public void setIpSocket(String ipSocket) {
        this.ipSocket = ipSocket;
    }
    
       public Integer getPortaSocket() {
        return portaSocket;
    }

    public void setPortaSocket(Integer portaSocket) {
        this.portaSocket = portaSocket;
    }

    public Date getDtTransacao() {
        return dtTransacao;
    }

    public void setDtTransacao(Date dtTransacao) {
        this.dtTransacao = dtTransacao;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parametros other = (Parametros) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parametros{" + "id=" + id + ", ipSocket=" + ipSocket + '}';
    }

}
