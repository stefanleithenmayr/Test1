package at.htl.soccer.gui;

import at.htl.soccer.business.Repository;
import at.htl.soccer.dbx.MatchDao;
import at.htl.soccer.dbx.TeamDao;
import at.htl.soccer.model.Match;
import at.htl.soccer.model.ResultLine;
import at.htl.soccer.model.Team;
import com.cedarsoftware.util.io.JsonWriter;

import javax.json.JsonArray;
import javax.json.JsonValue;
import java.util.*;

public class Main {

    private Scanner scanner = new Scanner(System.in);
    private int matchday = 11;
    private Repository repository;

    private TeamDao teamDao;
    private MatchDao matchDao;


    public Main() {
        //TODO: Konstruktor verwenden
        repository = new Repository();
        matchDao = new MatchDao();
        matchDao.dropTable();
        teamDao = new TeamDao();
        teamDao.dropTable();

        teamDao.createTable();
        matchDao.createTable();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }

    /**
     * Wir ein matchday eingegeben, darf er nur einen Wert zwischen 1 und 34 annehmen
     * <p>
     * Option 1:
     * Die Spielergebnisse des aktuellen Spieltages (matchday) werden in das JsonArray "matchesJson"
     * des Repositories geladen
     * <p>
     * Option 2:
     * Der Inhalt des JsonArrays "matchesJson" wird in ein Set vom Matches überführt
     * <p>
     * Option 3:
     * Der Inhalt des Set "matches" wird in der Datenbank gespeichert
     *
     * usw
     */
    public void mainMenu() {
            System.out.println("***************************************************");
            System.out.println();
            System.out.println("            Statistik deutsche Bundesliga");
            System.out.printf("                  am %d.Spieltag\n", matchday);
            System.out.println();
            System.out.println("***************************************************");
            System.out.println("\n");
            System.out.println("1 - update repository from rest");
            System.out.println("2 - show matches on screen");
            System.out.println("3 - persist repository in db");
            System.out.println("4 - current Bundesliga table");
            System.out.println("5 - change matchday");
            System.out.println("0 - exit");

            System.out.print("\n\nWählen Sie aus (Ziffer + <Enter>): ");
            String input = scanner.nextLine();
            System.out.println();

            if(input.equals("1")){
                this.repository.loadStatisticsFromServer(matchday);
                JsonArray matches = repository.getMatchesJson();
                String formatted = JsonWriter.formatJson(matches.toString());
                System.out.println(formatted);
                this.mainMenu();
            }
            else if(input.equals("2")){
                repository.createMatchListFromJson();
                for (Match match:
                     repository.getMatches()) {
                    System.out.println(match.toString());
                }
                this.mainMenu();
            }
            else if(input.equals("3")) {
                if (repository.getMatchesJson() == null){
                    System.out.println("Datenbank ist leer, downloaden Sie zuerst die Spiele");
                }else{
                    Set<Match> matches = repository.getMatches();

                    for (Match match:
                         matches) {
                        matchDao.insert(match);
                    }
                }
                this.mainMenu();
            }
            else if(input.equals("4")) {
                this.repository = new Repository();

                for (int i = 0; i < 12; i++) { //TODO: Index erhöhen
                    this.repository.loadStatisticsFromServer(i);
                    repository.createMatchListFromJson(); //TODO: Verschoben in die For Loop
                }

                Set<Match> matches = repository.getMatches();
                List<Team> teams = new ArrayList<>();
                for (Match match:
                     matches) {
                    if (!teams.contains(match.getTeam1()))teams.add(match.getTeam1());
                    if (!teams.contains(match.getTeam2()))teams.add(match.getTeam2());
                }
                List<ResultLine> resultLines = new ArrayList<>();
                for (Match match:
                     matches) {
                    ResultLine resultLine1 = this.getResultLine(resultLines, match.getTeam1()); //TODO: Verwendung einer Variable --> kein mehrfacher Aufruf der getResultLine Methode
                    if (resultLine1 != null){ //TODO If Bedingung verändert
                        if (match.getGoalsTeam1() > match.getGoalsTeam2()){
                            resultLine1.setPoints(resultLine1.getPoints() + 3);
                        }
                        if(match.getGoalsTeam1() == match.getGoalsTeam2()){
                            resultLine1.setPoints(resultLine1.getPoints() + 1);
                        }
                    }else{
                        if (match.getGoalsTeam1() > match.getGoalsTeam2()){
                            resultLines.add(new ResultLine(match.getTeam1(), 3));
                        }
                        if(match.getGoalsTeam1() == match.getGoalsTeam2()){
                            resultLines.add(new ResultLine(match.getTeam1(), 1));
                        }
                    }

                    resultLine1 = this.getResultLine(resultLines, match.getTeam2());
                    if (resultLine1 != null){ //TODO If Bedingung verändert
                        if (match.getGoalsTeam2() > match.getGoalsTeam1()){
                            resultLine1.setPoints(resultLine1.getPoints() + 3);
                        }
                        if(match.getGoalsTeam2() == match.getGoalsTeam1()){
                            resultLine1.setPoints(resultLine1.getPoints() + 1);
                        }
                    }else{
                        if (match.getGoalsTeam2() > match.getGoalsTeam1()){
                            resultLines.add(new ResultLine(match.getTeam2(), 3)); //TODO Veränderung auf Team2
                        }
                        if(match.getGoalsTeam2() == match.getGoalsTeam1()){
                            resultLines.add(new ResultLine(match.getTeam2(), 1));
                        }
                    }
                }

                Collections.sort(resultLines);
                for (ResultLine rs:
                        resultLines) {
                    System.out.println(rs.getTeam().getTeamName() + " " + rs.getPoints());
                }
                this.mainMenu();
            }
            else if(input.equals("5")){
                System.out.print("Geben Sie einen neuen Spieltag ein: ");
                String newNumberString = scanner.nextLine();
                matchday = Integer.parseInt(newNumberString);
                this.repository.loadStatisticsFromServer(matchday);
                this.repository.createMatchListFromJson();
                this.mainMenu();
            }
    }

    private ResultLine getResultLine(List<ResultLine> resultLines, Team team1) {
        for (ResultLine rs:
             resultLines) {
            if (rs.getTeam().equals(team1)){ //TODO: If Bedingung verändert sodass die überschriebene Equals Methode verwendet wird
                return rs;
            }
        }
        return null;
    }
}
