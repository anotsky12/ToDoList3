package org.example;

import org.example.data.Directory;
import org.example.frame.Frame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            new Directory().mkDirectory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Frame();
    }
}