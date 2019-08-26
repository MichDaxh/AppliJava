package ViewPackage;

import ModelPackage.SearchModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SearchPanel extends JPanel {
    private SearchForm searchForm;

    public SearchPanel(MainWindow mainWindow){
        this.setLayout(new BorderLayout());
        searchForm = new SearchForm(mainWindow, this);
        this.add(searchForm, BorderLayout.NORTH);

//
    }
}
