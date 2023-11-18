import java.util.*;

public class Illegals {
    private List<String> UID = new ArrayList<>();
    private String[] action;

    public Illegals(String[] action) {
        this.action = action;
    }

    // GETTERS
    public List<String> getIllegalUID() {
        return UID;
    }

    public String[] getIllegalAction() {
        return action;
    }
}
