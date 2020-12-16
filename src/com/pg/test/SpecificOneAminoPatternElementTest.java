package com.pg.test;

import com.pg.patternElement.PatternElement;
import com.pg.patternElement.PatternElementResult;
import com.pg.patternElement.SpecificOneAminoPatternElement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpecificOneAminoPatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "AC";
        PatternElement patternElement = new SpecificOneAminoPatternElement("A");

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

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
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new SpecificOneAminoPatternElement("A");

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }
}