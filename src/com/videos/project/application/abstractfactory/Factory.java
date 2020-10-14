package com.videos.project.application.abstractfactory;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

/**
 * Clase de la clase Application
 *
 * Se utiliza para implementar el patron AbstractFactory
 *
 * Implementa la interfaz AbstractFactory en cuyos metodos queda
 * desacoplada la creacion de objetos
 *
 */
public class Factory implements AbstractFactory{

    /**
     * Crea un objeto de tipo User
     *
     * @param name, nombre del usuario, parametro del constructor de la clase User
     * @param surname, apellido del usuario, parametro del constructor de la clase User
     * @param password, password del usuario, parametro del constructor de la clase User
     * @return el objeto de tipo User creado
     */
    @Override
    public User createUser(String name, String surname, String password) {
        User user=new User(name,surname,password);
        return user;
    }

    /**
     * Crea un objeto de tipo Video
     *
     * @param url, url del video, parametro del constructor de la clase Video
     * @param tittle, titulo del video, parametro del constructor de la clase Video
     * @return el objeto de tipo Video creado
     */
    @Override
    public Video createVideo(String url, String tittle) {
        Video video=new Video(url,tittle);
        return video;
    }


}
