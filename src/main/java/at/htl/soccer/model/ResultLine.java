package at.htl.soccer.model;

import java.util.Objects;

public class ResultLine implements Comparable<ResultLine>{

    private Team team;
    private Integer points;

    public ResultLine(Team team, Integer points) {
        this.team = team;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultLine that = (ResultLine) o;
        return Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }

    @Override
    public int compareTo(ResultLine o) {
        return this.points.compareTo(o.points);
    }
}
