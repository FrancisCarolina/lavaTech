package ifpr.pgua.eic.tarefas.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.ClienteDAO;
import ifpr.pgua.eic.tarefas.model.daos.LavaCarDAO;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;

public class RepositorioClientes {
  private ClienteDAO dao;

  public RepositorioClientes(ClienteDAO dao) {
    this.dao = dao;
  }

  public Resultado cadastrarCliente(String nome, String contato) {
    if (nome.isEmpty() || nome.isBlank()) {
      return Resultado.erro("Nome inválido!");
    }
    if (contato.isEmpty() || contato.isBlank()) {
      return Resultado.erro("Contato inválido!");
    }

    Cliente cliente = new Cliente(nome, contato);

    return dao.criar(cliente);
  }

}
