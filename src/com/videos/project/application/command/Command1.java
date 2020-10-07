package com.videos.project.application.command;

import com.videos.project.application.builder.ComplexObjectInterfaz;
import com.videos.project.application.builder.UserInterfaz;
import com.videos.project.application.builder.VideoInterfaz;

public class Command1 implements Command {

    private Receiver1 receiver1;

    public Command1(Receiver1 receiver1) {

        this.receiver1 = receiver1;
    }

    @Override
    public void executeActionShowInfo(ComplexObjectInterfaz user) {
        receiver1.actionShowInfo(user);
    }

    @Override
    public void executeActionAddVideoTag(ComplexObjectInterfaz video, String tag) {
        receiver1.actionAddVideoTag(video,tag);

    }
}
