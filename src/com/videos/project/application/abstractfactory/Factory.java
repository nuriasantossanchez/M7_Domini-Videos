package com.videos.project.application.abstractfactory;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

public class Factory implements AbstractFactory{

    @Override
    public User createUser(String name, String surname, String password) {
        User user=new User(name,surname,password);
        return user;
    }

    @Override
    public Video createVideo(String url, String tittle) {
        Video video=new Video(url,tittle);

        return video;
    }


}
