package at.htl.soccer.business;

import at.htl.soccer.model.Match;
import at.htl.soccer.model.Team;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * Damit keine doppelten Matches eingetragen werden können ist ein set für die "matches"
 * zu verwenden. Als Vergleichskriterium dient die "id".
 */
public class Repository {

    private Client client;
    private WebTarget target;
    private JsonArray matchesJson;
    private Set<Match> matches;

    public Repository() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("https://www.openligadb.de/api/getmatchdata/bl1/2018");
        matches = new HashSet<>();
    }

    //region Getter and Setter
    public JsonArray getMatchesJson() {
        return matchesJson;
    }

    private void setMatchesJson(JsonArray matchesJson) {
        this.matchesJson = matchesJson;
    }


    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }
    //endregion

    /**
     * Die Statistik der deutschen Bundesliga wird von einem REST-Server gelesen.
     * Hierzu wird die OpenLigaDB verwendet
     * https://www.openligadb.de/api/getmatchdata/bl1/2018/11
     * 2018 ... Jahr 2018
     * 11   ... Alle Ergebnisse des 11. Spieltags
     *
     * @return JsonArray mit allen Ergebnissen
     */
    public JsonArray loadStatisticsFromServer(int matchday) {
        Response response = this.target.path(Integer.toString(matchday)).request(MediaType.APPLICATION_JSON).get();
        JsonArray matches = response.readEntity(JsonArray.class);
        this.setMatchesJson(matches);
        return matches;
    }

    /**
     * Das JsonArray 'matchesJson' wird durchlaufen und die benötigten Werte zum Erstellen der Java-Objekte
     * für das Spiel (Match) sowie die beiden Teams (Team) werden ausgelesen. Anschließend werden die Match-Objekte
     * im Set 'matches' gespeichert.
     *
     *
     */
    public void createMatchListFromJson() {
        List<Team> teams = new ArrayList<>();
        for (JsonValue value:
             this.matchesJson) {
            JsonObject jsonObject = value.asJsonObject();
            JsonObject firstTeamJson = jsonObject.getJsonObject("Team1");
            JsonObject secondTeamJson = jsonObject.getJsonObject("Team2");

            //Two Teams as Objects
            Team firstTeam = new Team(new Long(firstTeamJson.getInt("TeamId")), firstTeamJson.getString("TeamName"), firstTeamJson.getString("ShortName"));
            Team secondTeam = new Team(new Long(secondTeamJson.getInt("TeamId")), secondTeamJson.getString("TeamName"), secondTeamJson.getString("ShortName"));
            if (!teams.contains(firstTeam)){
                teams.add(firstTeam);
            }
            if(!teams.contains(secondTeam)){
                teams.add(secondTeam);
            }
            //Match as Object
            JsonArray matchResult = jsonObject.getJsonArray("MatchResults");
            JsonObject endResult = matchResult.getJsonObject(0);
            int firstTeamPoints = endResult.getInt("PointsTeam1");
            int secondTeamPoints = endResult.getInt("PointsTeam2");

            JsonObject group = jsonObject.getJsonObject("Group");
            int matchday = group.getInt("GroupOrderID");

            String str = jsonObject.getString("MatchDateTime");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            Match match = new Match(new Long(jsonObject.getInt("MatchID")), this.getTeam(teams, firstTeam), this.getTeam(teams, secondTeam), firstTeamPoints, secondTeamPoints, matchday, dateTime);
            if(!matches.contains(match)){ //TODO: If für Erweiterung
                this.getMatches().add(match);
            }
        }
    }

    private Team getTeam(List<Team> teams, Team teamObject) {
        for (Team team:
             teams) {
            if (team == teamObject){
                return team;
            }
        }
        return null;
    }
}