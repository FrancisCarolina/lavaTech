package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Cliente;
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
  public Resultado getById(int id){

      try (Connection con = fabrica.getConnection()) {

          PreparedStatement pstm = con.prepareStatement("SELECT * FROM cliente WHERE id=?");

          pstm.setInt(1, id);

          ResultSet rs = pstm.executeQuery();
          
          if(rs.next()){
              String nome = rs.getString("nome");
              String contato = rs.getString("contato");

              Cliente cliente = new Cliente(id,nome, contato);

              return Resultado.sucesso("Cliente encontrado", cliente);
          }else{
              return Resultado.erro("Cliente não encontrado!");
          }


      } catch (SQLException e) {
          return Resultado.erro(e.getMessage());
      }


  }

  @Override
  public Resultado atualizar(int id, Cliente novo) {
        //try with resources, para não precisar fechar a conexao
        try(Connection con = fabrica.getConnection()){
            
          //Preparar o comando sql
          PreparedStatement pstm = con.
          prepareStatement("UPDATE cliente SET nome = ?, contato = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
          //Ajustar os parâmetros
          pstm.setString(1, novo.getNome());
          pstm.setString(2, novo.getContato());
          pstm.setInt(3, id);
          //Executar o comando
          int ret = pstm.executeUpdate();
          
          if(ret > 0){
              return Resultado.sucesso("Cliente editado com sucesso!", novo);
          }
          return Resultado.erro("Erro desconhecido!");
      }catch(SQLException e){
          return Resultado.erro(e.getMessage());
      }
  }

  @Override
  public Resultado deletar(int id) {
    
    try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("Delete FROM cliente where id=?", Statement.RETURN_GENERATED_KEYS);
           
            pstm.setInt(1, id);

            int linhasAfetadas = pstm.executeUpdate();

            if (linhasAfetadas > 0) {
                return Resultado.sucesso("Excluído com sucesso", "");
            } else {
                return Resultado.erro("Cliente não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
  }

  
  @Override
  public Resultado buscarClienteServico(int id) {
      
      try (Connection con = fabrica.getConnection()) {

          PreparedStatement pstm = con.prepareStatement("SELECT idCliente FROM servico WHERE id=?");

          pstm.setInt(1, id);

          ResultSet rs = pstm.executeQuery();
          rs.next();

          int idCliente = rs.getInt("idCliente");
          return getById(idCliente);


      } catch (SQLException e) {
          return Resultado.erro(e.getMessage());
      }


  }

}
