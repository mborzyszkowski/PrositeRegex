package com.pg.test;

import com.pg.patternElement.NoOneFromAminoSetPatternElement;
import com.pg.patternElement.PatternElement;
import com.pg.patternElement.PatternElementResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NoOneFromAminoSetPatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "CA";
        PatternElement patternElement = new NoOneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

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
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new NoOneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }
}