package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.entities.Tipo;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioClientes;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioServico;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CadastrarServico implements Initializable {

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

    private RepositorioServico repositorioServico;

    private RepositorioTipo repositorioTipo;

    private LavaCar logado;

    private Inicial inicial;

    public CadastrarServico(RepositorioClientes repositorioClientes, RepositorioTipo repositorioTipo,
            RepositorioServico repositorioServico, LavaCar logado) {
        this.repositorioClientes = repositorioClientes;
        this.repositorioTipo = repositorioTipo;
        this.repositorioServico = repositorioServico;
        this.logado = logado;
    }
     public CadastrarServico(RepositorioClientes repositorioClientes, RepositorioTipo repositorioTipo,
            RepositorioServico repositorioServico, LavaCar logado, Inicial inicial) {
        this.repositorioClientes = repositorioClientes;
        this.repositorioTipo = repositorioTipo;
        this.repositorioServico = repositorioServico;
        this.logado = logado;
        this.inicial = inicial;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String custo = tfCusto.getText();
        Cliente c = lstNomeCliente.getSelectionModel().getSelectedItem();
        LocalDate data = dpDataRealizacao.getValue();
        Tipo tipo = cbTipo.getSelectionModel().getSelectedItem();

        Resultado resultado = repositorioServico.cadastrarServico(custo, c, data, tipo, logado);

        Alert alert;

        if (resultado.foiErro()) {
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        } else {
            tfCusto.clear();
            dpDataRealizacao.getEditor().clear();
            cbTipo.getSelectionModel().clearSelection();
            lstNomeCliente.getSelectionModel().clearSelection();
            taClientes.clear();
            tfPesquisarCliente.clear();
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();
    }

    @FXML
    void pesquisar(KeyEvent event) {
        lstNomeCliente.getItems().clear();
        Resultado r1 = repositorioClientes.listarClientes(logado);

        if (r1.foiSucesso()) {
            List<Cliente> clientes = (List) r1.comoSucesso().getObj();

            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getNome().toLowerCase().contains(tfPesquisarCliente.getText().toLowerCase())) {
                    lstNomeCliente.getItems().add(clientes.get(i));
                }
            }
        }
    }

    @FXML
    void listar(ActionEvent event) {
        App.popScreen();
        App.pushScreen("LISTARSERVICO");
    }

    @FXML
    void novoCliente(ActionEvent event) {
        App.popScreen();
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
        if(inicial != null){
            inicial.atualizarDados();
        }
        App.popScreen();
    }

    @FXML
    void visualizarCliente(MouseEvent event) {
        Cliente c = lstNomeCliente.getSelectionModel().getSelectedItem();
        if (c != null) {
            taClientes.clear();
            taClientes.appendText("Nome: " + c.getNome());
            taClientes.appendText("\nContato: " + c.getContato());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cbTipo.getItems().clear();
        taClientes.setEditable(false);
        lstNomeCliente.getItems().clear();
        Resultado r1 = repositorioClientes.listarClientes(logado);

        if (r1.foiSucesso()) {
            List<Cliente> list = (List) r1.comoSucesso().getObj();
            lstNomeCliente.getItems().addAll(list);
        } else {
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }

        Resultado r2 = repositorioTipo.listarTipos();
        if (r2.foiSucesso()) {
            List<Tipo> list = (List) r2.comoSucesso().getObj();
            cbTipo.getItems().addAll(list);
        } else {
            Alert alert = new Alert(AlertType.ERROR, r2.getMsg());
            alert.showAndWait();
        }
    }

}
