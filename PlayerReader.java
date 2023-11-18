import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PlayerReader {

    public PlayerReader(List<Player> players) {
        File playerFile = new File("player_data.txt");
        // FILE READING
        try {
            Scanner sc = new Scanner(playerFile);
            List<String> uniques = new ArrayList<String>();
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                for (int f = 0; f < line.length; f++) {
                    if (line[f] == "") {
                        line[f] = "null";
                    }
                }
                if (!uniques.contains(line[0])) { // CHECK IF PLAYER UNIQUE
                    uniques.add(line[0]);
                    players.add(new Player(line[0]));
                }
                // IN CASE IF player_data.txt IS NOT ORDERED
                Player current = players.stream()
                        .filter(player -> line[0].equals(player.getUUID()))
                        .findAny()
                        .orElse(null);
                current.setActions(line);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
