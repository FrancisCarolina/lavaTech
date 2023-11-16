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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ExcluirCliente implements Initializable {

    @FXML
    private ListView<Cliente> lstClientes;

    @FXML
    private TextArea taDetalhes;

    
    private RepositorioClientes repositorioClientes;
    

    public ExcluirCliente(RepositorioClientes repositorioClientes) {
        this.repositorioClientes = repositorioClientes;
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
    void excluir(ActionEvent event) {
        Cliente c = lstClientes.getSelectionModel().getSelectedItem();
        if(c != null){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem certeza que deseja excluir?");

            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Resultado r = repositorioClientes.excluirCliente(c);
                    if(r.foiSucesso()){
                        Alert alertErro = new Alert(AlertType.INFORMATION, r.getMsg());
                        alertErro.showAndWait();
                        initialize(null, null);
                    }else{
                        Alert alertErro = new Alert(AlertType.ERROR, r.getMsg());
                        alertErro.showAndWait();
                    }
                }
            });
        }
    }

    @FXML
    void listar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARSERVICO");
    }
    @FXML
    void editarCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("EDITARCLIENTE");
    }

    @FXML
    void listarCliente(MouseEvent event) {
        App.popScreen();
        App.pushScreen("LISTARCLIENTE");
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
        Resultado r1 = repositorioClientes.listarClientes();

        if(r1.foiSucesso()){
            List<Cliente> list = (List) r1.comoSucesso().getObj();
            lstClientes.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
    }

}
