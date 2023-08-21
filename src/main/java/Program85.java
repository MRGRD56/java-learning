import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Program85 {
    public static void main(String[] args) {
        for (ScriptEngineFactory se : new ScriptEngineManager(null).getEngineFactories()) {
            System.out.println("se = " + se.getEngineName());
            System.out.println("se = " + se.getEngineVersion());
            System.out.println("se = " + se.getLanguageName());
            System.out.println("se = " + se.getLanguageVersion());
            System.out.println("se = " + se.getNames());
        }

        ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName("nashorn");

        try {
            jsEngine.eval("throw new Error('hello from js')");
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }
}
