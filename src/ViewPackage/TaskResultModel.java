package ViewPackage;

import ModelPackage.ResultTaskModel;
import ModelPackage.SearchModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TaskResultModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<ResultTaskModel> contents;

    public TaskResultModel(ArrayList<ResultTaskModel> results){
        columnNames = new ArrayList<>();
        columnNames.add("Year");
        columnNames.add("Total cash prize");
        setContents(results);
    }

    private void setContents(ArrayList<ResultTaskModel> results) {
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
        ResultTaskModel result = contents.get(row);
        switch (col){
            case 0 : return result.getYear();
            case 1 : return result.getTotalCashPrize();
            default : return null;
        }
    }
}
