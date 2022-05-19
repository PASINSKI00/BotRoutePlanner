package com.pasinski.internship;

import com.pasinski.internship.modules.Module;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            throw new IllegalArgumentException("Please provide 2 arguments: <gridFile> <jobFile>");
        }

        Setup setup = new Setup(args[0],args[1]);

        // Setting up the grid using provided dimensions
        final Map<String, Integer> dimensions = setup.getDimensions();
        final Grid grid = setup.getGrid(dimensions.get("rows"), dimensions.get("columns"), dimensions.get("depth"));
        setup.placeProducts(grid);

        // Setting the coordinates of a starting module and receiving station
        final List<Integer> startingPosition = setup.getStartingPosition();
        final List<Integer> finalPosition = setup.getFinalPosition(grid);

        // Setting the demanded product
        final Product product = setup.getProductToGet();

        // Getting the modules containing the product
        final List<Module> modulesWithProduct = setup.getModulesWithProduct(product, grid);

        // Setting up the starting and receiving modules
        Module startingModule = grid.getModule(startingPosition.get(0), startingPosition.get(1));
        Module receivingModule = grid.getModule(finalPosition.get(0), finalPosition.get(1));
        receivingModule.clearProducts();

        // Letting the modules know about their neighbours
        PathFinder pathFinder = new PathFinder();
        pathFinder.setNeighbours(grid, dimensions.get("rows")-1, dimensions.get("columns")-1);

        // Finding the quickest path and printing it
        pathFinder.calculateAndPrintQuickestPath(modulesWithProduct, grid, startingModule, receivingModule, product);
    }
}
