package at.htl.soccer.dbx;

import at.htl.soccer.model.Match;
import at.htl.soccer.model.Team;

import java.sql.*;

public class MatchDao extends AbstractDao {

    TeamDao teamDao = new TeamDao();

    /**
     * Erstellen der Tabelle BL_MATCH
     *
     * Fehlercode, falls Tabelle bereits exisitiert X0Y32 (https://stackoverflow.com/a/5866339)
     * siehe auch
     * http://db.apache.org/derby/docs/10.14/ref/
     * http://db.apache.org/derby/docs/10.14/ref/rrefexcept71493.html
     */
    public void createTable() {

        try {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE bl_match (" +
                    "id INT CONSTRAINT match_pk PRIMARY KEY," +
                    "team1_id INT NOT NULL CONSTRAINT team1_fk REFERENCES bl_team(id)," +
                    "team2_id INT NOT NULL CONSTRAINT team2_fk REFERENCES bl_team(id)," +
                    "team1_goals INT NOT NULL," +
                    "team2_goals INT NOT NULL," +
                    "matchday INT NOT NULL," +
                    "match_date_time TIMESTAMP" +
                    ")";

            stmt.execute(sql);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Entfernen der Tabelle BL_MATCH
     */
    public void dropTable() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "DROP TABLE bl_match";
            stmt.execute(sql);
        } catch (SQLException e) {
        }
    }

    /**
     * Einfügen eines Match-Objektes. Neue Teams werden automatisch angelegt.
     *
     * @param match zu speicherndes Objekt
     */
    public void insert(Match match) {

        teamDao.insert(match.getTeam1());
        teamDao.insert(match.getTeam2());

        String sql = "insert into bl_match (id, team1_id, team2_id, team1_goals, team2_goals, matchday, match_date_time) " +
                "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, match.getId());
            pstmt.setLong(2, match.getTeam1().getId());
            pstmt.setLong(3, match.getTeam2().getId());
            pstmt.setInt(4, match.getGoalsTeam1());
            pstmt.setInt(5, match.getGoalsTeam2());
            pstmt.setInt(6, match.getMatchday());
            pstmt.setTimestamp(7, Timestamp.valueOf(match.getMatchDateTime()));

            int rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
