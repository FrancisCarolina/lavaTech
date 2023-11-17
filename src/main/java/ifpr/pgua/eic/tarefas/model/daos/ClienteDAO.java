package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Cliente;

public interface ClienteDAO {

  Resultado criar(Cliente Cliente);

  Resultado listar(int idLavacar);

  Resultado getById(int id);

  Resultado atualizar(int id, Cliente novo);

  Resultado deletar(int id);

Resultado buscarClienteServico(int id);
}
