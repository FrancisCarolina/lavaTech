package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.entities.Servico;
import ifpr.pgua.eic.tarefas.model.entities.Tipo;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioClientes;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioServico;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EditarServico implements Initializable {

  private RepositorioClientes repositorioClientes;
  private RepositorioServico repositorioServico;
  private RepositorioTipo repositorioTipo;
  private LavaCar logado;
  private Servico servico;

  public EditarServico(RepositorioServico repositorioServico, RepositorioClientes repositorioClientes,
      LavaCar logado, RepositorioTipo repositorioTipo) {
    this.repositorioServico = repositorioServico;
    this.logado = logado;
    this.repositorioClientes = repositorioClientes;
    this.repositorioTipo = repositorioTipo;
  }

  public EditarServico(RepositorioServico repositorioServico, RepositorioClientes repositorioClientes,
      LavaCar logado, Servico servico, RepositorioTipo repositorioTipo) {
    this.repositorioServico = repositorioServico;
    this.logado = logado;
    this.repositorioClientes = repositorioClientes;
    this.servico = servico;
    this.repositorioTipo = repositorioTipo;
  }

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
  void editar(ActionEvent event) {
    String custo = tfCusto.getText();
    Cliente c = lstNomeCliente.getSelectionModel().getSelectedItem();
    LocalDate data = dpDataRealizacao.getValue();
    Tipo tipo = cbTipo.getSelectionModel().getSelectedItem();

    Resultado resultado;
    if (servico != null) {
      boolean efetuado = servico.isEfetuado();
      boolean pago = servico.isPago();
      resultado = repositorioServico.atualizarServico(servico.getId(), c, tipo, custo, data, logado, efetuado, pago);
      Alert alert;
      if (resultado.foiSucesso()) {
        alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
      } else {
        alert = new Alert(AlertType.ERROR, resultado.getMsg());
      }
      alert.showAndWait();
    }

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
  void visualizarCliente(MouseEvent event) {
    Cliente c = lstNomeCliente.getSelectionModel().getSelectedItem();
    if (c != null) {
      taClientes.clear();
      taClientes.appendText("Nome: " + c.getNome());
      taClientes.appendText("\nContato: " + c.getContato());
    }
  }

  @FXML
  void voltar(ActionEvent event) {
    App.popScreen();

  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    cbTipo.getItems().clear();
    taClientes.setEditable(false);
    lstNomeCliente.getItems().clear();
    int index = -1;
    Resultado r1 = repositorioClientes.listarClientes(logado);

    if (r1.foiSucesso()) {
      List<Cliente> list = (List) r1.comoSucesso().getObj();
      lstNomeCliente.getItems().addAll(list);
      if (servico != null) {
        for (int i = 0; i < list.size(); i++) {
          if (list.get(i).getId() == servico.getCliente().getId()) {
            index = i;
          }
        }
      }
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
    if (servico != null) {
      cbTipo.getSelectionModel().select(servico.getTipo());
      tfCusto.setText(servico.getCusto() + "");
      dpDataRealizacao.setValue(servico.getDataAgendamento());
      if (index > -1) {
        lstNomeCliente.getSelectionModel().select(lstNomeCliente.getItems().get(index));
      }

      taClientes.appendText("Nome: " + servico.getCliente().getNome());
      taClientes.appendText("\nContato: " + servico.getCliente().getContato());
    }
  }

}
