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
     * Constructor privado de la clase Controller donde se inicializa la clase Repository,
     * la clase Factory que implementa el patron AbstractFactory y la clase Invoker,
     * utilizada para implemetar el patron Command
     *
     * El constructor privado asegura que la clase solo tendra una instancia (patron Singleton)
     * La clase Controller proveera un punto global de acceso
     */
    private Controller() {
        this.repository = Repository.getInstance();
        this.factory = new Factory();
        this.invoker = new Invoker(new Command1(new Receiver(this)));

    }

    /**
     * Metodo que representa el punto global de acceso a la instancia unica de la clase Controller
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
     * Añade el objeto creado al Repository
     *
     * @param name, nombre del usuario, parametro del constructor de la clase User
     * @param surname, apellido del usuario, parametro del constructor de la clase User
     * @param password, password del usuario, parametro del constructor de la clase User
     * @throws Exception
     */
    public void createUser(String name, String surname, String password) throws Exception {
        this.user = factory.createUser(name, surname, password);
        repository.addUser(this.user);
    }

    /**
     * Delega en la clase Factory la creacion de un objeto de tipo Video
     *
     * Hace uso del patron Command para añadir el objeto creado a un listado
     * de objetos Video asociados a un usuario
     *
     * @param url, url del video, parametro del constructor de la clase Video
     * @param tittle, titulo del video, parametro del constructor de la clase Video
     */
    public boolean createVideo(String url, String tittle){
        boolean videoCreated=false;
        if(!repository.isUserVideoRepited(this.user,url, tittle)){
            this.video = factory.createVideo(url, tittle);
            addUserVideo();
            videoCreated=true;
        }

        return videoCreated;
    }

    /**
     * Hace uso del patron Command para añadir una etiqueta de tipo String a un video
     *
     * @param tag, etiqueta del video
     */
    public void addTagVideo(String tag){

        invoker.addTagVideo(this.video,tag);
    }

    public void addTagVideo(String tag, Video video){

        invoker.addTagVideo(video,tag);
    }

    /**
     * Delega en el patron Command la peticion de mostrar por consola los videos asociados a un usuario concreto
     */
    public void showInfoVideos(){

        invoker.showInfoVideos(this.user);
    }

    /**
     * Delega en el patron Command la peticion de añadir un video a un listado
     * de objetos Video asociados a un usuario
     */
    public void addUserVideo(){

        invoker.addUserVideo(this.video,this.user);
    }

    /**
     * Delega en el patron Command la peticion de mostrar por consola el numero de videos que va creando el usuario
     */
    public void getNumberOfVideos(){
        invoker.getNumberOfVideos();
    }

    /**
     * Retorna el objeto tipo User que esta actualmente en memoria
     *
     * @return un objeto tipo User
     */
    public User getUser() {

        return this.user;
    }

    /**
     * Retorna el objeto tipo Video que esta actualmente en memoria
     *
     * @return un objeto tipo Video
     */
    public Video getVideo() {

        return this.video;
    }

    public Video getVideo(User user,String url, String tittle){

        return repository.getVideo(user,url,tittle);
    }

}
