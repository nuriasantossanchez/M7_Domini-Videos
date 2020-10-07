package com.videos.project.application.builder;

public class Director {

    private ComplexObject complexObject;
    private Builder builder;

    public Director(Builder builder) {

        this.builder = builder;
    }

    public ComplexObjectInterfaz constructUser(String name, String surname, String password) {
        return builder.buildUser(name, surname, password);
    }

    public ComplexObjectInterfaz constructVideo(String url, String tittle) {

        return builder.buildVideo(url, tittle);
    }

    public void printComplexObject() {
        complexObject = builder.getComplexObject();
        System.out.println(complexObject.getParts());
    }
}
