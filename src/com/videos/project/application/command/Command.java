package com.videos.project.application.command;

import com.videos.project.application.builder.ComplexObjectInterfaz;

public interface Command {
    void executeActionShowInfo(ComplexObjectInterfaz user);

    void executeActionAddVideoTag(ComplexObjectInterfaz video, String tag);
}
