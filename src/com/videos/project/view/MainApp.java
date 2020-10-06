package com.videos.project.view;

import com.videos.project.application.Controller;
import com.videos.project.domain.User;

public class MainApp {

    private static Controller controller=new Controller();

    public static void main(String[] args) throws Exception {

        String tag1="tag1";
        String tag2="tag2";
        String tag3="tag3";
        String tag4="tag4";

        controller.createUser("aa","bb","12f");
        controller.createVideo("url1","title1");
        controller.addVideoTag(tag1);
        controller.addVideoTag(tag2);

        controller.createVideo("url2","title2");
        controller.addVideoTag(tag3);
        controller.addVideoTag(tag4);


        controller.createVideo("url3","title3");


        controller.createUser("2","2","22");
        controller.createVideo("url2222","title222");
        controller.addVideoTag("mi tag video");

        controller.printInfo();

        controller.printInfo(new User("2","2","22"));



    }


}

