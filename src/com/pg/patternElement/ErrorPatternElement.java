package com.pg.patternElement;

import java.util.List;

public class ErrorPatternElement implements PatternElement {

    private final String invalidPatternElement;

    public ErrorPatternElement(String invalidPatternElement) {
        this.invalidPatternElement = invalidPatternElement;
    }

    @Override
    public PatternElementResult parse(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        return null;
    }

    public String getError() {
        return "Invalid pattern element: " + this.invalidPatternElement;
    }
}
