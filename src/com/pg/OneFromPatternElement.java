package com.pg;

import com.pg.patternElement.PatternElement;
import com.pg.patternElement.PatternElementResult;

import java.util.List;

public class OneFromPatternElement implements PatternElement {

    private final String dictionary;

    public OneFromPatternElement(String dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public PatternElementResult parsePattern(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (!aminoSequence.isEmpty() && Pattern.getAminoDictionary().contains(aminoSequence.subSequence(0, 1)) && this.dictionary.contains(aminoSequence.subSequence(0, 1))) {
            return new PatternElementResult(aminoSequence.substring(0, 1), this, currentPosition);
        }
        return null;
    }
}
