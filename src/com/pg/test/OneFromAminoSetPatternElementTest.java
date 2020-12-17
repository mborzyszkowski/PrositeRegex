package com.pg.test;

import com.pg.patternElement.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OneFromAminoSetPatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "FC";
        PatternElement patternElement = new OneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertEquals(result.getStartPosition(), 1);
        assertEquals(result.getParsedAminoSequence(), "F");
        assertTrue(result.getPatternElement() instanceof OneFromAminoSetPatternElement);
    }

    @Test
    void ParseOneAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "CA";
        PatternElement patternElement = new OneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new OneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseAminoSequenceWithBorrowing() {
        //Arrange
        String examplePatternString = "AAA";
        List<PatternElementResult> resultList = new ArrayList<>();
        PatternElement patternElement = new SpecificMovingSequencePatternElement("A", 2, 3);
        PatternElementResult result = patternElement.parse(examplePatternString, 1, resultList);
        resultList.add(result);
        examplePatternString = examplePatternString.substring(result.getParsedAminoSequence().length());

        patternElement = new OneFromAminoSetPatternElement("ADFJW");

        // Act
        result = patternElement.parse(examplePatternString, result.getParsedAminoSequence().length() + 1, resultList);

        // Assert
        assertEquals(result.getStartPosition(), 3);
        assertEquals(result.getParsedAminoSequence(), "A");
        assertTrue(result.getPatternElement() instanceof OneFromAminoSetPatternElement);
    }
}