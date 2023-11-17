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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ListarServico implements Initializable {

    private RepositorioServico repositorioServico;
    private LavaCar logado;

    public ListarServico(RepositorioServico repositorioServico, LavaCar logado) {
        this.repositorioServico = repositorioServico;
        this.logado = logado;
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
        Servico s = tbvServicos.getSelectionModel().getSelectedItem();
        if (s != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem certeza que deseja excluir?");

            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Resultado r = repositorioServico.excluirServico(s);
                    if (r.foiSucesso()) {
                        Alert alertErro = new Alert(AlertType.INFORMATION, r.getMsg());
                        alertErro.showAndWait();
                        initialize(null, null);
                    } else {
                        Alert alertErro = new Alert(AlertType.ERROR, r.getMsg());
                        alertErro.showAndWait();
                    }
                }
            });
        }
    }

    @FXML
    void marcarComoEfetuado(ActionEvent event) {
        Servico servico = tbvServicos.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Serviço efetuado?");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                Resultado resultado = repositorioServico.marcarComoPago(servico);

                if (resultado.foiSucesso()) {
                    Alert alertErro = new Alert(AlertType.INFORMATION, resultado.getMsg());
                    alertErro.showAndWait();
                } else {
                    Alert alertErro = new Alert(AlertType.ERROR, resultado.getMsg());
                    alertErro.showAndWait();
                }
            }
        });

        tbvServicos.refresh();
    }

    @FXML
    void marcarComoPago(ActionEvent event) {
        Servico servico = tbvServicos.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Pagamento efetuado?");

        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                Resultado resultado = repositorioServico.marcarComoPago(servico);

                if (resultado.foiSucesso()) {
                    Alert alertErro = new Alert(AlertType.INFORMATION, resultado.getMsg());
                    alertErro.showAndWait();
                } else {
                    Alert alertErro = new Alert(AlertType.ERROR, resultado.getMsg());
                    alertErro.showAndWait();
                }
            }
        });

        tbvServicos.refresh();
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

        // Removendo o símbolo de moeda e espaços
        String valorSemSimbolo = valorString.replaceAll("[^\\d.,]", "");

        try {
            // Convertendo a string para um número
            double valorNumerico = NumberFormat.getNumberInstance(Locale.US).parse(valorSemSimbolo).doubleValue();

            // Criando um formato desejado com vírgula como separador decimal
            NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            // Formatando o número para a moeda brasileira (BRL)
            String valorFormatado = formato.format(valorNumerico);

            // Exibindo o resultado
            return valorFormatado;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return valorString;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cbFiltro.getItems().clear();
        cbFiltro.getItems().addAll("Todos", "Somente os Próximos", "Efetuados",
                "Não Efetuados", "Pagos", "Não Pagos");
        cbFiltro.getSelectionModel().select("Todos");

        // configurar a renderização das colunas

        tbcCliente.setCellValueFactory(celula -> new SimpleStringProperty(celula.getValue().getCliente().getNome()));
        tbcCusto.setCellValueFactory(
                celula -> new SimpleStringProperty(formatarCusto("R$ " + celula.getValue().getCusto())));
        tbcDataAgendada.setCellValueFactory(celula -> new SimpleStringProperty(
                celula.getValue().getDataAgendamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        tbcPago.setCellValueFactory(celula -> new SimpleStringProperty(celula.getValue().isPago() ? "SIM" : "NÃO"));
        tbcRealizado.setCellValueFactory(
                celula -> new SimpleStringProperty(celula.getValue().isEfetuado() ? "SIM" : "NÃO"));
        tbcTipoServico.setCellValueFactory(celula -> new SimpleStringProperty(celula.getValue().getTipo().getNome()));

        Resultado rs = repositorioServico.listar(logado.getId());

        if (rs.foiErro()) {
            Alert alert = new Alert(AlertType.ERROR, rs.getMsg());
            alert.showAndWait();
            return;
        }

        List<Servico> lista = (List) rs.comoSucesso().getObj();

        tbvServicos.getItems().addAll(lista);
    }

}
