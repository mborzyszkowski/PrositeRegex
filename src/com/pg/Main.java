package com.pg;

import com.pg.patternElement.PatternElementResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        PrositePattern pattern;
//        String patternString = "[RK]-G-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";                         // base example
//        String aminoAcidSequence = "SRSLKMRGQAFVIFKEVSSATKLTGRPRGVAFVRYNKREEAQVGCSVHKGFAFVQYVNERNAR";
        String patternString = "[RK]-G(1,3)-G(2)-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";               // full example
        String aminoAcidSequence = "SRSLKMRGGGQAFVIFKEVSSATKLTGRPRGGGVAFVRYNKREEAQVGCSVHKGGGFAFVQYVNERNAR";

        try {
            pattern = PrositePattern.CreatePattern(patternString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        List<List<PatternElementResult>> matchedResults = pattern.match(aminoAcidSequence);
        List<String> results = new ArrayList<>();
        for (var matchResult : matchedResults) {
            String matchedAminoAcid =
                    matchResult.stream()
                            .map(PatternElementResult::getParsedAminoSequence)
                            .collect(Collectors.joining());
            int startIdx = 1 +
                    matchResult.stream()
                            .findFirst()
                            .map(PatternElementResult::getStartPosition)
                            .orElse(0);
            results.add("Position " + startIdx + ": \t" + matchedAminoAcid);
        }
        results.forEach(System.out::println);
    }
}
