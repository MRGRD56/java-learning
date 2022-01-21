import com.google.gson.Gson;
import com.google.gson.internal.JavaVersion;
import com.mrgrd56.somePackage.models.Person;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
