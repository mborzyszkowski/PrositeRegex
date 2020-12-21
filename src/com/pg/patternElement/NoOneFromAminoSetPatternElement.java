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
        if (lastResults.size() > 0) {
            PatternElementResult lastMatchedPattern = lastResults.get(lastResults.size() - 1);
            String symbolToBorrow = lastMatchedPattern.getParsedAminoSequence().substring(0, 1);

            if (!this.dictionary.contains(symbolToBorrow)) {
                BorrowPossibilities borrowPossibilities = BorrowPossibilities.CheckBorrowPossibility(lastResults, symbolToBorrow);

                if (borrowPossibilities.getBorrowPossibilitiesLength() >= 1) {
                    if (borrowPossibilities.borrow(1)) {
                        return new PatternElementResult(symbolToBorrow, this, currentPosition - 1);
                    }
                }
            }
        }
        return null;
    }
}
