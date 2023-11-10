package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private ListView<Cliente> lstNomeCliente;

    @FXML
    private TextArea taClientes;

    @FXML
    private TextField tfCusto;

    @FXML
    private TextField tfPesquisarCliente;

    private RepositorioClientes repositorioClientes;

    public CadastrarServico(RepositorioClientes repositorioClientes) {
        this.repositorioClientes = repositorioClientes;
    }

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
    @FXML
    void visualizarCliente(MouseEvent event) {
        Cliente c = lstNomeCliente.getSelectionModel().getSelectedItem();
        if(c != null){
            taClientes.clear();
            taClientes.appendText("Nome: "+c.getNome());
            taClientes.appendText("\nContato: "+c.getContato());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        taClientes.setEditable(false);
        lstNomeCliente.getItems().clear();
        Resultado r1 = repositorioClientes.listarClientes();

        if(r1.foiSucesso()){
            List<Cliente> list = (List) r1.comoSucesso().getObj();
            lstNomeCliente.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
    }

}

