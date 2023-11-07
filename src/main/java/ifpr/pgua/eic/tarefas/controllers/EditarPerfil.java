package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditarPerfil implements Initializable{

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSenha;

    @FXML
    void agendar(ActionEvent event) {
        App.pushScreen("CADASTRARSERVICO");
    }

    @FXML
    void excluirPerfil(MouseEvent event) {

    }

    @FXML
    void listar(ActionEvent event) {

    }

    @FXML
    void perfil(MouseEvent event) {
        App.popScreen();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfLogin.setText("lavacar123");
        tfNome.setText("Lava Car1");
        tfSenha.setText("senha123");
    }

}
