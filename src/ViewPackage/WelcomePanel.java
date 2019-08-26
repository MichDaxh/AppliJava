package ViewPackage;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private JLabel text;

    public WelcomePanel(){
        super();
        text = new JLabel("Welcome in CSGO Events Manager", SwingConstants.CENTER);
        text.setFont((new Font(Font.SANS_SERIF, Font.PLAIN, 34)));
        this.setLayout(new FlowLayout());
        this.add(text);
    }
}
