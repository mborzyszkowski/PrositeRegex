package com.pg.test;

import com.pg.patternElement.PatternElement;
import com.pg.patternElement.PatternElementResult;
import com.pg.patternElement.SpecificStaticSequencePatternElement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpecificStaticSequencePatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "AAC";
        PatternElement patternElement = new SpecificStaticSequencePatternElement("A", 2);

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertEquals(result.getStartPosition(), 1);
        assertEquals(result.getParsedAminoSequence(), "A");
        assertTrue(result.getPatternElement() instanceof SpecificStaticSequencePatternElement);
    }

    @Test
    void ParseOneAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "ACA";
        PatternElement patternElement = new SpecificStaticSequencePatternElement("A", 2);

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new SpecificStaticSequencePatternElement("A", 2);

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }
}