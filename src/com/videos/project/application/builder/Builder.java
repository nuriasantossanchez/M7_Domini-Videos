package com.videos.project.application.builder;

/**
 * Interfaz de la capa Application, utilizada para la creacion de objetos
 *
 * Expone los metodos que han de ser implementados por la clase Builder1,
 * clase que implementa la interfaz Builder
 *
 *
 */
public interface Builder {

    WrapperObjectInterface buildUser(String name, String surname, String password);
    WrapperObjectInterface buildVideo(String url, String tittle);

}
