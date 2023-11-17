package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Servico;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioServico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ListarServico implements Initializable{

    private RepositorioServico repositorioServico;

    public ListarServico(RepositorioServico repositorioServico) {
        this.repositorioServico = repositorioServico;
    }

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
    private TableView<Servico> tbvServicos;

    @FXML
    private ComboBox<String> cbFiltro;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cbFiltro.getItems().clear();
        cbFiltro.getItems().addAll("Todos", "Somente os Próximos", "Efetuados", "Não Efetuados", "Pagos", "Não Pagos");
    }

}
