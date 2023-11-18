package ifpr.pgua.eic.tarefas;

import ifpr.pgua.eic.tarefas.controllers.Cadastrar;
import ifpr.pgua.eic.tarefas.controllers.CadastrarNovoCliente;
import ifpr.pgua.eic.tarefas.controllers.CadastrarServico;
import ifpr.pgua.eic.tarefas.controllers.EditarCliente;
import ifpr.pgua.eic.tarefas.controllers.EditarPerfil;
import ifpr.pgua.eic.tarefas.controllers.ExcluirCliente;
import ifpr.pgua.eic.tarefas.controllers.Inicial;
import ifpr.pgua.eic.tarefas.controllers.ListarCliente;
import ifpr.pgua.eic.tarefas.controllers.ListarServico;
import ifpr.pgua.eic.tarefas.controllers.Login;
import ifpr.pgua.eic.tarefas.controllers.Perfil;
import ifpr.pgua.eic.tarefas.controllers.Totalizar;
import ifpr.pgua.eic.tarefas.model.daos.ClienteDAO;
import ifpr.pgua.eic.tarefas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.tarefas.model.daos.JDBCClienteDAO;
import ifpr.pgua.eic.tarefas.model.daos.JDBCLavaCarDAO;
import ifpr.pgua.eic.tarefas.model.daos.JDBCServicoDAO;
import ifpr.pgua.eic.tarefas.model.daos.JDBCTipoDAO;
import ifpr.pgua.eic.tarefas.model.daos.LavaCarDAO;
import ifpr.pgua.eic.tarefas.model.daos.ServicoDAO;
import ifpr.pgua.eic.tarefas.model.daos.TipoDAO;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioClientes;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioLavaCar;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioServico;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTipo;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

        private LavaCarDAO lavacarDAO = new JDBCLavaCarDAO(FabricaConexoes.getInstance());
        private RepositorioLavaCar repositorioLavaCar = new RepositorioLavaCar(lavacarDAO);

        private ClienteDAO clienteDAO = new JDBCClienteDAO(FabricaConexoes.getInstance());
        private RepositorioClientes repositorioClientes = new RepositorioClientes(clienteDAO);

        private TipoDAO tipoDAO = new JDBCTipoDAO(FabricaConexoes.getInstance());
        private RepositorioTipo repositorioTipo = new RepositorioTipo(tipoDAO);

        private ServicoDAO servicoDAO = new JDBCServicoDAO(FabricaConexoes.getInstance());
        private RepositorioServico repositorioServico = new RepositorioServico(servicoDAO, clienteDAO, tipoDAO, lavacarDAO, logado);

        private static LavaCar logado = null;

        public static void main(String[] args) {
                launch();
        }

        @Override
        public String getHome() {
                // TODO Auto-generated method stub
                return "LOGIN";
        }

        public static void setLogado(LavaCar log) {
                logado = log;
        }

        @Override
        public String getAppTitle() {
                // TODO Auto-generated method stub
                return "LavaTech";
        }

        @Override
        public void registrarTelas() {
                registraTela("LOGIN",
                                new ScreenRegistryFXML(App.class, "login.fxml",
                                
                                                o -> new Login(repositorioLavaCar, logado)));
                registraTela("CADASTRAR",
                                new ScreenRegistryFXML(App.class, "cadastrar.fxml",
                                                o -> new Cadastrar(repositorioLavaCar)));
                registraTela("INICIAL", new ScreenRegistryFXML(App.class, "inicial.fxml", o -> new Inicial(repositorioServico, logado)));
                registraTela("PERFIL", new ScreenRegistryFXML(App.class, "perfil.fxml",
                                o -> new Perfil(logado, repositorioLavaCar)));
                registraTela("EDITARPERFIL", new ScreenRegistryFXML(App.class, "editarPerfil.fxml",
                                o -> new EditarPerfil(logado, repositorioLavaCar)));
                registraTela("CADASTRARSERVICO",
                                new ScreenRegistryFXML(App.class, "cadastrarServico.fxml",
                                                o -> new CadastrarServico(repositorioClientes, repositorioTipo,
                                                                repositorioServico, logado)));
                registraTela("CADASTRARCLIENTE",
                                new ScreenRegistryFXML(App.class, "cadastrarNovoCliente.fxml",
                                                o -> new CadastrarNovoCliente(repositorioClientes,logado)));
                registraTela("LISTARCLIENTE",
                                new ScreenRegistryFXML(App.class, "listarClientes.fxml",
                                                o -> new ListarCliente(repositorioClientes,logado)));
                registraTela("EXCLUIRCLIENTE",
                                new ScreenRegistryFXML(App.class, "excluirCliente.fxml",
                                                o -> new ExcluirCliente(repositorioClientes,logado)));
                registraTela("EDITARCLIENTE",
                                new ScreenRegistryFXML(App.class, "editarCliente.fxml",
                                                o -> new EditarCliente(repositorioClientes,logado)));
                registraTela("TOTALIZAR", new ScreenRegistryFXML(App.class, "totalizarServico.fxml", o -> new Totalizar()));
                registraTela("LISTARSERVICO", new ScreenRegistryFXML(App.class, "listarServico.fxml", o -> new ListarServico(repositorioServico, logado)));
        }

}