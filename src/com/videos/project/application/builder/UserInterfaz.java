package com.videos.project.application.builder;

import com.videos.project.domain.Video;

public interface UserInterfaz extends ComplexObjectInterfaz {
    void addVideoUser(Video video);

    String getName();
}
