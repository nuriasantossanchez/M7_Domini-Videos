package com.videos.project.application.command;

import com.videos.project.domain.User;
import com.videos.project.domain.UserInterfaz;
import com.videos.project.domain.Video;
import com.videos.project.domain.VideoInterfaz;
import com.videos.project.persistence.Repository;

public class Receiver1 {

    private Repository repository = Repository.getInstance();


    public void actionShowInfo(UserInterfaz user) {
        System.out.println(user.toString());
        if (!repository.getUserVideos((User) user).isEmpty()) {
            System.out.println("\nVideos de '" + user.getName() + '\'');
            for (Video video : repository.getUserVideos((User) user)) {
                System.out.println(video.toString());
            }
        } else {
            System.out.println("\n'" + user.getName() + '\'' + "aun no ha creado ningun video");
        }
    }

    public void actionAddVideoTag(VideoInterfaz video, String tag) {
        video.addVideoTag(tag);
    }
}
