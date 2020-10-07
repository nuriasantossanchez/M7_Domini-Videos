package com.videos.project.application.command;

import com.videos.project.application.builder.ComplexObjectInterfaz;

public class Invoker {
    private Command command;

    public Invoker(Command command) {

        this.command = command;
    }

    public void operationShowInfo(ComplexObjectInterfaz user) {
        command.executeActionShowInfo(user);
    }
    public void operationAddVideoTag(ComplexObjectInterfaz video, String tag) {
        command.executeActionAddVideoTag(video,tag);
    }
}
