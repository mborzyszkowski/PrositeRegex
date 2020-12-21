package com.pg.test;

import com.pg.patternElement.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecificOneAminoPatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "AC";
        PatternElement patternElement = new SpecificOneAminoPatternElement("A");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertEquals(result.getStartPosition(), 1);
        assertEquals(result.getParsedAminoSequence(), "A");
        assertTrue(result.getPatternElement() instanceof SpecificOneAminoPatternElement);
    }

    @Test
    void ParseOneAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "CA";
        PatternElement patternElement = new SpecificOneAminoPatternElement("A");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new SpecificOneAminoPatternElement("A");

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseAminoSequenceWithBorrowing() {
        //Arrange
        String examplePatternString = "AAA";
        PatternElement firstParsed = new SpecificMovingSequencePatternElement("A", 1, 3);
        PatternElement secondParsed = new SpecificOneAminoPatternElement("A");
        List<PatternElementResult> resultList =
                Arrays.asList(
                        new PatternElementResult("AA", firstParsed, 0),
                        new PatternElementResult("A", secondParsed, 2));

        PatternElement patternElement = new SpecificOneAminoPatternElement("A");

        // Act
        PatternElementResult result = patternElement.parse("", 3, resultList);

        // Assert
        assertEquals(result.getStartPosition(), 2);
        assertEquals(result.getParsedAminoSequence(), "A");
        assertTrue(result.getPatternElement() instanceof SpecificOneAminoPatternElement);
    }
}