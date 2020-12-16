package com.pg.patternElement;

import com.pg.Pattern;

import java.util.List;

public class SpecificOneAminoPatternElement implements PatternElement {

    private final String specificAmino;

    public SpecificOneAminoPatternElement(String specificAmino) {
        this.specificAmino = specificAmino;
    }

    @Override
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (!aminoSequence.isEmpty() && Pattern.getAminoDictionary().contains(aminoSequence.subSequence(0, 1)) && specificAmino.equals(aminoSequence.substring(0, 1))) {
            return new PatternElementResult(this.specificAmino, this, currentPosition);
        }
        return null;
    }
}
