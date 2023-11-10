package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CadastrarServico implements Initializable{

    
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
        taClientes.setEditable(false);
    }

}

