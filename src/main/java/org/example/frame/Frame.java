package org.example.frame;


import org.example.data.Directory;
import org.example.data.TxtFile;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;


public class Frame extends JFrame {
    private ButtonPanel buttonPanel;
    private JButton addTask;
    private JButton clear;
    private final TitleBar titleBar;
    private final List list;

    private JPanel jPanel = new JPanel();


    private JTextPane jTextPane = new JTextPane();


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
        repaint();
        revalidate();


        show();


    }

    private void addListener() {
        addTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                ThreadB threadB = new ThreadB(){
                    threadB.start();
                    threadB.wait();
                };
                System.out.println(threadB.total);


                int i = 1;
                Task task;
                if (i == 1) {
                    try {
                        File[] collection = Directory.folder.listFiles();
                        java.util.List<File> fileList = Arrays.asList(collection);
                        System.out.println(fileList);
                        for (int j = 0; j < collection.length; j++) {
                            for (File f : fileList) {


                                task = new Task(f.getName(), 1);
                                System.out.println(f.getName());

                                list.add(task);
                                list.updateNumbers();
                                repaint();
                                revalidate();
                            }
                            i++;

                        }
                        System.out.println("read");
                    } catch (IOException ez) {
                        String nameOfTask = JOptionPane.showInputDialog(jPanel, "Task");
                        try {
                            task = new Task(nameOfTask);
                            list.add(task);
                            list.updateNumbers();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
                i++;






                repaint();
                revalidate();


                Task finalTask = task;
                task.getDoneButton().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String textTemp = (finalTask.getDoneButton().isSelected()) ? "Undo" : "Done";
                        finalTask.getDoneButton().setText(textTemp);
                        if (finalTask.getDoneButton().isSelected()) {
                            try {
                                finalTask.changeStatusTrue(nameOfTask, true);
                                repaint();
                                revalidate();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (!finalTask.getDoneButton().isSelected()) {
                            try {
                                finalTask.changeStatusFalse(nameOfTask, false);
                                repaint();
                                revalidate();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                });

                repaint();
                revalidate();

                Task finalTask1 = task;
                task.getShowDescription().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        try {
                            JOptionPane.showMessageDialog(jTextPane, finalTask1.getDescription(nameOfTask), nameOfTask, 1);
                            revalidate();
                            repaint();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });


            }
        });


        clear.addMouseListener(new MouseAdapter() {


            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                list.removeComplitedTask();
                list.updateNumbers();
                TxtFile.deleteTrue();
                repaint();
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
        repaint();
        revalidate();
    }

}
