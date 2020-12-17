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

            if(Stream.of(substring.split("")).allMatch(c -> c.equals(this.specificAminoSign))) {
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

            if (lastResults.size() > 0 && lastResults.get(lastResults.size() - 1).getPatternElement() instanceof SpecificMovingSequencePatternElement) {
                PatternElementResult lastMatched = lastResults.get(lastResults.size() - 1);
                String lastMatchedAminoString = lastMatched.getParsedAminoSequence();
                int requiredEndLength = this.sequenceLength - resultAminoSequence.length();

                if (lastMatchedAminoString.length() >= ((SpecificMovingSequencePatternElement) lastMatched.getPatternElement()).getMinSequenceLength() + requiredEndLength
                        && ((SpecificMovingSequencePatternElement) lastMatched.getPatternElement()).getSpecificAminoSign().equals(this.specificAminoSign)) {
                    lastMatched.setParsedAminoSequence(lastMatchedAminoString.substring(0, lastMatchedAminoString.length() - requiredEndLength));
                    return new PatternElementResult(resultAminoSequence.toString() + lastMatchedAminoString.substring(lastMatchedAminoString.length() - requiredEndLength),
                            this, currentPosition - requiredEndLength);
                }
            }
        }
        return null;
    }
}
