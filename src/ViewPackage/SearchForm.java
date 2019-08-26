package ViewPackage;

import ExceptionPackage.AllPlayersException;
import ExceptionPackage.ConnectionException;
import ExceptionPackage.SearchException;
import ModelPackage.Player;
import ModelPackage.SearchModel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchForm extends JPanel {
    private MainWindow mainWindow;
    private JComboBox player;
    private JDateChooser date1;
    private JButton searchButton;
    private JLabel playerLabel, date1Label;
    private ArrayList<Player> players;
    private Integer[] ids;
    private int chosenPlayerId;
    private ArrayList<SearchModel> results;
    private JPanel parent;
    private ResultsModel model;

    public SearchForm(MainWindow mainWindow, JPanel parent) {
        this.mainWindow = mainWindow;
        this.parent = parent;
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        playerLabel = new JLabel("Player : ");
        date1Label = new JLabel("Date");
        date1 = new JDateChooser();
        searchButton =  new JButton("Search");
        String[] values = playerNames();
        if(values != null){
            player = new JComboBox(values);
            player.setSelectedIndex(-1);
            ComboBoxListener listener = new ComboBoxListener();
            player.addItemListener(listener);
            p.add(playerLabel);
            p.add(player);
        }
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(date1Label);
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(date1);
        p.add(Box.createRigidArea(new Dimension(10, 0)));
        p.add(searchButton);
        this.add(p, BorderLayout.CENTER);
        searchButton.addActionListener(new SearchListener());


    }

    private class ComboBoxListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            int i = player.getSelectedIndex();
            chosenPlayerId = ids[i];
        }
    }

    private String[] playerNames()  {
        try {
            players = mainWindow.getAppController().getAllPlayers();
            String[] output = new String[players.size()];
            ids = new Integer[players.size()];
            int i = 0;
            for (Player player : players) {
                output[i] = player.getFirstName() + " " + player.getLastName();
                ids[i] = player.getId();
                i++;
            }
            return output;
        }
        catch( ConnectionException | AllPlayersException e){
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            GregorianCalendar dateGreg = new GregorianCalendar();
            dateGreg.setTime(date1.getDate());
            try {
                results = mainWindow.getAppController().search(chosenPlayerId, dateGreg);
                model = new ResultsModel(results);
                JTable table = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(table);
                parent.add(scrollPane, BorderLayout.CENTER);
                parent.revalidate();
                parent.repaint();
            } catch (ConnectionException |SearchException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
