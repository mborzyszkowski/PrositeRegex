package com.pg.patternElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BorrowPossibilities {

    private final List<PatternElementResult> resultToBorrow;
    private int borrowPossibilitiesLength;
    private final String borrowSymbol;

    private BorrowPossibilities(List<PatternElementResult> resultToBorrow, int borrowPossibilitiesLength, String borrowType) {
        this.resultToBorrow = resultToBorrow;
        this.borrowPossibilitiesLength = borrowPossibilitiesLength;
        this.borrowSymbol = borrowType;
    }

    public boolean borrow(int count) {
        int borrowedCount = 0;

        for (var res : this.resultToBorrow) {
            res.setStartPosition(res.getStartPosition() - borrowedCount);

            if (borrowedCount != count && res.getPatternElement() instanceof SpecificMovingSequencePatternElement) {
                SpecificMovingSequencePatternElement smspe = (SpecificMovingSequencePatternElement) res.getPatternElement();
                int currentBorrow = Math.min(res.getParsedAminoSequence().length() - smspe.getMinSequenceLength(), count - borrowedCount);
                borrowedCount += currentBorrow;
                res.setParsedAminoSequence(res.getParsedAminoSequence().substring(currentBorrow));
            }
        }

        this.borrowPossibilitiesLength -= borrowedCount;
        return count == borrowedCount;
    }

    public static BorrowPossibilities CheckBorrowPossibility(List<PatternElementResult> lastResults, String borrowSymbol) {
        List<PatternElementResult> reverseResults = new ArrayList<>(lastResults);
        Collections.reverse(reverseResults);
        List<PatternElementResult> orderedResultHavingTheSameSymbol = new ArrayList<>();
        int borrowPossibilitiesLength = 0;

        for (var res : reverseResults) {
            if (Arrays.stream(res.getParsedAminoSequence().split("")).allMatch(p -> p.equals(borrowSymbol))) {
                orderedResultHavingTheSameSymbol.add(res);
                if (res.getPatternElement() instanceof SpecificMovingSequencePatternElement) {
                    SpecificMovingSequencePatternElement smspe = (SpecificMovingSequencePatternElement) res.getPatternElement();
                    borrowPossibilitiesLength += res.getParsedAminoSequence().length() - smspe.getMinSequenceLength();
                }
            } else {
                break;
            }
        }

        Collections.reverse(orderedResultHavingTheSameSymbol);

        return new BorrowPossibilities(orderedResultHavingTheSameSymbol, borrowPossibilitiesLength, borrowSymbol);
    }

    public int getBorrowPossibilitiesLength() {
        return borrowPossibilitiesLength;
    }
}
