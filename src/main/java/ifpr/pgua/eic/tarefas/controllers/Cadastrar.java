package ifpr.pgua.eic.tarefas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioLavaCar;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Cadastrar {

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSenha;

    private RepositorioLavaCar repositorio;

    public Cadastrar(RepositorioLavaCar repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void cadastrar() {
        String nome = tfNome.getText();
        String senha = tfSenha.getText();
        String login = tfLogin.getText();

        Resultado resultado = repositorio.cadastrarLavacar(nome, login, senha);

        Alert alert;
        
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();
    }

    @FXML
    void cancelar() {
        App.popScreen();
    }
}
