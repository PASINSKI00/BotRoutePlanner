package com.pasinski.internship.modules;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleBTest {

    @Test
    void givenModuleB_whenGetTimeToRideOnto_thenReturn1() {
        //Given
        Module module = new ModuleB(0,0,0);
        double expected = 1;

        //When
        double actual = module.getTimeToRideOnto();

        //Then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 2",
            "1, 4",
            "2, 6",
            "3, 8",
    })
    void givenModuleB_whenGetTimeToRemoveProduct_ThenShouldReturnProperValue(int index, double expected) {
        //Given
        Module module = new ModuleB(0,0,index+1);

        //When
        double actual = module.getTimeToRemoveProduct(index);

        //Then
        assertEquals(expected, actual);
    }
}
