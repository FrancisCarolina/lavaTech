package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.entities.Servico;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioServico;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class Totalizar implements Initializable{

  @FXML
  private Label labelTotal;

  @FXML
  private TableColumn<Servico, String> tbcDia;

  @FXML
  private TableColumn<Servico, String> tbcServico;

  @FXML
  private TableColumn<Servico,String> tbcValor;

  @FXML
  private TableView<Servico> tbvValores;

  private LavaCar logado;
  private RepositorioServico repositorioServico;

  public Totalizar(LavaCar logado, RepositorioServico repositorioServico) {
    this.logado = logado;
    this.repositorioServico = repositorioServico;
  }

  @FXML
  void agendar(ActionEvent event) {
    App.popScreen();
    App.pushScreen("CADASTRARSERVICO");
  }

  @FXML
  void listar(ActionEvent event) {
    App.popScreen();
    App.pushScreen("LISTARSERVICO");
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

  
  public String formatarCusto(String valorString) {
      String valorSemSimbolo = valorString.replaceAll("[^\\d.,]", "");
      try {
          double valorNumerico = NumberFormat.getNumberInstance(Locale.US).parse(valorSemSimbolo).doubleValue();
          NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
          String valorFormatado = formato.format(valorNumerico);
          return valorFormatado;
      } catch (ParseException e) {
          e.printStackTrace();
      }
      return valorString;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
      tbvValores.getItems().clear();
      tbcValor.setCellValueFactory(
                celula -> new SimpleStringProperty(formatarCusto("R$ " + celula.getValue().getCusto())));
      tbcDia.setCellValueFactory(celula -> new SimpleStringProperty(
                celula.getValue().getDataAgendamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
      tbcServico.setCellValueFactory(celula -> new SimpleStringProperty(celula.getValue().getTipo().getNome()));


      Resultado r1 = repositorioServico.listar(logado.getId());

      if(r1.foiSucesso()){
          List<Servico> list = (List) r1.comoSucesso().getObj();
          tbvValores.getItems().addAll(list);
          double soma = 0;
          for(Servico s:list){
            soma = soma + s.getCusto();
          }
          labelTotal.setText("Total: "+formatarCusto("R$ " +soma));
      }else{
          Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
          alert.showAndWait();
      }
  }

}
