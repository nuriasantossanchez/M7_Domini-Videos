package com.videos.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Video implements VideoInterfaz {
    private String url;
    private String tittle;
    private List<String> tags = new ArrayList<>();

    public Video(String url, String tittle) {
        this.url = url;
        this.tittle = tittle;
    }

    @Override
    public void addVideoTag(String tag) {

        this.tags.add(tag);
    }


    @Override
    public String toString() {
        return "\nVideo\n{" +
                "url='" + url + '\'' +
                ", tittle='" + tittle + '\'' +
                ", tags=" + tags.toString() +
                "}";
    }


}
