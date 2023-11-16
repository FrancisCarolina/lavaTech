package ifpr.pgua.eic.tarefas.model.repositories;

import ifpr.pgua.eic.tarefas.model.daos.ServicoDAO;

public class RepositorioServico {
    private ServicoDAO dao;

    public RepositorioServico(ServicoDAO dao) {
        this.dao = dao;
    }
}
