package ViewPackage;

import ExceptionPackage.CloseException;
import controllerPackage.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame{
    private JMenuBar menu;
    private JMenu eventMenu;
    private JMenuItem addEvent, listEvent, search, task;

    private Container container;
    private JScrollPane contend;

    private ApplicationController appController;

    public void setContend( Component contend){
        this.contend = new JScrollPane(contend);
    }

    public MainWindow(){
        //title
        super("CSGO Events Manager");
        //window extented
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(0,0, screenSize.width/3 ,screenSize.height/3);
        //add closing management
        appController = new ApplicationController();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try{
                    appController.closingProcedure();
                    System.exit(0);
                }
                catch(CloseException err){
                    JOptionPane.showMessageDialog(null, err, err.toString(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        createMenu();

        this.setLayout(new BorderLayout());
        container = this.getContentPane();
        setContend(new WelcomePanel());
        contend.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contend.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        container.add(contend, BorderLayout.CENTER);
        ThreadPanel threadPanel = new ThreadPanel();
        container.add(threadPanel, BorderLayout.SOUTH);
        setVisible(true);

    }

    private void createMenu(){
        //create menuBar
        menu = new JMenuBar();
        setJMenuBar(menu);

        //Create menu buttons
        eventMenu = new JMenu("Events");

        //Create menu options
        addEvent = new JMenuItem("Add event");
        listEvent =  new JMenuItem("List events");
        search =  new JMenuItem("Search team by major player");
        task = new JMenuItem("Task");


        //add menu
        menu.add(eventMenu);

        //add menu options
        eventMenu.add(addEvent);
        eventMenu.addSeparator();
        eventMenu.add(listEvent);
        eventMenu.addSeparator();
        eventMenu.add(search);
        eventMenu.addSeparator();
        eventMenu.add(task);

        //add actions
        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refresh(new AddEventPanel(MainWindow.this));
            }
        });

        listEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refresh(new ListEventPanel(MainWindow.this));
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refresh(new SearchPanel(MainWindow.this));
            }
        });

        task.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refresh(new TaskPanel(MainWindow.this));
            }
        });
    }

    public void refresh(JPanel panel){
        container.remove(contend);
        setContend(panel);
        container.add(contend, BorderLayout.CENTER);
        MainWindow.this.revalidate();
    }

    public ApplicationController getAppController() {
        return appController;
    }
}
