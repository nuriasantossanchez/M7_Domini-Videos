package com.videos.project.persistence;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static Repository instance=null;
    private static List<User> users = new ArrayList<>();

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
}

