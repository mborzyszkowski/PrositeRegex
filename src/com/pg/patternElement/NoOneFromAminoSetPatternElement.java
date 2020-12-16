package com.pg.patternElement;

import com.pg.PrositePattern;

import java.util.List;

public class NoOneFromAminoSetPatternElement implements PatternElement {

    private final String dictionary;

    public NoOneFromAminoSetPatternElement(String dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (aminoSequence != null && !aminoSequence.isEmpty() && PrositePattern.getAminoDictionary().contains(aminoSequence.subSequence(0, 1))
                && !this.dictionary.contains(aminoSequence.subSequence(0, 1))) {
            return new PatternElementResult(aminoSequence.substring(0, 1), this, currentPosition);
        }
        return null;
    }
}
