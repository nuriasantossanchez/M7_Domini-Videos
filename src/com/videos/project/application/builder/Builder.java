package com.videos.project.application.builder;

public interface Builder {

    ComplexObjectInterfaz buildUser(String name, String surname, String password);
    ComplexObjectInterfaz buildVideo(String url, String tittle);

    ComplexObject getComplexObject();
}
