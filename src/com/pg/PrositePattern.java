package com.pg;

import com.pg.patternElement.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrositePattern {

    private static final String AMINO_SYMBOLS = "ACDEFGHIKLMNPQRSTVWY";
    public static final String SEPARATOR = "-";
    private final List<PatternElement> patternElements;

    private PrositePattern(List<PatternElement> patternElements) {
        this.patternElements = patternElements;
    }

    public List<String> match(String aminoAcidSequence) {
        List<String> results = new ArrayList<>();

        for (int i = 0; i < aminoAcidSequence.length(); i++) {
            String currentAminoAcidSequence = aminoAcidSequence.substring(i);
            List<PatternElementResult> matchedPatterns = new ArrayList<>();
            int currentAminoAcidIdx = i;
            boolean fullyMatched = true;

            for (var patternElement : this.patternElements) {
                PatternElementResult matchedResult = patternElement.parse(currentAminoAcidSequence, currentAminoAcidIdx, matchedPatterns);
                if (matchedResult != null) {
                    matchedPatterns.add(matchedResult);
                    currentAminoAcidIdx = i +
                            matchedPatterns.stream()
                                    .map(r -> r.getParsedAminoSequence().length())
                                    .reduce(Integer::sum)
                                    .orElse(0);
                    currentAminoAcidSequence = aminoAcidSequence.substring(currentAminoAcidIdx);
                } else {
                    fullyMatched = false;
                    break;
                }
            }

            if (fullyMatched) {
                String matchedAminoAcid =
                        matchedPatterns.stream()
                                .map(PatternElementResult::getParsedAminoSequence)
                                .collect(Collectors.joining());
                int startIdx = 1 +
                        matchedPatterns.stream()
                                .findFirst()
                                .map(PatternElementResult::getStartPosition)
                                .orElse(0);
                results.add("Position " + startIdx + ": \t" + matchedAminoAcid);
            }
        }
        return results;
    }

    public static PrositePattern CreatePattern(String stringPattern) throws Exception {
        List<PatternElement> patternElements = parsePatternElementList(stringPattern);
        return new PrositePattern(patternElements);
    }

    private static List<PatternElement> parsePatternElementList(String stringPattern) throws Exception {
        List<PatternElement> result =
                Arrays.stream(stringPattern.split(SEPARATOR))
                        .map(PrositePattern::parsePatternElement)
                        .collect(Collectors.toList());

        List<ErrorPatternElement> patternElementList =
                result.stream()
                        .filter(e -> e instanceof ErrorPatternElement)
                        .map(e -> (ErrorPatternElement) e)
                        .collect(Collectors.toList());

        if (!patternElementList.isEmpty())
            throw new Exception(
                    patternElementList.stream()
                            .map(ErrorPatternElement::getError)
                            .collect(Collectors.joining("\n")));

        return result;
    }

    private static PatternElement parsePatternElement(String stringPatternElement) {
        if (stringPatternElement.isEmpty()) {
            return new ErrorPatternElement("Empty pattern");
        }

        if (stringPatternElement.equals("x")) {                                                         // x
            return new AnyOneAminoPatternElement();
        } else if (AMINO_SYMBOLS.contains(stringPatternElement.subSequence(0, 1))) {
            if (stringPatternElement.length() == 1) {                                                   // V
                return new SpecificOneAminoPatternElement(stringPatternElement.substring(0, 1));
            } else if (stringPatternElement.charAt(1) == '(') {                                         // e(i) || e(i, j)
                return parseSequentialPatternElement(stringPatternElement);
            } else {
                return new ErrorPatternElement(stringPatternElement);
            }
        } else if (stringPatternElement.charAt(0) == '[' || stringPatternElement.charAt(0) == '{') {    // [...] || {...}
            return parseFromPatternElement(stringPatternElement);
        }

        return new ErrorPatternElement(stringPatternElement);
    }

    private static PatternElement parseSequentialPatternElement(String stringPatternElement) {
        StringBuilder number = new StringBuilder();
        char lastCharacter = 0;

        for (var character : stringPatternElement.substring(2).toCharArray()) {
            if (character == ',' || character == ')') {
                lastCharacter = character;
                break;
            } else {
                number.append(character);
            }
        }

        if (lastCharacter == 0) {
            return new ErrorPatternElement(stringPatternElement);
        }

        int firstNumber;
        try {
            firstNumber = Integer.parseInt(number.toString());
            if (firstNumber < 1) {
                return new ErrorPatternElement("First number of sequence " + stringPatternElement + " should be positive no zero integer");
            }
        } catch (Exception e) {
            return new ErrorPatternElement(stringPatternElement);
        }

        if (lastCharacter == ')' && stringPatternElement.charAt(stringPatternElement.length() - 1) == ')') {
            return new SpecificStaticSequencePatternElement(stringPatternElement.substring(0, 1), firstNumber);
        }
        if (lastCharacter == ',') {
            String part = stringPatternElement.split(",")[1];
            number = new StringBuilder();
            lastCharacter = 0;

            for (var character : part.toCharArray()) {
                if (character == ')') {
                    lastCharacter = character;
                    break;
                } else {
                    number.append(character);
                }
            }

            if (lastCharacter == 0) {
                return new ErrorPatternElement(stringPatternElement);
            }

            int secondNumber;
            try {
                secondNumber = Integer.parseInt(number.toString());
                if (secondNumber < 1) {
                    return new ErrorPatternElement("Second number of sequence " + stringPatternElement + " should be positive nonzero integer");
                }
            } catch (Exception e) {
                return new ErrorPatternElement(stringPatternElement);
            }

            if (firstNumber > secondNumber) {
                return new ErrorPatternElement("First number of sequence " + stringPatternElement + " can not be grater then second number");
            }

            if (stringPatternElement.charAt(stringPatternElement.length() - 1) == ')') {
                return new SpecificMovingSequencePatternElement(stringPatternElement.substring(0, 1), firstNumber, secondNumber);
            }
        }

        return new ErrorPatternElement("");
    }

    private static PatternElement parseFromPatternElement(String stringPatternElement) {
        StringBuilder aminoPattern = new StringBuilder();

        for (var character : stringPatternElement.substring(1).toCharArray()) {
            if (AMINO_SYMBOLS.contains(String.valueOf(character))) {
                aminoPattern.append(character);
            } else if (character == ']' || character == '}') {
                break;
            } else {
                return new ErrorPatternElement("Element '" + character + "' in pattern " + stringPatternElement + " does not match the amino acid dictionary");
            }
        }

        if (aminoPattern.length() + 2 != stringPatternElement.length()
                || (stringPatternElement.charAt(0) == '[' && stringPatternElement.charAt(stringPatternElement.length() - 1) != ']')
                || (stringPatternElement.charAt(0) == '{' && stringPatternElement.charAt(stringPatternElement.length() - 1) != '}')) {
            return new ErrorPatternElement(stringPatternElement);
        }

        if (stringPatternElement.charAt(0) == '[') {
            return new OneFromAminoSetPatternElement(aminoPattern.toString());
        } else if (stringPatternElement.charAt(0) == '{') {
            return new NoOneFromAminoSetPatternElement(aminoPattern.toString());
        }

        return new ErrorPatternElement(stringPatternElement);
    }

    // GETTERS AND SETTERS

    public List<PatternElement> getPatternElements() {
        return patternElements;
    }

    public static String getAminoDictionary() {
        return AMINO_SYMBOLS;
    }
}
