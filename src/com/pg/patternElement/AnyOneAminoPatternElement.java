package com.pg.patternElement;

import com.pg.Pattern;

import java.util.List;

public class AnyOneAminoPatternElement implements PatternElement {

    @Override
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (!aminoSequence.isEmpty() && Pattern.getAminoDictionary().contains(aminoSequence.subSequence(0, 1))) {
            return new PatternElementResult(aminoSequence.substring(0, 1), this, currentPosition);
        }
        return null;
    }
}
