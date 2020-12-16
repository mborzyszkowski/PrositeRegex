package com.pg.patternElement;

import java.util.List;

public class SpecificMovingSequencePatternElement implements PatternElement {

    private final String specificAmino;
    private final int firstNumber;
    private final int secondNumber;

    public SpecificMovingSequencePatternElement(String specificAmino, int firstNumber, int secondNumber) {
        this.specificAmino = specificAmino;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    @Override
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        throw new UnsupportedOperationException();
    }
}
