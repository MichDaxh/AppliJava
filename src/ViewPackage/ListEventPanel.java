package ViewPackage;

import ExceptionPackage.*;
import ModelPackage.Event;
import controllerPackage.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class ListEventPanel extends JPanel {
    private ApplicationController appController;
    private ArrayList<Event> allEvents;
    private JPopupMenu popupMenu;

    ListSelectionModel listSelect;
    int selectLineIndex;

    public ListEventPanel(MainWindow mainWindow){
        appController = mainWindow.getAppController();
        try {
            allEvents = appController.getAllEvents();
            AllEventsModel model = new AllEventsModel(allEvents);
            JTable table = new JTable(model);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = table.getSelectionModel();
            JScrollPane scrollPane = new JScrollPane(table);
            this.setLayout(new BorderLayout());
            this.add(scrollPane, BorderLayout.CENTER);


            //popup management
            popupMenu = new JPopupMenu();
            JMenuItem modifyMenu= new JMenuItem("Modify");
            popupMenu.add(modifyMenu);
            popupMenu.addSeparator();
            JMenuItem removeMenu = new JMenuItem("Remove");
            popupMenu.add(removeMenu);
            MouseEventListener listener = new MouseEventListener();
            table.addMouseListener(listener);
            modifyMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ModifyWindow modifyWindow = new ModifyWindow(mainWindow);
                    modifyWindow.setLocationRelativeTo(mainWindow);
                    modifyWindow.setPreferredSize(new Dimension(mainWindow.getWidth()/2, mainWindow.getHeight()/2));
                    modifyWindow.setLocation(mainWindow.getWidth()/3, mainWindow.getHeight()/3);
                    copyData(modifyWindow, table);
                }
            });
            removeMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        mainWindow.getAppController().deleteEvent((int)table.getValueAt(selectLineIndex, 0));
                    } catch (ConnectionException | DeleteException e) {
                        JOptionPane.showMessageDialog(mainWindow, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
        catch(AllEventsException | ConnectionException | CashPrizeException | EndDateException | NbTeamsException | IdException e){
            JOptionPane.showMessageDialog(mainWindow, e, e.toString(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void copyData(ModifyWindow modifyWindow, JTable table){
        modifyWindow.getForm().setTextEventName(table.getValueAt(selectLineIndex, 1).toString());
        modifyWindow.getForm().setNbTeams((int)table.getValueAt(selectLineIndex, 2));
        modifyWindow.getForm().setCashPrize((int)table.getValueAt(selectLineIndex, 3));
        modifyWindow.getForm().setTextLocality(table.getValueAt(selectLineIndex, 4).toString());
        modifyWindow.getForm().setCalStartDate((Date)table.getValueAt(selectLineIndex, 5));
        modifyWindow.getForm().setCalEndDate((Date)table.getValueAt(selectLineIndex, 6));
        modifyWindow.getForm().setIsMajor((Boolean)table.getValueAt(selectLineIndex, 7));
        String[] sponsors = new String[5];
        String[] casters = new String[5];
        for(int i = 0; i<5 && table.getValueAt(selectLineIndex, (8+i)) != null ;i++){
            sponsors[i] = table.getValueAt(selectLineIndex, (8+i)).toString();
        }
        for(int i = 0; i<5 && table.getValueAt(selectLineIndex, (13+i)) != null ;i++){
            casters[i] = table.getValueAt(selectLineIndex, (13 +i)).toString();
        }
        modifyWindow.getForm().getSponsor().setFields(sponsors);
        modifyWindow.getForm().getCaster().setFields(casters);
        modifyWindow.setIdEventToMod((int)table.getValueAt(selectLineIndex, 0));


    }
    private class MouseEventListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            selectLineIndex = listSelect.getMinSelectionIndex( );
            if(mouseEvent.getButton() == mouseEvent.BUTTON3 && selectLineIndex >= 0){
                popupMenu.show(ListEventPanel.this, mouseEvent.getX(), mouseEvent.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }
    }
}
