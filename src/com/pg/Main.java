package com.pg;

import java.util.List;

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

        List<String> matchedResults = pattern.match(aminoAcidSequence);
        matchedResults.forEach(System.out::println);
    }
}
