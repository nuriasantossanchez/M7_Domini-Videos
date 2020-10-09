package com.videos.project.application.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de la capa Application
 *
 * Es una clase envoltorio, por aceptar cualquier objeto que implemente la inteface
 * WrapperObjectInterface, o cualquier otra interface que extienda a WrapperObjectInterface,
 * en este caso, acepta objetos de tipo User y Video por implementar las intefaces UserInterface y
 * VideoInterface, respectivamente, que extienden a WrapperObjectInterface
 */
public class WrapperObject {
    private List<WrapperObjectInterface> children = new ArrayList<WrapperObjectInterface>();

    /**
     * Añade a un listado cualquier objeto que implemente la interface
     * WrapperObjectInterface o cualquier otra interface que extienda
     * a WrapperObjectInterface, en este caso, objetos de tipo User y Video
     *
     * @param child, interface de tipo WrapperObjectInterface
     *
     */
    public void add(WrapperObjectInterface child) {
        children.add(child);
    }

}
