package Vue;
import javax.swing.*;
import java.awt.*;

public class Main {
    private static JPanel mainPanel;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("PREHOSPITAL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500,800);
        frame.setVisible(true);

        mainPanel(frame);

        PageConnexion pc = new PageConnexion(frame, mainPanel);
        pc.connecter();
        frame.add(pc.getMainPanel(), BorderLayout.CENTER);
    }

    private static void mainPanel(JFrame frame) {
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mainPanel.setLayout(new GridLayout(0, 3));
    }
}
