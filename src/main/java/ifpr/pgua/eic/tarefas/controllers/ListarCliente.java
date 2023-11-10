package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ListarCliente {

    @FXML
    private ListView<Cliente> lstClientes;

    @FXML
    private TextArea taDetalhes;

    @FXML
    void agendar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("CADASTRARSERVICO");
    }

    @FXML
    void cadastrarCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("CADASTRARCLIENTE");
    }

    @FXML
    void excluirCliente(MouseEvent event) {

    }

    @FXML
    void listar(ActionEvent event) {

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

}
