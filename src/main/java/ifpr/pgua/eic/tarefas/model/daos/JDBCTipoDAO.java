package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Cliente;
import ifpr.pgua.eic.tarefas.model.entities.Tipo;

public class JDBCTipoDAO implements TipoDAO{

    private FabricaConexoes fabrica;

    public JDBCTipoDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM tipo");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Tipo> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                Tipo tipo = new Tipo(id,nome);
                lista.add(tipo);

            }
            
            return Resultado.sucesso("Lista de Tipos", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado getById(int id) {
        
      try (Connection con = fabrica.getConnection()) {

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM tipo WHERE id=?");

        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            String nome = rs.getString("nome");

            Tipo tipo = new Tipo(id,nome);

            return Resultado.sucesso("Tipo encontrado", tipo);
        }else{
            return Resultado.erro("Tipo não encontrado!");
        }


    } catch (SQLException e) {
        return Resultado.erro(e.getMessage());
    }

    }

    @Override
    public Resultado buscarTipoServico(int id) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT idTipo FROM servico WHERE id=?");
  
            pstm.setInt(1, id);
  
            ResultSet rs = pstm.executeQuery();
            rs.next();
  
            int idTipo = rs.getInt("idTipo");
            return getById(idTipo);
  
  
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
