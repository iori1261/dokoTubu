package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MuttersDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost/example";
	private final String DB_USER = "root";
	private final String DB_PASS = "kcsf";
	
	
	public List<Mutter> findAll(){
		List<Mutter> mutterList = new ArrayList<>();
		
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			//select分の準備
			String sql = "SELECT ID,NAME,TEXT FROM MUTTERS ORDER BY I DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SQL分の実行
			ResultSet rs = pStmt.executeQuery();
					
			//SELECT分の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Mutter mutter = new Mutter(id, userName, text);
				mutterList.add(mutter);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	public boolean create(Mutter mutter) {
		//データベース接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			//INSERT分の準備(idは自動連番なので指定しなくていい)
			String sql = "INSERT INTO MUTTERS(NAME, TEXT) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//INSERT文中の「？」に使用する値を指定してSQL分を完成
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.geText());
			
			//INSERT分を実行(resultには追加された行数が代入される)
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
				return false;
		}
		return true;
	}
}
