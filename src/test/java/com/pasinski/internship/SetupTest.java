package com.pasinski.internship;

import com.pasinski.internship.modules.Module;
import com.pasinski.internship.modules.ModuleB;
import com.pasinski.internship.modules.ModuleH;
import com.pasinski.internship.modules.ModuleO;
import com.pasinski.internship.modules.ModuleS;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


class SetupTest {
    Setup setup;
    Grid grid;

    @BeforeEach
    void setUp() throws FileNotFoundException {
         setup = new Setup("./src/test/resources/grid-1.txt", "./src/test/resources/job-1.txt");

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
        setup = null;
        grid = null;
    }

    @Test
    void givenData_whenGetDimensions_thenShouldReturnAMap() {
        //given
        Map<String, Integer> expected = new HashMap<>();
        expected.put("rows", 3);
        expected.put("columns", 4);
        expected.put("depth", 3);

        //when
        Map<String, Integer> actual = setup.getDimensions();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenInstructions_whenGetGrid_thenReturnGrid() {
        //given
        setup.gridData.nextLine();

        //when
        Grid actual = setup.getGrid(3,4,3);

        //then
        assertTrue(actual.getSet().containsAll(grid.getSet()));
    }

    @Test
    void givenProductInfo_whenPlaceProducts_thenProductIsPlaced() {
        //given
        setup.gridData.nextLine();
        setup.gridData.nextLine();
        setup.gridData.nextLine();
        setup.gridData.nextLine();

        //when
        setup.placeProducts(grid);

        //then
        assertTrue(grid.getModule(2,3).checkIfProductPresent(new Product("P1")));
        assertTrue(grid.getModule(2,0).checkIfProductPresent(new Product("P1")));
        assertTrue(grid.getModule(1,1).checkIfProductPresent(new Product("P2")));
    }

    @Test
    void getStartingPosition() {
        //given
        List<Integer> expected = List.of(1,1);

        //when
        List<Integer> actual = setup.getStartingPosition();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void getFinalPosition() {
        //given
        setup.jobData.nextLine();
        List<Integer> expected = List.of(0,0);

        //when
        List<Integer> actual = setup.getFinalPosition(grid);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void getProductToGet() {
        //given
        setup.jobData.nextLine();
        setup.jobData.nextLine();
        Product expected = new Product("P1");

        //when
        Product actual = setup.getProductToGet();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void getModulesWithProduct() {
        //given
        Product product = new Product("P1");
        Module module1 = grid.getModule(2,3);
        Module module2 = grid.getModule(2,0);
        Module module3 = grid.getModule(1,1);

        module1.addProduct(1,product);
        module2.addProduct(2,product);
        module3.addProduct(2,product);

        List<Module> expected = List.of(module1, module2, module3);

        //when
        List<Module> actual = setup.getModulesWithProduct(product, grid);

        //then
        assertTrue(actual.containsAll(expected));
    }
}