package com.videos.project.application.command;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

/**
 *  Clase de la capa Application, se utiliza para implementar el patron Command
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
     * @param command, interfaz de tipo Command
     */
    public Invoker(Command command) {

        this.command = command;
    }

    /**
     * Metodo que solicita una peticion para ejecutar una accion
     *
     * Delega en la interfaz Command la llamada al metodo listarVideos()
     * a traves del cual, el objeto que imlementa la intreface, objeto Command1, realiza
     * una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar la accion
     *
     * @param user, objeto de tipo User
     */
    public void listarVideos(User user) {

        command.listarVideos(user);
    }

    /**
     * Metodo que solicita una peticion para ejecutar una accion
     *
     * Delega en la interfaz Command la llamada al metodo addTagVideo()
     * a traves del cual, el objeto que imlementa la intreface, objeto Command1, realiza
     * una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar la accion
     *
     * @param video, objeto de tipo Video
     * @param tag String que representa la etiqueta que sera añadida a un video concreto
     */
    public void addTagVideo(String tag, Video video) {

        command.addTagVideo(tag, video);
    }

    /**
     * Metodo que solicita una peticion para ejecutar una accion
     *
     * Delega en la interfaz Command la llamada al metodo addUserVideo()
     * a traves del cual, el objeto que imlementa la intreface, objeto Command1, realiza
     * una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar la accion
     *
     * @param user, objeto de tipo User
     * @param video, objeto de tipo Video
     */
    public void addUserVideo(User user, Video video){

        command.addUserVideo(user, video);
    }

    /**
     * Metodo que solicita una peticion para ejecutar una accion
     *
     * Delega en la interfaz Command la llamada al metodo getNumberOfUserVideos()
     * a traves del cual, el objeto que imlementa la intreface, objeto Command1, realiza
     * una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar la accion
     *
     * @param user, objeto de tipo User
     * @return un entero que reprenseta el numero de videos creados por un usuario
     */
    public int getNumberOfUserVideos(User user) {

        return command.getNumberOfUserVideos(user);
    }

    /**
     * Metodo que solicita una peticion para ejecutar una accion
     *
     * Delega en la interfaz Command la llamada al metodo matchUrl()
     * a traves del cual, el objeto que imlementa la intreface, objeto Command1, realiza
     * una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar la accion
     *
     * @param url, String que representa la url que se quiere checkear
     * @return true, si la url cumple con el patron de formato especificado,
     *         false en caso contrario
     */
    public boolean matchUrl(String url) {

        return command.matchUrl(url);
    }
}
