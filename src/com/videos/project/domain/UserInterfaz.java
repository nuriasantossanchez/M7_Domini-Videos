package com.videos.project.domain;

import com.videos.project.application.builder.ComplexObjectInterfaz;

public interface UserInterfaz extends ComplexObjectInterfaz {
    void addVideoUser(Video video);

    String getName();
}
