package br.com.cocus.automacaococus.bean;

import br.com.cocus.automacaococus.dao.ModuloDao;
import br.com.cocus.automacaococus.model.JSON.Dispositivo;
import br.com.cocus.automacaococus.model.Modelo;
import br.com.cocus.automacaococus.model.Modulo;
import br.com.cocus.automacaococus.tools.EnviarMensagemSocket;
import br.com.cocus.automacaococus.tools.Mensagem;
import br.com.cocus.automacaococus.tools.MensagensServidor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jsoliveira
 */
@SessionScoped
@Named
public class ModuloBean implements Serializable {

    @Inject
    private Mensagem men;

    @Inject
    private ModuloDao mDao;

    @Inject
    private LoginBean loginBean;

    @Inject
    private ModeloBean modeloBean;

    @Inject
    private EnviarMensagemSocket enviarMensagemSocket;

    private Modulo m;

    private List<SelectItem> modulosConverter;

    private List<Modulo> modulos = new ArrayList<>();

    private List<Dispositivo> disposistivosOnline = new ArrayList<>();

    private Dispositivo dispositivo;

    private final Gson gson;

    public ModuloBean() {
        this.gson = new Gson();
    }

    public void cadastrar() {

        Modelo modelo = modeloBean.getDesc(this.dispositivo.getModulo());

        if (modelo == null) {

            men.error(this.dispositivo.getModulo() + " nâo cadastrado");
            return;
        }

        this.m.setDataTransacao(new Date());
        this.m.setInAtivo('A');
        this.m.setId(new Long(this.dispositivo.getID()));
        this.m.setModelo(modelo);

    }

    public void save() {

        try {

            if (mDao.getById(this.m.getId()) == null) {

                mDao.save(this.m);
                men.info("Modulo Salva com Sucesso!!");

            } else {

                mDao.update(this.m);
                men.info("Modulo Alterada com Sucesso!!");

            }
            limpar();

        } catch (Exception e) {
            e.printStackTrace();

            men.error("Erro ao Salvar Modulo");

        }

    }

    public void del() {

        try {
            mDao.remove(this.m);
            limpar();
            men.info("Modulo Excluida com Sucesso!!");

        } catch (Exception e) {

            men.error("Erro ao Excluir Modulo");
        }

    }

    public void limpar() {

        this.m = new Modulo();
        this.modulos = null;
        this.modulosConverter = null;

    }

    public void addNovoDispositivo() {

        this.m = new Modulo();
        this.modulos = null;
        carregarDispositivos();

    }

    public List<Dispositivo> getDisposistivosOnline() {

        return this.disposistivosOnline;

    }

    public void carregarDispositivos() {
        try {

            ArrayList<String> men = new ArrayList<>();

            men.add(MensagensServidor.adicionarComando.getMensagem());
            men.add(MensagensServidor.dispositivosOnline.getMensagem());

            String message = enviarMensagemSocket.receberMensagemSocket(men);

            List<Dispositivo> disps
                    = (List<Dispositivo>) gson.fromJson(message,
                            new TypeToken<List<Dispositivo>>() {
                            }.getType());

            System.out.println(disps);

            for (Dispositivo d : disps) {

                d.setInCadastrado(inCadastrado(d.getID()));

            }

            this.disposistivosOnline = disps;

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    public void onRowEdit(RowEditEvent event) {

        this.m = (Modulo) event.getObject();
        save();

    }

    public boolean inCadastrado(Integer id) {

        for (Modulo modulo : getModulos()) {
            if (modulo.getId().intValue() == id) {
                return true;
            }
        }

        return false;
    }

    public Modulo getM() {
        return m;
    }

    public void setM(Modulo m) {
        this.m = m;
    }

     public List<Modulo> getModulos() {

        if (this.modulos == null || this.modulos.isEmpty()) {

            this.modulos = mDao.findAll("id", Boolean.TRUE);
        }

        return this.modulos;
    }

    public void setModulos(List<Modulo> modelos) {
        this.modulos = modelos;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public List<SelectItem> getModulosConverter() {

        if (this.modulosConverter == null || this.modulosConverter.isEmpty()) {
            this.modulosConverter = mDao.getSelectItens("id", Boolean.TRUE);
            this.modulosConverter.add(0, new SelectItem(null, "Selecione"));
        }
        return this.modulosConverter;
    }

    public void setModulosConverter(List<SelectItem> modulosConverter) {
        this.modulosConverter = modulosConverter;
    }

}
