package com.pg.patternElement;

import com.pg.PrositePattern;

import java.util.List;

public class SpecificOneAminoPatternElement implements PatternElement {

    private final String specificAminoSign;

    public SpecificOneAminoPatternElement(String specificAminoSign) {
        this.specificAminoSign = specificAminoSign;
    }

    @Override
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (!aminoSequence.isEmpty() && PrositePattern.getAminoDictionary().contains(aminoSequence.subSequence(0, 1)) && specificAminoSign.equals(aminoSequence.substring(0, 1))) {
            return new PatternElementResult(this.specificAminoSign, this, currentPosition);
        }
        return null;
    }
}
