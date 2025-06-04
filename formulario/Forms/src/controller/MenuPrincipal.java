package controller;// Archivo: src/main/java/MenuPrincipal.java
// NO hay declaración de paquete aquí (paquete por defecto).

import view.Formulario1;
import view.Formulario2;
import view.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.net.URL; // Necesario para cargar la imagen como recurso

public class MenuPrincipal extends JFrame {

    private JPanel contentPanel;
    private JPanel sidebarPanel;
    private boolean sidebarVisible = true; // Estado inicial del sidebar

    // Definición de los colores de la paleta
    private final Color COLOR_MARRON_BRONCEADO = new Color(0xAA8F66); // AA8F66
    private final Color COLOR_NARANJA_DORADO = new Color(0xED9840); // ED9840
    private final Color COLOR_BLANCO_CREMA = new Color(0xFFEEDB); // FFEEDB

    public MenuPrincipal() {
        setTitle("Aplicación con Menú Principal");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); //

        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BLANCO_CREMA); // Aplicar color de fondo al JFrame

        // --- Panel Superior para el Botón de Hamburguesa y Título ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COLOR_MARRON_BRONCEADO); //
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_MARRON_BRONCEADO.darker())); // Borde inferior

        JButton hamburgerButton = new JButton(); // Crea el botón sin texto inicialmente
        try {
            // **¡Ruta Corregida para tu imagen menu.png!**
            // Busca directamente menu.png en la raíz del classpath (que incluye src/main/java/ si no hay declaración de paquete)
            URL imageUrl = getClass().getResource("/menu.png");

            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                // Escalar la imagen a un tamaño adecuado (ej. 32x32 píxeles)
                Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                hamburgerButton.setIcon(new ImageIcon(scaledImage));
                hamburgerButton.setMargin(new Insets(5, 10, 5, 10)); // Padding para el botón con icono
            } else {
                // Fallback si la imagen no se encuentra
                System.err.println("Advertencia: No se pudo cargar la imagen del icono de hamburguesa. URL nula: /menu.png");
                hamburgerButton.setText("☰"); //
                hamburgerButton.setFont(new Font("Arial", Font.BOLD, 24));
                hamburgerButton.setMargin(new Insets(5, 10, 5, 10));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar la imagen del icono de hamburguesa: " + e.getMessage());
            hamburgerButton.setText("☰"); // Fallback en caso de otra excepción
            hamburgerButton.setFont(new Font("Arial", Font.BOLD, 24));
            hamburgerButton.setMargin(new Insets(5, 10, 5, 10));
        }

        hamburgerButton.setForeground(Color.WHITE);
        hamburgerButton.setBackground(COLOR_MARRON_BRONCEADO); //
        hamburgerButton.setBorderPainted(false);
        hamburgerButton.setFocusPainted(false);

        hamburgerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSidebar(); //
            }
        });
        headerPanel.add(hamburgerButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel(getTitle(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // --- Crear el panel lateral del menú ---
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridBagLayout());
        sidebarPanel.setBackground(COLOR_MARRON_BRONCEADO); // Fondo del sidebar (marrón/bronceado)
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        sidebarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, COLOR_MARRON_BRONCEADO.darker())); // Borde más oscuro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1.0;

        // Botón Formulario 1
        JButton btnFormulario1 = createSidebarButton("Formulario 1");
        btnFormulario1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(new Formulario1()); //
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        sidebarPanel.add(btnFormulario1, gbc);

        // Botón Formulario 2
        JButton btnFormulario2 = createSidebarButton("Formulario 2");
        btnFormulario2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel(new Formulario2()); //
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        sidebarPanel.add(btnFormulario2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        sidebarPanel.add(Box.createVerticalGlue(), gbc); // Espacio flexible para empujar el botón de Cerrar Sesión abajo

        // Botón Cerrar Sesión
        JButton btnCerrarSesion = createSidebarButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(COLOR_NARANJA_DORADO); // Establecer un color diferente para el botón de cerrar sesión
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSesion.setBackground(COLOR_NARANJA_DORADO.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesion.setBackground(COLOR_NARANJA_DORADO);
            }
        });
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(MenuPrincipal.this,
                        "¿Estás seguro de que quieres cerrar sesión?",
                        "Cerrar Sesión",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose(); // Cierra la ventana principal
                    new LoginFrame().setVisible(true); // Abre la ventana de Login nuevamente
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(10, 0, 20, 0);
        sidebarPanel.add(btnCerrarSesion, gbc);

        add(sidebarPanel, BorderLayout.WEST);

        // --- Crear el panel de contenido principal (a la derecha del sidebar) ---
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(COLOR_BLANCO_CREMA); // Fondo del panel de contenido
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel initialLabel = new JLabel("Bienvenido. Selecciona una opción del menú lateral.");
        initialLabel.setHorizontalAlignment(SwingConstants.CENTER);
        initialLabel.setForeground(COLOR_MARRON_BRONCEADO.darker()); // Color de texto
        initialLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPanel.add(initialLabel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        // Ocultar el sidebar al inicio
        sidebarVisible = false;
        sidebarPanel.setVisible(sidebarVisible);
        SwingUtilities.invokeLater(() -> {
            getContentPane().revalidate();
            getContentPane().repaint();
        });

        setVisible(true);
    }

    /**
     * Alterna la visibilidad del panel lateral.
     */
    private void toggleSidebar() {
        sidebarVisible = !sidebarVisible;
        sidebarPanel.setVisible(sidebarVisible);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    /**
     * Método auxiliar para crear botones estilizados para el sidebar.
     * Utiliza los colores de la paleta.
     */
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(COLOR_MARRON_BRONCEADO); // Fondo del botón
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(10, 20, 10, 20));

        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(COLOR_MARRON_BRONCEADO.brighter()); // Tono más claro al pasar el ratón
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(COLOR_MARRON_BRONCEADO); //
            }
        });
        return button;
    }

    /**
     * Método para mostrar un JPanel dentro del contentPanel principal.
     */
    private void showPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}