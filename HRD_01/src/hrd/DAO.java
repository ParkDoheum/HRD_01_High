package hrd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

interface I_DAO {
	void prepare(PreparedStatement ps, HashMap<String, Object> hm) throws Exception;	
	int prepare(PreparedStatement ps) throws Exception;
}

public class DAO {
    private static Connection getCon() throws Exception{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection
                          ("jdbc:oracle:thin:@//localhost:1521/xe", "loginboard", "hkit2019");
        System.out.println("DB연결");
        return con;
    }
    
    private static void close(Connection con, PreparedStatement ps, ResultSet rs) {
    	if(rs != null) {
    		try {	rs.close();} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	if(ps != null) {
    		try { ps.close(); } catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	if(con != null) {
    		try { con.close(); } catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public static Object executeQuery(String sql, I_DAO dao) {
		HashMap<String, Object> hm  = new HashMap();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getCon();			
			ps = con.prepareStatement(sql);
			dao.prepare(ps, hm);
			rs = (ResultSet) hm.get("rs");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return hm.get("data");    	
    }
    
    public static int executeUpdate(String sql, I_DAO dao) {
    	int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getCon();			
			ps = con.prepareStatement(sql);
			result = dao.prepare(ps);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		
		return result;    	
    }
}






