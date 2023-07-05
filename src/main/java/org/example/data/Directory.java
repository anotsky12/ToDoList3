package org.example.data;

import java.io.File;
import java.io.IOException;


public class Directory {
    public static File folder;
    //public File folder;
    private static String URI;

    public static String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }

    public void mkDirectory() throws IOException {



            folder = new File("DataOfToDoList3");
            if (!folder.exists()) {
                folder.mkdir();
              // setURI(folder.getAbsolutePath());
                System.out.println("The folder created" + getURI());
            } else {
                setURI(folder.getAbsolutePath());
                System.out.println("The folder has already been created: " + getURI());
               // TxtFile.readAtStart();
            }





    }
}
