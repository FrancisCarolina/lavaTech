package ifpr.pgua.eic.tarefas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.LavaCar;
import ifpr.pgua.eic.tarefas.utils.DBUtils;

public class JDBCLavaCarDAO implements LavaCarDAO{

    private FabricaConexoes fabrica;

    public JDBCLavaCarDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(LavaCar lavacar) {
        //try with resources, para não precisar fechar a conexao
        try(Connection con = fabrica.getConnection()){
            
            //Preparar o comando sql
            PreparedStatement pstm = con.
            prepareStatement("INSERT INTO lavacar(nome, login, senha) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            //Ajustar os parâmetros
            pstm.setString(1,lavacar.getNome());
            pstm.setString(2, lavacar.getLogin());
            pstm.setString(3, lavacar.getSenha());
            //Executar o comando
            int ret = pstm.executeUpdate();

            if(ret == 1){
                int id = DBUtils.getLastId(pstm);

                lavacar.setId(id);

                return Resultado.sucesso("LavaCar cadastrado com sucesso!", lavacar);
            }
            return Resultado.erro("Erro desconhecido!");
        }catch(SQLException e){
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
    public Resultado atualizar(int id, LavaCar novo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(int id) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("Delete FROM lavacar where id=?", Statement.RETURN_GENERATED_KEYS);
           
            pstm.setInt(1, id);

            int linhasAfetadas = pstm.executeUpdate();

            if (linhasAfetadas > 0) {
                return Resultado.sucesso("Excluído com sucesso", "");
            } else {
                return Resultado.erro("Lavacar não encontrado");
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado logar(String login, String senha) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("Select * from lavacar where login = ? and senha = ?");
            
            pstm.setString(1, login);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                LavaCar lavacar = new LavaCar(id,nome, login, senha);
                return Resultado.sucesso("Logado com sucesso", lavacar);
            }
            
            return Resultado.erro("Login ou Senha inválida!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
    
}
