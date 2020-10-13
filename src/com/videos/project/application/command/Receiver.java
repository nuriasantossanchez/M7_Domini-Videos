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


    public Receiver(){

    }

    /**
     * Metodo que accede al repositorio para mostrar el listado de videos asociados a un usuario determinado
     *
     * @param user, objeto de tipo User
     *
     */
    public void listarVideos(User user) {
        System.out.println(user.toString());
        if (!Repository.getInstance().getUserVideos(user).isEmpty()) {
            System.out.println("\nVideos de '" + (user).getName() + '\'');
            for (Video video : Repository.getInstance().getUserVideos(user)) {
                System.out.println(video.toString());
            }
        }
    }

    /**
     *  Metodo que añade una nueva etiqueta al listado de etiquetas de un video determinado
     *
     * @param video, objeto de tipo Video
     * @param tag String que representa la etiqueta que sera añadida al listado de etiquetas
     *            de un video concreto
     */
    public void addTagVideo(String tag, Video video) {

        video.addTag(tag);
    }

    /**
     * Metodo que añade un nuevo video al listado de videos de un usuario
     *
     * @param user, objeto de tipo User
     * @param video, objeto de tipo Video
     */
    public void addUserVideo(User user, Video video) {
        user.addVideo(video);

    }

    /**
     * Metodo que devuelve el numero de videos que va creando un usuario
     *
     * @param user, objeto de tipo User
     * @return un entero que reprenseta el numero de videos creados por el usuario
     */
    public int getNumberOfUserVideos(User user) {

        return user.getVideos().size();
    }

    /**
     * Metodo que con una expresion regular comprueba si una url cumple con el
     * patron de formato especificado
     *
     * @param url, String que representa la url que se quiere checkear
     * @return true, si la url cumple con el patron de formato especificado,
     *         false en caso contrario
     */
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
