package org.example;

import org.example.data.Directory;
import org.example.data.TxtFile;
import org.example.frame.Frame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


            new Directory().mkDirectory();

        new Frame();
    }
}