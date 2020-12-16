package com.pg;

public class Main {

    public static void main(String[] args) {
        Pattern pattern;
//        String examplePatternString = "[RK]-G-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";    //base example
        String examplePatternString = "[RK]-G(2a)-G(2,3b)a-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";

        try {
            pattern = Pattern.CreatePattern(examplePatternString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }


        // match results by pattern
        // print results


    }
}
