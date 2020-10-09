package com.videos.project.application.command;

import com.videos.project.application.builder.VideoInterface;
import com.videos.project.application.builder.WrapperObjectInterface;
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

    /**
     * Accede a un usuario del Repository y muestra la info de ese usuario y sus videos asociados
     *
     * @param user, cualquier objeto que implemente la interface WrapperObjectInterface
     *              o cualquier otra interface que extienda a WrapperObjectInterface,
     *              en este caso, objeto de tipo User, que implementa la interface
     *              UserInterface que extiende a WrapperObjectInterface
     */
    public void actionShowInfo(WrapperObjectInterface user) {
        System.out.println(user.toString());
        if (!repository.getUserVideos((User) user).isEmpty()) {
            System.out.println("\nVideos de '" + ((User) user).getName() + '\'');
            for (Video video : repository.getUserVideos((User) user)) {
                System.out.println(video.toString());
            }
        } else {
            System.out.println("\n'" + ((User) user).getName() + '\'' + "aun no ha creado ningun video");
        }
    }

    /**
     * Añade una etiqueta a un video determinado
     *
     * @param video, cualquier objeto que implemente la interface WrapperObjectInterface
     *               o cualquier otra interface que extienda a WrapperObjectInterface,
     *               en este caso, objeto de tipo Video, que implementa la interface
     *               VideoInterface que extiende a WrapperObjectInterface
     *
     * @param tag String que representa una etiqueta que sera añadida a un video en concreto
     */
    public void actionAddVideoTag(WrapperObjectInterface video, String tag) {

        ((VideoInterface)video).addVideoTag(tag);
    }
}
