package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Cadastrar {

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSenha;

    @FXML
    void cadastrar() {

    }

    @FXML
    void cancelar() {
        App.popScreen();
    }
}
