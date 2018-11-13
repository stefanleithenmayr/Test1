package at.htl.soccer.model;

import java.util.Objects;

/**
 * Als id wird die id aus dem REST-Service ghenommen
 */
public class Team {

    private Long id;
    private String teamName;
    private String shortName;




    //region Constructors

    public Team(Long id, String teamName, String shortName) {
        this.id = id;
        this.teamName = teamName;
        this.shortName = shortName;
    }

    public Team() {


    }


    //endregion

    // region Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    //

    @Override
    public String toString() {
        return getId() + ": " + getShortName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
