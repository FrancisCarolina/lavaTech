package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Perfil implements Initializable{

    @FXML
    private Label lbLogin;
    @FXML
    private Label lbNome;

    private LavaCar logado;

    public Perfil(LavaCar logado) {
        this.logado = logado;
    }

    @FXML
    void agendar(ActionEvent event) {
        App.popScreen();
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
    void sair(MouseEvent event) {
        App.setLogado(null);
        App.popScreen();
        App.popScreen();
    }

    @FXML
    void voltar() {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(this.logado!= null){
            lbLogin.setText(this.logado.getLogin());
            lbNome.setText(this.logado.getNome());
        }
    }
}
