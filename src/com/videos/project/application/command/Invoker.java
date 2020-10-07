package com.videos.project.application.command;

import com.videos.project.domain.UserInterfaz;
import com.videos.project.domain.VideoInterfaz;

public class Invoker {
    private Command command;

    public Invoker(Command command) {

        this.command = command;
    }

    public void operationVerVideos(UserInterfaz user) {
        command.executeActionVerVideos(user);
    }
    public void operationAddVideoTag(VideoInterfaz video, String tag) {
        command.executeActionAddVideoTag(video,tag);
    }
}
