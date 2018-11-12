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

    //endregion


    // region Getter and Setter

    //


//    @Override
//    public String toString() {
//        return getId() + ": Spieltag " + getMatchday() + ", "
//                + getTeam1().getShortName() + " " + getGoalsTeam1() + " : "
//                + getTeam2().getShortName() + " " + getGoalsTeam2();
//    }


}
