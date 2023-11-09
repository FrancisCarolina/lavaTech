package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CadastrarNovoCliente {
    @FXML
    private TextField tfContato;

    @FXML
    private TextField tfNomeCliente;

    @FXML
    void cadastrar(ActionEvent event) {

    }

    @FXML
    void agendar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("CADASTRARSERVICO");
    }
    @FXML
    void inicial(MouseEvent event) {
        App.popScreen();
        App.pushScreen("INICIAL");
    }

    @FXML
    void listar(ActionEvent event) {

    }

    @FXML
    void perfil(MouseEvent event) {
        App.popScreen();
        App.pushScreen("PERFIL");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
}
