package ifpr.pgua.eic.tarefas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CadastrarNovoCliente {

    private RepositorioClientes repositorio;
    private LavaCar logado;

    public CadastrarNovoCliente(RepositorioClientes repositorio, LavaCar logado) {
        this.repositorio = repositorio;
        this.logado = logado;
    }

    @FXML
    private TextField tfNomeCliente;

    @FXML
    private TextField tfContato;

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNomeCliente.getText();
        String contato = tfContato.getText();

        Resultado resultado = repositorio.cadastrarCliente(nome, contato, logado);

        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            tfContato.clear();
            tfNomeCliente.clear();
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();
    }
    @FXML
    void editarCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("EDITARCLIENTE");
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
        App.popScreen();
        App.pushScreen("LISTARSERVICO");
    }

    @FXML
    void clientes(ActionEvent event) {
        App.popScreen();
        App.pushScreen("CADASTRARCLIENTE");
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
    void excluirCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("EXCLUIRCLIENTE");
    }

    @FXML
    void listarCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("LISTARCLIENTE");
    }
}
