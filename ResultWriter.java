import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class ResultWriter {
    private int illegalCount;

    public ResultWriter(String filename, long casinoBalance, List<Player> players, List<Illegals> illegals) {
        try {
            FileWriter output = new FileWriter(filename);
            for (int i = 0; i < players.size(); i++) {
                if (!players.get(i).getLegalityState()) {
                    output.write(players.get(i).getUUID() + " ");
                    output.write(Long.toString(players.get(i).getBalance()) + " ");
                    output.write(players.get(i)
                            .getWinRate()
                            .toString()
                            .replace(".", ",") + "\n");
                } else {
                    illegalCount++;
                }
            }
            if (illegalCount == players.size()) {
                output.write("\n");
            }
            if (illegals.size() > 0) {

                for (int a = 0; a < illegals.size(); a++) {
                    output.write("\n");
                    String[] illegalAction = illegals.get(a).getIllegalAction();
                    if (illegalAction.length < 5) {
                        output.write(Arrays.toString(illegalAction)
                                .replace(",", "")
                                .replace("[", "")
                                .replace("]", "") + " null");
                    } else {
                        output.write(Arrays.toString(illegalAction)
                                .replace(",", "")
                                .replace("[", "")
                                .replace("]", ""));
                    }
                }
            } else {
                output.write("\n");
            }
            output.write("\n" + "\n");
            output.write(Long.toString(casinoBalance));
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
