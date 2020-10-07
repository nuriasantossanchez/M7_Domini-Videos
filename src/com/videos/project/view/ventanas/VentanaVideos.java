package com.videos.project.view.ventanas;


import com.videos.project.application.Controller;
import com.videos.project.domain.UserInterfaz;
import com.videos.project.domain.Video;
import com.videos.project.domain.VideoInterfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VentanaVideos extends JFrame implements ActionListener {

    private JLabel labelTitulo, labelTabla;
    private JTextField textUrl, textTittle;
    private JLabel linkTags;
    private JLabel url, tittle;
    private JButton botonCrearVideo, botonSalir;
    JTable videoTable;
    JScrollPane scroll;
    private Controller controller;
    private UserInterfaz user;

    private ArrayList<VideoInterfaz> videoList = new ArrayList<VideoInterfaz>();

    /**
     * constructor de la clase donde se inicializan todos los componentes de la
     * ventana de videos
     */
    public VentanaVideos(Controller controller, UserInterfaz user) {
        this.controller= controller;
        this.user=user;

        botonCrearVideo = new JButton();
        botonCrearVideo.setBounds(90, 220, 140, 30);
        botonCrearVideo.setText("Crear Video");

        botonSalir = new JButton();
        botonSalir.setBounds(240, 220, 100, 30);
        botonSalir.setText("Salir");

        labelTitulo = new JLabel();
        labelTitulo.setText("Hola "+ controller.getUser().getName().toUpperCase() + ", ya puedes Crear tus Videos");
        labelTitulo.setBounds(80, 20, 300, 30);

        labelTabla = new JLabel();
        labelTabla.setText("MIS VIDEOS");
        labelTabla.setBounds(40, 250, 380, 50);

        url = new JLabel();
        url.setText("URL");
        url.setBounds(50, 80, 100, 30);
        add(url);

        tittle = new JLabel();
        tittle.setText("Titulo");
        tittle.setBounds(50, 130, 100, 30);
        add(tittle);

        linkTags = new JLabel();
        linkTags.setText("Crea tus Tags");
        linkTags.setBounds(50, 180, 100, 30);
        linkTags.setForeground(Color.BLUE.darker());
        linkTags.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(linkTags);

        textUrl = new JTextField();
        textUrl.setBounds(120, 80, 250, 30);
        add(textUrl);

        textTittle = new JTextField();
        textTittle.setBounds(120, 130, 250, 30);
        add(textTittle);


        botonCrearVideo.addActionListener(this);
        botonSalir.addActionListener(this);

        linkTags.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // the user clicks on the label
            }

        });

        scroll = new JScrollPane();
        scroll.setBounds(40, 300, 400, 130);
        try {
            mostrarVideos();// muestra la tabla de videos del usuario
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(botonSalir);
        add(botonCrearVideo);
        add(labelTitulo);
        add(labelTabla);
        add(scroll);
        limpiar();
        setSize(480, 650);
        setTitle("CREA TUS VIDEOS");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Permite el llenado de la tabla1 usando sin utilizar el table model, para
     * esto usamos logica de programacion por medio de la captura de informacion
     * en una lista y luego en una matriz para llenar la tabla
     */
    public void mostrarVideos() throws Exception {

        String titulos[] = {"URL", "Titulo", "Tags"};
        String informacion[][] = obtieneMariz();// obtenemos la informacion de la matriz

        videoTable = new JTable(informacion, titulos);
        videoTable.setEnabled(false);
        videoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scroll.setViewportView(videoTable);
    }

    /**
     * Metodo que permite retornar la matriz con informacion de los videos del usuario
     *
     * @return
     */
    private String[][] obtieneMariz() throws Exception {

        String informacion[][] = new String[videoList.size()][3];

        for (int x = 0; x < informacion.length; x++) {
            informacion[x][0] = ((Video) videoList.get(x)).getUrl() + "";
            informacion[x][1] = ((Video) videoList.get(x)).getTittle() + "";
            informacion[x][2] = ((Video) videoList.get(x)).getTags() + "";
        }
        return informacion;
    }


    /**
     * Limpia el formulario de creacion de videos
     */
    private void limpiar() {
        textUrl.setText("");
        textTittle.setText("");
    }

    private void showInfo(){
        if(videoList.size()!=1){
            System.out.println('\''+ controller.getUser().getName()+'\'' + " ha creado " + videoList.size() +" videos");
        }
        else{
            System.out.println('\''+ controller.getUser().getName()+'\'' + " ha creado " + videoList.size() +" video");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCrearVideo) {

            try {
                controller.createVideo(textUrl.getText(),textTittle.getText());
                videoList.add(controller.getVideo());
                showInfo();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Error en la Entrada de Datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                /* Actualizamos la tabla despues de añadir cada video */
                try {
                    mostrarVideos();

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                limpiar();
            }
        }
        if (e.getSource() == botonSalir) {
            this.controller.executeOperationShowInfo();
            System.exit(0);
        }
    }


}
