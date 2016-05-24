package br.com.cocus.automacaococus.tools;

import br.com.cocus.automacaococus.dao.ParametroDao;
import br.com.cocus.automacaococus.model.Parametros;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jsoliveira
 */
@Named(value = "enviarMensagemSocket")
@SessionScoped
public class EnviarMensagemSocket  implements Serializable{

    @Inject
    private Mensagem men;

    @Inject
    private ParametroDao pDao;

    private Socket socket = null;
    private PrintWriter print = null;
    private Scanner scanner = null;
    private final StringBuilder retorno = new StringBuilder();
    private Parametros p;

    
    @PostConstruct
    public void init() {

        if (this.p == null) {
            this.p = pDao.getById(Long.valueOf(1));
        }
    }

    public boolean enviarMsgSocket(String msg) {

   
        try {

            try {

                socket = new Socket(this.p.getIpSocket(), this.p.getPortaSocket()
                );
            } catch (IOException ex) {
                men.error("Servidor Socket não está em execução");
                return false;
            }

            print = new PrintWriter(socket.getOutputStream());

            print.println(msg);
            print.flush();

            return true;

        } catch (IOException ex) {

            ex.printStackTrace();
            return false;
        } finally {
            fechar();
        }

    }

    public boolean enviarMsgSocket(List<String> msgs) {


        try {

            try {

                socket = new Socket(this.p.getIpSocket(), this.p.getPortaSocket());
            } catch (IOException ex) {
                men.error("Servidor Socket não está em execução");
                return false;
            }

            print = new PrintWriter(socket.getOutputStream());

            for (String m : msgs) {

                print.println(m);
                print.flush();

            }

            return true;

        } catch (IOException ex) {

            ex.printStackTrace();
            return false;
        } finally {
            fechar();
        }

    }

    public String receberMensagemSocket(List<String> msgs) {

       

        try {

            try {

                socket = new Socket(this.p.getIpSocket(), this.p.getPortaSocket());
            } catch (IOException ex) {
                men.error("Servidor Socket não está em execução");
                return null;
            }

            print = new PrintWriter(socket.getOutputStream());

            for (String m : msgs) {

                print.println(m);
                print.flush();

            }

            //read the server response message
            scanner = new Scanner(socket.getInputStream());

            retorno.setLength(0);
            retorno.append((String) scanner.next());

            System.out.println(retorno.toString());

            return retorno.toString();

        } catch (IOException ex) {

            ex.printStackTrace();
            return null;
        } finally {

            fechar();
        }

    }

    private void fechar() {

        try {
            retorno.setLength(0);

            if (socket != null) {

                socket.close();
                socket = null;
            }

            if (print != null) {

                print.close();
                print = null;
            }

            if (scanner != null) {

                scanner.close();
                scanner = null;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
