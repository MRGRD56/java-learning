import com.mrgrd56.somePackage.lombok.HTMLElement;
import lombok.val;

public class LombokProgram {
    public static void main(String[] args) {
        val element = HTMLElement.builder()
                .id("first-form")
                .className("form ajax-form")
                .child(new HTMLElement())
                .build();


    }
}
