package com.pasinski.internship.modules;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ModuleHTest {

    @Test
    void givenModuleH_whenGetTimeToRideOnto_thenReturnHalf() {
        //Given
        Module module = new ModuleH(0,0,0);
        double expected = 0.5;

        //When
        double actual = module.getTimeToRideOnto();

        //Then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 4",
            "1, 7",
            "2, 10",
            "3, 13",
    })
    void givenModuleH_whenGetTimeToRemoveProduct_ThenShouldReturnProperValue(int index, double expected) {
        //Given
        Module module = new ModuleH(0,0,index+1);

        //When
        double actual = module.getTimeToRemoveProduct(index);

        //Then
        assertEquals(expected, actual);
    }
}