package com.pasinski.internship.modules;

public class ModuleH extends Module {

    public ModuleH(int row, int column, int depth) {
        super(row, column, depth);
    }

    @Override
    public double getTimeToRideOnto() {
        return 0.5;
    }

    @Override
    public double getTimeToRemoveProduct(int n) {
        return 3*n+4;
    }
}
