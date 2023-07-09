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
    public static  List list;

    private JPanel jPanel = new JPanel();



    private JTextPane jTextPane = new JTextPane();





    public Frame() throws IOException {



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
        boolean k = false;
        boolean b = TxtFile.isEmpty();
        for (int i = 0; i < 1; i++) {
            if (b) addListener(b);else addListener(k);



        }
        addListener(k);



        repaint();
        revalidate();


        show();


    }

    public  void addListener(boolean b) {
        addTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);


                if(!b) {
                    String nameOfTask = JOptionPane.showInputDialog(jPanel, "Task");
                    Task task;
                    try {
                        task = new Task(nameOfTask, b);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    list.add(task);
                    list.updateNumbers();
                    repaint();
                    revalidate();

                    task.getDoneButton().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            String textTemp = (task.getDoneButton().isSelected()) ? "Undo" : "Done";
                            task.getDoneButton().setText(textTemp);
                            if (task.getDoneButton().isSelected()) {
                                try {
                                    task.changeStatusTrue(nameOfTask, true);
                                    repaint();
                                    revalidate();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                            if (!task.getDoneButton().isSelected()) {
                                try {
                                    task.changeStatusFalse(nameOfTask, false);
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
                    task.getShowDescription().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            super.mousePressed(e);
                            try {
                                JOptionPane.showMessageDialog(jTextPane, task.getDescription(nameOfTask), nameOfTask, 1);
                                revalidate();
                                repaint();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    repaint();
                    revalidate();







                } else {
                    File[] collection = Directory.folder.listFiles();
                    java.util.List<File> fileList = Arrays.asList(collection);
                    if (!fileList.isEmpty()) {

                        for (File f :
                                fileList) {
                            Task task;
                            String nameOfTask;

                            try {
                                task = new Task(f.getName(), !b);
                                 nameOfTask = f.getName();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            list.add(task);
                            list.updateNumbers();
                            repaint();
                            revalidate();

                            task.getDoneButton().addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    super.mouseClicked(e);
                                    String textTemp = (task.getDoneButton().isSelected()) ? "Undo" : "Done";
                                    task.getDoneButton().setText(textTemp);
                                    if (task.getDoneButton().isSelected()) {
                                        try {
                                            task.changeStatusTrue(nameOfTask, true);
                                            repaint();
                                            revalidate();
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                    if (!task.getDoneButton().isSelected()) {
                                        try {
                                            task.changeStatusFalse(nameOfTask, false);
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
                            task.getShowDescription().addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    super.mousePressed(e);
                                    try {
                                        JOptionPane.showMessageDialog(jTextPane, task.getDescription(nameOfTask), nameOfTask, 1);
                                        revalidate();
                                        repaint();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            });
                            repaint();
                            revalidate();
                        }
                    }













                    }
                }});
        repaint();
        revalidate();


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
