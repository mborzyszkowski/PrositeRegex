package com.pg.patternElement;

import com.pg.PrositePattern;

import java.util.List;

public class AnyOneAminoPatternElement implements PatternElement {

    @Override
    public PatternElementResult parse(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (aminoSequence != null && !aminoSequence.isEmpty() && PrositePattern.getAminoDictionary().contains(aminoSequence.subSequence(0, 1))) {
            return new PatternElementResult(aminoSequence.substring(0, 1), this, currentPosition);
        }
        return null;
    }
}
