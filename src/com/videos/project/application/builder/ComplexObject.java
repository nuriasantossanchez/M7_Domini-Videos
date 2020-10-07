package com.videos.project.application.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexObject {
    private List<ComplexObjectInterfaz> children = new ArrayList<ComplexObjectInterfaz>();

    public String getParts() {
        Iterator<ComplexObjectInterfaz> i = iterator();
        String str="";
        while (i.hasNext()) {
            str += i.next().toString() + "\n";
        }
        return str;
    }

    public boolean add(ComplexObjectInterfaz child) {

        return children.add(child);
    }

    public Iterator<ComplexObjectInterfaz> iterator() {
        return children.iterator();

    }

}
