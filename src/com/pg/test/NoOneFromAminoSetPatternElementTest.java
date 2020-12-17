package com.pg.test;

import com.pg.patternElement.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        List<PatternElementResult> resultList = new ArrayList<>();
        PatternElement patternElement = new SpecificMovingSequencePatternElement("C", 2, 3);
        PatternElementResult result = patternElement.parse(examplePatternString, 1, resultList);
        resultList.add(result);
        examplePatternString = examplePatternString.substring(result.getParsedAminoSequence().length());

        patternElement = new NoOneFromAminoSetPatternElement("ADFJW");

        // Act
        result = patternElement.parse(examplePatternString, result.getParsedAminoSequence().length() + 1, resultList);

        // Assert
        assertEquals(result.getStartPosition(), 3);
        assertEquals(result.getParsedAminoSequence(), "C");
        assertTrue(result.getPatternElement() instanceof NoOneFromAminoSetPatternElement);
    }
}