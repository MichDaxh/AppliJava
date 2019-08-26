package ViewPackage;

import javax.swing.*;
import java.awt.*;

public class ThreadPanel extends JPanel {
    private JLabel text;
    private ThreadMessage threadMessage;

    public ThreadPanel() {
        text = new JLabel();
        add(text);
        threadMessage = new ThreadMessage(this);
        threadMessage.start();
    }

    public JLabel getText() {
        return text;
    }
}
