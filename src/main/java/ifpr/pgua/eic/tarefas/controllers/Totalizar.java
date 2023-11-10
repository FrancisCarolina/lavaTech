package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class Totalizar {

  @FXML
  private Label labelTotal;

  @FXML
  private TableColumn<?, ?> tbcDia;

  @FXML
  private TableColumn<?, ?> tbcServico;

  @FXML
  private TableColumn<?, ?> tbcValor;

  @FXML
  private TableView<?> tbvValores;

  @FXML
  void agendar(ActionEvent event) {

  }

  @FXML
  void listar(ActionEvent event) {

  }

  @FXML
  void clientes(ActionEvent event) {

  }

  @FXML
  void perfil(MouseEvent event) {

  }

  @FXML
  void voltar(ActionEvent event) {
    App.popScreen();
  }

}
