package com.videos.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de la capa Domain
 *
 * Representa a un video creado por un usuario, identificado por
 * una url y un titulo
 *
 * Contiene un listado de tags, que son etiquetas que el usuario
 * propietario del video puede ir añadiendo a cada video
 *
 */
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
