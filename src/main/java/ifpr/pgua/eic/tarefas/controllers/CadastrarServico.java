package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CadastrarServico {

    @FXML
    private ComboBox<?> cbTipo;

    @FXML
    private DatePicker dpDataRealizacao;

    @FXML
    private ListView<?> lstNomeCliente;

    @FXML
    private TextArea taClientes;

    @FXML
    private TextField tfCusto;

    @FXML
    private TextField tfPesquisarCliente;

    @FXML
    void cadastrar(ActionEvent event) {

    }

    @FXML
    void listar(ActionEvent event) {

    }

    @FXML
    void novoCliente(ActionEvent event) {
        App.pushScreen("CADASTRARCLIENTE");
    }

    @FXML
    void clientes(ActionEvent event) {

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
