package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.hugoperlin.results.Resultado;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public LocalDate StringToLocalDate(String dataString) {

        // Criando um formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");

        // Parse da String para LocalDate
        LocalDate dataFormatada = LocalDate.parse(dataString, formatter);
        return dataFormatada;
            
    }

    @Override
    public Resultado listar(int idLogado) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM servico where idLavacar = ?");

            pstm.setInt(1, idLogado);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Servico> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("id");
                String dataAgendada = rs.getString("dataAgendada");
                Float custo = rs.getFloat("custo");
                int efetuado = rs.getInt("efetuado");
                int pago = rs.getInt("pago");

                LocalDate dateLocal = StringToLocalDate(dataAgendada);

                Servico servico = new Servico(id, null, null, null,custo, efetuado==1?true:false, pago==1?true:false,dateLocal);
                lista.add(servico);

            }
            
            return Resultado.sucesso("Lista de servicos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
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
