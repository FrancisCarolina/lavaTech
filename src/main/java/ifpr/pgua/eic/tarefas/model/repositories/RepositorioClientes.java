package ifpr.pgua.eic.tarefas.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.ClienteDAO;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;

public class RepositorioClientes {
  private ClienteDAO dao;

  public RepositorioClientes(ClienteDAO dao) {
    this.dao = dao;
  }

  public Resultado cadastrarCliente(String nome, String contato, LavaCar lavaCar) {
    if (nome.isEmpty() || nome.isBlank()) {
      return Resultado.erro("Nome inválido!");
    }
    if (contato.isEmpty() || contato.isBlank()) {
      return Resultado.erro("Contato inválido!");
    }

    Cliente cliente = new Cliente(nome, contato, lavaCar);

    return dao.criar(cliente);
  }

  public Resultado listarClientes(LavaCar logado) {
    return dao.listar(logado.getId());
  }

  public Resultado excluirCliente(Cliente c) {
      return dao.deletar(c.getId());
  }

  public Resultado editarCliente(Cliente c) {
    if (c.getNome().isEmpty() || c.getNome().isBlank()) {
      return Resultado.erro("Nome inválido!");
    }
    if (c.getContato().isEmpty() || c.getContato().isBlank()) {
      return Resultado.erro("Contato inválido!");
    }
    return dao.atualizar(c.getId(), c);
  }

}
