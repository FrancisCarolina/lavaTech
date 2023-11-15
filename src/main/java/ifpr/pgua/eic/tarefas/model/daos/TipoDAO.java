package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

public interface TipoDAO {
    Resultado listar();
    Resultado getById(int id);
}
