package com.pg.patternElement;

import java.util.List;

public interface PatternElement {
    PatternElementResult parsePattern(String aminoSequence, int currentPosition, final List<PatternElementResult> lastResults);
}
