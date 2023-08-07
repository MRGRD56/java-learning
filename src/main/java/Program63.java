import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Program63 {
    public static void main(String[] args) throws BackingStoreException {
        Preferences preferences = Preferences.userNodeForPackage(Program63.class);
        preferences.putInt("PROGRAM63", preferences.getInt("PROGRAM63", 0) + 1);

        System.out.println(preferences.getInt("PROGRAM63", -1));
    }
}
