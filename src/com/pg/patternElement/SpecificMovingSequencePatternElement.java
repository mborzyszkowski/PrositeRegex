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
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        throw new UnsupportedOperationException();
    }
}
