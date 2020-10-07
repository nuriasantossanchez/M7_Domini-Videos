package com.videos.project.application.command;

import com.videos.project.domain.UserInterfaz;
import com.videos.project.domain.VideoInterfaz;

public class Command1 implements Command {

    private Receiver1 receiver1;

    public Command1(Receiver1 receiver1) {

        this.receiver1 = receiver1;
    }

    @Override
    public void executeActionVerVideos(UserInterfaz user) {
        receiver1.actionVerVideos(user);
    }

    @Override
    public void executeActionAddVideoTag(VideoInterfaz video, String tag) {
        receiver1.actionAddVideoTag(video,tag);

    }
}
