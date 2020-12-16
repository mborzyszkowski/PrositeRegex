package com.pg.patternElement;

public class PatternElementResult {

    String parsedAminoSequence;
    PatternElement patternElement;
    int startPosition;

    public PatternElementResult(String parsedAminoSequence, PatternElement patternElement, int startPosition) {
        this.parsedAminoSequence = parsedAminoSequence;
        this.patternElement = patternElement;
        this.startPosition = startPosition;
    }
}
