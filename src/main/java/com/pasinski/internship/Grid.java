package com.pasinski.internship;

import com.pasinski.internship.modules.*;
import com.pasinski.internship.modules.Module;

import java.util.HashSet;
import java.util.Set;

public class Grid {
    private final Set<Module> set = new HashSet<>();

    public void addModule(Module module) {
        set.add(module);
    }

    public Module getModuleOfAppropriateType(int row, int column, int depth, char moduleType) {
        if (moduleType == 'H')
            return new ModuleH(row, column, depth);
        else if(moduleType == 'B')
            return new ModuleB(row, column, depth);
        else if (moduleType == 'S')
            return new ModuleS(row, column, depth);
        else
            return new ModuleO(row, column, depth);
    }

    public Module getModule(int row, int column) {
        return set.stream()
                .filter(module -> module.getRow() == row && module.getColumn() == column).findFirst().get();
    }

    public Set<Module> getSet() {
        return set;
    }
}
