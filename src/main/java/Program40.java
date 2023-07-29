import edu.stanford.nlp.simple.*;

import java.util.List;

public class Program40 {
    public static void main(String[] args) {
        Sentence sentence = new Sentence(
                "For information about making contributions to Stanford CoreNLP, see the file.");
        List<Token> tokens = sentence.tokens();

        for (Token token : tokens) {
            System.out.printf("%s - %s\n", token.lemma(), token.tag());
        }
    }
}
