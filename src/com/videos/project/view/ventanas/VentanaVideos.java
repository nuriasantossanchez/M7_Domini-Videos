package com.videos.project.view.ventanas;

import com.videos.project.application.Controller;
import com.videos.project.domain.Video;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


/**
 * Clase de la capa View
 * <p>
 * Muestra un formulario para la creacion de videos asociados a un usuario
 * El formulario no permite campos vacios, y lanza una excepcion de tipo
 * EntradaDeDatosEnBlancoException
 *
 * Muestra la info de cada nuevo video en un componente JTable
 *
 * Permite añadir tags a un video, tanto en el momento de la creacion
 * como posteriormente
 *
 */
public class VentanaVideos extends JFrame implements ActionListener {

    private JLabel labelTitulo, labelTabla;
    private JTextField textUrl, textTittle;
    private JLabel linkTags;
    private JLabel url, tittle;
    private JButton botonCrearVideo, botonSalir, botonCambiarDeUsuario;
    JTable videosJTable;
    JScrollPane scrollTabla;
    private Controller controller;


    /**
     * Constructor de la clase donde se inicializan todos los componentes de la
     * ventana de videos
     */
    public VentanaVideos(Controller controller) {
        this.controller = controller;

        botonCrearVideo = new JButton();
        botonCrearVideo.setBounds(40, 220, 140, 30);
        botonCrearVideo.setText("Crear Video");
        add(botonCrearVideo);

        botonCambiarDeUsuario = new JButton();
        botonCambiarDeUsuario.setBounds(190, 220, 150, 30);
        botonCambiarDeUsuario.setText("Cambiar de Usuario");
        add(botonCambiarDeUsuario);

        botonSalir = new JButton();
        botonSalir.setBounds(350, 220, 100, 30);
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
        botonCambiarDeUsuario.addActionListener(this);
        botonSalir.addActionListener(this);

        /**
         * Añade el evento MouseListener a la JLabel linkTags
         * Esta JLabel es tratada como un link o boton
         *
         */
        addMouseListenerLinkTags();

        /**
         * Muestra en un JTable los videos que va creando el usuario
         */
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
     * Limpia el formulario de creacion de videos
     */
    private void limpiar() {
        textUrl.setText("");
        textTittle.setText("");
    }

    /**
     * Permite el llenado de un JTable con la info de los videos que va creando el usuario
     *
     * Captura el evento click del JTable para permitir añadir etiquetas/tags a cualquiera
     * de los videos ya creados
     */
    public void mostrarVideos() {

        String titulos[] = {"URL", "Titulo", "Tags"};
        String informacion[][] = obtieneMarizVideos();

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
                  inputDialogAddTag(controller,url,tittle);
              } catch (EntradaDeDatosEnBlancoException entradaDeDatosEnBlancoException) {
                  JOptionPane.showMessageDialog(null,
                          "Rellene los Datos Solicitados, por favor", "Campos Vacios No Permitidos",
                          JOptionPane.WARNING_MESSAGE);
              }
          }
        });
    }

    /**
     * Retorna una matriz de 2 dimensiones con la info de los videos creados por el usuario
     * Esta matriz alimenta los datos del componente JTable
     *
     * La primera dimension(x) varia en funcion del numero de videos que va creando el usuario
     * La segunda dimension(y) es fija y representa la cabecera del JTable, con la info que
     * se muestra de cada video (url, titulo y tags)
     *
     * @return
     */
    private String[][] obtieneMarizVideos() {
        List<Video> videoList=controller.getUser().getVideos();

        String informacion[][] = new String[videoList.size()][3];

        for (int x = 0; x < informacion.length; x++) {
            informacion[x][0] = videoList.get(x).getUrl();
            informacion[x][1] = videoList.get(x).getTittle();
            informacion[x][2] = videoList.get(x).getTags().toString();
        }
        return informacion;
    }

    /**
     * Realiza la accion de crear un video, cambiar de usuario o salir de la aplicacion,
     * en funcion del evento de usuario capturado
     * <p>
     * La creacion de un video permite la creacion opcional de tags para ese video
     * El formulario de creacion de un video no permite campos vacios, y lanza una
     * excepcion de tipo EntradaDeDatosEnBlancoException
     * <p>
     * Cada vez que se crea un video, se actualiza el componente JTable que muestra
     * la info de los videos creados
     *
     * El boton 'Cambiar de Usuario', muestra la ventana de login y permite el acceso
     * de usuarios distintos. Cada usuario que hece login tiene acceso solo a sus videos
     *
     * El boton 'Salir', sale de la aplicacion y muestra por consola los videos del usuario logado
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

                if(!controller.matchUrl(textUrl.getText().trim())){
                    JOptionPane.showMessageDialog(null,
                            "Introduzca una URL con un Formato Correcto, por favor\n\n" +
                                    "https://www.ejemplo.com\n" +
                                    "http://www.ejemplo.com\n" +
                                    "www.ejemplo.com\n", "Formato de URL No Permitido",
                            JOptionPane.WARNING_MESSAGE);
                    limpiar();
                } else if (!controller.createVideo(textUrl.getText(), textTittle.getText())) {
                    JOptionPane.showMessageDialog(null,
                            "El Video que Quiere Crear ya Existe", "Video Repetido",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    controller.getNumberOfUserVideos(controller.getUser());
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
                     * Se actualiza el JTable que contiene la info de los videos,
                     * cada vez que el usuario crea un nuevo video
                     */
                    mostrarVideos();
                    limpiar();
                }
            }
        }
        else if (e.getSource() == botonCambiarDeUsuario) {
            this.setVisible(false);
            VentanaLogin ventanaLogin = VentanaLogin.getInstance(controller);
            ventanaLogin.setVisible(true);
        }
        else if (e.getSource() == botonSalir) {
            controller.listarVideos();
            System.exit(0);
        }
    }

    /**
     * Añade el evento MouseListener a la JLabel linkTags, que es tratada como un link o boton
     *
     * Cada vez que se hace click en la JLabel el evento mouseClicked es capturado
     * y se hace una llamada al metodo inputDialogAddTag().
     *
     * El metodo inputDialogAddTag() muestra recursivamente un InputDialog
     * para la entrada de datos de un String, que representa una etiqueta o tag
     * que es añadida a un video concreto
     *
     */
    private void addMouseListenerLinkTags() {
        linkTags.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    inputDialogAddTag(controller, controller.getVideo().getUrl(), controller.getVideo().getTittle());
                } catch (EntradaDeDatosEnBlancoException entradaDeDatosEnBlancoException) {
                    JOptionPane.showMessageDialog(null,
                            "Rellene los Datos Solicitados, por favor", "Campos Vacios No Permitidos",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     * Muestra recursivamente un InputDialog para la entrada de datos de un String,
     * utilizado para la creacion de etiquetas o tags de un video concreto
     *
     * La entrada de datos en blanco no es permtida, y lanza una excepcion de tipo
     * EntradaDeDatosEnBlancoException
     *
     * Este metodo se llama al hacer click en la etiqueta 'Crea tus Tags' que aparece
     * cada vez que un usuario crea un nuevo video y al hacer click en cualquier celda
     * del componente JTable que muestra los videos creados por el usuario, es decir,
     * permite añadir etiquetas a un video en el momento de su creacion y/o posteriormente
     *
     */
    private void inputDialogAddTag(Controller controller, String url, String tittle) throws EntradaDeDatosEnBlancoException {
        String tag = JOptionPane.showInputDialog(null, "Crea un Tag para tu Video", "CREA TUS TAGS", JOptionPane.QUESTION_MESSAGE);

        while (null != tag) {
            if (tag.trim().isEmpty()) {
                throw new EntradaDeDatosEnBlancoException("Campos Vacios No Permitidos");
            }
            controller.addTagVideo(tag,controller.getVideo(controller.getUser(),url,tittle));
            mostrarVideos();
            tag = JOptionPane.showInputDialog(null, "Crea un Tag mas para tu Video", "CREA TUS TAGS", JOptionPane.QUESTION_MESSAGE);
        }

        linkTags.setVisible(false);
    }

}
