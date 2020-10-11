package com.videos.project.application.command;

import com.videos.project.application.Controller;
import com.videos.project.domain.User;
import com.videos.project.domain.Video;
import com.videos.project.persistence.Repository;

/**
 *  Clase de la capa Application, utilizada para implementar el patron Command
 *
 *  Se encarga de realizar las peticiones solicitadas
 *
 */
public class Receiver {

    private Repository repository = Repository.getInstance();
    private Controller controller;

    private int numberOfVideos;


    public Receiver(Controller controller){
        this.controller=controller;

    }

    /**
     * Accede a un usuario del Repository y muestra la info de ese usuario y sus videos asociados
     *
     * @param user, cualquier objeto que implemente la interface WrapperObjectInterface
     *              o cualquier otra interface que extienda a WrapperObjectInterface,
     *              en este caso, objeto de tipo User, que implementa la interface
     *              UserInterface que extiende a WrapperObjectInterface
     */
    public void showInfoVideos(User user) {
        System.out.println(user.toString());
        if (!this.repository.getUserVideos(user).isEmpty()) {
            System.out.println("\nVideos de '" + (user).getName() + '\'');
            for (Video video : this.repository.getUserVideos(user)) {
                System.out.println(video.toString());
            }

        } else {
            getNumberOfVideos();
        }
    }

    /**
     * Añade una etiqueta a un video determinado
     *  @param video, cualquier objeto que implemente la interface WrapperObjectInterface
     *               o cualquier otra interface que extienda a WrapperObjectInterface,
     *               en este caso, objeto de tipo Video, que implementa la interface
     *               VideoInterface que extiende a WrapperObjectInterface
     *
     * @param tag String que representa una etiqueta que sera añadida a un video en concreto
     */
    public void addTagVideo(String tag, Video video) {
        video.addTag(tag);
    }

    public void addUserVideo(Video video, User user) {
        user.addVideo(video);
        numberOfVideos++;

    }

    public void getNumberOfVideos() {
        if(numberOfVideos==0){
            System.out.println("\n'" + controller.getUser().getName() + '\'' + " aun no ha creado ningun video");
        }
        else if(numberOfVideos==1){
            System.out.println('\''+ controller.getUser().getName()+'\'' + " ha creado "
                    + numberOfVideos +" video");
        }
        else{
            System.out.println('\''+ controller.getUser().getName()+'\'' + " ha creado "
                    + numberOfVideos +" videos");
        }
    }
}
