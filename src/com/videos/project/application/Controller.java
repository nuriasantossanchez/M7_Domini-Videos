package com.videos.project.application;

import com.videos.project.application.builder.Builder1;
import com.videos.project.application.builder.WrapperObjectInterface;
import com.videos.project.application.builder.Director;
import com.videos.project.application.builder.UserInterface;

import com.videos.project.application.command.Command1;
import com.videos.project.application.command.Invoker;
import com.videos.project.application.command.Receiver;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

import com.videos.project.persistence.Repository;

/**
 * Clase de la capa Application
 *
 * Implementa el patron Singleton, y hace uso del patron Builder para la
 * creacion de objetos de la capa de Dominio y del patron Command para
 * ejecutar ciertas peticiones, como mostrar la info de un usuario y
 * sus videos asociados o añadir etiquetas a un video determinado
 *
 */
public class Controller {

    private Repository repository;
    private WrapperObjectInterface user;
    private WrapperObjectInterface video;
    private Director director;
    private Invoker invoker;
    private static Controller instance=null;

    /**
     * Constructor privado de la clase Controller donde se inicializa la clase Repository,
     * la clase Director que implementa el patron Builder y la clase Invoker que implemeta
     * el patron Command
     *
     * El constructor privado asegura que la clase solo tendra una instancia
     * La clase Controller proveera un punto global de acceso
     * Implementa el patron Singleton
     */
    private Controller() {
        this.repository = Repository.getInstance();
        this.director = new Director(new Builder1());
        this.invoker = new Invoker(new Command1(new Receiver()));

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
     * Hace uso del patron Builder para crear un objeto de tipo User
     * Añade el objeto creado al Repository
     *
     * @param name, nombre del usuario, parametro del constructor de la clase User
     * @param surname, apellido del usuario, parametro del constructor de la clase User
     * @param password, password del usuario, parametro del constructor de la clase User
     * @throws Exception
     */
    public void createUser(String name, String surname, String password) throws Exception {
        this.user = director.constructUser(name, surname, password);
        repository.addUser((User) user);
    }

    /**
     * Hace uso del patron Builder para crear un objeto de tipo Video
     *
     * Añade el objeto creado a un listado de objetos Video asociados
     * a un usuario creado previamente
     *
     * @param url, url del video, parametro del constructor de la clase Video
     * @param tittle, titulo del video, parametro del constructor de la clase Video
     */
    public void createVideo(String url, String tittle){
        this.video = director.constructVideo(url, tittle);
        ((UserInterface)this.user).addVideoUser((Video) video);
    }

    /**
     * Hace uso del patron Command para añadir una etiqueta de tipo String a un video
     * creado previamente
     *
     * @param tag, etiqueta del video
     */
    public void executeOperationAddVideoTag(String tag){
        invoker.operationAddVideoTag(this.video,tag);
    }

    /**
     * Hace uso del patron Command para mostrar por consola los videos asociados a un usuario concreto
     */
    public void executeOperationShowInfo(){
        invoker.operationShowInfo(this.user);
    }

    /**
     * Retorna el objeto tipo User que esta actualmente en memoria
     *
     * @return un objeto que implementa la interface de tipo WrapperObjectInterface,
     * en este caso un objeto tipo User
     */
    public WrapperObjectInterface getUser() {
        return this.user;
    }

    /**
     * Retorna el objeto tipo Video que esta actualmente en memoria
     *
     * @return un objeto que implementa la interface de tipo WrapperObjectInterface,
     * en este caso un objeto tipo Video
     */
    public WrapperObjectInterface getVideo() {
        return this.video;
    }

}
