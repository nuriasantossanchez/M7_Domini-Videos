package com.videos.project.view;

import com.videos.project.application.Controller;
import com.videos.project.view.ventanas.VentanaLogin;


public class MainApp {

    private static Controller controller=Controller.getInstance();

    public static void main(String[] args) {

        VentanaLogin loginUser=VentanaLogin.getInstance(controller);
        loginUser.setVisible(true);

    }


}

