import java.util.*;

public class Casino {
    private long balance = 0;
    private Long currBalance;
    private List<String[]> matches = new ArrayList<>();
    private List<Illegals> illegals = new ArrayList<>();

    public void setMatches(List<String[]> games) {
        matches = games;
    }

    public String[] getMatches(int location) {
        return matches.get(location);
    }

    public long getBalance() {
        return balance;
    }

    public void handleTransactions(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            List<String> uniqueMatchID = new ArrayList<>();
            Player player = players.get(i);
            currBalance = 0L;
            for (int a = 0; a < player.getActions().size(); a++) {
                String[] currentAction = player.getActions().get(a);
                switch (currentAction[1]) {
                    case ("DEPOSIT"):
                        player.setBalance(Integer.parseInt(currentAction[3])); // ADD MONEY TO BALANCE
                        break;
                    case ("BET"):
                        if (!uniqueMatchID.contains(currentAction[2])) { // CHECK IF PLAYER HAS MADE A BET ON SAME MATCH
                                                                         // BEFORE
                            if (player.getBalance() < Integer.parseInt(currentAction[3]) // CHECK IF ILLEGAL ACTION
                                    || Integer.parseInt(currentAction[3]) <= 0) { // HANDLE <=0 COIN BET AS ILLEGAL
                                System.out.println("ILLEGAL");
                                Illegals curr_illegal = new Illegals(currentAction); // ADD TO ILLEGALS
                                player.setLegalityState(); // SET PLAYER AS ILLEGAL
                                illegals.add(curr_illegal); // ADD TO ILLEGALS
                            } else {
                                player.setBalance(-Integer.parseInt(currentAction[3]));
                                uniqueMatchID.add(currentAction[3]);
                                handleBets(currentAction, player); // CALL HELPER FUNCTION
                                // TO HANDLE THE
                                // BET
                            }
                        } else {
                            System.out.println("ILLEGAL?");
                        }
                        break;
                    case ("WITHDRAW"):
                        if (player.getBalance() < Integer.parseInt(currentAction[3])) { // CHECK IF ILLEGAL ACTION
                            System.out.println("ILLEGAL");
                            Illegals curr_illegal = new Illegals(currentAction);
                            player.setLegalityState(); // SET PLAYER AS ILLEGAL
                            illegals.add(curr_illegal); // ADD TO ILLEGALS
                        } else {
                            player.setBalance(-Integer.parseInt(currentAction[3])); // REMOVE MONEY FROM BALANCE
                        }
                        break;
                }
            }
            if (!player.getLegalityState()) { // ADD HOLDER TO CASINO BALANCE IF NO ILLEGAL ACTIONS
                balance += currBalance;
            }
        }
    }

    // FUNCTION TO HANDLE BETTINGS ON PLAYER ACTION
    public void handleBets(String[] action, Player player) {
        String[] current = matches.stream()
                .filter(thing -> action[2].equals(thing[0]))
                .findAny()
                .orElse(null); // SELECT CURRENT MATCH
        String playerBet = action[4];
        String winSide = current[3];
        if (playerBet.equals(winSide)) {
            player.setWins();
            switch (winSide) {
                case "A":
                    // ASSIGNMENT RULE: TRANSACTION MUST BE INT
                    // CALCULATE RETURNED COIN AMOUNT ON CASE "A"
                    player.setBalance(Math.round(Float.parseFloat(action[3]) * Float.parseFloat(current[1])));
                    currBalance += Integer.parseInt(action[3])
                            - Math.round(Float.parseFloat(action[3]) * Float.parseFloat(current[1]));

                    break;
                case "B":
                    // CALCULATE RETURNED COIN AMOUNT ON CASE "B"
                    player.setBalance(Math.round(Float.parseFloat(action[3]) * Float.parseFloat(current[2])));
                    currBalance += Integer.parseInt(action[3])
                            - Math.round((Float.parseFloat(action[3]) * Float.parseFloat(current[2])));
                    break;
            }
        } else if (winSide.equals("DRAW")) {
            // RETURN COINS IF GAME ENDS WITH DRAW
            player.setLosses();
            player.setBalance(Integer.parseInt(action[3]));
        } else {
            // ADD COINS TO CASINO
            currBalance += Integer.parseInt(action[3]);
            player.setLosses();
        }

    }

    public List<Illegals> getIllegals() {
        return illegals;
    }
}
