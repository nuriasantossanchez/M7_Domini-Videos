package com.videos.project.application.command;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

/**
 *  Clase de la capa Application, utilizada para implementar el patron Command
 *
 *  Realiza la solicitud de peticiones, haciendo uso de la interface comun Command,
 *  a traves de la cual se pueden ejecutar distintas solicitudes.
 *
 */

public class Invoker {
    private Command command;

    /**
     * Constructor de la clase, compuesto por una interface de tipo Command,
     * pasada como parametro, que encapsula las distintas peticiones
     *
     * @param command, objeto de tipo Command, realiza operaciones sobre otro objeto
     */
    public Invoker(Command command) {

        this.command = command;
    }

    /**
     * Solcita una peticion para ejecutar una accion
     *
     * Hace una llamada al metodo executeActionShowInfo() de la interface Command
     * a traves del cual, el objeto que imlementa la intreface, objeto Command1, realiza
     * una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar la accion
     *
     * @param user, cualquier objeto que implemente la interface WrapperObjectInterface
     *              o cualquier otra interface que extienda a WrapperObjectInterface,
     *              en este caso, objeto de tipo User
     */
    public void listarVideos(User user) {

        command.listarVideos(user);
    }

    /**
     * Solcita una peticion para ejecutar una accion
     *
     * Hace una llamada al metodo executeActionAddVideoTag() de la interface Command
     * a traves del cual, el objeto que imlementa la intreface, objeto Command1, realiza
     * una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar la accion
     *  @param video, cualquier objeto que implemente la interface WrapperObjectInterface
     *               o cualquier otra interface que extienda a WrapperObjectInterface,
     *               en este caso, objeto de tipo Video
     *
     * @param tag String que representa una etiqueta que sera añadida a un video en concreto
     */
    public void addTagVideo(String tag, Video video) {
        command.addTagVideo(tag, video);
    }

    public void addUserVideo(User user, Video video){

        command.addUserVideo(user, video);
    }

    public int getNumberOfUserVideos(User user) {

        return command.getNumberOfUserVideos(user);
    }

    public boolean matchUrl(String url) {
        return command.matchUrl(url);
    }
}
