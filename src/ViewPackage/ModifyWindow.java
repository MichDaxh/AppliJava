package ViewPackage;

import javax.swing.*;
import java.awt.*;

public class ModifyWindow extends JDialog {
    private EventForm form ;
    private int idEventToMod;

    public ModifyWindow(MainWindow mainWindow) {
        super(mainWindow, "Modify");
        this.form = new EventForm(mainWindow, 1, this);
        this.setLayout(new BorderLayout());
        this.add(form, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public void setIdEventToMod(int idEventToMod) {
        this.idEventToMod = idEventToMod;
    }

    public int getIdEventToMod() {
        return idEventToMod;
    }

    public EventForm getForm() {
        return form;
    }
}
