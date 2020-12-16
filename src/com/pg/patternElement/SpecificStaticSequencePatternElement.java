package com.pg.patternElement;

import java.util.List;

public class SpecificStaticSequencePatternElement implements PatternElement {

    private final String specificAmino;
    private final int times;

    public SpecificStaticSequencePatternElement(String specificAmino, int times) {
        this.specificAmino = specificAmino;
        this.times = times;
    }

    @Override
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        throw new UnsupportedOperationException();
    }
}
