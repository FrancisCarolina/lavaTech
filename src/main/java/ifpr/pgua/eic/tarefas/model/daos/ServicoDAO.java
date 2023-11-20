package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Servico;

public interface ServicoDAO {

    Resultado criar(Servico servico);

    Resultado listar(int idLogado);

    Resultado atualizar(int id, Servico novo);

    Resultado deletar(int id);

    Resultado marcarComoPago(Servico servico);

    Resultado marcarComoEfetuado(Servico servico);

    Resultado filtrar(int idLogado, String filtro);

    Resultado totalizarMes(int mes, int idLogado);

    Resultado listarEssaSemana(int idLogado);

    Resultado listarTotal(int id);

}
