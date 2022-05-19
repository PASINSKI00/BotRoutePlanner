package com.pasinski.internship;

import com.pasinski.internship.modules.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Setup {
    Scanner gridData;
    Scanner jobData;

    public Setup(String gridFile, String jobFile) throws FileNotFoundException {
        this.gridData =new Scanner(new File(gridFile));
        this.jobData = new Scanner(new File(jobFile));
    }

    public Map<String,Integer> getDimensions() {
        String[] dimensions = this.gridData.nextLine().split(" ");

        Map<String, Integer> map = new HashMap<>();
        map.put("columns", Integer.parseInt(dimensions[0]));
        map.put("rows", Integer.parseInt(dimensions[1]));
        map.put("depth", Integer.parseInt(dimensions[2]));

        return map;
    }

    public Grid getGrid(int rows, int columns, int depth) {
        Grid grid = new Grid();
        String line = "";
        char moduleType = ' ';

        for(int i = 0; i < rows; i++) {
            line = this.gridData.nextLine();
            for(int j = 0; j < columns; j++) {
                moduleType = line.charAt(j);
                Module module = grid.getModuleOfAppropriateType(i,j,depth, moduleType);
                grid.addModule(module);
            }
        }
        return grid;
    }

    public void placeProducts(Grid grid) {
        while(this.gridData.hasNextLine()) {
            String[] productString = this.gridData.nextLine().split(" ");

            Product product = new Product(productString[0]);
            int column = Integer.parseInt(productString[1]);
            int row = Integer.parseInt(productString[2]);
            int depth = Integer.parseInt(productString[3]);

            grid.getSet().stream().filter(module -> module.getRow() == row && module.getColumn() == column).forEach(module -> module.addProduct(depth, product));
        }
    }

    public List<Integer> getStartingPosition() {
        String[] jobString = this.jobData.nextLine().split(" ");
        int column = Integer.parseInt(jobString[0]);
        int row = Integer.parseInt(jobString[1]);

        return new ArrayList<Integer>(Arrays.asList(row, column));
    }

    public List<Integer> getFinalPosition(Grid grid) {
        String[] jobString = this.jobData.nextLine().split(" ");
        int column = Integer.parseInt(jobString[0]);
        int row = Integer.parseInt(jobString[1]);
        grid.getSet().stream().filter(module -> module.getRow() == row && module.getColumn() == column).forEach(module -> module.setReceiving(true));

        return new ArrayList<Integer>(Arrays.asList(row, column));
    }

    public  Product getProductToGet() {
        String product = this.jobData.nextLine();
        return new Product(product);
    }

    public List<Module> getModulesWithProduct(Product product, Grid grid) {
        return grid.getSet().stream().filter(module -> module.checkIfProductPresent(product)).collect(Collectors.toList());
    }
}
