package com.videos.project.view.ventanas;

import com.videos.project.application.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase de la capa View
 * Implementa el patron Singleton
 *
 * Muestra un formulario de Login que permite la creacion de un usuario
 * El formulario no permite campos vacios, y lanza una excepcion de tipo
 * EntradaDeDatosEnBlancoException
 *
 */
public class VentanaLogin extends JFrame implements ActionListener {

	private JLabel labelTitulo;
	private JTextField textName , textSurname, textPassword;
	private JLabel name, surname, password;
	private JButton botonLogin;
	private Controller controller;
	private static VentanaLogin instance;

	/**
	 * Constructor privado de la clase donde se inicializan todos los componentes
	 * de la ventana de login
	 */
	private VentanaLogin(Controller controller) {
		this.controller= controller;

		botonLogin = new JButton();
		botonLogin.setBounds(120, 250, 100, 30);
		botonLogin.setText("Login");
		add(botonLogin);


		labelTitulo = new JLabel();
		labelTitulo.setText("LOGATE PARA ACCEDER A TUS VIDEOS");
		labelTitulo.setBounds(80, 20, 300, 30);
		add(labelTitulo);

		name = new JLabel();
		name.setText("Nombre");
		name.setBounds(50, 80, 100, 30);
		add(name);

		surname = new JLabel();
		surname.setText("Apellido");
		surname.setBounds(50, 130, 100, 30);
		add(surname);

		password = new JLabel();
		password.setText("Password");
		password.setBounds(50, 180, 100, 30);
		add(password);

		textName = new JTextField();
		textName.setBounds(120, 80, 160, 30);
		add(textName);

		textSurname = new JTextField();
		textSurname.setBounds(120, 130, 160, 30);
		add(textSurname);

		textPassword = new JTextField();
		textPassword.setBounds(120, 180, 160, 30);
		add(textPassword);

		botonLogin.addActionListener(this);

		setSize(380, 380);
		setTitle("USER LOGIN");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Metodo que representa el punto global de acceso a la instancia unica de la clase VentanaLogin
	 *
	 * @param controller, objeto de tipo Controller
	 * @return instacia unica de la clase VentanaLogin
	 */
	public static VentanaLogin getInstance(Controller controller){
		if (instance==null){
			instance=new VentanaLogin(controller);
		}
		return instance;

	}

	/**
	 * Limpia el formulario de login de usuario
	 */
	private void limpiar() {
		textName.setText("");
		textSurname.setText("");
		textPassword.setText("");
	}

	//Todo
	/**
	 * Realiza la accion de crear un usuario
	 *
	 * El formulario de creacion de un usuario no permite campos vacios
	 *
	 * Una vez creado un usuario se muestra por consola la info del usuario, se oculta
	 * la ventana con el formulario de Login y se muestra la ventana de creacion
	 * de Videos para ese usuario
	 *
	 * @param e, evento generado por la accion click del usuario sobre el componente JButton
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botonLogin) {
			try {
				if (textName.getText().trim().isEmpty() || textSurname.getText().trim().isEmpty()
						|| textPassword.getText().trim().isEmpty()) {
					throw new EntradaDeDatosEnBlancoException("Campos Vacios No Permitidos");
				}
				controller.createUser(textName.getText(),textSurname.getText(),textPassword.getText());
			}catch (EntradaDeDatosEnBlancoException ex) {
				JOptionPane.showMessageDialog(null,
						"Rellene los Datos Solicitados, por favor", "Campos Vacios No Permitidos",
						JOptionPane.WARNING_MESSAGE);
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Error en la Entrada de Datos", "Error",
						JOptionPane.ERROR_MESSAGE);
			} finally {
				if(null!=controller.getUser()) {
					limpiar();
					this.setVisible(false);
					VentanaVideos videos = new VentanaVideos(controller);
					videos.setVisible(true);
				}
			}
		}
	}

}
