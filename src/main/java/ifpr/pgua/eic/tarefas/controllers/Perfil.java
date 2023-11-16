package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioLavaCar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Perfil implements Initializable {

    @FXML
    private Label lbLogin;
    @FXML
    private Label lbNome;

    private LavaCar logado;
    private RepositorioLavaCar repositorioLavaCar;

    public Perfil(LavaCar logado, RepositorioLavaCar repositorioLavaCar) {
        this.logado = logado;
        this.repositorioLavaCar = repositorioLavaCar;
    }

    @FXML
    void agendar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("CADASTRARSERVICO");
    }

    @FXML
    void listar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARSERVICO");
    }

    @FXML
    void editarPerfil(MouseEvent event) {
        App.popScreen();
        App.pushScreen("EDITARPERFIL");
    }

    @FXML
    void excluirPerfil(MouseEvent event) {
        if(logado != null){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem certeza que deseja excluir?");

            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Resultado r = repositorioLavaCar.excluirLavaCar(logado);
                    if(r.foiSucesso()){
                        Alert alertErro = new Alert(AlertType.INFORMATION, r.getMsg());
                        alertErro.showAndWait();
                        App.popScreen();
                        App.popScreen();
                    }else{
                        Alert alertErro = new Alert(AlertType.ERROR, r.getMsg());
                        alertErro.showAndWait();
                    }
                }
            });
        }
    }

    @FXML
    void clientes(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARCLIENTE");
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
        if (this.logado != null) {
            lbLogin.setText(this.logado.getLogin());
            lbNome.setText(this.logado.getNome());
        }
    }
}
