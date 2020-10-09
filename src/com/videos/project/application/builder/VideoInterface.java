package com.videos.project.application.builder;

/**
 * Interface de la capa Application
 * Extiende a la interface WrapperObjectInterface
 *
 * Expone los metodos que han de ser implementados por la clase Video,
 * clase que implementa la interface VideoInterface
 */
public interface VideoInterface extends WrapperObjectInterface {
    void addVideoTag(String tag);
}
