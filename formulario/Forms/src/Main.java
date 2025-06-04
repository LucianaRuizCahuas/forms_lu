
import view.LoginFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Ahora, inicia la ventana de Login
                new LoginFrame().setVisible(true);
            }
        });
    }
}