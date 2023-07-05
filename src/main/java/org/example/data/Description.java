//package org.example.data;
//
//import javax.swing.*;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class Description {
//    private static String taskDescription;
//    static JPanel jPanel = new JPanel();
//    public static String description2;
//
//    public static void setDescription() {
//        taskDescription = JOptionPane.showInputDialog(jPanel, "Description");
//        //System.out.println(taskDescription);
//        try {
//            PrintWriter pw = new PrintWriter(
//                    txtFile.tempTxt.getAbsoluteFile());
//            pw.println(taskDescription);
//            pw.close();
//            System.out.println("Task : " + txtFile.tempTxt.getName()
//                    + " ,description of task: "
//                    + taskDescription
//                    + " successfully added!");
//            description2 = taskDescription;
//        } catch (FileNotFoundException ex) {
//            throw new RuntimeException(ex);
//        }
//
//    }
//
//
//
//    public static String getDescription()  {
//
//
//        FileReader reader = null;
//        try {
//            reader = new FileReader(txtFile.tempTxt.getAbsoluteFile());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        int c;
//        String s = null;
//        while (true) {
//            try {
//                if (!((c= reader.read())!= -1)) break;
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            s= String.valueOf((char)c).toString();
//            System.out.print(s);
//
//        }
//
//        return s;
//
//    }
//
//}



