package test2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test2.db.DBConn;
import test2.model.ScTeams;

public class ScTeamsRepository {
	private static final String TAG = "ScTeams : ";
	private static ScTeamsRepository instance = new ScTeamsRepository();
	private ScTeamsRepository() {}
	public static ScTeamsRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<ScTeams> findTeam() {
		final String SQL = "SELECT * FROM SCTEAMS";
		List<ScTeams> scteams = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ScTeams scteam = new ScTeams(
									rs.getInt("id"),
									rs.getString("teamname")
					);
				scteams.add(scteam);
			}
			return scteams;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findTeam : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null;
	}
}
