package view;// Archivo: src/main/java/LoginFrame.java
// NO hay declaración de paquete aquí (paquete por defecto).

import controller.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    private final String USUARIO_CORRECTO = "admin";
    private final String CONTRASENA_CORRECTA = "12345";

    // Definición de los colores de la paleta
    private final Color COLOR_MARRON_BRONCEADO = new Color(0xAA8F66); // AA8F66
    private final Color COLOR_NARANJA_DORADO = new Color(0xED9840); // ED9840
    private final Color COLOR_BLANCO_CREMA = new Color(0xFFEEDB); // FFEEDB

    public LoginFrame() {
        setTitle("Inicio de Sesión");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Usamos un JPanel para organizar los componentes del login
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_BLANCO_CREMA); // Fondo del panel de login
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Componentes de Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setForeground(COLOR_MARRON_BRONCEADO.darker()); // Texto más oscuro para contraste
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        userField = new JTextField(15);
        userField.setBackground(Color.WHITE); // Fondo blanco para campos de texto
        userField.setBorder(BorderFactory.createLineBorder(COLOR_MARRON_BRONCEADO.brighter()));
        panel.add(userField, gbc);

        // Componentes de Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setForeground(COLOR_MARRON_BRONCEADO.darker());
        panel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        passField = new JPasswordField(15);
        passField.setBackground(Color.WHITE);
        passField.setBorder(BorderFactory.createLineBorder(COLOR_MARRON_BRONCEADO.brighter()));
        panel.add(passField, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_BLANCO_CREMA); // Fondo del panel de botones

        JButton loginButton = new JButton("Ingresar");
        loginButton.setBackground(COLOR_NARANJA_DORADO); // Fondo naranja para el botón
        loginButton.setForeground(Color.WHITE); // Texto blanco
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(COLOR_MARRON_BRONCEADO); // Fondo marrón para el botón
        cancelButton.setForeground(Color.WHITE); // Texto blanco
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.equals(USUARIO_CORRECTO) && password.equals(CONTRASENA_CORRECTA)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "¡Inicio de sesión exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MenuPrincipal().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Usuario o contraseña incorrectos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                    userField.setText("");
                    passField.setText("");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}