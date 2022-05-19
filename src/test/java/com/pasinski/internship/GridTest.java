package com.pasinski.internship;

import com.pasinski.internship.modules.*;
import com.pasinski.internship.modules.Module;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    private Grid grid;
    private Module moduleH;
    private Module moduleB;
    private Module moduleS;
    private Module moduleO;

    @BeforeEach
    void setUp() {
        grid = new Grid();
        moduleH = new ModuleH(1, 1, 0);
        moduleB = new ModuleB(0, 1, 0);
        moduleS = new ModuleS(1, 0, 0);
        moduleO = new ModuleO(0, 0, 0);
    }

    @AfterEach
    void tearDown() {
        grid = null;
        moduleH = null;
        moduleB = null;
        moduleS = null;
        moduleO = null;
    }

    @Test
    void givenModules_whenAddModule_thenListSizeValid() {
        //given
        int expectedSize = 4;

        //when
        grid.addModule(moduleH);
        grid.addModule(moduleB);
        grid.addModule(moduleS);
        grid.addModule(moduleO);
        int actualSize = grid.getSet().size();

        //then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void givenType_whenGetModuleOfAppropriateType_thenShouldReturnModuleOfType() {
        // given
        int row = 10;
        int column = 10;
        int depth = 0;
        char type = 'H';

        // when
        Module actualModule = grid.getModuleOfAppropriateType(row, column, depth, type);

        // then
        assertEquals(moduleH.getClass(), actualModule.getClass());
    }

    @Test
    void givenRightCoordinates_whenGetModule_thenShouldReturnModule() {
        // given
        int row = 1;
        int column = 1;
        grid.addModule(moduleH);

        // when
        Module actualModule = grid.getModule(row, column);

        // then
        assertEquals(moduleH , actualModule);
    }

    @Test
    void givenWrongCoordinates_whenGetModule_thenShouldThrowException() {
        // given
        int row = 10;
        int column = 10;

        // when

        // then
        assertThrows(NoSuchElementException.class, () -> { grid.getModule(row, column); });
    }

    @Test
    void givenModules_whenGetSet_thenShouldReturnProperSet() {
        //given
        grid.addModule(moduleH);
        grid.addModule(moduleB);
        grid.addModule(moduleS);
        grid.addModule(moduleO);

        Set<Module> expectedSet = new HashSet<>();
        expectedSet.add(moduleH);
        expectedSet.add(moduleB);
        expectedSet.add(moduleS);
        expectedSet.add(moduleO);

        //when

        //then
        assertEquals(expectedSet, grid.getSet());
    }
}