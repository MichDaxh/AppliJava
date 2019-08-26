package ViewPackage;

import javax.swing.*;
import java.awt.*;

public class AddEventPanel extends JPanel {
    private EventForm eventForm;

    public AddEventPanel(MainWindow mainWindow){
        super();
        this.setLayout(new BorderLayout());
        eventForm = new EventForm(mainWindow, 0, this);
        this.add(eventForm, BorderLayout.CENTER);
    }
}
