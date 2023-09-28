package ifpr.pgua.eic.tarefas.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    
    public static int getLastId(PreparedStatement pstm) throws SQLException{

        Connection con = pstm.getConnection();
        int id = -1;

        if(con.getMetaData().getDatabaseProductName().equals("SQLite")){
            ResultSet rs2 = con.prepareStatement("select last_insert_rowid();").executeQuery();
            rs2.next();
            id = rs2.getInt(1);
            rs2.close();
        }else{
            ResultSet rs = pstm.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        }
        return id;
    }
}
