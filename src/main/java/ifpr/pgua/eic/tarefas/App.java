package ifpr.pgua.eic.tarefas;

import ifpr.pgua.eic.tarefas.controllers.Cadastrar;
import ifpr.pgua.eic.tarefas.controllers.CadastrarNovoCliente;
import ifpr.pgua.eic.tarefas.controllers.CadastrarServico;
import ifpr.pgua.eic.tarefas.controllers.EditarPerfil;
import ifpr.pgua.eic.tarefas.controllers.Inicial;
import ifpr.pgua.eic.tarefas.controllers.Login;
import ifpr.pgua.eic.tarefas.controllers.Perfil;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "LOGIN";
    }

    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "LavaTech";
    }

    @Override
    public void registrarTelas() {
        registraTela("LOGIN", new ScreenRegistryFXML(App.class, "login.fxml", o -> new Login()));
        registraTela("CADASTRAR", new ScreenRegistryFXML(App.class, "cadastrar.fxml", o -> new Cadastrar()));
        registraTela("INICIAL", new ScreenRegistryFXML(App.class, "inicial.fxml", o -> new Inicial()));
        registraTela("PERFIL", new ScreenRegistryFXML(App.class, "perfil.fxml", o -> new Perfil()));
        registraTela("EDITARPERFIL", new ScreenRegistryFXML(App.class, "editarPerfil.fxml", o -> new EditarPerfil()));
        registraTela("CADASTRARSERVICO", new ScreenRegistryFXML(App.class, "cadastrarServico.fxml", o -> new CadastrarServico()));
        registraTela("CADASTRARCLIENTE", new ScreenRegistryFXML(App.class, "cadastrarNovoCliente.fxml", o -> new CadastrarNovoCliente()));
    }

}