package com.videos.project.persistence;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository instance=null;
    private static List<User> users = new ArrayList<>();
    private User user = null;

    private Repository(){

    }

    public static Repository getInstance(){
        if (instance==null){
            instance=new Repository();
        }
        return instance;

    }

    public void addUser(User user) throws Exception {

        if (user == null) {
            throw new Exception();
        }

        users.add(user);
    }

    public List<User> getAllUsers() {

        return new ArrayList<>(users);
    }

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

    public boolean isUserExisting(String name, String surname, String password) {
        boolean isUserExisting=false;
        for (User usr: getAllUsers()){
            if(usr.getName().equals(name)
                    && usr.getSurname().equals(surname)
                    && usr.getPassword().equals(password)){
                this.user=usr;
                isUserExisting=true;
                break;
            }
        }
        return isUserExisting;
    }

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

    public User getUser() {
        return user;
    }

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

