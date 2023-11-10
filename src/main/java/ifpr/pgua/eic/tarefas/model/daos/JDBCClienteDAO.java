package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCClienteDAO implements ClienteDAO {

  private FabricaConexoes fabrica;

  public JDBCClienteDAO(FabricaConexoes fabrica) {
    this.fabrica = fabrica;
  }

  @Override
  public Resultado criar(Cliente cliente) {
    // try with resources, para não precisar fechar a conexao
    try (Connection con = fabrica.getConnection()) {

      // Preparar o comando sql
      PreparedStatement pstm = con.prepareStatement("INSERT INTO cliente(nome, contato) VALUES (?, ?)",
          Statement.RETURN_GENERATED_KEYS);
      // Ajustar os parâmetros
      pstm.setString(1, cliente.getNome());
      pstm.setString(2, cliente.getContato());
      // Executar o comando
      int ret = pstm.executeUpdate();

      if (ret == 1) {
        int id = DBUtils.getLastId(pstm);

        cliente.setId(id);

        return Resultado.sucesso("Clinte cadastrado com sucesso!", cliente);
      }
      return Resultado.erro("Erro desconhecido!");
    } catch (SQLException e) {
      return Resultado.erro(e.getMessage());
    }
  }

  @Override
  public Resultado listar() {
    try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM cliente");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Cliente> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");

                Cliente cliente = new Cliente(id,nome, contato);
                lista.add(cliente);

            }
            
            return Resultado.sucesso("Lista de clientes", lista);
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
  public Resultado atualizar(int id, LavaCar novo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
  }

  @Override
  public Resultado deletar(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deletar'");
  }

}
