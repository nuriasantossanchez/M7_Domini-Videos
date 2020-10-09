package com.videos.project.domain;

import com.videos.project.application.builder.VideoInterface;

import java.util.ArrayList;
import java.util.List;

public class Video implements VideoInterface {
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

    public String getUrl() {
        return url;
    }

    public String getTittle() {
        return tittle;
    }

    public List<String> getTags() {
        return tags;
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
