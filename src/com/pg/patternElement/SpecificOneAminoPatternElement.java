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
        if (lastResults.size() > 0) {
            PatternElementResult lastMatchedPattern = lastResults.get(lastResults.size() - 1);
            String symbolToBorrow = lastMatchedPattern.getParsedAminoSequence().substring(0, 1);

            if (this.specificAminoSign.equals(symbolToBorrow)) {
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
