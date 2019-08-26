package ViewPackage;

import ExceptionPackage.*;
import ModelPackage.Event;
import com.mysql.cj.util.StringUtils;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class EventForm extends JPanel {
    private JLabel labelEventName, labelTeam, labelCashPrize, labelLocality, labelStartDate, labelEndDate, labelMajor, labelCasters, labelSponsors;
    private JSpinner nbTeams, cashPrize;
    private JTextField textEventName, textLocality;
    private JCheckBox isMajor;
    private JDateChooser calStartDate, calEndDate;
    private JPanel p;
    private MultiTextFieldPanel caster, sponsor;
    private JButton backButton, okButton;
    private MainWindow mainWindow;
    //0 to add, 1 to update
    private int use;
    private Component parent;

    public EventForm(MainWindow mainWindow, int use, Component parent){
        p = new JPanel();
        this.parent = parent;
        this.use = use;
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.mainWindow = mainWindow;

        //Labels
        //<editor-fold desc="Labels, Textfields, etc">
        labelEventName = new JLabel("Event name :");
        labelEventName.setHorizontalAlignment(SwingConstants.RIGHT);

        labelTeam = new JLabel(("Numbers of teams attending :"));
        labelTeam.setHorizontalAlignment(SwingConstants.RIGHT);

        labelCashPrize = new JLabel("Cash prize :");
        labelCashPrize.setHorizontalAlignment(SwingConstants.RIGHT);

        labelLocality = new JLabel("City :");
        labelLocality.setHorizontalAlignment(SwingConstants.RIGHT);

        labelStartDate = new JLabel("Starting date :");
        labelStartDate.setHorizontalAlignment(SwingConstants.RIGHT);

        labelEndDate = new JLabel("Ending date :");
        labelEndDate.setHorizontalAlignment(SwingConstants.RIGHT);

        labelMajor = new JLabel("Major event :");
        labelMajor.setHorizontalAlignment(SwingConstants.RIGHT);

        labelCasters = new JLabel("Caster's name :");
        labelCasters.setHorizontalAlignment(SwingConstants.RIGHT);

        labelSponsors = new JLabel("Sponsors :");
        labelSponsors.setHorizontalAlignment(SwingConstants.RIGHT);

        //Textfields
        textEventName = new JTextField();
        textLocality = new JTextField();

        //Checkboxs
        isMajor = new JCheckBox();

        //DateChooser
        calStartDate = new JDateChooser();
        calEndDate = new JDateChooser();

        //Spinner

        nbTeams = new JSpinner(new SpinnerNumberModel(16, 2, 32, 1));
        cashPrize =  new JSpinner(new SpinnerNumberModel(10000, 1, Integer.MAX_VALUE, 500));

        //caster and sponsor panel
        caster = new MultiTextFieldPanel();
        sponsor = new MultiTextFieldPanel();

        //buttons
        backButton =  new JButton("Cancel");
        okButton = new JButton("OK");
        backButton.addActionListener(new BackListener());
        okButton.addActionListener(new OkListener());

        //add
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        p.add(labelEventName,c);
        c.gridx = 1;
        c.gridy = 0;
        p.add(textEventName,c);
        c.gridx = 0;
        c.gridy = 1;
        p.add(labelTeam,c);
        c.gridx = 1;
        c.gridy = 1;
        p.add(nbTeams,c);
        c.gridx = 0;
        c.gridy = 2;
        p.add(labelCashPrize,c);
        c.gridx = 1;
        c.gridy = 2;
        p.add(cashPrize,c);
        c.gridx = 0;
        c.gridy = 3;
        p.add(labelStartDate,c);
        c.gridx = 1;
        c.gridy = 3;
        p.add(calStartDate,c);
        c.gridx = 0;
        c.gridy = 4;
        p.add(labelEndDate,c);
        c.gridx = 1;
        c.gridy = 4;
        p.add(calEndDate,c);
        c.gridx = 0;
        c.gridy = 5;
        p.add(labelLocality,c);
        c.gridx = 1;
        c.gridy = 5;
        p.add(textLocality,c);
        c.gridx = 0;
        c.gridy = 6;
        p.add(labelMajor,c);
        c.gridx = 1;
        c.gridy = 6;
        p.add(isMajor,c);
        c.gridx = 0;
        c.gridy = 7;
        c.gridheight = 5;
        p.add(labelCasters,c);
        c.gridx = 1;
        c.gridy = 7;
        p.add(caster,c);
        c.gridx = 0;
        c.gridy = 12;
        p.add(labelSponsors,c);
        c.gridx = 1;
        c.gridy = 12;
        p.add(sponsor,c);
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 17;
        c.anchor =  GridBagConstraints.PAGE_END;
        p.add(backButton,c);
        c.gridx = 1;
        c.gridy = 17;
        p.add(okButton,c);
        //</editor-fold>

        //p.setBorder(BorderFactory.createLineBorder(Color.RED));
        this.add(p, BorderLayout.CENTER);
        //this.setBorder(BorderFactory.createLineBorder(Color.GREEN));

    }

    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (use){
                case 0 :
                    mainWindow.refresh(new WelcomePanel());
                    break;
                case 1 :
                    parent.setVisible(false);
            }
        }
    }

    private class OkListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                checkEntries();
                Event event = new Event((int)nbTeams.getValue(), (int)cashPrize.getValue(), textEventName.getText(), textLocality.getText(), caster.getFields(), sponsor.getFields(),calStartDate.getDate(), calEndDate.getDate(), isMajor.isSelected());
                switch (use){
                    case 0:
                        mainWindow.getAppController().addEvent(event);
                        break;
                    case 1:
                        event.setId(((ModifyWindow) parent).getIdEventToMod());
                        mainWindow.getAppController().modifyEvent(event);
                }
                JOptionPane.showMessageDialog(mainWindow, "Succes", "", JOptionPane.PLAIN_MESSAGE);
                mainWindow.revalidate();
                mainWindow.repaint();
            } catch (NbTeamsException | CashPrizeException | EndDateException | ConnectionException | AddEventException | CastersException | SponsorsException | IdException | EntriesException | UpdateException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void checkEntries() throws EntriesException {
        String output ="\n";
        if((int)nbTeams.getValue() < 2)
            output +="Number of teams : Must be >= 2.\n";
        if((int) cashPrize.getValue() < 0)
            output +="Cash prize : Must be >= 0.\n";
        if(textEventName.getText().isBlank())
            output +="Event name : can't be empty.\n";
        if(textLocality.getText().isBlank())
            output +="City : can't be empty.\n";
        if(calEndDate.getDate() == null)
            output += "Ending date : select a date.\n";
        else if(calEndDate.getDate().before(calStartDate.getDate()))
            output += "Ending date : can't be anterior to starting date.\n";
        if(calStartDate.getDate() == null)
            output += "Starting date : select a date.\n";
        if(!output.isBlank())
            throw new EntriesException(output);
    }

    public MultiTextFieldPanel getCaster() {
        return caster;
    }

    public MultiTextFieldPanel getSponsor() {
        return sponsor;
    }

    public void setNbTeams(int nbTeams) {
        this.nbTeams.setValue(nbTeams);
    }

    public void setCashPrize(int cashPrize) {
        this.cashPrize.setValue(cashPrize);
    }

    public void setTextEventName(String txt) {
        this.textEventName.setText(txt);
    }

    public void setTextLocality(String textLocality) {
        this.textLocality.setText(textLocality);
    }

    public void setIsMajor(Boolean isMajor) {
        this.isMajor.setSelected(isMajor);
    }

    public void setCalStartDate(Date date) {
        this.calStartDate.setDate(date);
    }

    public void setCalEndDate(Date date) {
        this.calEndDate.setDate(date);
    }
}
