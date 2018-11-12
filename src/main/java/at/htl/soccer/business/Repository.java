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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


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
        return null;
    }

    /**
     * Das JsonArray 'matchesJson' wird durchlaufen und die benötigten Werte zum Erstellen der Java-Objekte
     * für das Spiel (Match) sowie die beiden Teams (Team) werden ausgelesen. Anschließend werden die Match-Objekte
     * im Set 'matches' gespeichert.
     *
     *
     */
    public void createMatchListFromJson() {

    }


}
