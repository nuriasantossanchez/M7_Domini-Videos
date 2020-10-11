package com.videos.project.view.ventanas;

import com.videos.project.application.Controller;
import com.videos.project.domain.User;
import com.videos.project.domain.Video;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Clase de la capa View
 * <p>
 * Muestra un formulario para la creacion de Videos asociados a un usuario
 * Muestra la info de cada nuevo video en un componente JTable
 * <p>
 * El formulario no permite campos vacios
 */
public class VentanaVideos extends JFrame implements ActionListener {

    private JLabel labelTitulo, labelTabla;
    private JTextField textUrl, textTittle;
    private JLabel linkTags;
    private JLabel url, tittle;
    private JButton botonCrearVideo, botonSalir;
    JTable videosJTable;
    JScrollPane scrollTabla;
    private Controller controller;

    private ArrayList<Video> videoList = new ArrayList<Video>();

    /**
     * constructor de la clase donde se inicializan todos los componentes de la
     * ventana de Videos
     */
    public VentanaVideos(Controller controller, User user) {
        this.controller = controller;

        botonCrearVideo = new JButton();
        botonCrearVideo.setBounds(90, 220, 140, 30);
        botonCrearVideo.setText("Crear Video");
        add(botonCrearVideo);

        botonSalir = new JButton();
        botonSalir.setBounds(240, 220, 100, 30);
        botonSalir.setText("Salir");
        add(botonSalir);

        labelTitulo = new JLabel();
        labelTitulo.setText("<html>Hola " + (controller.getUser()).getName().toUpperCase()
                + " !<br/>Ya puedes Crear tus Videos</html>");
        labelTitulo.setBounds(120, 20, 300, 50);
        add(labelTitulo);

        labelTabla = new JLabel();
        labelTabla.setText("MIS VIDEOS");
        labelTabla.setBounds(40, 270, 380, 30);
        add(labelTabla);

        scrollTabla = new JScrollPane();
        scrollTabla.setBounds(40, 300, 400, 130);
        add(scrollTabla);

        url = new JLabel();
        url.setText("URL");
        url.setBounds(50, 80, 100, 30);
        add(url);

        tittle = new JLabel();
        tittle.setText("Titulo");
        tittle.setBounds(50, 130, 100, 30);
        add(tittle);

        linkTags = new JLabel();
        linkTags.setText("<HTML><U>Crea tus Tags</U></HTML>");
        linkTags.setBounds(50, 170, 100, 30);
        linkTags.setFont(new java.awt.Font("Verdana", Font.ITALIC, 14));
        linkTags.setForeground(Color.BLUE.darker());
        linkTags.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkTags.setVisible(false);
        add(linkTags);

        textUrl = new JTextField();
        textUrl.setBounds(120, 80, 250, 30);
        add(textUrl);

        textTittle = new JTextField();
        textTittle.setBounds(120, 130, 250, 30);
        add(textTittle);

        botonCrearVideo.addActionListener(this);
        botonSalir.addActionListener(this);

        /**
         * Añade el evento MouseListener a la JLabel linkTags, que es tratada como un link
         *
         * Al clickar el link, se muestra recursivamente un InputDialog para crear tags
         * que se van añadiendo al video recien creado por el usuario
         *
         */
        linkTags.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    showInputDialogAddTag(controller,controller.getVideo().getUrl(),controller.getVideo().getTittle());
                } catch (EntradaDeDatosEnBlancoException entradaDeDatosEnBlancoException) {
                    JOptionPane.showMessageDialog(null,
                            "Rellene los Datos Solicitados, por favor", "Campos Vacios No Permitidos",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        mostrarVideos();
        limpiar();

        setSize(480, 650);
        setTitle("CREA TUS VIDEOS");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Permite el llenado de un JTable con la info de los videos que va creando el usuario
     */
    public void mostrarVideos() {

        String titulos[] = {"URL", "Titulo", "Tags"};
        String informacion[][] = obtieneMarizVideos();// obtenemos la informacion de la matriz

        videosJTable = new JTable(informacion, titulos);
        videosJTable.setEnabled(true);
        videosJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollTabla.setViewportView(videosJTable);

        videosJTable.addMouseListener(new java.awt.event.MouseAdapter() {

          public void mouseClicked(java.awt.event.MouseEvent e) {

              videosJTable.getModel();
              String url = (String) videosJTable.getModel().getValueAt(videosJTable.getSelectedRow(), 0);
              String tittle = (String) videosJTable.getModel().getValueAt(videosJTable.getSelectedRow(), 1);
              try {
                  showInputDialogAddTag(Controller.getInstance(),url,tittle);
              } catch (EntradaDeDatosEnBlancoException entradaDeDatosEnBlancoException) {
                  JOptionPane.showMessageDialog(null,
                          "Rellene los Datos Solicitados, por favor", "Campos Vacios No Permitidos",
                          JOptionPane.WARNING_MESSAGE);
              }
          }
      });

}

    private void showInputDialogAddTag(Controller controller, String url, String tittle) throws EntradaDeDatosEnBlancoException {
        String tag = JOptionPane.showInputDialog(null, "Crea un Tag para tu Video", "CREA TUS TAGS", JOptionPane.QUESTION_MESSAGE);

        while (null != tag) {
            if (tag.trim().isEmpty()) {
                throw new EntradaDeDatosEnBlancoException("Campos Vacios No Permitidos");
            }
            controller.addTagVideo(tag,controller.getVideo(controller.getUser(),url,tittle));
            mostrarVideos();
            tag = JOptionPane.showInputDialog(null, "Crea un Tag Mas para tu Video", "CREA TUS TAGS", JOptionPane.QUESTION_MESSAGE);
        }
        linkTags.setVisible(false);
    }

    /**
     * Metodo que retorna una matriz con la info de los videos creados por el usuario
     * Esta matriz se genera a partir de un listado que almacena objetos/implementaciones
     * de tipo ComplexObjectInterfaz, en este caso, un listado de objetos tipo Video
     *
     * @return
     */
    private String[][] obtieneMarizVideos() {

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

    /**
     * Realiza la accion de crear un video o salir de la aplicacion, en funcion del evento de usuario capturado
     * <p>
     * La creacion de un video permite la creacion opcional de tags para ese video
     * <p>
     * Cada vez que se crea un video, se añade a un listado de videos que alimenta y actualiza
     * el componente JTable con la info de los videos creados
     * <p>
     * El formulario de creacion de un video no permite campos vacios
     *
     * @param e, evento generado por la accion click del usuario sobre el componente JButton
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCrearVideo) {
            try {
                if (textUrl.getText().trim().isEmpty() || textTittle.getText().trim().isEmpty()) {
                    throw new EntradaDeDatosEnBlancoException("Campos Vacios No Permitidos");
                }
                if (!controller.createVideo(textUrl.getText(), textTittle.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "El Video que Quiere Crear ya Existe", "Video Repetido",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    videoList.add(controller.getVideo());
                    controller.getNumberOfVideos();
                    linkTags.setVisible(true);
                }

            } catch (EntradaDeDatosEnBlancoException ex) {
                JOptionPane.showMessageDialog(null,
                        "Rellene los Datos Solicitados, por favor", "Campos Vacios No Permitidos",
                        JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Error en la Entrada de Datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                if (null != controller.getVideo()) {
                    /**
                     * Se actualiza la JTable que contiene la info de los videos,
                     * cada vez que el usuario crea un nuevo video
                     */
                    mostrarVideos();
                    limpiar();
                }
            }
        }
        if (e.getSource() == botonSalir) {
            controller.showInfoVideos();
            System.exit(0);
        }
    }


}
