package com.videos.project.application.command;

import com.videos.project.application.builder.ComplexObjectInterfaz;
import com.videos.project.domain.User;
import com.videos.project.application.builder.UserInterfaz;
import com.videos.project.domain.Video;
import com.videos.project.application.builder.VideoInterfaz;
import com.videos.project.persistence.Repository;

public class Receiver1 {

    private Repository repository = Repository.getInstance();


    public void actionShowInfo(ComplexObjectInterfaz user) {
        System.out.println(user.toString());
        if (!repository.getUserVideos((User) user).isEmpty()) {
            System.out.println("\nVideos de '" + ((User) user).getName() + '\'');
            for (Video video : repository.getUserVideos((User) user)) {
                System.out.println(video.toString());
            }
        } else {
            System.out.println("\n'" + ((User) user).getName() + '\'' + "aun no ha creado ningun video");
        }
    }

    public void actionAddVideoTag(ComplexObjectInterfaz video, String tag) {

        ((VideoInterfaz)video).addVideoTag(tag);
    }
}
