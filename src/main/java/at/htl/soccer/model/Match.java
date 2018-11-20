package at.htl.soccer.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Als id wird die id aus dem REST-Service ghenommen
 */
public class Match {
    private Long id;
    private Team team1;
    private Team team2;
    private int goalsTeam1;
    private int goalsTeam2;
    private int matchday;
    private LocalDateTime matchDateTime;

    //region Constructors

    public Match() {
    }

    public Match(Long id, Team team1, Team team2, int goalsTeam1, int goalsTeam2, int matchday, LocalDateTime matchDateTime) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.goalsTeam1 = goalsTeam1;
        this.goalsTeam2 = goalsTeam2;
        this.matchday = matchday;
        this.matchDateTime = matchDateTime;
    }

    //endregion


    // region Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getGoalsTeam1() {
        return goalsTeam1;
    }

    public void setGoalsTeam1(int goalsTeam1) {
        this.goalsTeam1 = goalsTeam1;
    }

    public int getGoalsTeam2() {
        return goalsTeam2;
    }

    public void setGoalsTeam2(int goalsTeam2) {
        this.goalsTeam2 = goalsTeam2;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }


    //


    @Override
    public String toString() {
        return getId() + ": Spieltag " + getMatchday() + ", "
                + getTeam1().getShortName() + " " + getGoalsTeam1() + " : "
                + getTeam2().getShortName() + " " + getGoalsTeam2();
    }

    //TODO: Erweiterung - kein mehrmaliges Einfügen mehr möglich
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
