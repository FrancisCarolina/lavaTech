package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;

public interface ClienteDAO {

  Resultado criar(Cliente Cliente);

  Resultado listar();

  Resultado getById(int id);

  Resultado atualizar(int id, LavaCar novo);

  Resultado deletar(int id);
}
