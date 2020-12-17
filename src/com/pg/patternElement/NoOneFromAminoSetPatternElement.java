package com.pg.patternElement;

import com.pg.PrositePattern;

import java.util.List;

public class NoOneFromAminoSetPatternElement implements PatternElement {

    private final String dictionary;

    public NoOneFromAminoSetPatternElement(String dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public PatternElementResult parse(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (aminoSequence != null && !aminoSequence.isEmpty() && PrositePattern.getAminoDictionary().contains(aminoSequence.subSequence(0, 1))
                && !this.dictionary.contains(aminoSequence.subSequence(0, 1))) {
            return new PatternElementResult(aminoSequence.substring(0, 1), this, currentPosition);
        }

        // borrow one amino from SpecificMovingSequencePatternElement if possible
        if (lastResults.size() > 0 && lastResults.get(lastResults.size() - 1).getPatternElement() instanceof SpecificMovingSequencePatternElement) {
            PatternElementResult lastMatched = lastResults.get(lastResults.size() - 1);
            String lastMatchedAminoString = lastMatched.getParsedAminoSequence();

            if (lastMatchedAminoString.length() > ((SpecificMovingSequencePatternElement)lastMatched.getPatternElement()).getMinSequenceLength()
                    && !this.dictionary.contains(((SpecificMovingSequencePatternElement) lastMatched.getPatternElement()).getSpecificAminoSign())) {
                lastMatched.setParsedAminoSequence(lastMatchedAminoString.substring(0, lastMatchedAminoString.length() - 1));
                return new PatternElementResult(lastMatchedAminoString.substring(lastMatchedAminoString.length() - 1), this, currentPosition - 1);
            }
        }
        return null;
    }
}
