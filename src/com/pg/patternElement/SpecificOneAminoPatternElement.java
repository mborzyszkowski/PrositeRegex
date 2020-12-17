package com.pg.patternElement;

import com.pg.PrositePattern;

import java.util.List;

public class SpecificOneAminoPatternElement implements PatternElement {

    private final String specificAminoSign;

    public SpecificOneAminoPatternElement(String specificAminoSign) {
        this.specificAminoSign = specificAminoSign;
    }

    @Override
    public PatternElementResult parse(String aminoSequence, int currentPosition, List<PatternElementResult> lastResults) {
        if (aminoSequence != null && !aminoSequence.isEmpty() && PrositePattern.getAminoDictionary().contains(aminoSequence.subSequence(0, 1))
                && specificAminoSign.equals(aminoSequence.substring(0, 1))) {
            return new PatternElementResult(this.specificAminoSign, this, currentPosition);
        }

        // borrow one amino from SpecificMovingSequencePatternElement if possible
        if (lastResults.size() > 0 && lastResults.get(lastResults.size() - 1).getPatternElement() instanceof SpecificMovingSequencePatternElement) {
            PatternElementResult lastMatched = lastResults.get(lastResults.size() - 1);
            String lastMatchedAminoString = lastMatched.getParsedAminoSequence();

            if (lastMatchedAminoString.length() > ((SpecificMovingSequencePatternElement)lastMatched.getPatternElement()).getMinSequenceLength()
                    && ((SpecificMovingSequencePatternElement) lastMatched.getPatternElement()).getSpecificAminoSign().equals(this.specificAminoSign)) {
                lastMatched.setParsedAminoSequence(lastMatchedAminoString.substring(0, lastMatchedAminoString.length() - 1));
                return new PatternElementResult(lastMatchedAminoString.substring(lastMatchedAminoString.length() - 1), this, currentPosition - 1);
            }
        }
        return null;
    }
}
