package com.videos.project.application.command;

import com.videos.project.application.builder.WrapperObjectInterface;

/**
 * Interface de la capa Application, utilizada para implementar el patron Command
 *
 * Encapsula ciertas peticiones en un objeto con el fin de desacoplar
 * el objeto que solicita una peticion, de la peticon en si
 *
 * Expone los metodos que han de ser implementados por la clase Command1,
 * clase que implementa la interface Command
 *
 */
public interface Command {
    void executeActionShowInfo(WrapperObjectInterface user);

    void executeActionAddVideoTag(WrapperObjectInterface video, String tag);
}
