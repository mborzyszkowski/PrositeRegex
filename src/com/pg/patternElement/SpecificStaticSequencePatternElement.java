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
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (aminoSequence != null && !aminoSequence.isEmpty() && aminoSequence.length() >= this.sequenceLength) {
            String substring = aminoSequence.substring(0, this.sequenceLength);

            if(Stream.of(substring.split("")).allMatch(c -> c.equals(specificAminoSign))) {
                return new PatternElementResult(substring, this, currentPosition);
            }
        }
        return null;
    }
}
