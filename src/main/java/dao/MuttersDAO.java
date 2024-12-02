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
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:mysql://localhost/dokoTubu";
    private final String DB_USER = "root";
    private final String DB_PASS = "kcsf";

    public List<Mutter> findAll() {
        List<Mutter> mutterList = new ArrayList<>();
        
        //JDBCドライバを読み込む
        
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
        	throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
        }
        
        
        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // select分の準備
            String sql = "SELECT ID, NAME, TEXT FROM mutters ORDER BY ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // SQL分の実行
            ResultSet rs = pStmt.executeQuery();

            // SELECT分の結果をArrayListに格納
            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("NAME");
                String text = rs.getString("TEXT");
                Mutter mutter = new Mutter(id, userName, text);
                mutterList.add(mutter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("失敗2");
            return null;
        }
        return mutterList;
    }

    public boolean create(Mutter mutter) {
        // データベース接続
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            
        	// INSERT文の準備
            String sql = "INSERT INTO mutters(NAME, TEXT) VALUES(?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // パラメータのセット
            pStmt.setString(1, mutter.getUserName());
            pStmt.setString(2, mutter.getText());

            // INSERT文を実行
            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("失敗3");
            return false;
        }
        return true;
    }

}
