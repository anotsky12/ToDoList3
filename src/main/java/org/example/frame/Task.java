package org.example.frame;

//import org.example.data.txtFile;

import org.example.data.TxtFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;



public class Task extends JPanel {

    private  boolean checked;

    private  JButton showDescription;
    private JLabel  textOfTask;
    private  JToggleButton doneButton;
    private JPanel panelOfButtonsS_D;
    private JLabel index;





    public Task(String nameOfTask) throws IOException {

        TxtFile txtFile = new TxtFile();
        txtFile.createTxtFolder(nameOfTask);

        this.setPreferredSize(new Dimension(40,20));
        this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());

        index = new JLabel("");
        index.setPreferredSize(new Dimension(20, 20));
        index.setHorizontalAlignment(JLabel.CENTER);
        this.add(index, BorderLayout.WEST);

        textOfTask = new JLabel(nameOfTask);
        textOfTask.setBorder(BorderFactory.createEmptyBorder());
        textOfTask.setBackground(Color.RED);
        this.add(textOfTask, BorderLayout.CENTER);

        panelOfButtonsS_D = new JPanel();
        panelOfButtonsS_D.setPreferredSize(new Dimension(120, 40));
        panelOfButtonsS_D.setBorder(BorderFactory.createEmptyBorder());


        doneButton = new JToggleButton("Done", false);
        doneButton.setPreferredSize(new Dimension(40, 40));
        doneButton.setBorder(BorderFactory.createEmptyBorder());


        showDescription = new JButton("show");
        showDescription.setPreferredSize(new Dimension(40,40));
        showDescription.setBorder(BorderFactory.createEmptyBorder());

        panelOfButtonsS_D.add(doneButton, BorderLayout.WEST);

        panelOfButtonsS_D.add(showDescription, BorderLayout.EAST);


        this.add(panelOfButtonsS_D, BorderLayout.EAST);

    }

    public void changeStatusTrue(String nameOfTask, boolean b) throws IOException {
        this.setBackground(Color.GREEN);
        textOfTask.setBackground(Color.GREEN);
        setChecked(true);
        System.out.println(checked);
        TxtFile.changeStatus(b, nameOfTask);


    }

    public void changeStatusFalse(String nameOfTask, boolean b) throws IOException {
        this.setBackground(Color.RED);
        textOfTask.setBackground(Color.RED);
        setChecked(false);
        System.out.println(checked);
        TxtFile.changeStatus(b, nameOfTask);


    }


    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public JToggleButton getDoneButton() { return doneButton;}
    public JButton getShowDescription() {return showDescription;}
   // public JPanel getPanelOfButtonsS_D() {return getPanelOfButtonsS_D();}
    public void changeIndex(int num) {
        this.index.setText(String.valueOf(num));
        this.revalidate();
    }
    public boolean getState() {
        return this.checked;
    }

    public String getDescription(String nameOfTask) throws IOException {
        String temp;
        temp = TxtFile.getDescription(nameOfTask).toString();
        System.out.println(nameOfTask);
        return temp;
    }





}
