package com.videos.project.view;

import com.videos.project.application.Controller;
import com.videos.project.view.ventanas.VentanaLogin;
import com.videos.project.view.ventanas.VentanaVideos;


public class MainApp {

    private static Controller controller=Controller.getInstance();

    public static void main(String[] args) throws Exception {

        VentanaLogin loginUser=new VentanaLogin(controller);
        loginUser.setVisible(true);

        //controller.createUser("Nuria","bb","12f");
        //VentanaVideos videos=new VentanaVideos(controller,controller.getUser());
        //videos.setVisible(true);



        /*
        String tag1="tag1";
        String tag2="tag2";
        String tag3="tag3";
        String tag4="tag4";


        controller.createUser("aa","bb","12f");
        controller.createVideo("url1","title1");
        controller.executeOperationAddVideoTag(tag1);
        controller.executeOperationAddVideoTag(tag2);

        controller.createVideo("url2","title2");
        controller.executeOperationAddVideoTag(tag3);
        controller.executeOperationAddVideoTag(tag4);

        controller.createVideo("url3","title3");

        controller.createUser("2","2","22");
        controller.createVideo("url2222","title222");
        controller.executeOperationAddVideoTag("mi tag video");

        controller.printInfoRepository();

        controller.executeOperationVerVideos();

         */

    }


}

