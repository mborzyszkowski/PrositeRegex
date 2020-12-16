package com.pg.patternElement;

import java.util.List;

public class SpecificMovingSequencePatternElement implements PatternElement {

    private final String specificAminoSign;
    private final int minSequenceLength;
    private final int maxSequenceLength;

    public SpecificMovingSequencePatternElement(String specificAminoSign, int minSequenceLength, int maxSequenceLength) {
        this.specificAminoSign = specificAminoSign;
        this.minSequenceLength = minSequenceLength;
        this.maxSequenceLength = maxSequenceLength;
    }

    @Override
    public PatternElementResult parse(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (aminoSequence != null && !aminoSequence.isEmpty() && aminoSequence.length() >= this.minSequenceLength) {
            StringBuilder resultSequence = new StringBuilder();
            int counter = 0;

            for (var character : aminoSequence.toCharArray()) {
                if (this.specificAminoSign.equals(String.valueOf(character)) && this.maxSequenceLength > counter) {
                    resultSequence.append(character);
                    counter++;
                } else {
                    break;
                }
            }

            if (this.maxSequenceLength >= resultSequence.length() && this.minSequenceLength <= resultSequence.length()) {
                return new PatternElementResult(resultSequence.toString(), this, currentPosition);
            }
        }
        return null;
    }
}
