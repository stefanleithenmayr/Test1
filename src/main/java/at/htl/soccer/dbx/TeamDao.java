package at.htl.soccer.dbx;

import at.htl.soccer.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeamDao extends AbstractDao {


    /**
     * Erstellen der Tabelle BL_TEAM
     *
     * Fehlercode, falls Tabelle bereits exisitiert X0Y32 (https://stackoverflow.com/a/5866339)
     * siehe auch
     * http://db.apache.org/derby/docs/10.14/ref/
     * http://db.apache.org/derby/docs/10.14/ref/rrefexcept71493.html
     */
    public void createTable() {

        try {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE bl_team (" +
                    "id INT CONSTRAINT team_pk PRIMARY KEY," +
                    "team_name VARCHAR(255) NOT NULL," +
                    "short_name VARCHAR(50) NOT NULL)";

            stmt.execute(sql);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Entfernen der Tabelle BL_TEAM
     */
    public void dropTable() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "DROP TABLE bl_team";
            stmt.execute(sql);
        } catch (SQLException e) {
        }
    }

    /**
     * Einfügen eines Team-Objektes.
     *
     * @param team zu soeicherndes Objekt
     */
    public void insert(Team team) {

        if (IsTeamInDB(team)){
            return;
        }

        String sql = "insert into bl_team (id, team_name, short_name) " +
                "VALUES(?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, team.getId());
            pstmt.setString(2, team.getTeamName());
            pstmt.setString(3, team.getShortName());

            int rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean IsTeamInDB(Team team) {
        String sql = "select * from bl_team";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                if (rs.getLong(1) == team.getId()){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}