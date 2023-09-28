package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Login {
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfSenha;

    @FXML
    void cadastrar() {
        App.pushScreen("CADASTRAR");
    }

    @FXML
    void logar() {

    }

}
