package com.videos.project.application.command;

import com.videos.project.domain.UserInterfaz;
import com.videos.project.domain.VideoInterfaz;

public class Invoker {
    private Command command;

    public Invoker(Command command) {

        this.command = command;
    }

    public void operationShowInfo(UserInterfaz user) {
        command.executeActionShowInfo(user);
    }
    public void operationAddVideoTag(VideoInterfaz video, String tag) {
        command.executeActionAddVideoTag(video,tag);
    }
}
