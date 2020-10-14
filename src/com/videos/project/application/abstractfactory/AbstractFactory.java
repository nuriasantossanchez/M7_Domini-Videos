package com.videos.project.application.abstractfactory;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

/**
 * Interfaz de la clase Application
 *
 * Se utiliza para implementar el patron AbstractFactory con el fin
 * de desacoplar la creacion de objetos
 */
public interface AbstractFactory {
    User createUser(String name, String surname, String password);
    Video createVideo(String url, String tittle);
}
