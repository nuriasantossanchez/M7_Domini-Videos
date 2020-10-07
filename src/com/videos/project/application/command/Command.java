package com.videos.project.application.command;

import com.videos.project.domain.UserInterfaz;
import com.videos.project.domain.VideoInterfaz;

public interface Command {
    void executeActionVerVideos(UserInterfaz user);

    void executeActionAddVideoTag(VideoInterfaz video, String tag);
}
