package com.videos.project.application;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

public class Builder1 implements Builder {

    private ComplexObject complexObject = new ComplexObject();

    @Override
    public User buildUser(String name, String surname, String password) {
        User user=new User(name,surname,password);
        complexObject.add(user);
        return user;


    }

    @Override
    public Video buildVideo(String url, String tittle) {
        Video video=new Video(url,tittle);
        complexObject.add(video);
        return video;

    }

    @Override
    public ComplexObject getComplexObject() {
        return complexObject;
    }
}
