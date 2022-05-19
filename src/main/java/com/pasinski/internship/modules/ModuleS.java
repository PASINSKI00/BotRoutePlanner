package com.pasinski.internship.modules;

public class ModuleS extends Module {

    public ModuleS(int row, int column, int depth) {
        super(row, column, depth);
    }

    @Override
    public double getTimeToRideOnto() {
        return 2;
    }

    @Override
    public double getTimeToRemoveProduct(int n) {
        return n+1;
    }
}
