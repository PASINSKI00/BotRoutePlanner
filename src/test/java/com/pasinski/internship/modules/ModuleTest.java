package com.pasinski.internship.modules;

import com.pasinski.internship.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuleTest {
    Module moduleH;
    Module moduleB;
    Module moduleS;
    Module moduleO;

    @BeforeEach
    void setUp() {
        moduleH = new ModuleH(1, 1, 3);
        moduleB = new ModuleB(0, 1, 3);
        moduleS = new ModuleS(1, 0, 3);
        moduleO = new ModuleO(0, 0, 3);
    }

    @AfterEach
    void tearDown() {
        moduleH = null;
        moduleB = null;
        moduleS = null;
        moduleO = null;
    }


    @Test
    void givenNeighbouringModules_whenAddNeighbouringModule_thenAdddedNeighbours() {
        //given
        int expectedSize = 2;

        //when
        moduleH.addNeighbouringModule(moduleB, 0);
        moduleH.addNeighbouringModule(moduleS, 0);

        //then
        assertEquals(expectedSize, moduleH.getNeighbouringModules().size());
    }

    @Test
    void givenNeighbouringModuleO_whenAddNeighbouringModule_thenShouldNotAddNeighbour() {
        //given
        int expectedSize = 1;

        //when
        moduleH.addNeighbouringModule(moduleO, 0);
        moduleH.addNeighbouringModule(moduleS, 0);

        //then
        assertEquals(expectedSize, moduleH.getNeighbouringModules().size());
    }

    @Test
    void givenProduct_whenAddProduct_thenProductAdded() {
        //given
        Product product = new Product("P1");
        int expectedSize = 1;

        //when
        moduleH.addProduct(1,product);

        //then
        assertTrue(moduleH.checkIfProductPresent(product));

    }

    @Test
    void givenModuleWithProduct_whenRemoveProduct_thenProductRemoved() {
        //given
        Product product = new Product("P1");
        moduleH.addProduct(1,product);
        int expectedSize = 0;

        //when
        moduleH.removeProduct(1);

        //then
        assertFalse(moduleH.checkIfProductPresent(product));
    }

    @Test
    void givenModuleWithProduct_whenRemoveProduct_thenTimeAdded() {
        //given
        Product product = new Product("P1");
        moduleH.addProduct(1,product);
        moduleH.setQuickestPathDuration(0);
        int expectedTime = 7;

        //when
        moduleH.removeProduct(1);

        //then
        assertEquals(expectedTime, moduleH.getQuickestPathDuration());
    }
}