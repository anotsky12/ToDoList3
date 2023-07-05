package org.example;

import org.example.data.Directory;
import org.example.frame.Frame;

public class Main {
    public static void main(String[] args) {
        new Frame();
        new Directory().mkDirectory();

    }
}