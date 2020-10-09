package com.videos.project.application.builder;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

/**
 *  Clase de la capa Application, implementa la interfaz Builder
 *
 *  Esta obligada a implementar los metodos de creacion que expone la interfaz Builder
 */
public class Builder1 implements Builder {

    private WrapperObject wrapperObject = new WrapperObject();

    /**
     * Crea un objeto de tipo User y lo añade a un objeto envoltorio de tipo WrapperObject
     *
     * @param name, nombre del usuario, parametro del constructor de la clase User
     * @param surname, apellido del usuario, parametro del constructor de la clase User
     * @param password, password del usuario, parametro del constructor de la clase User
     *
     * @return interfaz de tipo WrapperObjectInterfaz, que representa a un objeto de tipo
     * User por implementar esta interfaz
     */
    @Override
    public WrapperObjectInterface buildUser(String name, String surname, String password) {
        User user=new User(name,surname,password);
        wrapperObject.add(user);
        return user;
    }

    /**
     *
     * Crea un objeto de tipo Video y lo añade a un objeto envoltorio de tipo WrapperObject
     *
     * @param url, url del video, parametro del constructor de la clase Video
     * @param tittle, titulo del video, parametro del constructor de la clase Video
     *
     * @return interfaz de tipo WrapperObjectInterfaz, que representa a un objeto de tipo
     * Video por implementar esta interfaz
     */
    @Override
    public WrapperObjectInterface buildVideo(String url, String tittle) {
        Video video=new Video(url,tittle);
        wrapperObject.add(video);
        return video;
    }

}
