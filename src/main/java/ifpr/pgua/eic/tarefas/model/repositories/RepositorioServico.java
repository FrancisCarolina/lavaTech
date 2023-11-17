package ifpr.pgua.eic.tarefas.model.repositories;

import java.time.LocalDate;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.ClienteDAO;
import ifpr.pgua.eic.tarefas.model.daos.LavaCarDAO;
import ifpr.pgua.eic.tarefas.model.daos.ServicoDAO;
import ifpr.pgua.eic.tarefas.model.daos.TipoDAO;
import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.model.entities.Servico;
import ifpr.pgua.eic.tarefas.model.entities.Tipo;

public class RepositorioServico {
    private ServicoDAO dao;
    private ClienteDAO clienteDAO;
    private TipoDAO tipoDAO;
    private LavaCarDAO lavacarDAO;

    public RepositorioServico(ServicoDAO dao, ClienteDAO clienteDAO, TipoDAO tipoDAO, LavaCarDAO lavacarDAO,
            LavaCar logado) {
        this.dao = dao;
        this.clienteDAO = clienteDAO;
        this.tipoDAO = tipoDAO;
        this.lavacarDAO = lavacarDAO;
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

    public Resultado listar(int idLogado) {
        Resultado resultado = dao.listar(idLogado);

        if (resultado.foiSucesso()) {
            // iremos finalizar de montar os objetos
            List<Servico> lista = (List<Servico>) resultado.comoSucesso().getObj();

            for (Servico servico : lista) {
                Resultado r1 = clienteDAO.buscarClienteServico(servico.getId());
                if (r1.foiErro()) {
                    return r1;
                }
                Cliente cliente = (Cliente) r1.comoSucesso().getObj();
                servico.setCliente(cliente);

                Resultado r2 = tipoDAO.buscarTipoServico(servico.getId());
                if (r2.foiErro()) {
                    return r2;
                }
                Tipo tipo = (Tipo) r2.comoSucesso().getObj();
                servico.setTipo(tipo);

                Resultado r3 = lavacarDAO.buscarLavacarServico(servico.getId());
                if (r3.foiErro()) {
                    return r3;
                }
                LavaCar lc = (LavaCar) r3.comoSucesso().getObj();
                servico.setLavacar(lc);
            }

        }

        return resultado;
    }

    public Resultado marcarComoPago(Servico servico) {
        if (servico != null) {
            Resultado r = dao.marcarComoPago(servico);

            System.out.println(servico.getTipo());

            return r;
        }

        return Resultado.erro("Serviço inválido");
    }

    public Resultado marcarComoEfetuado(Servico servico) {
        if (servico != null) {
            Resultado r = dao.marcarComoEfetuado(servico);

            System.out.println(servico.getTipo());

            return r;
        }

        return Resultado.erro("Serviço inválido");
    }
}
