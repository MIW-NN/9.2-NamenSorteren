package namen_sorteren.project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class NameFileProcessor {

    public static int countNamesInFile(String fileName) {
        int nameCounter = 0;
        try {
            File file = new File(fileName);
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                readFile.next();
                nameCounter++;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Bestand niet gevonden.");
        }
        return nameCounter;
    }

    public static ArrayList<String> readNamesOfFile(String fileName) {
        ArrayList<String> nameList = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                nameList.add(readFile.next());
            }
        } catch (FileNotFoundException fout) {
            System.out.println("Bestand niet gevonden.");
        }
        return nameList;
    }

    public static void AddNameToSortedList(String name, ArrayList<String> list) {
        int index = 0;
        while (name.compareTo(list.get(index)) > 0) {
            index++;
            if (index >= list.size()) {
                list.add(name);
                return;
            }
        }
        list.add(index, name);
    }

    public static void addListToSortedList(ArrayList<String> newList, ArrayList<String> oldList) {
        for (String name : newList) {
            AddNameToSortedList(name, oldList);
        }
    }

    public static void makeFileFromList(ArrayList<String> list, String fileName) {
        try {
            PrintWriter printWriter = new PrintWriter(fileName);
            for (String name : list) {
                printWriter.println(name);
            }
            printWriter.close();
        } catch (Exception Exception) {
            System.out.println(Exception.getMessage());
        }
    }
}
