package com.pg;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        PrositePattern pattern;
        String patternString = "[RK]-G-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";              // base example
//        String patternString = "[RK]-G(1)-G(2,3)-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";    // full example
        String aminoAcidSequence = "SRSLKMRGQAFVIFKEVSSATKLTGRPRGVAFVRYNKREEAQVGCSVHKGFAFVQYVNERNAR";

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
