package com.videos.project.application;

import com.videos.project.application.abstractfactory.AbstractFactory;
import com.videos.project.application.abstractfactory.Factory;

import com.videos.project.application.command.Command1;
import com.videos.project.application.command.Invoker;
import com.videos.project.application.command.Receiver;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

import com.videos.project.persistence.Repository;

/**
 * Clase de la capa Application
 *
 * Implementa el patron Singleton
 * Hace uso del patron AbstractFactory para la creacion de objetos de la capa de Dominio
 * Hace uso del patron Command para ejecutar solicitudes de peticiones
 *
 */
public class Controller {

    private Repository repository;
    private User user;
    private Video video;
    private AbstractFactory factory;
    private Invoker invoker;
    private static Controller instance=null;

    /**
     * Constructor privado de la clase Controller
     *
     * Inicializa la clase Repository que alamcena la informacion de usuarios creados
     * y sus videos asociados
     *
     * Inicializa la clase Factory que implementa el patron AbstractFactory
     *
     * Inicializa la clase Invoker, que se utiliza para implemetar el patron Command
     *
     * El constructor privado asegura que la clase solo tendra una instancia (patron Singleton)
     * La clase Controller proveera un punto global de acceso
     */
    private Controller() {
        this.repository = Repository.getInstance();
        this.factory = new Factory();
        this.invoker = new Invoker(new Command1(new Receiver()));

    }

    /**
     * Metodo que representa el punto global de acceso a la instancia unica
     * de la clase Controller
     *
     * @return instacia unica de la clase Controller
     */
    public static Controller getInstance(){
        if (instance==null){
            instance=new Controller();
        }
        return instance;

    }

    /**
     * Delega en la clase Factory la creacion de un objeto de tipo User
     *
     * Añade el objeto creado al Repository, en caso de que sea un nuevo usuario
     * Si el usuario ya existe, inicicaliza la instacia de usuario que esta actualmente
     * en memoria con el valor recuperado del repositorio
     *
     * @param name, nombre del usuario, parametro del constructor de la clase User
     * @param surname, apellido del usuario, parametro del constructor de la clase User
     * @param password, password del usuario, parametro del constructor de la clase User
     * @throws Exception
     */
    public void createUser(String name, String surname, String password) throws Exception {
        if(!repository.isUserExisting(name, surname, password)){
            this.user = factory.createUser(name, surname, password);
            repository.addUser(this.user);
        }else{
            this.user=repository.getCurrentUser();
        }
    }

    /**
     * Delega en la clase Factory la creacion de un objeto de tipo Video
     * No permite crear videos iguales, con la misma url y el mismo titulo
     *
     * Hace uso del patron Command para añadir el nuevo objeto creado al listado
     * de objetos de tipo Video asociados a un usuario determinado, addUserVideo()
     *
     * @param url, url del video, parametro del constructor de la clase Video
     * @param tittle, titulo del video, parametro del constructor de la clase Video
     * @return true si se ha creado un nuevo video y añadido al listado de videos del
     *         usuario, false en caso contrario, si el video ya existe en el listado y por
     *         tanto no se crea por ser repetido
     */

    public boolean createVideo(String url, String tittle){
        boolean isVideoCreated=false;
        if(!repository.isUserVideoExisting(this.user,url, tittle)){
            this.video = factory.createVideo(url, tittle);
            this.addUserVideo();
            isVideoCreated=true;
        }
        return isVideoCreated;
    }

    /**
     * Hace uso del patron Command para checkear si una matricula cumple con el
     * patron de formato especificado
     *
     * @param url, String que representa la url que se quiere checkear
     * @return true, si la url cumple con el patron de formato especificado,
     *         false en caso contrario
     */
    public boolean matchUrl(String url) {
        return invoker.matchUrl(url);
    }

    /**
     * Hace uso del patron Command para añadir una etiqueta a un video
     *
     * @param tag String que representa la etiqueta que sera añadida a un video concreto
     */
    public void addTagVideo(String tag, Video video){

        invoker.addTagVideo(tag, video);
    }

    /**
     * Delega en el patron Command la peticion de mostrar por consola
     * los videos asociados a un usuario determinado
     */
    public void listarVideos(){

        invoker.listarVideos(this.user);
    }

    /**
     * Delega en el patron Command la peticion de añadir un nuevo video a
     * un listado de objetos Video asociados a un usuario determinado
     */
    public void addUserVideo(){

        invoker.addUserVideo(this.user, this.video);
    }

    /**
     * Delega en el patron Command la peticion de mostrar por consola el numero
     * de videos que va creando un usuario
     *
     * @param user, objeto de tipo User
     */
    public void getNumberOfUserVideos(User user){
        int numberOfUserVideos = invoker.getNumberOfUserVideos(user);;

        if(numberOfUserVideos !=1){
            System.out.println('\''+ getUser().getName()+'\'' + " ha creado "
                    + numberOfUserVideos +" videos");
        }
        else{
            System.out.println('\''+ getUser().getName()+'\'' + " ha creado "
                    + numberOfUserVideos +" video");
        }
    }

    /**
     * Retorna el objeto de tipo User que esta actualmente en memoria
     *
     * @return un objeto tipo User
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Retorna el objeto de tipo Video que esta actualmente en memoria
     *
     * @return un objeto tipo Video
     */
    public Video getVideo() {

        return this.video;
    }

    /**
     * Retorna un objeto de tipo Video que forma parte del listado de videos
     * creados por un usuario determinado
     *
     * @param user objeto tipo User
     * @param url String que representa la url de un video
     * @param tittle String que representa el titulo de un video
     * @return un objeto tipo Video cuya url y titulo coincide
     *         con la url y tittle pasados como parametro,
     *         null si el video no existe en el listado
     *
     */
    public Video getVideo(User user,String url, String tittle){
        return repository.getVideo(user,url,tittle);
    }

}
