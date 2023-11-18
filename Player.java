import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Player {
    private long balance = 0;
    private float wins = 0;
    private float losses = 1; // TO AVOID NumberFormatException
    private String UUID;
    private boolean isIllegal = false;
    private List<String[]> actions = new ArrayList<>();

    // INIT
    public Player(String ID) {
        this.UUID = ID;
    }

    // GETTERS
    public boolean getLegalityState() {
        return isIllegal;
    }

    public String getUUID() {
        return UUID;
    }

    public long getBalance() {
        return balance;
    }

    public List<String[]> getActions() {
        return actions;
    }

    public BigDecimal getWinRate() {
        BigDecimal winrate = new BigDecimal(wins / losses);
        winrate = winrate.setScale(2, RoundingMode.HALF_EVEN);
        return winrate;
    }

    // SETTERS
    public void setBalance(int input) {
        balance += input;
    }

    public void setActions(String[] input) {
        actions.add(input);
    }

    public void setWins() {
        wins += 1;
    }

    public void setLosses() {
        losses += 1;
    }

    public void setLegalityState() {
        isIllegal = true;
    }

}
