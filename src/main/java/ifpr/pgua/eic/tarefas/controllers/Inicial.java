package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Inicial {
    @FXML
    private ComboBox<?> cbFiltro;

    @FXML
    private TableView<?> tbAgendamentos;

    @FXML
    private TableColumn<?, ?> tcDia;

    @FXML
    private TableColumn<?, ?> tcHora;

    @FXML
    private TableColumn<?, ?> tcSemanal;

    @FXML
    private TableColumn<?, ?> tcServico;

    @FXML
    private TableColumn<?, ?> tcTotal;

    @FXML
    private TableView<?> tvLucro;

    @FXML
    void agendar() {
        App.pushScreen("CADASTRARSERVICO");
    }

    @FXML
    void contabilicar() {

    }

    @FXML
    void listar() {

    }

    @FXML
    void listarServico() {

    }

    @FXML
    void clientes(ActionEvent event) {
        App.pushScreen("LISTARCLIENTE");
    }

    @FXML
    void perfil() {
        App.pushScreen("PERFIL");
    }
}
