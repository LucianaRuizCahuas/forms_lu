// Archivo: src/main/java/view/Formulario2.java
package view;

import javax.swing.*;
import java.awt.*;

public class Formulario2 extends JPanel {

    // Definición de los colores de la paleta (para referencia)
    private final Color COLOR_BLANCO_CREMA = new Color(0xFFEEDB); // FFEEDB
    private final Color COLOR_NARANJA_DORADO = new Color(0xED9840); // ED9840

    public Formulario2() {
        setLayout(new BorderLayout());
        setBackground(COLOR_BLANCO_CREMA); // Fondo del formulario

        JLabel label = new JLabel("¡Hola desde el Formulario 2!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(COLOR_NARANJA_DORADO.darker()); // Texto con color de la paleta
        add(label, BorderLayout.CENTER);
    }
}