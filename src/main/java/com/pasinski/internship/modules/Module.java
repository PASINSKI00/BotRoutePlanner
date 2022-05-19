package com.pasinski.internship.modules;

import com.pasinski.internship.Product;

import java.util.*;

public abstract class Module {
    private final List<Product> products = new ArrayList<>();
    private final int row;
    private final int column;
    private boolean receiving = false;

    private List<Module> quickestPath = new ArrayList<>();
    private double quickestPathDuration = Double.MAX_VALUE - 1000;
    private final Map<Module,Double> neighbouringModules = new HashMap<>();

    public Module(int row, int column, int depth) {
        this.row = row;
        this.column = column;

        for(int i = 0; i < depth; i++)
            this.products.add(null);
    }

    public void addNeighbouringModule(Module module, double duration) {
        if(!module.getClass().equals(ModuleO.class))
            neighbouringModules.put(module, duration);
    }

    abstract public double getTimeToRideOnto();
    abstract public double getTimeToRemoveProduct(int n);

//     Getters, setters and clearing functions
    public Map<Module, Double> getNeighbouringModules() {
        return neighbouringModules;
    }

    public void setNeighbouringModules(Map<Module, Double> neighbouringModules) {
        this.neighbouringModules.putAll(neighbouringModules);
    }

    public void clearNeighbouringModules() {
        this.neighbouringModules.clear();
    }


    public void addProduct(int index, Product product) {
        products.remove(index);
        products.add(index, product);
    }

    public void removeProduct(int index) {
        products.remove(index);
        this.quickestPathDuration += getTimeToRemoveProduct(index);
    }

    public boolean checkIfProductPresent(Product product) {
        return products.contains(product);
    }

    public int getProductIndex(Product product) {
        return products.indexOf(product);
    }

    public void clearProducts(){
        products.clear();
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


    public List<Module> getQuickestPath() {
        return quickestPath;
    }

    public void setQuickestPath(List<Module> quickestPath) {
        this.quickestPath = quickestPath;
    }

    public void clearQuickestPath() {
        quickestPath.clear();
    }

    public double getQuickestPathDuration() {
        return quickestPathDuration;
    }


    public void clearQuickestPathDuration() {
        quickestPathDuration = Double.MAX_VALUE - 1000;
    }

    public void setQuickestPathDuration(double quickestPathDuration) {
        this.quickestPathDuration = quickestPathDuration;
    }

    public boolean isReceiving() {
        return receiving;
    }

    public void setReceiving(boolean receiving) {
        this.receiving = receiving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return row == module.row && column == module.column && receiving == module.receiving && products.equals(module.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, row, column, receiving);
    }
}
