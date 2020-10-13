package com.videos.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Video{
    private String url;
    private String tittle;
    private List<String> tags = new ArrayList<>();

    public Video(String url, String tittle) {
        this.url = url;
        this.tittle = tittle;
    }

    public void addTag(String tag) {

        this.tags.add(tag);
    }

    public String getUrl() {

        return this.url;
    }

    public String getTittle() {

        return this.tittle;
    }

    public List<String> getTags() {

        return this.tags;
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
