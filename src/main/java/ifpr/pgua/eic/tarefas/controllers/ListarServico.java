package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ListarServico {

    // private RepositorioS
    

    // public ListarCliente(RepositorioClientes repositorioClientes) {
    //     this.repositorioClientes = repositorioClientes;
    // }

    @FXML
    private TableColumn<?, ?> tbcCliente;

    @FXML
    private TableColumn<?, ?> tbcCusto;

    @FXML
    private TableColumn<?, ?> tbcDataAgendada;

    @FXML
    private TableColumn<?, ?> tbcPago;

    @FXML
    private TableColumn<?, ?> tbcRealizado;

    @FXML
    private TableColumn<?, ?> tbcTipoServico;

    @FXML
    private TableView<?> tbvServicos;

    @FXML
    void agendar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("CADASTRARSERVICO");
    }

    @FXML
    void clientes(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARCLIENTE");
    }

    @FXML
    void editarServico(ActionEvent event) {

    }

    @FXML
    void excluirServico(ActionEvent event) {

    }

    @FXML
    void marcarComoEfetuado(ActionEvent event) {

    }

    @FXML
    void marcarComoPago(ActionEvent event) {

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
