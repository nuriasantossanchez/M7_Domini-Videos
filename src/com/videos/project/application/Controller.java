package com.videos.project.application;

import com.videos.project.application.command.Command1;
import com.videos.project.application.command.Invoker;
import com.videos.project.application.command.Receiver1;
import com.videos.project.application.builder.Builder1;
import com.videos.project.application.builder.Director;
import com.videos.project.domain.User;
import com.videos.project.domain.UserInterfaz;
import com.videos.project.domain.Video;
import com.videos.project.domain.VideoInterfaz;
import com.videos.project.persistence.Repository;

public class Controller {

    private Repository repository;
    private UserInterfaz user;
    private VideoInterfaz video;
    private Director director;
    private Invoker invoker;

    public Controller() {
        this.repository = Repository.getInstance();
        this.director = new Director(new Builder1());
        this.invoker = new Invoker(new Command1(new Receiver1()));

    }

    public void createUser(String name, String surname, String password) throws Exception {
        this.user = (UserInterfaz) director.constructUser(name, surname, password);
        repository.addUser((User) user);
    }

    public void createVideo(String url, String tittle) throws Exception {
        this.video = (VideoInterfaz) director.constructVideo(url, tittle);
        this.user.addVideoUser((Video) video);
    }

    public void executeOperationAddVideoTag(String tag){
        invoker.operationAddVideoTag(this.video,tag);
    }

    public void executeOperationVerVideos(){
        invoker.operationVerVideos(this.user);
    }

    public void printInfoRepository() {
        //director.printComplexObject();
        for (User user : repository.getAllUsers()) {
            System.out.println(user.toString());
            System.out.println("\nVideos de '" + user.getName()+ '\'');
            for (Video video : repository.getUserVideos(user)) {
                System.out.println(video.toString());
            }
        }
    }
    //TODO: borrar
    public void printInfoRepository(User user) {
        System.out.println("\nVideos de '" + user.getName()+ '\'');
        for (Video video : repository.getUserVideos(user)) {
            System.out.println(video.toString());
        }
    }

    //TODO: borrar
    public void addVideoTag(String tag) {
        this.video.addVideoTag(tag);

    }

}
