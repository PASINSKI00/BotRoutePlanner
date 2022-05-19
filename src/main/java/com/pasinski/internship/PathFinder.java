package com.pasinski.internship;

import com.pasinski.internship.modules.Module;

import java.util.*;

public class PathFinder {

    private double quickestPathTimeGlobally = Double.MAX_VALUE;
    private final List<Module> quickestPathGlobally = new ArrayList<>();

    public void calculateAndPrintQuickestPath(
            List<Module> modulesWithProduct,
            Grid grid,
            Module startingModule,
            Module receivingModule,
            Product product) {

        double tempQuickestPathTime;
        List<Module> tempQuickestPath = new ArrayList<>();

        for(Module productModule : modulesWithProduct) {
            tempQuickestPathTime = 0.0;
            tempQuickestPath.clear();

            // Get Time and Path from Start to Product
            prepToAndCalculateQuickestPathFromSource(startingModule, productModule, grid);
            productModule.removeProduct(productModule.getProductIndex(product));
            tempQuickestPathTime += productModule.getQuickestPathDuration();
            tempQuickestPath.addAll(productModule.getQuickestPath());

            // Get Time and Path from Product to Receiver
            prepToAndCalculateQuickestPathFromSource(productModule, receivingModule, grid);
            tempQuickestPathTime += receivingModule.getQuickestPathDuration();
            tempQuickestPath.addAll(receivingModule.getQuickestPath());


            if (tempQuickestPathTime < quickestPathTimeGlobally) {
                quickestPathTimeGlobally = tempQuickestPathTime;
                quickestPathGlobally.clear();
                quickestPathGlobally.addAll(tempQuickestPath);
            }
        }

        // Printing out the results
        System.out.println(quickestPathGlobally.size());
        System.out.println(quickestPathTimeGlobally);
        quickestPathGlobally.forEach(module -> System.out.println(module.getColumn() + " " + module.getRow()));
        System.out.println(receivingModule.getColumn() + " " + receivingModule.getRow());
    }

    public void setNeighbours(Grid grid, int maxRowIndex, int maxColumnIndex) {
        grid.getSet().forEach(module -> {
            int row = module.getRow();
            int column = module.getColumn();

            if (row > 0) {
                Module neighbour = grid.getModule(row - 1, column);
                module.addNeighbouringModule(neighbour, Math.max(neighbour.getTimeToRideOnto(), module.getTimeToRideOnto()));
            }
            if (row < maxRowIndex) {
                Module neighbour = grid.getModule(row + 1, column);
                module.addNeighbouringModule(neighbour, Math.max(neighbour.getTimeToRideOnto(), module.getTimeToRideOnto()));
            }
            if (column > 0) {
                Module neighbour = grid.getModule(row, column - 1);
                module.addNeighbouringModule(neighbour, Math.max(neighbour.getTimeToRideOnto(), module.getTimeToRideOnto()));
            }
            if (column < maxColumnIndex) {
                Module neighbour = grid.getModule(row, column + 1);
                module.addNeighbouringModule(neighbour, Math.max(neighbour.getTimeToRideOnto(), module.getTimeToRideOnto()));
            }
        });
    }

    private void prepToAndCalculateQuickestPathFromSource(Module startModule, Module finishModule, Grid grid) {
        Map<Module, Double> tempMap = new HashMap<>(finishModule.getNeighbouringModules());

        clearGridOfPathStuff(grid);
        finishModule.clearNeighbouringModules();

        calculateQuickestPathFromSource(startModule);

        finishModule.setNeighbouringModules(tempMap);
    }

    private void calculateQuickestPathFromSource(Module source) {
        source.setQuickestPathDuration(0);

        Set<Module> settledModules = new HashSet<>();
        Set<Module> unsettledModules = new HashSet<>();

        unsettledModules.add(source);

        while(!unsettledModules.isEmpty()) {
            Module current = getLowestTimeToGetOntoModule(unsettledModules);
            unsettledModules.remove(current);

            for(Map.Entry<Module, Double> neighbour : current.getNeighbouringModules().entrySet()) {
                Module neighbourModule = neighbour.getKey();
                Double neighbourTimeToGetOnto = neighbour.getValue();

                if(!settledModules.contains(neighbourModule)) {
                    calculateMinimumTimeToGetOnto(neighbourModule, neighbourTimeToGetOnto, current);
                    unsettledModules.add(neighbourModule);
                }
            }
            settledModules.add(current);
        }
    }

    private Module getLowestTimeToGetOntoModule(Set<Module> unsettledModules) {
        Module lowestTimeModule = null;
        double lowestTime = Double.MAX_VALUE;

        for(Module module : unsettledModules) {
            double moduleTime = module.getQuickestPathDuration();
            if(moduleTime < lowestTime) {
                lowestTime = moduleTime;
                lowestTimeModule = module;
            }
        }
        return lowestTimeModule;
    }

    private void calculateMinimumTimeToGetOnto(Module evaluationModule, Double timeToGetOnto, Module source) {
        double sourceTime = source.getQuickestPathDuration();

        if(sourceTime + timeToGetOnto < evaluationModule.getQuickestPathDuration()) {
            evaluationModule.setQuickestPathDuration(sourceTime + timeToGetOnto);
            List<Module> quickestPath = new ArrayList<>(source.getQuickestPath());
            quickestPath.add(source);
            evaluationModule.setQuickestPath(quickestPath);
        }
    }

    private void clearGridOfPathStuff(Grid grid) {
        grid.getSet().forEach(module1 -> {module1.clearQuickestPath(); module1.clearQuickestPathDuration();} );
    }
}
