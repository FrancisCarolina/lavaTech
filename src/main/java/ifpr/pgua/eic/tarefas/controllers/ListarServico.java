package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Servico;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioServico;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private TableColumn<Servico, String> tbcCliente;

    @FXML
    private TableColumn<Servico, String> tbcCusto;

    @FXML
    private TableColumn<Servico, String> tbcDataAgendada;

    @FXML
    private TableColumn<Servico, String> tbcPago;

    @FXML
    private TableColumn<Servico, String> tbcRealizado;

    @FXML
    private TableColumn<Servico, String> tbcTipoServico;

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
    
        //configurar a renderização das colunas

        tbcCliente.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getCliente().getNome()));
        tbcCusto.setCellValueFactory(celula->new SimpleStringProperty("R$ "+celula.getValue().getCusto()));
        tbcDataAgendada.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getDataAgendamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        tbcPago.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().isPago()?"SIM": "NÃO"));
        tbcRealizado.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().isEfetuado()?"SIM":"NÃO"));
        tbcTipoServico.setCellValueFactory(celula -> new SimpleStringProperty(celula.getValue().getTipo().getNome()));

        Resultado rs = repositorioServico.listar();

        if(rs.foiErro()){
            Alert alert = new Alert(AlertType.ERROR,rs.getMsg());
            alert.showAndWait();
            return;
        }

        List<Servico> lista = (List)rs.comoSucesso().getObj();
        
        tbvServicos.getItems().addAll(lista);
    }

}
