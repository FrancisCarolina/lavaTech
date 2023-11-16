package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.github.hugoperlin.results.Resultado;
import java.sql.Statement;
import java.time.LocalDate;

import ifpr.pgua.eic.tarefas.model.entities.Servico;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCServicoDAO implements ServicoDAO {

    private FabricaConexoes fabrica;

    public JDBCServicoDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    private String formatarData(LocalDate data) {
        String dataFormatada = data.getDayOfMonth() + "-" + data.getMonthValue() + "-" + data.getYear();
        return dataFormatada;
    }

    @Override
    public Resultado criar(Servico servico) {
        try (Connection con = fabrica.getConnection()) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement(
                    "INSERT INTO servico(idCliente, dataAgendada, custo, efetuado, pago, idTipo, idLavacar) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            // Ajustar os parâmetros
            pstm.setInt(1, servico.getCliente().getId());
            pstm.setString(2, formatarData(servico.getDataAgendamento()));
            pstm.setFloat(3, servico.getCusto());
            pstm.setInt(4, servico.isEfetuado() == true ? 1 : 0);
            pstm.setInt(5, servico.isPago() == true ? 1 : 0);
            pstm.setInt(6, servico.getTipo().getId());
            pstm.setInt(7, servico.getLavacar().getId());
            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret == 1) {
                int id = DBUtils.getLastId(pstm);

                servico.setId(id);

                return Resultado.sucesso("Servico cadastrado com sucesso!", servico);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Resultado getById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public Resultado atualizar(int id, Servico novo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    @Override
    public Resultado logar(String login, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logar'");
    }

}
