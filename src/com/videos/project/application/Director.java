package com.videos.project.application;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

public class Director {

    private ComplexObject complexObject;
    private Builder builder;

    public Director(Builder builder) {

        this.builder = builder;
    }

    public User constructUser(String name, String surname, String password) {
        return builder.buildUser(name, surname, password);
    }

    public Video constructVideo(String url, String tittle) {
        return builder.buildVideo(url, tittle);
    }

    public void printComplexObject() {
        complexObject = builder.getComplexObject();
        System.out.println(complexObject.getParts());
    }
}
