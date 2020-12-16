package com.pg.test;

import com.pg.patternElement.OneFromAminoSetPatternElement;
import com.pg.patternElement.PatternElement;
import com.pg.patternElement.PatternElementResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OneFromAminoSetPatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "FC";
        PatternElement patternElement = new OneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

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
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }

    @Test
    void ParseEmptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new OneFromAminoSetPatternElement("ADFJW");

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }
}