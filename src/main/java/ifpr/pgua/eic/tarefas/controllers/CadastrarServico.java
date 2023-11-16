package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.Tipo;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioClientes;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTipo;
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
    private ComboBox<Tipo> cbTipo;

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

    private RepositorioTipo repositorioTipo;

    public CadastrarServico(RepositorioClientes repositorioClientes,  RepositorioTipo repositorioTipo) {
        this.repositorioClientes = repositorioClientes;
        this.repositorioTipo = repositorioTipo;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        /*String custo = tfCusto.getText();

        Resultado resultado = repositorio.cadastrarCliente(nome, contato);

        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            tfContato.clear();
            tfNomeCliente.clear();
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();*/
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
        App.popScreen();
        App.pushScreen("LISTARCLIENTE");
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
        cbTipo.getItems().clear();
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

        Resultado r2 = repositorioTipo.listarTipos();
        if(r2.foiSucesso()){
            List<Tipo> list = (List) r2.comoSucesso().getObj();
            cbTipo.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR, r2.getMsg());
            alert.showAndWait();
        }
    }

}
