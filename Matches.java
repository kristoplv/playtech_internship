import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Matches {
    private List<String[]> all = new ArrayList<>();

    public Matches(String filename) {
        File matchFile = new File(filename);
        try {
            Scanner sc = new Scanner(matchFile);
            while (sc.hasNextLine()) {
                all.add((sc.nextLine().split(",")));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getMatches() {
        return all;
    }

}
