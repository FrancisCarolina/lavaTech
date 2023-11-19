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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditarPerfil implements Initializable {

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSenha;

    private LavaCar logado;
    private RepositorioLavaCar repositorioLavaCar;

    public EditarPerfil(LavaCar logado, RepositorioLavaCar repositorioLavaCar) {
        this.logado = logado;
        this.repositorioLavaCar = repositorioLavaCar;
    }

    @FXML
    void agendar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("CADASTRARSERVICO");
    }

    @FXML
    void excluirPerfil(MouseEvent event) {
        if (logado != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem certeza que deseja excluir?");

            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Resultado r = repositorioLavaCar.excluirLavaCar(logado);
                    if (r.foiSucesso()) {
                        Alert alertErro = new Alert(AlertType.INFORMATION, r.getMsg());
                        alertErro.showAndWait();
                        App.popScreen();
                        App.popScreen();
                    } else {
                        Alert alertErro = new Alert(AlertType.ERROR, r.getMsg());
                        alertErro.showAndWait();
                    }
                }
            });
        }
    }

    @FXML
    void sair(MouseEvent event) {
        App.setLogado(null);
        App.popScreen();
        App.popScreen();
    }

    @FXML
    void listar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARSERVICO");
    }

    @FXML
    void clientes(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARCLIENTE");
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

    @FXML
    void confirmar(ActionEvent event) {
        LavaCar lc = new LavaCar(logado.getId(), tfNome.getText(), tfLogin.getText(), tfSenha.getText());
        Resultado resultado = repositorioLavaCar.editarLavaCar(lc);

        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            App.setLogado(lc);
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfLogin.setText(logado.getLogin());
        tfNome.setText(logado.getNome());
        tfSenha.setText(logado.getSenha());
    }

}
