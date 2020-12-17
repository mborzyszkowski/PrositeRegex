package com.pg.test;

import com.pg.PrositePattern;
import com.pg.patternElement.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrositePatternTest {

    @Test
    void ParseTwoTypesOfAnyOneAminoPatternElementsFromStringExpectListWithTwoTypesOfAnyOneAminoPatternElements() throws Exception {
        //Arrange
        String examplePatternString = "x-x";

        // Act
        PrositePattern pattern = PrositePattern.CreatePattern(examplePatternString);

        // Assert
        assertEquals(pattern.getPatternElements().size(), 2);
        PatternElement element = pattern.getPatternElements().stream().findFirst().orElse(null);
        assertTrue(element instanceof AnyOneAminoPatternElement);
    }

    @Test
    void ParseTwoTypesOfSpecificOneAminoPatternElementsFromStringExpectListWithTwoTypesOfSpecificOneAminoPatternElements() throws Exception {
        //Arrange
        String examplePatternString = "A-C";

        // Act
        PrositePattern pattern = PrositePattern.CreatePattern(examplePatternString);

        // Assert
        assertEquals(pattern.getPatternElements().size(), 2);
        PatternElement element = pattern.getPatternElements().stream().findFirst().orElse(null);
        assertTrue(element instanceof SpecificOneAminoPatternElement);
    }

    @Test
    void ParseTwoTypesOfOneFromAminoSetPatternElementsFromStringExpectListWithTwoTypesOfOneFromAminoSetPatternElements() throws Exception {
        //Arrange
        String examplePatternString = "[RK]-[RDH]";

        // Act
        PrositePattern pattern = PrositePattern.CreatePattern(examplePatternString);

        // Assert
        assertEquals(pattern.getPatternElements().size(), 2);
        PatternElement element = pattern.getPatternElements().stream().findFirst().orElse(null);
        assertTrue(element instanceof OneFromAminoSetPatternElement);
    }

    @Test
    void ParseTwoTypesOfNoOneFromAminoSetPatternElementsFromStringExpectListWithTwoTypesOfNoOneFromAminoSetPatternElements() throws Exception {
        //Arrange
        String examplePatternString = "{EDRKHPCG}-{EDRCG}";

        // Act
        PrositePattern pattern = PrositePattern.CreatePattern(examplePatternString);

        // Assert
        assertEquals(pattern.getPatternElements().size(), 2);
        PatternElement element = pattern.getPatternElements().stream().findFirst().orElse(null);
        assertTrue(element instanceof NoOneFromAminoSetPatternElement);
    }

    @Test
    void ParseTwoTypesOfSpecificStaticSequencePatternElementsFromStringExpectListWithTwoTypesOfSpecificStaticSequencePatternElements() throws Exception {
        //Arrange
        String examplePatternString = "G(1)-C(4)";

        // Act
        PrositePattern pattern = PrositePattern.CreatePattern(examplePatternString);

        // Assert
        assertEquals(pattern.getPatternElements().size(), 2);
        PatternElement element = pattern.getPatternElements().stream().findFirst().orElse(null);
        assertTrue(element instanceof SpecificStaticSequencePatternElement);
    }

    @Test
    void ParseTwoTypesOfSpecificMovingSequencePatternElementsFromStringExpectListWithTwoTypesOfSpecificMovingSequencePatternElements() throws Exception {
        //Arrange
        String examplePatternString = "G(1,3)-C(4,5)";

        // Act
        PrositePattern pattern = PrositePattern.CreatePattern(examplePatternString);

        // Assert
        assertEquals(pattern.getPatternElements().size(), 2);
        PatternElement element = pattern.getPatternElements().stream().findFirst().orElse(null);
        assertTrue(element instanceof SpecificMovingSequencePatternElement);
    }

    @Test
    void ParseLongPatternFromStringExpectNineElements() throws Exception {
        //Arrange
        String examplePatternString = "[RK]-G(1)-G(2,3)-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";

        // Act
        PrositePattern pattern = PrositePattern.CreatePattern(examplePatternString);

        // Assert
        assertEquals(pattern.getPatternElements().size(), 9);
    }

    @Test()
    void ParseErrorPatternFromStringExpectException() throws Exception {
        //Arrange
        String examplePatternString = "[RK]-G(1)-G(2,1)-{EDRKHPCG-[AGSCI]-[FY]-[LIVA]-x-[FYM]";

        // Act
        // Assert
        Exception exception = assertThrows(Exception.class, () -> {
            PrositePattern pattern = PrositePattern.CreatePattern(examplePatternString);
        });
    }

    @Test
    void PatternMatchTest1() throws Exception {
        //Arrange
        String patternString = "[RK]-G(1,3)-G(2)-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";
        String aminoAcidSequence = "SRSLKMRGGGQAFVIFKEVSSATKLTGRPRGGGVAFVRYNKREEAQVGCSVHKGGGFAFVQYVNERNAR";
        PrositePattern pattern = PrositePattern.CreatePattern(patternString);

        // Act
        List<String> results = pattern.matchAsStrings(aminoAcidSequence);

        // Assert
        assertArrayEquals(results.toArray(), Arrays.asList("RGGGQAFVIF", "RGGGVAFVRY", "KGGGFAFVQY").toArray());
    }

    @Test
    void PatternMatchTest2() throws Exception {
        //Arrange
        String patternString = "[KP]-A-C-x";
        String aminoAcidSequence = "KACPACL";
        PrositePattern pattern = PrositePattern.CreatePattern(patternString);

        // Act
        List<String> results = pattern.matchAsStrings(aminoAcidSequence);

        // Assert
        assertArrayEquals(results.toArray(), Arrays.asList("KACP", "PACL").toArray());
    }
}