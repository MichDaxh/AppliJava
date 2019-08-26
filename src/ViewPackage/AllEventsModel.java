package ViewPackage;

import ExceptionPackage.AllEventsException;
import ModelPackage.Event;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class AllEventsModel extends AbstractTableModel {

    private ArrayList<String> columnNames;
    private ArrayList<Event> contents;

    public AllEventsModel(ArrayList<Event> events){
        columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Event");
        columnNames.add("Number of teams");
        columnNames.add("Cashprize");
        columnNames.add("City");
        columnNames.add(("Starting date"));
        columnNames.add("Ending date");
        columnNames.add(("Major"));
        columnNames.add("Sponsor1");
        columnNames.add("Sponsor2");
        columnNames.add("Sponsor3");
        columnNames.add("Sponsor4");
        columnNames.add("Sponsor5");
        columnNames.add(("Caster1"));
        columnNames.add(("Caster2"));
        columnNames.add(("Caster3"));
        columnNames.add(("Caster4"));
        columnNames.add(("Caster5"));
        setContents(events);
    }

    public void setContents(ArrayList<Event> events){
        contents = events;
    }
    @Override
    public int getRowCount() {
        return contents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int row, int col) {
        Event event = contents.get(row);
        switch (col){
            case 0 : return event.getId();
            case 1 : return event.getName();
            case 2 : return event.getNbTeams();
            case 3 : return event.getCashPrize();
            case 4 : return event.getLocality();
            case 5 : return event.getStartDate().getTime();
            case 6 : return event.getEndDate().getTime();
            case 7 : return event.isMajor();
            case 8 : if(event.getSponsor()[0] !=null) return event.getSponsor()[0];
                     else return null;
            case 9 : if(event.getSponsor()[1] !=null) return event.getSponsor()[1];
            else return null;
            case 10 : if(event.getSponsor()[2] !=null) return event.getSponsor()[2];
            else return null;
            case 11 : if(event.getSponsor()[3] !=null) return event.getSponsor()[3];
            else return null;
            case 12 : if(event.getSponsor()[4] !=null) return event.getSponsor()[4];
            else return null;
            case 13 : if(event.getCaster()[0] != null) return event.getCaster()[0];
                     else return null;
            case 14 : if(event.getCaster()[1] != null) return event.getCaster()[1];
            else return null;
            case 15 : if(event.getCaster()[2] != null) return event.getCaster()[2];
            else return null;
            case 16 : if(event.getCaster()[3] != null) return event.getCaster()[3];
            else return null;
            case 17 : if(event.getCaster()[4] != null) return event.getCaster()[4];
            else return null;
            default : return null;
        }
    }
//
    @Override
    public Class getColumnClass(int col){
        Class c;
        switch(col){
            case 0 :
            case 2 :
            case 3 :
                c = Integer.class;
                break;
            case 5 :
            case 6 :
                c = Date.class;
                break;
            case 7 : c =  Boolean.class;
                break;
            default: c = String.class;
        }
        return c;
    }

}
