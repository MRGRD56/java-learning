import java.util.ArrayList;
import java.util.List;

public class Program26 {
    private String word;

    public Program26(String word) {
        this.word = word;
    }

    public static void main(String[] args) {
        Program26 p26 = new Program26("hello2");
        Program26.WordProgram wp = p26.new WordProgram();

//        class WordProgramPlus extends WordProgram {
//            @Override
//            public String getWord() {
//                return super.getWord() + " PLUS";
//            }
//        }

        Program26.WordProgram wp2 = p26.new WordProgram() {
            @Override
            public String getWord() {
                return super.getWord() + " PLUS";
            }
        };

        Object __null = null;
    }

    private class WordProgram {
        public String getWord() {
            return Program26.this.word;
        }
    }
}