package com.pg.test;

import com.pg.patternElement.PatternElement;
import com.pg.patternElement.PatternElementResult;
import com.pg.patternElement.SpecificMovingSequencePatternElement;
import com.pg.patternElement.SpecificStaticSequencePatternElement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    void ParseAminoSequenceWithBorrowing() {
        //Arrange
        String examplePatternString = "CCCC";
        PatternElement firstParsed = new SpecificMovingSequencePatternElement("C", 1, 3);
        PatternElement secondParsed = new SpecificMovingSequencePatternElement("C", 1, 3);
        List<PatternElementResult> resultList =
                Arrays.asList(
                        new PatternElementResult("C", firstParsed, 0),
                        new PatternElementResult("CCC", secondParsed, 3));

        PatternElement patternElement = new SpecificMovingSequencePatternElement("C", 2, 3);

        // Act
        PatternElementResult result = patternElement.parse("", 4, resultList);

        // Assert
        assertEquals(result.getStartPosition(), 2);
        assertEquals(result.getParsedAminoSequence(), "CC");
        assertTrue(result.getPatternElement() instanceof SpecificMovingSequencePatternElement);
    }
}