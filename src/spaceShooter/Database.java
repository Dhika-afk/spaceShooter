package spaceShooter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import spaceShooter.Menu.HasilPemain;

public class Database {
	
	public static Connection connect() {
		Connection conn = null;
		String dbName = "pboshooter";
		String host = "localhost";
		int port = 3306;
		String timezone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String mySqlUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName + timezone;
		String username = "root";
		String password = "";
		
		try {
			conn = DriverManager.getConnection(mySqlUrl, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConn(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void save(String namaPemain, long waktuBertahan, int difficulty) {
		Connection conn = connect();
		String query = "INSERT INTO pemain VALUES (NULL, (?), (?), (?))";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, namaPemain);
			stmt.setLong(2, waktuBertahan);
			stmt.setInt(3, difficulty);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn(conn);
	}
	
	public static void  load(HasilPemain[] leaderboard) {
		Connection conn = Database.connect();
		String query = "SELECT nama, waktu_bertahan FROM pemain WHERE id_leaderboard=2 ORDER BY waktu_bertahan DESC LIMIT 5";
		int iterasi = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next())
			{
			    leaderboard[iterasi] = new HasilPemain(result.getString("nama"), result.getLong("waktu_bertahan"));
			    iterasi++;
			}
		} catch (SQLException er) {
			er.printStackTrace();
		}
		
		query = "SELECT nama, waktu_bertahan FROM pemain WHERE id_leaderboard=3 ORDER BY waktu_bertahan DESC LIMIT 5";
		iterasi = 5;
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			
			while(result.next())
			{
			    leaderboard[iterasi] = new HasilPemain(result.getString("nama"), result.getLong("waktu_bertahan"));
			    iterasi++;
			}
		} catch (SQLException er) {
			er.printStackTrace();
		}
		Database.closeConn(conn);
	}
	
}
