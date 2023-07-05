package org.example.frame;


import org.example.data.TxtFile;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;

import java.util.ArrayList;


public class Frame extends JFrame {
    private ButtonPanel buttonPanel;
    private JButton addTask;
    private JButton clear;
    private final TitleBar titleBar;
    private final List list;

    static JPanel jPanel = new JPanel();
    static Task task;


    static JTextPane jTextPane = new JTextPane();


    private String nameOfTask;


    public Frame() {

        this.setSize(Constant.FRAME_SIZE_WIDTH, Constant.FRAME_SIZE_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        buttonPanel = new ButtonPanel();
        titleBar = new TitleBar();
        list = new List();

        this.add(titleBar, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(list, BorderLayout.CENTER);

        addTask = buttonPanel.getAddTask();
        clear = buttonPanel.getClearDoneTask();

        addListener();


        show();


    }

    private void addListener() {
        addTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                nameOfTask = JOptionPane.showInputDialog(jPanel, "Task");
                try {
                    task = new Task(nameOfTask);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                list.add(task);
                list.updateNumbers();


                task.getDoneButton().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);


                        String textTemp = (task.getDoneButton().isSelected()) ? "Undo" : "Done";
                        task.getDoneButton().setText(textTemp);
                        if (task.getDoneButton().isSelected()) {
                            try {
                                task.changeStatusTrue(nameOfTask, true);
                                revalidate();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            revalidate();
                        }
                        if (!task.getDoneButton().isSelected()) {
                            try {
                                task.changeStatusFalse(nameOfTask, false);
                                revalidate();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            revalidate();
                        }

                        revalidate();

                    }
                });


                revalidate();

                task.getShowDescription().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        JOptionPane.showMessageDialog(jTextPane, TxtFile.getDescriptionString(), nameOfTask, 1);
                    }
                });
                revalidate();


            }
        });
        clear.addMouseListener(new MouseAdapter() {


            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                list.removeComplitedTask();
                list.updateNumbers();
                TxtFile.deleteTrue();
                revalidate();



//                int k = 0;
//                java.util.List<File> lst3 = new ArrayList<>() ;
//                if(Directory.folder.exists()){
//                    if(Directory.folder.length()!=0){
//
//                        for(File file : Directory.folder.listFiles()){
//                            if (file.isFile()) lst3.add(file);
//                        }
//                    }
//
//                }
//
//                ArrayList<String> trueTxtName = new ArrayList();
//                for (int i = 0; i <lst3.size() ; i++) {
//                    trueTxtName.add(lst3.get(i).getName().toString());
//                }
//                for (int i = 0; i < trueTxtName.size(); i++) {
//                    for (int j = 0; j < trueTxt.size(); j++) {
//                        if(trueTxtName.get(i).equals(trueTxt.get(j))==true){
//                            try {
//                                Files.delete(Path.of(Directory.folder.getAbsoluteFile() + "\\" + trueTxtName.get(i) + ".txt"));
//                            } catch (IOException ex) {
//                                throw new RuntimeException(ex);
//                            }
//                        }
//
//                    }
//
//                }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////                while(Directory.folder.getAbsoluteFile().length() != 0) {
////
////
////                        trueTxt = new HashSet<>();
////                     if(txtFile.isCheckedTxt())
////                         trueTxt.add(txtFile.getNames());
////
////
////
////
////                    while(!trueTxt.isEmpty()){
////                        for (String k:
////                            trueTxt ) {
////                            try {
////                                Files.delete(Path.of(Directory.folder.getAbsoluteFile() + "\\" + k + ".txt"));
////                                trueTxt.remove(k);
////
////                                revalidate();
////
////
////                            } catch (IOException ex) {
////                                System.out.println("successfully remotely");
////                                break;
////                            }
////
////                        }
////                    }
////                }
//               revalidate();


            }
        });
        revalidate();
    }

}
