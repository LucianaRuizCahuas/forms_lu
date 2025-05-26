import javax.swing.SwingUtilities;
import view.Forms;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Forms ventana = new Forms();
            ventana.setVisible(true);
        });
    }
}
