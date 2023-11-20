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

            while (rs.next()) {
                int id = rs.getInt("id");
                String dataAgendada = rs.getString("dataAgendada");
                Float custo = rs.getFloat("custo");
                int efetuado = rs.getInt("efetuado");
                int pago = rs.getInt("pago");

                LocalDate dateLocal = StringToLocalDate(dataAgendada);

                Servico servico = new Servico(id, null, null, null, custo, efetuado == 1 ? true : false,
                        pago == 1 ? true : false, dateLocal);
                lista.add(servico);

            }

            return Resultado.sucesso("Lista de servicos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado marcarComoPago(Servico servico) {
        // UPDATE servico SET pago = 1 WHERE id = ?
        // try with resources, para não precisar fechar a conexao
        try (Connection con = fabrica.getConnection()) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement("UPDATE servico SET pago = 1 WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            // Ajustar os parâmetros
            pstm.setInt(1, servico.getId());
            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret > 0) {
                return Resultado.sucesso("Marcado como pago!", servico);
            }
            return Resultado.erro("Não achou serviço!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado marcarComoEfetuado(Servico servico) {
        // UPDATE servico SET efetuado = 1 WHERE id = ?
        // try with resources, para não precisar fechar a conexao
        try (Connection con = fabrica.getConnection()) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement("UPDATE servico SET efetuado = 1 WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            // Ajustar os parâmetros
            pstm.setInt(1, servico.getId());
            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret > 0) {
                return Resultado.sucesso("Marcado como efetuado!", servico);
            }
            return Resultado.erro("Não achou serviço!");
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
        // UPDATE servico SET idCliente=?, dataAgendada=?, custo=?, efetuado=?, pago=?,
        // idTipo=?, idLavacar=? WHERE id = ?;
        // try with resources, para não precisar fechar a conexao
        try (Connection con = fabrica.getConnection()) {

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement(
                    "UPDATE servico SET idCliente=?, dataAgendada=?, custo=?, efetuado=?, pago=?, idTipo=?, idLavacar=? WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            // Ajustar os parâmetros
            pstm.setInt(1, novo.getCliente().getId());
            pstm.setString(2, formatarData(novo.getDataAgendamento()));
            pstm.setFloat(3, novo.getCusto());
            pstm.setInt(4, novo.isEfetuado() ? 1 : 0);
            pstm.setInt(5, novo.isPago() ? 1 : 0);
            pstm.setInt(6, novo.getTipo().getId());
            pstm.setInt(7, novo.getLavacar().getId());
            pstm.setInt(8, id);

            // Executar o comando
            int ret = pstm.executeUpdate();

            if (ret > 0) {
                return Resultado.sucesso("Serviço editado com sucesso!", novo);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("Delete FROM servico where id=?",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, id);

            int linhasAfetadas = pstm.executeUpdate();

            if (linhasAfetadas > 0) {
                return Resultado.sucesso("Excluído com sucesso", "");
            } else {
                return Resultado.erro("Serviço não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado filtrar(int idLogado, String filtro) {
        String sql = "SELECT * FROM servico where idLavacar = ?";
        if (filtro == "Não Efetuados") {
            sql = "SELECT * FROM servico where idLavacar = ? and efetuado = 0";
        } else if (filtro == "Efetuados") {
            sql = "SELECT * FROM servico where idLavacar = ? and efetuado = 1";
        } else if (filtro == "Pagos") {
            sql = "SELECT * FROM servico where idLavacar = ? and pago = 1";
        } else if (filtro == "Não Pagos") {
            sql = "SELECT * FROM servico where idLavacar = ? and pago = 0";
        } 
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idLogado);
            

            ResultSet rs = pstm.executeQuery();

            ArrayList<Servico> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String dataAgendada = rs.getString("dataAgendada");
                Float custo = rs.getFloat("custo");
                int efetuado = rs.getInt("efetuado");
                int pago = rs.getInt("pago");

                LocalDate dateLocal = StringToLocalDate(dataAgendada);
                LocalDate hoje = LocalDate.now();

                Servico servico = new Servico(id, null, null, null, custo,efetuado == 1 ? true : false,pago == 1 ? true : false, dateLocal);
                
                if ((filtro == "Somente os Próximos" && !dateLocal.isBefore(hoje))|| filtro != "Somente os Próximos") {
                    lista.add(servico);
                }
            }
            if (lista.size() == 0) {
                return Resultado.erro("Nenhum serviço encontrado");
            } else {
                return Resultado.sucesso("Lista de servicos", lista);
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado totalizarMes(int mes, int idLogado) {
        //SELECT SUM(custo) AS custoTotal, SUM(custo)/4 AS mediaSemanal, SUM(custo) / 30 AS mediaDiaria FROM servico WHERE CAST(SUBSTR(dataAgendada, INSTR(dataAgendada, '-') + 1, INSTR(SUBSTR(dataAgendada, INSTR(dataAgendada, '-') + 1), '-') - 1) AS INTEGER) = ?;
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT SUM(custo) AS custoTotal, SUM(custo)/4 AS mediaSemanal, SUM(custo) / 30 AS mediaDiaria FROM servico WHERE idLavacar = ? and CAST(SUBSTR(dataAgendada, INSTR(dataAgendada, '-') + 1, INSTR(SUBSTR(dataAgendada, INSTR(dataAgendada, '-') + 1), '-') - 1) AS INTEGER) = ?");
            
            pstm.setInt(1, idLogado);
            pstm.setInt(2, mes);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Float> lista = new ArrayList<>();

            while (rs.next()) {
                Float total = rs.getFloat("custoTotal");
                Float semana = rs.getFloat("mediaSemanal");
                Float dia = rs.getFloat("mediaDiaria");

                lista.add(total);
                lista.add(semana);
                lista.add(dia);
                return Resultado.sucesso("Lucros no Mês selecionado", lista);
            }

            return Resultado.erro("Não foi possível calcular lucro no mês selecionado");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listarEssaSemana(int idLogado) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM servico where idLavacar = ?");

            pstm.setInt(1, idLogado);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Servico> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String dataAgendada = rs.getString("dataAgendada");
                Float custo = rs.getFloat("custo");
                int efetuado = rs.getInt("efetuado");
                int pago = rs.getInt("pago");

                LocalDate dateLocal = StringToLocalDate(dataAgendada);
                LocalDate hoje= LocalDate.now();

                if(hoje.minusDays(8).isBefore(dateLocal) && hoje.plusDays(8).isAfter(dateLocal) ){

                    Servico servico = new Servico(id, null, null, null, custo, efetuado == 1 ? true : false,
                            pago == 1 ? true : false, dateLocal);
                    lista.add(servico);
                }


            }

            return Resultado.sucesso("Lista de servicos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

}
