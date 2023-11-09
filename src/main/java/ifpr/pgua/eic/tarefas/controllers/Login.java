package ifpr.pgua.eic.tarefas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioLavaCar;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Login {
    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfSenha;

    @FXML
    void cadastrar() {
        App.pushScreen("CADASTRAR");
    }
    
    private RepositorioLavaCar repositorio;

    public Login(RepositorioLavaCar repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void logar() {
        String senha = tfSenha.getText();
        String login = tfLogin.getText();

        Resultado resultado = repositorio.logar( login, senha);

        Alert alert;
        
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());

            alert.showAndWait();
        }else{
            App.pushScreen("INICIAL");
        }
    }

}
