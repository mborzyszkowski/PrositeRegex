package com.pg.patternElement;

import java.util.List;

public class SpecificStaticSequencePatternElement implements PatternElement {

    private final String specificAminoSign;
    private final int sequenceLength;

    public SpecificStaticSequencePatternElement(String specificAminoSign, int times) {
        this.specificAminoSign = specificAminoSign;
        this.sequenceLength = times;
    }

    @Override
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        throw new UnsupportedOperationException();
    }
}
