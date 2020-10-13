package com.videos.project.application.command;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

/**
 *  Clase de la capa Application, se utiliza para implementar el patron Command
 *
 *  Esta clase implementa la interfaz Command por lo que esta obligada a implementar los metodos
 *  que expone la interfaz
 *
 *  La interfaz Command encapsula las distintas peticiones, la implementacion de
 *  estas peticiones permiten ejecutar una solicitud
 *
 */
public class Command1 implements Command {

    private Receiver receiver;

    /**
     * Constructor de la clase, compuesto por un objeto de tipo Receiver, pasado como parametro,
     * encargado de ejecutar las distintas acciones
     *
     * @param receiver, objeto de tipo Receiver que realiza las acciones solicitadas
     */
    public Command1(Receiver receiver) {

        this.receiver = receiver;
    }

    /**
     * Metodo que realiza una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar una accion,
     * en este caso, listarVideos()
     *
     * @param user, objeto de tipo User
     */
    @Override
    public void listarVideos(User user) {

        receiver.listarVideos(user);
    }

    /**
     * Metodo que realiza una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar una accion,
     * en este caso, addUserVideo()
     *
     * @param user, objeto de tipo User
     * @param video, objeto de tipo Video
     */
    @Override
    public void addUserVideo(User user, Video video) {
        receiver.addUserVideo(user, video);

    }

    /**
     * Metodo que realiza una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar una accion,
     * en este caso, getNumberOfUserVideos()
     *
     * @param user, objeto de tipo User
     * @return un entero que reprenseta el numero de videos creados por un usuario
     *
     */
    @Override
    public int getNumberOfUserVideos(User user) {

        return receiver.getNumberOfUserVideos(user);

    }

    /**
     * Metodo que realiza una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar una accion,
     * en este caso, matchUrl()
     *
     * @param url, String que representa la url que se quiere checkear
     * @return true, si la url cumple con el patron de formato especificado,
     *         false en caso contrario
     */
    @Override
    public boolean matchUrl(String url) {
        return receiver.matchUrl(url);
    }

    /**
     * Metodo que realiza una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar una accion,
     * en este caso, addTagVideo()
     *
     * @param video, objeto de tipo Video
     * @param tag String que representa la etiqueta que sera añadida a un video concreto
     */
    @Override
    public void addTagVideo(String tag, Video video) {
        receiver.addTagVideo(tag, video);

    }

}
