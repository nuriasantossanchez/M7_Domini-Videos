package com.videos.project.application.builder;

import java.util.ArrayList;
import java.util.List;

public class ComplexObject {
    private List<ComplexObjectInterfaz> children = new ArrayList<ComplexObjectInterfaz>();

    public boolean add(ComplexObjectInterfaz child) {

        return children.add(child);
    }

}
