package test2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test2.db.DBConn;
import test2.model.ScPlayers;


public class ScPlayersRepository {
	private static final String TAG = "ScPlayers : ";
	private static ScPlayersRepository instance = new ScPlayersRepository();
	private ScPlayersRepository() {}
	public static ScPlayersRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<ScPlayers> findPlayers(String team) {
		final String SQL = "SELECT NAME, BACK_NUM FROM SCPLAYERS WHERE TEAM LIKE ? ORDER BY BACK_NUM ASC";
		List<ScPlayers> scplayers = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, team);
			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					
					ScPlayers scplayer = ScPlayers.builder()
							.name(rs.getString(1))
							.back_num(rs.getInt(2))
							.build();
					
					scplayers.add(scplayer);
				}
			
			return scplayers;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findPlayer : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<ScPlayers> findPlayerInfo(String team, int back_num) {
		final String SQL = "SELECT BACK_NUM , NAME, POSITION FROM SCPLAYERS WHERE TEAM LIKE ? AND BACK_NUM LIKE ?";
		List<ScPlayers> scplayerInfo = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, team);
			pstmt.setInt(2, back_num);
			rs = pstmt.executeQuery();
			
				while(rs.next()) {
					
					ScPlayers scplayIn = ScPlayers.builder()
							.back_num(rs.getInt(1))
							.name(rs.getString(2))
							.position(rs.getString(3))
							.build();
					
					scplayerInfo.add(scplayIn);
				}
			
			return scplayerInfo;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findPlayerInfo : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
}
