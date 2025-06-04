// Archivo: src/main/java/view/Formulario1.java
package view;

import javax.swing.*;
import java.awt.*;

public class Formulario1 extends JPanel {

    // Definición de los colores de la paleta (para referencia, aunque el fondo ya lo tenemos)
    private final Color COLOR_BLANCO_CREMA = new Color(0xFFEEDB); // FFEEDB
    private final Color COLOR_MARRON_BRONCEADO = new Color(0xAA8F66); // AA8F66

    public Formulario1() {
        setLayout(new BorderLayout());
        setBackground(COLOR_BLANCO_CREMA); // Fondo del formulario

        JLabel label = new JLabel("¡Bienvenido al Formulario 1!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(COLOR_MARRON_BRONCEADO.darker()); // Texto con color de la paleta
        add(label, BorderLayout.CENTER);
    }
}