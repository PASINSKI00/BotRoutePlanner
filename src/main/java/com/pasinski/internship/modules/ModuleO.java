package com.pasinski.internship.modules;

public class ModuleO extends Module {

    public ModuleO(int row, int column, int depth) {
        super(row, column, depth);
    }

    @Override
    public double getTimeToRideOnto() {
        return 0;
    }

    @Override
    public double getTimeToRemoveProduct(int n) {
        return 0;
    }
}
