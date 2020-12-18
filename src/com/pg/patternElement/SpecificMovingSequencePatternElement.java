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

            if (lastResults.size() > 0 && lastResults.get(lastResults.size() - 1).getPatternElement() instanceof SpecificMovingSequencePatternElement) {
                PatternElementResult lastMatched = lastResults.get(lastResults.size() - 1);
                String lastMatchedAminoString = lastMatched.getParsedAminoSequence();
                int requiredEndLength = this.minSequenceLength - resultSequence.length();

                if (lastMatchedAminoString.length() >= ((SpecificMovingSequencePatternElement) lastMatched.getPatternElement()).getMinSequenceLength() + requiredEndLength
                        && ((SpecificMovingSequencePatternElement) lastMatched.getPatternElement()).getSpecificAminoSign().equals(this.specificAminoSign)) {
                    int maxBorrow = Math.min(this.maxSequenceLength - resultSequence.length(),
                            lastMatched.getParsedAminoSequence().length() - ((SpecificMovingSequencePatternElement)lastMatched.getPatternElement()).getMinSequenceLength());
                    lastMatched.setParsedAminoSequence(lastMatchedAminoString.substring(0, lastMatchedAminoString.length() - maxBorrow));
                    return new PatternElementResult(resultSequence.toString() + lastMatchedAminoString.substring(lastMatchedAminoString.length() - maxBorrow),
                            this, currentPosition - maxBorrow);
                }
            }

            if (this.maxSequenceLength >= resultSequence.length() && this.minSequenceLength <= resultSequence.length()) {
                return new PatternElementResult(resultSequence.toString(), this, currentPosition);
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

            if (lastResults.size() > 0 && lastResults.get(lastResults.size() - 1).getPatternElement() instanceof SpecificMovingSequencePatternElement) {
                PatternElementResult lastMatched = lastResults.get(lastResults.size() - 1);
                String lastMatchedAminoString = lastMatched.getParsedAminoSequence();
                int requiredEndLength = this.minSequenceLength - resultAminoSequence.length();

                if (lastMatchedAminoString.length() >= ((SpecificMovingSequencePatternElement) lastMatched.getPatternElement()).getMinSequenceLength() + requiredEndLength
                        && ((SpecificMovingSequencePatternElement) lastMatched.getPatternElement()).getSpecificAminoSign().equals(this.specificAminoSign)) {
                    int maxBorrow = Math.min(this.maxSequenceLength - resultAminoSequence.length(),
                            lastMatched.getParsedAminoSequence().length() - ((SpecificMovingSequencePatternElement)lastMatched.getPatternElement()).getMinSequenceLength());
                    lastMatched.setParsedAminoSequence(lastMatchedAminoString.substring(0, lastMatchedAminoString.length() - maxBorrow));
                    return new PatternElementResult(resultAminoSequence.toString() + lastMatchedAminoString.substring(lastMatchedAminoString.length() - maxBorrow),
                            this, currentPosition - maxBorrow);
                }
            }
        }
        return null;
    }

    // GETTERS AND SETTERS

    public String getSpecificAminoSign() {
        return specificAminoSign;
    }

    public int getMinSequenceLength() {
        return minSequenceLength;
    }

    public int getMaxSequenceLength() {
        return maxSequenceLength;
    }
}
