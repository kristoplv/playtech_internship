import java.util.*;

public class Main {

    public static void main(String[] args) {
        Matches matches = new Matches("match_data.txt"); // READ match_data.txt
        List<Player> players = new ArrayList<Player>(); // INIT LIST OF PLAYERS
        new PlayerReader(players);
        Casino casino = new Casino(); // CASINO INIT
        casino.setMatches(matches.getMatches()); // SET MATCHES TO CASINO
        casino.handleTransactions(players); // HANDLE MATCHES AND BETS
        new ResultWriter("Results.txt", casino.getBalance(), players, casino.getIllegals());

    }
}