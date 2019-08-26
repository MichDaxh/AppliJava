package ViewPackage;

import ModelPackage.SearchModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ResultsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<SearchModel> contents;

    public ResultsModel(ArrayList<SearchModel> results){
        columnNames = new ArrayList<>();
        columnNames.add("Team Name");
        columnNames.add("Event Name");
        columnNames.add("Starting date");
        columnNames.add("Ending date");
        setContents(results);
    }

    private void setContents(ArrayList<SearchModel> results) {
        contents = results;
    }


    @Override
    public int getRowCount() {
        return contents.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        SearchModel result = contents.get(row);
        switch (col){
            case 0 : return result.getTeamName();
            case 1 : return result.getEventName();
            case 2 : return result.getEventStartDate();
            case 3 : return result.getEventEndDate();
            default : return null;
        }
    }
}
