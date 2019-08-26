package ViewPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiTextFieldPanel extends JPanel {
    private JTextField[] fields;
    private JButton addField;
    private int cpt;
    private JPanel textPanel;

    public MultiTextFieldPanel (){
        cpt = 0;
        textPanel = new JPanel();
        JTextField field = new JTextField();
        fields = new JTextField[5];
        fields[cpt] =  field;
        cpt ++;
        addField = new JButton("+");
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.add(field);
        this.add(textPanel);
        this.add(addField);
        addField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(cpt <5){
                    JTextField newField = new JTextField();
                    MultiTextFieldPanel.this.textPanel.add(newField);
                    fields[cpt] = newField;
                    cpt ++;
                }
                else {
                    MultiTextFieldPanel.this.remove(addField);
                }
                MultiTextFieldPanel.this.revalidate();
                MultiTextFieldPanel.this.repaint();
            }
        });
    }

    public String[] getFields() {
        String[] output = new String[5];
        for(int i = 0; i < cpt; i++){
            output[i] = fields[i].getText();
        }
        return output;
    }

    public void setFields(String[] fields) {
        cpt = 0;
        MultiTextFieldPanel.this.textPanel.removeAll();
        for(int i = 0; i < 5 && fields[i] != null && cpt < 5;i++){
            JTextField newField = new JTextField(fields[i]);
            MultiTextFieldPanel.this.textPanel.add(newField);
            this.fields[cpt] = newField;
            cpt ++;
            if(cpt >= 5) MultiTextFieldPanel.this.remove(addField);
        };
        this.revalidate();
        this.repaint();

    }
}
