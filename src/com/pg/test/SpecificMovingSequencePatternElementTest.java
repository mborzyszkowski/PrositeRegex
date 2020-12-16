package com.pg.test;

import com.pg.patternElement.PatternElement;
import com.pg.patternElement.PatternElementResult;
import com.pg.patternElement.SpecificMovingSequencePatternElement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpecificMovingSequencePatternElementTest {

    @Test
    void ParseOneMinAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "AAC";
        PatternElement patternElement = new SpecificMovingSequencePatternElement("A", 2, 3);

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertEquals(result.getStartPosition(), 1);
        assertEquals(result.getParsedAminoSequence(), "AA");
        assertTrue(result.getPatternElement() instanceof SpecificMovingSequencePatternElement);
    }

    @Test
    void ParseOneMaxAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "AAAC";
        PatternElement patternElement = new SpecificMovingSequencePatternElement("A", 2, 3);

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertEquals(result.getStartPosition(), 1);
        assertEquals(result.getParsedAminoSequence(), "AAA");
        assertTrue(result.getPatternElement() instanceof SpecificMovingSequencePatternElement);
    }

    @Test
    void ParseOneAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "ACA";
        PatternElement patternElement = new SpecificMovingSequencePatternElement("A", 2, 3);

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new SpecificMovingSequencePatternElement("A", 2, 3);

        // Act
        PatternElementResult result = patternElement.parse(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }
}