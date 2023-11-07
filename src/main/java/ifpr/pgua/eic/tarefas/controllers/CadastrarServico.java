package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CadastrarServico {

    @FXML
    private ComboBox<?> cbTipo;

    @FXML
    private DatePicker dpDataRealizacao;

    @FXML
    private TextField tfContatoCliente;

    @FXML
    private TextField tfCusto;

    @FXML
    private TextField tfNomeCliente;


    @FXML
    void listar(ActionEvent event) {

    }


    @FXML
    void cadastrar(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}

