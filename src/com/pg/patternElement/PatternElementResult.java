package com.pg.patternElement;

public class PatternElementResult {

    private String parsedAminoSequence;
    private PatternElement patternElement;
    private  int startPosition;

    public PatternElementResult(String parsedAminoSequence, PatternElement patternElement, int startPosition) {
        this.parsedAminoSequence = parsedAminoSequence;
        this.patternElement = patternElement;
        this.startPosition = startPosition;
    }

    // GETTERS AND SETTERS

    public String getParsedAminoSequence() {
        return parsedAminoSequence;
    }

    public void setParsedAminoSequence(String parsedAminoSequence) {
        this.parsedAminoSequence = parsedAminoSequence;
    }

    public PatternElement getPatternElement() {
        return patternElement;
    }

    public void setPatternElement(PatternElement patternElement) {
        this.patternElement = patternElement;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
}
