package com.videos.project.application;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;
import com.videos.project.persistence.Repository;

public class Controller {

    private Repository repository;
    private User user;
    private Video video;
    private Director director;

    public Controller() {
        this.repository = Repository.getInstance();
        this.director = new Director(new Builder1());

    }

    public void createUser(String name, String surname, String password) throws Exception {
        this.user = director.constructUser(name, surname, password);
        repository.addUser(user);
    }

    public void createVideo(String url, String tittle) throws Exception {
        this.video = director.constructVideo(url, tittle);
        this.user.addVideoUser(video);
    }

    public void printInfo() {
        //director.printComplexObject();
        for (User user : repository.getAllUsers()) {
            System.out.println(user.toString());
            System.out.println("\nVideos de " + user.getName());
            for (Video video : repository.getUserVideos(user)) {
                System.out.println(video.toString());
            }
        }
    }

    public void printInfo(User user) {
        System.out.println("\nVideos de " + user.getName());
        for (Video video : repository.getUserVideos(user)) {
            System.out.println(video.toString());
        }
    }

    public void addVideoTag(String tag) {
        this.video.addVideoTag(tag);

    }

}
