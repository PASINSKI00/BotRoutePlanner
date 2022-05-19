package com.pasinski.internship.modules;

public class ModuleB extends Module {

    public ModuleB(int row, int column, int depth) {
        super(row, column, depth);
    }

    @Override
    public double getTimeToRideOnto() {
        return 1;
    }

    @Override
    public double getTimeToRemoveProduct(int n) {
        return 2*n+2;
    }
}
