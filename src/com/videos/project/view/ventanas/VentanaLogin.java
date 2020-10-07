package com.videos.project.view.ventanas;

import com.videos.project.application.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame implements ActionListener {

	private JLabel labelTitulo;
	private JTextField textName , textSurname, textPassword;
	private JLabel name, surname, password;
	private JButton botonLogin, botonExit;
	private Controller controller;

	/**
	 * Constructor de la clase donde se inicializan todos los componentes de la
	 * ventana de login
	 */
	public VentanaLogin(Controller controller) {
		this.controller= controller;

		botonLogin = new JButton();
		botonLogin.setBounds(90, 250, 100, 30);
		botonLogin.setText("Login");

		botonExit = new JButton();
		botonExit.setBounds(200, 250, 100, 30);
		botonExit.setText("Salir");

		labelTitulo = new JLabel();
		labelTitulo.setText("LOGATE PARA ACCEDER A TUS VIDEOS");
		labelTitulo.setBounds(80, 20, 300, 30);

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
		botonExit.addActionListener(this);

		add(botonExit);
		add(botonLogin);
		add(labelTitulo);

		setSize(380, 380);
		setTitle("USER LOGIN");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == botonLogin) {
			try {
				controller.createUser(textName.getText(),textSurname.getText(),textPassword.getText());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						"Error en la Entrada de Datos", "Error",
						JOptionPane.ERROR_MESSAGE);
			} finally {
				this.controller.executeOperationShowInfo();
				this.setVisible(false);
				VentanaVideos videos=new VentanaVideos(controller,controller.getUser());
				videos.setVisible(true);
			}
		}
		if (e.getSource() == botonExit) {
			System.exit(0);
		}
	}

}
