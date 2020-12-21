package com.pg.test;

import com.pg.patternElement.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecificStaticSequencePatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "AAC";
        PatternElement patternElement = new SpecificStaticSequencePatternElement("A", 2);

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertEquals(result.getStartPosition(), 1);
        assertEquals(result.getParsedAminoSequence(), "AA");
        assertTrue(result.getPatternElement() instanceof SpecificStaticSequencePatternElement);
    }

    @Test
    void ParseOneAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "ACA";
        PatternElement patternElement = new SpecificStaticSequencePatternElement("A", 2);

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new SpecificStaticSequencePatternElement("A", 2);

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseAminoSequenceWithBorrowing() {
        //Arrange
        String examplePatternString = "CCCCC";
        PatternElement firstParsed = new SpecificMovingSequencePatternElement("C", 1, 3);
        PatternElement secondParsed = new SpecificStaticSequencePatternElement("C", 2);
        List<PatternElementResult> resultList =
                Arrays.asList(
                        new PatternElementResult("CCC", firstParsed, 0),
                        new PatternElementResult("CC", secondParsed, 3));

        PatternElement patternElement = new SpecificStaticSequencePatternElement("C", 2);

        // Act
        PatternElementResult result = patternElement.parse("", 5, resultList);

        // Assert
        assertEquals(result.getStartPosition(), 3);
        assertEquals(result.getParsedAminoSequence(), "CC");
        assertTrue(result.getPatternElement() instanceof SpecificStaticSequencePatternElement);
    }
}