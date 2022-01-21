import com.google.gson.Gson;
import com.mrgrd56.somePackage.models.Person;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

public class JsonProgram {
    private static final Gson GSON = new Gson();

    public static void main(String[] args) throws IOException {
        var resourceStream = JsonProgram.class.getResourceAsStream("person.json");
        assert resourceStream != null;
        var jsonBytes = resourceStream.readAllBytes();
        var jsonString = new String(jsonBytes, StandardCharsets.UTF_8);

        var person = GSON.fromJson(jsonString, Person.class);

        var petBirthDate = person.getPet().getBirthDate();

        var petBirthDateString = new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(petBirthDate);
        System.out.println(petBirthDateString);
    }
}
