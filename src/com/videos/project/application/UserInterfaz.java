package com.videos.project.application;

import com.videos.project.domain.User;
import com.videos.project.domain.Video;

public interface UserInterfaz extends ComplexObjectInterfaz {
    void addVideoUser(Video video);
}
