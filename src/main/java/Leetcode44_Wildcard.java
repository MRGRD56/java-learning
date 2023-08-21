import java.util.ArrayList;
import java.util.List;

public class Leetcode44_Wildcard {
    public static void main(String[] args) {
        Solution.WildcardPattern pattern = Solution.WildcardPattern.parse("*abc???de*");

        boolean matches1 = pattern.matches("abcabczzzde");
        boolean matches2 = pattern.matches("abcwerwerabczzzdeabc");

        Object __null = null;
    }

    class Solution {
        public boolean isMatch(String s, String p) {
            WildcardPattern pattern = WildcardPattern.parse(p);
            return pattern.matches(s);
        }

        public static class WildcardPattern {
            private final List<PatternPart> parts;

            private WildcardPattern(List<PatternPart> parts) {
                this.parts = parts;
            }

            public static WildcardPattern parse(String pattern) {
                final char wildcardAnySequenceChar = WildcardPatternPart.ANY_SEQUENCE.getCharacter();
                final char wildcardSingleChar = WildcardPatternPart.ANY_CHARACTER.getCharacter();

                List<PatternPart> parts = new ArrayList<>();
                StringBuilder textPartBuilder = new StringBuilder();

                for (char character : pattern.toCharArray()) {
                    boolean isAnySequenceChar = character == wildcardAnySequenceChar;

                    if (isAnySequenceChar || character == wildcardSingleChar) {
                        if (!textPartBuilder.isEmpty()) {
                            parts.add(new TextPatternPart(textPartBuilder.toString()));
                            textPartBuilder.setLength(0);
                        }

                        if (isAnySequenceChar) {
                            parts.add(WildcardPatternPart.ANY_SEQUENCE);
                        } else {
                            parts.add(WildcardPatternPart.ANY_CHARACTER);
                        }

                        continue;
                    }

                    textPartBuilder.append(character);
                }

                if (!textPartBuilder.isEmpty()) {
                    parts.add(new TextPatternPart(textPartBuilder.toString()));
                }

                return new WildcardPattern(parts);
            }

            public boolean matches(String input) {
                boolean isAfterAnySequenceWildcard = false;

                int index = 0;
                for (PatternPart part : parts) {
                    if (part.isTextSequence()) {
                        String textPart = part.asTextPart().getText();

                        if (isAfterAnySequenceWildcard) {
                            int textPartIndex = input.indexOf(textPart, index);
                            if (textPartIndex == -1) {
                                return false;
                            }
                            index = textPartIndex + textPart.length();
                        } else {
                            if (!input.startsWith(textPart, index)) {
                                return false;
                            }
                            index += textPart.length();
                        }

//                        int textPartIndex = input.indexOf(textPart, index);
//
//                        if (textPartIndex == -1) {
//                            return false;
//                        }
//
//                        if (textPartIndex != index && !isAfterAnySequenceWildcard) {
//                            return false;
//                        }

//                        index = textPartIndex + textPart.length();
                        isAfterAnySequenceWildcard = false;
                        continue;
                    }

                    if (part.isAnySequence()) {
                        isAfterAnySequenceWildcard = true;
                        continue;
                    }

                    if (part.isAnyCharacter()) {
                        if (index >= input.length()) {
                            return false;
                        }

                        index++;
                        isAfterAnySequenceWildcard = false;
                        continue;
                    }
                }

                return isAfterAnySequenceWildcard || index == input.length();
            }

            private interface PatternPart {
                default boolean isAnyCharacter() {
                    return false;
                }

                default boolean isAnySequence() {
                    return false;
                }

                default boolean isTextSequence() {
                    return false;
                }

                default TextPatternPart asTextPart() {
                    throw new UnsupportedOperationException();
                }
            }

            private static class TextPatternPart implements PatternPart {
                private final String text;

                public TextPatternPart(String text) {
                    this.text = text;
                }

                @Override
                public boolean isTextSequence() {
                    return true;
                }

                @Override
                public TextPatternPart asTextPart() {
                    return this;
                }

                public String getText() {
                    return text;
                }

                @Override
                public String toString() {
                    return text;
                }
            }

            private enum WildcardPatternPart implements PatternPart {
                ANY_CHARACTER {
                    @Override
                    public char getCharacter() {
                        return '?';
                    }

                    @Override
                    public boolean isAnyCharacter() {
                        return true;
                    }
                },
                ANY_SEQUENCE {
                    @Override
                    public char getCharacter() {
                        return '*';
                    }

                    @Override
                    public boolean isAnySequence() {
                        return true;
                    }
                };

                public abstract char getCharacter();

                @Override
                public String toString() {
                    return Character.toString(getCharacter());
                }
            }
        }
    }
}
