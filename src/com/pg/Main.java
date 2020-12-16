package com.pg;

public class Main {

    public static void main(String[] args) {
        PrositePattern pattern;
//        String examplePatternString = "[RK]-G-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";    //base example
        String examplePatternString = "[RK]-G(1)-G(2,3)-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";

        try {
            pattern = PrositePattern.CreatePattern(examplePatternString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }


        // match results by pattern
        // print results


    }
}
