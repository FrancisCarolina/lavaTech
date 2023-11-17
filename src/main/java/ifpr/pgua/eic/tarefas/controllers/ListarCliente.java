package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioClientes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ListarCliente implements Initializable{

    @FXML
    private ListView<Cliente> lstClientes;

    @FXML
    private TextArea taDetalhes;

    private RepositorioClientes repositorioClientes;
    private LavaCar logado;
    

    public ListarCliente(RepositorioClientes repositorioClientes, LavaCar logado) {
        this.repositorioClientes = repositorioClientes;
        this.logado = logado;
    }

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
        App.popScreen();
        App.pushScreen("EXCLUIRCLIENTE");
    }

    @FXML
    void editarCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("EDITARCLIENTE");
    }

    @FXML
    void listar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARSERVICO");

    }

    @FXML
    void perfil(MouseEvent event) {
        App.popScreen();
        App.pushScreen("PERFIL");
    }

    @FXML
    void mostrarDetalhes(MouseEvent event) {
        Cliente c = lstClientes.getSelectionModel().getSelectedItem();
        if(c != null){
            taDetalhes.clear();
            taDetalhes.appendText("Nome: "+c.getNome());
            taDetalhes.appendText("\nContato: "+c.getContato());
        }
    }
    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        taDetalhes.setEditable(false);
        lstClientes.getItems().clear();
        Resultado r1 = repositorioClientes.listarClientes(logado);

        if(r1.foiSucesso()){
            List<Cliente> list = (List) r1.comoSucesso().getObj();
            lstClientes.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
    }

}
