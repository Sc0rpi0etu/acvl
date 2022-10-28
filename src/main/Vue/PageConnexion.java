package Vue;

import javax.swing.*;
import java.awt.*;

public class PageConnexion {
    private JFrame frame;
    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public PageConnexion(JFrame frame, JPanel mainPanel) {
        this.frame = frame;
        setMainPanel(mainPanel);
    }
    public void connecter() {
        afficherPage();
    }

    private void afficherPage() {
        setMainPanel(new JPanel());
        mainPanel.setLayout(new GridLayout(0, 3));
        JButton btn = new JButton("hello");
        btn.setPreferredSize(new Dimension(40, 40));

        JPanel connectPanel = new JPanel();
        connectPanel.setLayout(new GridLayout(0, 2));
        connectPanel.add(new JLabel("login : "));
        connectPanel.add(new JTextField("test"));
        connectPanel.add(new JLabel("mot de passe : "));
        connectPanel.add(new JLabel("f"));
        connectPanel.add(new JButton("connexion"));

        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(connectPanel);
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
    }
}
