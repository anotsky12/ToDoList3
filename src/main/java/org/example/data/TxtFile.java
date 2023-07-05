package org.example.data;//package org.example.data;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class txtFile {
//    private static String name;
//    static File tempTxt;
//    Directory directory;
//    private static boolean checkedTxt;
//
//
//    public static boolean isCheckedTxt() {
//        return checkedTxt;
//    }
//
//    public static void setCheckedTxt(boolean checkedTxt) {
//        txtFile.checkedTxt = checkedTxt;
//    }
////    public txtFile(String name) {
////        this.name = name;
////    }
//
//    public static String getNames() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void createTxt(String name) throws IOException {
//
//        setName(name);
//        tempTxt = new File(Directory.folder.getAbsoluteFile() + "\\" + getNames() + ".txt");
//        tempTxt.createNewFile();
//        setName(name);
//
//
//    }
//
//    public static void deleteTxt() throws IOException {
//
//
//
//
//
//
//
//    }
//
//
//}

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;


public class TxtFile {

    private String nameOfTxtFile;
    private static File txtFile;


    private static File description;
    private static String descriptionString;
    private static File status, status2;


    private String taskDescription;
    static JPanel jPanel = new JPanel();


    public void createTxtFolder(String nameOfTask) {
        setNameOfTxtFile(nameOfTask);

        txtFile = new File(Directory.folder.getAbsoluteFile() + "\\" + getNameOfTxtFile());
        txtFile.mkdir();
        status = new File(txtFile.getAbsoluteFile() + "\\" + "status" + ".txt");
        try {
            status.createNewFile();
        } catch (IOException e) {
            System.out.println("status of " + nameOfTask + " not created");
        }
        setStatus(false);
        description = new File(txtFile.getAbsoluteFile() + "\\" + "description" + ".txt");
        try {
            description.createNewFile();

        } catch (IOException e) {
            System.out.println("description of " + nameOfTask + "not created");
        }

        setDescriptionAfterCreated();


    }

    public static void setStatus(boolean b) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(TxtFile.status.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.println(b);
        pw.close();

    }

    public void setDescriptionAfterCreated() {
        taskDescription = JOptionPane.showInputDialog(jPanel, "Description");
        try {
            PrintWriter pw = new PrintWriter(
                    description.getAbsolutePath());
            pw.println(taskDescription);
            pw.close();
            System.out.println("Task : " + txtFile.getName()
                    + " ,description of task: "
                    + taskDescription
                    + " successfully added!");

            setDescriptionString(taskDescription, txtFile);


        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        setStatus(false);
    }

    public static void changeStatus(boolean b, String nameOfTask) throws IOException {
        //status.delete();
        Files.delete(Path.of((Directory.folder.getAbsolutePath() + "\\" + nameOfTask + "\\" + "status" + ".txt")));
        status2 = new File(Directory.folder.getAbsoluteFile() + "\\" + nameOfTask + "\\" + "status" + ".txt");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(Directory.folder.getAbsoluteFile() + "\\" + nameOfTask + "\\" + "status" + ".txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.println(b);
        pw.close();

    }

//    public static Boolean getStatus() {
//        boolean k = false;
//        return k;
//    }

    public static void deleteTrue() {
        File[] collection = Directory.folder.listFiles();
        List<File> fileList = Arrays.asList(collection);
        //System.out.println(fileList);
        for (File f :
                fileList) {
            File[] files = f.listFiles();
            List<File> files1 = Arrays.asList(files);
            for (File files2 :
                    files1) {
                Path path = Path.of(files2.toURI());

                for (String s :
                        getListOfName(path)) {
                    if (s.equals("status.txt")) {
                        String fileName = files2.getAbsoluteFile().toString();
                        try {
                            Optional<String> line = Files.lines(Paths.get(fileName)).findFirst();
                            if (line.get().equals("true")) {
                                String k = f.getAbsolutePath().toString();
                                //System.out.println(k);
                                String m = k.substring(k.lastIndexOf('\\'));
                                System.out.println(m);
                                FileUtils.deleteDirectory(new File(Directory.folder.getAbsolutePath() + m));

                                //File(Path.of(Directory.folder.getAbsolutePath() +  m ));
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }


    }

    public static List<String> getListOfName(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Could not read files for path " + path);
        }
    }


    public String getNameOfTxtFile() {
        return nameOfTxtFile;
    }

    public void setNameOfTxtFile(String nameOfTxtFile) {
        this.nameOfTxtFile = nameOfTxtFile;
    }

    public static void setDescriptionString(String descriptionString, File txtFile) {
        TxtFile.descriptionString = descriptionString;
    }

    public static String getDescriptionString() {
        return descriptionString;
    }
}