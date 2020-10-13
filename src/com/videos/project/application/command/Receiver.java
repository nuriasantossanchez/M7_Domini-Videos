package com.videos.project.application.command;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;
import com.videos.project.persistence.Repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Clase de la capa Application, utilizada para implementar el patron Command
 *
 *  Se encarga de realizar las peticiones solicitadas
 *
 */
public class Receiver {

    private Repository repository = Repository.getInstance();


    public Receiver(){

    }

    /**
     * Accede a un usuario del Repository y muestra la info de ese usuario y sus videos asociados
     *
     * @param user, cualquier objeto que implemente la interface WrapperObjectInterface
     *              o cualquier otra interface que extienda a WrapperObjectInterface,
     *              en este caso, objeto de tipo User, que implementa la interface
     *              UserInterface que extiende a WrapperObjectInterface
     */
    public void listarVideos(User user) {
        System.out.println(user.toString());
        if (!this.repository.getUserVideos(user).isEmpty()) {
            System.out.println("\nVideos de '" + (user).getName() + '\'');
            for (Video video : this.repository.getUserVideos(user)) {
                System.out.println(video.toString());
            }

        } else {
            getNumberOfUserVideos(user);
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

    public void addUserVideo(User user, Video video) {
        user.addVideo(video);

    }

    public int getNumberOfUserVideos(User user) {

        return user.getVideos().size();
    }

    public boolean matchUrl(String url) {
        boolean urlOk = false;
        final String regex = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|www\\.)" +
                "[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            urlOk=true;

        }
        return urlOk;
    }

}
