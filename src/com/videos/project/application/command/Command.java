package com.videos.project.application.command;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

/**
 * Interface de la capa Application, se utiliza para implementar el patron Command
 *
 * Encapsula ciertas peticiones en un objeto con el fin de desacoplar
 * el objeto que solicita una peticion, de la peticon en si
 *
 * Expone los metodos que han de ser implementados por la clase Command1,
 * clase que implementa la interface Command
 *
 */
public interface Command {
    void listarVideos(User user);

    void addTagVideo(String tag, Video video);

    void addUserVideo(User user, Video video);

    int getNumberOfUserVideos(User user);

    boolean matchUrl(String url);

}
