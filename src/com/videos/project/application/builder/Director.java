package com.videos.project.application.builder;

/**
 * Clase de la capa Application
 *
 * Implementa el patron Builder
 * Delega la creacion de objetos de dominio a la interfaz Builder
 */
public class Director {

    private Builder builder;

    /**
     * Constructor de la clase, inicializa la interfaz Builder pasada como parmetro
     *
     * @param builder, interfaz encargada de la creacion de objetos
     */
    public Director(Builder builder) {

        this.builder = builder;
    }

    /**
     * Delega a la interfaz Builder la creacion de un objeto que implemente
     * la interfaz WrapperObjectInterfaz, en este caso un objeto tipo User
     *
     * @param name, nombre del usuario, parametro del constructor de la clase User
     * @param surname, apellido del usuario, parametro del constructor de la clase User
     * @param password, password del usuario, parametro del constructor de la clase User
     *
     * @return interfaz de tipo WrapperObjectInterfaz, que representa a cualquier
     * clase que la implemente, en este caso el objeto User
     */
    public WrapperObjectInterface constructUser(String name, String surname, String password) {
        return builder.buildUser(name, surname, password);
    }

    /**
     * Delega a la interfaz Builder la creacion de un objeto que implemente
     * la interfaz WrapperObjectInterfaz, en este caso un objeto tipo Video
     *
     * @param url, url del video, parametro del constructor de la clase Video
     * @param tittle, titulo del video, parametro del constructor de la clase Video
     *
     * @return, interfaz de tipo WrapperObjectInterfaz, que representa a cualquier
     * clase que la implemente, en este caso el objeto Video
     */
    public WrapperObjectInterface constructVideo(String url, String tittle) {

        return builder.buildVideo(url, tittle);
    }

}
