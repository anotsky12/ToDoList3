package org.example.frame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton addTask;
    private JButton clearDoneTask;

    Border emptyBorder = BorderFactory.createEmptyBorder();



    ButtonPanel(){
        this.setPreferredSize(new Dimension(Constant.FRAME_SIZE_WIDTH, Constant.TITLE_SIZE_HEIGHT));

        addTask = new JButton("Add new task");
        addTask.setBorder(emptyBorder);
        addTask.setFont(new Font("Sans-serif", Font.BOLD,20));
        this.add(addTask);

        this.add(Box.createHorizontalStrut(20));

        clearDoneTask = new JButton("Clear complited task");
        clearDoneTask.setBorder(emptyBorder);
        clearDoneTask.setFont(new Font("Sans-serif", Font.BOLD,20));

        this.add(clearDoneTask);



    }
    public JButton getAddTask() {
        return addTask;
    }

    public JButton getClearDoneTask() {
        return clearDoneTask;
    }

}
