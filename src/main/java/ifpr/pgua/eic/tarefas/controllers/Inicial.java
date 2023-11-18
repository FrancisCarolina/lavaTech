package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Servico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfDiaria.setEditable(false);
        tfSemanal.setEditable(false);
        tfTotal.setEditable(false);

        cbFiltro.getItems().clear();
        List<String> meses = Arrays.asList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
        cbFiltro.getItems().addAll(meses);
        LocalDate dataAtual = LocalDate.now();
        int numeroMes = dataAtual.getMonthValue();
        cbFiltro.getSelectionModel().select(meses.get(numeroMes-1));
    }
}
