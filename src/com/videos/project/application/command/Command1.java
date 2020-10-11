package com.videos.project.application.command;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

/**
 *  Clase de la capa Application, utilizada para implementar el patron Command
 *
 *  Esta clase implementa la interfaz Command por lo que esta obligada a implementar los metodos
 *  que expone la interfaz
 *
 *  La interfaz Command encapsula las distintas peticiones, a traves de cuyas implementaciones
 *  se puede ejecutar una solicitud
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
     * Realiza una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar una accion,
     * en este caso, actionShowInfo(user)
     *
     * @param user, cualquier objeto que implemente la interface WrapperObjectInterface
     *              o cualquier otra interface que extienda a WrapperObjectInterface,
     *              en este caso, objeto de tipo User
     */
    @Override
    public void showInfoVideos(User user) {

        receiver.showInfoVideos(user);
    }

    @Override
    public void addUserVideo(Video video, User user) {
        receiver.addUserVideo(video, user);

    }

    @Override
    public void getNumberOfVideos() {
        receiver.getNumberOfVideos();
    }

    /**
     * Realiza una operacion sobre otro objeto, objeto Receiver, encargado de ejecutar una accion,
     * en este caso, actionAddVideoTag(user)
     *  @param video, cualquier objeto que implemente la interface WrapperObjectInterface
     *               o cualquier otra interface que extienda a WrapperObjectInterface,
     *               en este caso, objeto de tipo Video
     *
     * @param tag String que representa una etiqueta que sera añadida a un video en concreto
     */
    @Override
    public void addTagVideo(String tag, Video video) {
        receiver.addTagVideo(tag, video);

    }


}
