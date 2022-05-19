package com.pasinski.internship;

import com.pasinski.internship.modules.*;
import com.pasinski.internship.modules.Module;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {
    PathFinder pathFinder;
    Grid grid;

    @BeforeEach
    void setUp() {
        pathFinder = new PathFinder();

        grid = new Grid();
        grid.addModule(new ModuleH(0,0,3));
        grid.addModule(new ModuleH(0,1,3));
        grid.addModule(new ModuleS(0,2,3));
        grid.addModule(new ModuleH(0,3,3));

        grid.addModule(new ModuleH(1,0,3));
        grid.addModule(new ModuleB(1,1,3));
        grid.addModule(new ModuleH(1,2,3));
        grid.addModule(new ModuleH(1,3,3));

        grid.addModule(new ModuleH(2,0,3));
        grid.addModule(new ModuleH(2,1,3));
        grid.addModule(new ModuleO(2,2,3));
        grid.addModule(new ModuleS(2,3,3));
    }

    @AfterEach
    void tearDown() {
        pathFinder = null;
        grid = null;
    }

    @Test
    void setNeighbours() {
        //given
        Grid actual = new Grid();
        actual.getSet().addAll(grid.getSet().stream().filter(m -> m.getRow() == 0 ).collect(Collectors.toSet()));

        Grid expected = new Grid();
        expected.getSet().addAll(grid.getSet().stream().filter(m -> m.getRow() == 0 ).collect(Collectors.toSet()));

        expected.getModule(0,0).addNeighbouringModule(expected.getModule(0,1), 0.5);
        expected.getModule(0,1).addNeighbouringModule(expected.getModule(0,0), 0.5);
        expected.getModule(0,1).addNeighbouringModule(expected.getModule(0,2), 2);
        expected.getModule(0,2).addNeighbouringModule(expected.getModule(0,1), 2);
        expected.getModule(0,2).addNeighbouringModule(expected.getModule(0,3), 2);
        expected.getModule(0,3).addNeighbouringModule(expected.getModule(0,2), 2);

        //when
        pathFinder.setNeighbours(actual, 0,3);

        //then
        assertEquals(expected.getSet(), actual.getSet());
    }

    @Test
    void calculateAndPrintQuickestPath() {
        //given
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        grid.getModule(2,3).addProduct(1,new Product("P1"));
        grid.getModule(2,0).addProduct(2,new Product("P1"));
        Product product = new Product("P1");

        List<Module> modulesWithProducts = List.of(grid.getModule(2,3), grid.getModule(2,0));
        Module startingModule = grid.getModule(1,1);
        Module finalModule = grid.getModule(0,0);
        pathFinder.setNeighbours(grid, 2,3);

        String expected = "8\n" +
                "10.5\n" +
                "1 1\n" +
                "2 1\n" +
                "3 1\n" +
                "3 2\n" +
                "3 1\n" +
                "2 1\n" +
                "1 1\n" +
                "1 0\n" +
                "0 0\n";

        //when
        pathFinder.calculateAndPrintQuickestPath(modulesWithProducts, grid, startingModule, finalModule, product);

        //then
        expected = expected.replace("\n", System.lineSeparator());
        assertEquals(expected.trim(), outputStreamCaptor.toString().trim());

        System.setOut(standardOut);
    }

}