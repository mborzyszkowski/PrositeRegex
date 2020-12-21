package com.pg.test;

import com.pg.patternElement.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoOneFromAminoSetPatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "CA";
        PatternElement patternElement = new NoOneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertEquals(result.getStartPosition(), 1);
        assertEquals(result.getParsedAminoSequence(), "C");
        assertTrue(result.getPatternElement() instanceof NoOneFromAminoSetPatternElement);
    }

    @Test
    void ParseOneAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "FA";
        PatternElement patternElement = new NoOneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new NoOneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseAminoSequenceWithBorrowing() {
        //Arrange
        String examplePatternString = "CCC";
        PatternElement firstParsed = new SpecificMovingSequencePatternElement("C", 1, 3);
        PatternElement secondParsed = new NoOneFromAminoSetPatternElement("ADFJW");
        List<PatternElementResult> resultList =
                Arrays.asList(
                        new PatternElementResult("CC", firstParsed, 0),
                        new PatternElementResult("C", secondParsed, 2));

        PatternElement patternElement = new NoOneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parse("", 3, resultList);

        // Assert
        assertEquals(result.getStartPosition(), 2);
        assertEquals(result.getParsedAminoSequence(), "C");
        assertTrue(result.getPatternElement() instanceof NoOneFromAminoSetPatternElement);
    }
}