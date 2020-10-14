package com.videos.project.persistence;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de la capa Persistence
 *
 * Implementa el patron Singleton
 *
 * Almacena en un listado de objetos de tipo User a todos los usuario creados
 * Cada objeto User contiene un listado de objetos de tipo Video con los videos
 * asociados a ese usuario concreto
 *
 */
public class Repository {

    private static Repository instance=null;
    private static List<User> users = new ArrayList<>();
    private User currentUser;

    /**
     * Constructor privado de la clase Controller (patron Singleton)
     */
    private Repository(){

    }

    /**
     * Metodo que retorna una instancia unica de la clase Repository
     * y representa el punto global de acceso al Repository
     *
     * @return instacia unica de la clase Repository
     */
    public static Repository getInstance(){
        if (instance==null){
            instance=new Repository();
        }
        return instance;

    }

    /**
     * Añade un objeto de tipo User al listado de usuarios que contiene el Repository
     *
     * @param user, objeto de tipo User
     * @throws Exception, lanza una exception si el objeto User es nullo
     */
    public void addUser(User user) throws Exception {

        if (user == null) {
            throw new Exception();
        }

        users.add(user);
    }

    /**
     * Retorna el listado de objetos de tipo User que contiene la clase Repository
     *
     * @return un listado de objetos de tipo User
     */
    public List<User> getAllUsers() {

        return new ArrayList<>(users);
    }

    /**
     * Retorna un listado de objetos de tipo Video asociados a un usuario determinado
     *
     * @param user, objeto de tipo User
     * @return un listado de objeto de tipo Video asociados a un usuario determinado
     */
    public List<Video> getUserVideos(User user) {
        List<Video> userVideos=new ArrayList<>();
        for (User usr: getAllUsers()){
            if(usr.getName().equals(user.getName())
                && usr.getSurname().equals(user.getSurname())
                && usr.getPassword().equals(user.getPassword())){
                    userVideos.addAll(usr.getVideos());
                    break;
            }
        }

        return userVideos;
    }

    /**
     * Comprueba si un usuario existe en el listado de usuarios que contiene la clase Repository
     *
     * Si el usuario existe, recupera la instancia de ese usuario y la asigna a una variable de
     * tipo User, currentUser, propia de la clase Repository, que representa el usuario actual
     * que esta usando el sistema y que no es un nuevo usuario
     *
     * @param name, String que representa el nombre del ususario que se esta buscando en el listado
     * @param surname, String que representa el apellido del ususario que se esta buscando en el listado
     * @param password, String que representa la password del ususario que se esta buscando en el listado
     * @return true, si existe un objeto de tipo User con el mismo nombre, apellido y password,
     *         false en caso contrario
     */
    public boolean isUserExisting(String name, String surname, String password) {
        boolean isUserExisting=false;
        for (User user: getAllUsers()){
            if(user.getName().equals(name)
                    && user.getSurname().equals(surname)
                    && user.getPassword().equals(password)){
                this.currentUser =user;
                isUserExisting=true;
                break;
            }
        }
        return isUserExisting;
    }

    /**
     * Comprueba si un video existe en el listado de videos que pertenecen a un usuario determinado
     *
     * @param user, objeto de tipo User
     * @param url, String que representa la url del video que se esta buscando en el listado
     * @param tittle, String que representa el titulo del video que se esta buscando en el listado
     * @return true si el video existe en el listado de videos de un usuario concreto,
     *         false en caso contrario
     */
    public boolean isUserVideoExisting(User user, String url, String tittle) {
        boolean isUserVideoExisting=false;
        for (Video vdo: getUserVideos(user)){
            if(vdo.getUrl().equals(url) && vdo.getTittle().equals(tittle)){
                isUserVideoExisting=true;
                break;
            }
        }
        return isUserVideoExisting;
    }
    /**
     * Retorna una isntacia de tipo User que representa un usuario que ya existe
     * en el listado de usuarios que contiene la clase Repository
     *
     * @return un objeto de tipo User, que representa el usuario actual que
     *         esta usando el sistema y que no es un nuevo usuario
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Recorre el listado de videos que pertenecen a un usuario determinado y retona,
     * si existe, un objeto de tipo Video cuya url y titulo coinciden con la url y titulo
     * pasados como paramtero
     *
     * @param user, objeto de tipo User
     * @param url, String que representa la url del video que se esta buscando en el listado
     *             de videos del usuario
     * @param tittle, String que representa el titulo del video que se esta buscando en el listado
     *                de videos del usuario
     * @return un objeto de tipo Video que ya existe en el listado de videos del usuario,
     *         null si el video no existe en el listado
     */
    public Video getVideo(User user, String url, String tittle){
        Video video=null;
        for (Video vdo: getUserVideos(user)){
            if(vdo.getUrl().equals(url) && vdo.getTittle().equals(tittle)){
                video=vdo;
                break;
            }
        }
        return video;
    }
}

