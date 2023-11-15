package ifpr.pgua.eic.tarefas.model.repositories;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.TipoDAO;

public class RepositorioTipo {
    private TipoDAO dao;

    public RepositorioTipo(TipoDAO dao){
        this.dao = dao;
    }

    public Resultado listarTipos() {
        return dao.listar();
    }
    
}
