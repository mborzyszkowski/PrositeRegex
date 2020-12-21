package com.pg.patternElement;

import java.util.List;
import java.util.stream.Stream;

public class SpecificStaticSequencePatternElement implements PatternElement {

    private final String specificAminoSign;
    private final int sequenceLength;

    public SpecificStaticSequencePatternElement(String specificAminoSign, int times) {
        this.specificAminoSign = specificAminoSign;
        this.sequenceLength = times;
    }

    @Override
    public PatternElementResult parse(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (aminoSequence != null && !aminoSequence.isEmpty() && aminoSequence.length() >= this.sequenceLength) {
            String substring = aminoSequence.substring(0, this.sequenceLength);

            if (Stream.of(substring.split("")).allMatch(c -> c.equals(this.specificAminoSign))) {
                return new PatternElementResult(substring, this, currentPosition);
            }
        }

        // borrow requiredEndLength amino from SpecificMovingSequencePatternElement if possible
        StringBuilder resultAminoSequence = new StringBuilder();

        if (aminoSequence != null) {
            for (var character : aminoSequence.split("")) {
                if (this.specificAminoSign.equals(character)) {
                    resultAminoSequence.append(character);
                } else {
                    break;
                }
            }

            if (lastResults.size() > 0) {
                PatternElementResult lastMatchedPattern = lastResults.get(lastResults.size() - 1);
                String symbolToBorrow = lastMatchedPattern.getParsedAminoSequence().substring(0, 1);
                int requiredEndLength = this.sequenceLength - resultAminoSequence.length();

                if (this.specificAminoSign.equals(symbolToBorrow)) {
                    BorrowPossibilities borrowPossibilities = BorrowPossibilities.CheckBorrowPossibility(lastResults, symbolToBorrow);

                    if (borrowPossibilities.getBorrowPossibilitiesLength() >= requiredEndLength) {
                        if (borrowPossibilities.borrow(requiredEndLength)) {
                            return new PatternElementResult(
                                    resultAminoSequence.toString() + generateStringN(symbolToBorrow, requiredEndLength),
                                    this, currentPosition - requiredEndLength);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static String generateStringN(String symbol, int n) {
        return String.valueOf(symbol).repeat(Math.max(0, n));
    }
}
