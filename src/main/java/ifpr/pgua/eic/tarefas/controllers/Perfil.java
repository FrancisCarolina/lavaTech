package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Perfil {


    @FXML
    void agendar(ActionEvent event) {
        App.pushScreen("CADASTRARSERVICO");
    }

    @FXML
    void listar(ActionEvent event) {

    }

    @FXML
    void editarPerfil(MouseEvent event) {
        App.pushScreen("EDITARPERFIL");
    }

    @FXML
    void excluirPerfil(MouseEvent event) {

    }

    @FXML
    void voltar() {
        App.popScreen();
    }
}
