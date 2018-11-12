package at.htl.soccer.gui;

import at.htl.soccer.business.Repository;
import at.htl.soccer.db.MatchDao;
import at.htl.soccer.db.TeamDao;
import at.htl.soccer.model.Match;
import com.cedarsoftware.util.io.JsonWriter;

import javax.json.JsonArray;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private Scanner scanner = new Scanner(System.in);
    private int matchday = 11;
    private Repository repository;

    private TeamDao teamDao;
    private MatchDao matchDao;


    public Main() {
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


    }
}
