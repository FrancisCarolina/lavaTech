package ifpr.pgua.eic.tarefas.model.repositories;

import java.time.LocalDate;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.ServicoDAO;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.entities.Servico;
import ifpr.pgua.eic.tarefas.model.entities.Tipo;

public class RepositorioServico {
    private ServicoDAO dao;

    public RepositorioServico(ServicoDAO dao) {
        this.dao = dao;
    }

    public Resultado cadastrarServico(String custo, Cliente c, LocalDate data, Tipo tipo, LavaCar logado) {
        if (custo.isEmpty() || custo.isBlank()) {
            return Resultado.erro("Custo inválido!");
        }
        if (c == null) {
            return Resultado.erro("Cliente inválido!");
        }
        if (data == null) {
            return Resultado.erro("Data inválida!");
        }
        if (tipo == null) {
            return Resultado.erro("Tipo inválido!");
        }

        Servico servico = new Servico(c, logado, tipo, Float.parseFloat(custo), false, false, data);

        return dao.criar(servico);
    }
}
