package resultgen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.utility.DBUtil;

public class TeamAnswerDaoImpl implements TeamAnswerDao {

	Connection conn = null;
	String db;
	String quesTable;

	public TeamAnswerDaoImpl(String db) {
		super();
		this.db = db;
	}

	@Override
	public ArrayList<TeamAnswerBean> getAnsweredQuestionByTeam(int teamID, int i, int l) {
		ArrayList<TeamAnswerBean> alist = new ArrayList<>();

		conn = DBUtil.getConnection(db);

		if (l == 1)
			quesTable = "cquesans";
		else
			quesTable = "javaquesans";

		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement(
						"select a.teamid, a.t_name ,a.lang, a.qno , a.givenans , a.rectime, q.ans " + " from answer" + i
								+ " a, " + quesTable + " q " + " where a.qno = q.qno " + " and a.teamid = ?");

				ps.setInt(1, teamID);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					int teamid = rs.getInt(1);
					String teamName = rs.getString(2);
					int lang = rs.getInt(3);
					int qno = rs.getInt(4);
					int givenAns = rs.getInt(5);
					Timestamp ts = rs.getTimestamp(6);
					int correctans = rs.getInt(7);

					alist.add(new TeamAnswerBean(teamid, teamName, lang, qno, givenAns, ts, correctans));

				}
				DBUtil.closeConnection();

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n" + e.getMessage());
			}
		}

		return alist;
	}

	@Override
	public int countTeam() {
		int c = 0;

		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select count(*) from team");

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {

					c = rs.getInt(1);

				}
				DBUtil.closeConnection();

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n" + e.getMessage());
			}
		}

		return c;
	}

	@Override
	public int countAnsTables() {
		int c = 0;

		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("SELECT count(*) "
						+ " FROM information_schema.tables "
						+ " WHERE table_type='base table' "
						+ " AND table_schema='bughunt' "
						+ " AND table_name LIKE 'answer%'");

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {

					c = rs.getInt(1);

				}
				DBUtil.closeConnection();

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n" + e.getMessage());
			}
		}

		return c;
	}

	@Override
	public TeamContactDetails getTeamContactDetailsById(int id) {
		TeamContactDetails tcd=null;

		conn = DBUtil.getConnection(db);

		if (conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement("select mem1,mem2,inst,phn,email from team where teamid = ?");
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {

					tcd = new TeamContactDetails(id, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5));

				}
				DBUtil.closeConnection();

			} catch (SQLException e) {
				System.out.println("Incorrect SQL Query!!!\n" + e.getMessage());
			}
		}

		return tcd;
	}

}
