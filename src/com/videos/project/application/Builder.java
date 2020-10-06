package com.videos.project.application;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

public interface Builder {

    User buildUser(String name, String surname, String password);
    Video buildVideo(String url, String tittle);

    ComplexObject getComplexObject();
}
