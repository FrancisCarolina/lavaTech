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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditarCliente implements Initializable{

    @FXML
    private ListView<Cliente> lstClientes;

    @FXML
    private TextField tfContato;

    @FXML
    private TextField tfNome;

    private RepositorioClientes repositorioClientes;
    private LavaCar logado;
    

    public EditarCliente(RepositorioClientes repositorioClientes, LavaCar logado) {
        this.repositorioClientes = repositorioClientes;
        this.logado =logado;
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
    void editar(ActionEvent event) {
        Cliente c = lstClientes.getSelectionModel().getSelectedItem();
        Cliente novo = new Cliente(c.getId(), tfNome.getText(), tfContato.getText(), logado);
        Resultado resultado = repositorioClientes.editarCliente(novo);

        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            initialize(null, null);
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();
    }

    @FXML
    void listar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARSERVICO");
    }

    @FXML
    void listarCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("LISTARCLIENTE");
    }

    @FXML
    void mostrarDetalhes(MouseEvent event) {
        Cliente c = lstClientes.getSelectionModel().getSelectedItem();
        if(c != null){
            tfNome.setText(c.getNome());
            tfContato.setText(c.getContato());
        }
    }

    @FXML
    void perfil(MouseEvent event) {
        App.popScreen();
        App.pushScreen("PERFIL");
    }
    @FXML
    void excluirCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("EXCLUIRCLIENTE");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
