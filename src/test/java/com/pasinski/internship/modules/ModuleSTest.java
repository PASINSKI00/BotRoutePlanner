package com.pasinski.internship.modules;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleSTest {


    @Test
    void givenModuleS_whenGetTimeToRideOnto_thenReturn2() {
        //Given
        Module module = new ModuleS(0,0,0);
        double expected = 2;

        //When
        double actual = module.getTimeToRideOnto();

        //Then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 2",
            "2, 3",
            "3, 4",
    })
    void givenModuleS_whenGetTimeToRemoveProduct_ThenShouldReturnProperValue(int index, double expected) {
        //Given
        Module module = new ModuleS(0,0,index+1);

        //When
        double actual = module.getTimeToRemoveProduct(index);

        //Then
        assertEquals(expected, actual);
    }
}
