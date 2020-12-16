package com.pg.test;

import com.pg.patternElement.AnyOneAminoPatternElement;
import com.pg.patternElement.PatternElement;
import com.pg.patternElement.PatternElementResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AnyOneAminoPatternElementTest {

    @Test
    void ParseOneAminoExpectParseSuccess() {
        //Arrange
        String examplePatternString = "AC";
        PatternElement patternElement = new AnyOneAminoPatternElement();

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertEquals(result.getStartPosition(), 1);
        assertEquals(result.getParsedAminoSequence(), "A");
        assertTrue(result.getPatternElement() instanceof AnyOneAminoPatternElement);
    }

    @Test
    void ParseemptyAminoExpectParseFail() {
        //Arrange
        String examplePatternString = "";
        PatternElement patternElement = new AnyOneAminoPatternElement();

        // Act
        PatternElementResult result = patternElement.parsePattern(examplePatternString, 1, new ArrayList<>());

        // Assert
        assertNull(result);
    }
}