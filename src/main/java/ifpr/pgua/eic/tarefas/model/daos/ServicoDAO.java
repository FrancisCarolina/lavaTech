package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Servico;

public interface ServicoDAO {

    Resultado criar(Servico servico);

    Resultado listar(int idLogado);

    Resultado getById(int id);

    Resultado atualizar(int id, Servico novo);

    Resultado deletar(int id);

    Resultado logar(String login, String senha);

}
