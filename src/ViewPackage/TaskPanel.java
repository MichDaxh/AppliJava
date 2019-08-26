package ViewPackage;

import ExceptionPackage.ConnectionException;
import ExceptionPackage.TaskException;
import ModelPackage.ResultTaskModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaskPanel extends JPanel {
    private ArrayList<ResultTaskModel> results;
    private TaskResultModel model;

    public TaskPanel(MainWindow mainWindow){
        try {
            results = mainWindow.getAppController().startTask();
            setLayout(new BorderLayout());
            model = new TaskResultModel(results);
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            this.add(scrollPane, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        } catch (ConnectionException | TaskException e) {
            JOptionPane.showMessageDialog(mainWindow, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
