package com.videos.project.application.builder;

import com.videos.project.domain.Video;

/**
 * Interface de la capa Application
 * Extiende a la interface WrapperObjectInterface
 *
 * Expone los metodos que han de ser implementados por la clase User,
 * clase que implementa la interface UserInterface
 */
public interface UserInterface extends WrapperObjectInterface {
    void addVideoUser(Video video);
    String getName();
}
