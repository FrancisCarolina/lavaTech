package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.entities.Servico;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Inicial implements Initializable{
    @FXML
    private ComboBox<String> cbFiltro;

    @FXML
    private TableView<Servico> tbAgendamentos;

    @FXML
    private TableColumn<Servico, String> tcDia;

    @FXML
    private TableColumn<Servico, String> tcHora;

    @FXML
    private TableColumn<Servico, String> tcServico;

    @FXML
    private TextField tfDiaria;

    @FXML
    private TextField tfSemanal;

    @FXML
    private TextField tfTotal;

    private RepositorioServico repositorioServico;
    private LavaCar logado;
    private List<String> meses = Arrays.asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
        

    public Inicial(RepositorioServico repositorioServico, LavaCar logado) {
        this.repositorioServico = repositorioServico;
        this.logado = logado;
    }

    @FXML
    void agendar() {
        App.pushScreen("CADASTRARSERVICO");
    }

    @FXML
    void totalizar() {
        App.pushScreen("TOTALIZAR");
    }

    @FXML
    void listar() {
        App.pushScreen("LISTARSERVICO");
    }   

    @FXML
    void listarServico() {
        App.pushScreen("LISTARSERVICO");
    }

    @FXML
    void clientes(ActionEvent event) {
        App.pushScreen("LISTARCLIENTE");
    }

    @FXML
    void perfil() {
        App.pushScreen("PERFIL");
    }

    @FXML
    void mudarMes(ActionEvent event) {
        tfDiaria.clear();
        tfSemanal.clear();
        tfTotal.clear();
        int numeroMes = cbFiltro.getSelectionModel().getSelectedIndex()+1;
        Resultado r1 = repositorioServico.calcularMedia(numeroMes, logado);

        if (r1.foiSucesso()) {
            List<Float> list = (List) r1.comoSucesso().getObj();
            tfTotal.appendText(formatarCusto("R$ "+list.get(0)));
            tfSemanal.appendText(formatarCusto("R$ "+list.get(1)));
            tfDiaria.appendText(formatarCusto("R$ "+list.get(2)));
        } else {
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
    }


    public String formatarCusto(String valorString) {

        String valorSemSimbolo = valorString.replaceAll("[^\\d.,]", "");

        try {
            double valorNumerico = NumberFormat.getNumberInstance(Locale.US).parse(valorSemSimbolo).doubleValue();

            NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            String valorFormatado = formato.format(valorNumerico);

            return valorFormatado;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return valorString;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfDiaria.setEditable(false);
        tfSemanal.setEditable(false);
        tfTotal.setEditable(false);

        cbFiltro.getItems().clear();
        cbFiltro.getItems().addAll(meses);
        LocalDate dataAtual = LocalDate.now();
        int numeroMes = dataAtual.getMonthValue();
        cbFiltro.getSelectionModel().select(meses.get(numeroMes-1));

        Resultado r1 = repositorioServico.calcularMedia(numeroMes, logado);

        if (r1.foiSucesso()) {
            List<Float> list = (List) r1.comoSucesso().getObj();
            tfTotal.appendText(formatarCusto("R$ "+list.get(0)));
            tfSemanal.appendText(formatarCusto("R$ "+list.get(1)));
            tfDiaria.appendText(formatarCusto("R$ "+list.get(2)));
        } else {
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
    }
}
