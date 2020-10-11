package com.videos.project.application.abstractfactory;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

public interface AbstractFactory {
    User createUser(String name, String surname, String password);
    Video createVideo(String url, String tittle);
}
