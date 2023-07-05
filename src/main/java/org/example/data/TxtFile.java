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
import org.example.frame.Task;


public class TxtFile {

    private String nameOfTxtFile;
    private static File txtFile;


    private static File description;
    private  File status;
    private static File status2;




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
        setStatus(false, nameOfTxtFile);
        description = new File(txtFile.getAbsoluteFile() + "\\" + "description" + ".txt");
        try {
            description.createNewFile();

        } catch (IOException e) {
            System.out.println("description of " + nameOfTask + "not created");
        }

        setDescriptionAfterCreated(getNameOfTxtFile());


    }

    public static void setStatus(boolean b, String nameOfTxtFile) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(Directory.folder.getAbsoluteFile() + "\\" + nameOfTxtFile + "\\" + "status" + ".txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.println(b);
        pw.close();

    }

    public static void setDescriptionAfterCreated(String nameOfTxtFile) {
        JPanel jPanel = new JPanel();
        String taskDescription = JOptionPane.showInputDialog(jPanel, "Description");
        try {
            PrintWriter pw = new PrintWriter(
                    Directory.folder.getAbsoluteFile() + "\\" + nameOfTxtFile + "\\" + "description" + ".txt");
            pw.println(taskDescription);
            pw.close();
            System.out.println("Task : " + txtFile.getName()
                    + " ,description of task: "
                    + taskDescription
                    + " successfully added!");





        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        setStatus(false, nameOfTxtFile);
    }

    public static void changeStatus(boolean b, String nameOfTask) throws IOException {
        //status.delete();
        String boolTemp = String.valueOf(b) ;

        Files.delete(Path.of((Directory.folder.getAbsolutePath() + "\\" + nameOfTask + "\\" + "status" + ".txt")));
        status2 = new File(Directory.folder.getAbsoluteFile() + "\\" + nameOfTask + "\\" + "status" + ".txt");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(Directory.folder.getAbsoluteFile() + "\\" + nameOfTask + "\\" + "status" + ".txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.println(boolTemp);
        pw.close();

    }

    public static Boolean getStatus() {
        boolean k = false;
        return k;
    }

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
    public static void readAtStart() throws IOException {
        File[] collection = Directory.folder.listFiles();
        List<File> fileList = Arrays.asList(collection);
        for (File f:
             fileList) {

            Task task = new Task(f.getName());

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


    public static String getDescription(String nameOfTask) throws IOException {

        String fileName = Directory.folder.getAbsoluteFile() + "\\" + nameOfTask + "\\" + "description" + ".txt";
        String content = Files.lines(Paths.get(fileName)).reduce("", String::concat);
        return content;


    }
}