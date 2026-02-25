package namen_sorteren.project.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class NamenBestandVerwerker {

    public static int telNamenBestand(String bestandsnaam) {
        int nameCounter = 0;
        try {
            File file = new File(bestandsnaam);
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

    public static ArrayList<String> leesNamenBestand(String bestandsnaam) {
        ArrayList<String> nameList = new ArrayList<>();
        try {
            File file = new File(bestandsnaam);
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                nameList.add(readFile.next());
            }
        } catch (FileNotFoundException fout) {
            System.out.println("Bestand niet gevonden.");
        }
        return nameList;
    }

    public static void voegNaamToeAanGesorteerdeLijst(String naam, ArrayList<String> lijst) {
        int index = 0;
        while (naam.compareTo(lijst.get(index)) > 0) {
            index++;
            if (index >= lijst.size()) {
                lijst.add(naam);
                return;
            }
        }
        lijst.add(index, naam);
    }

    public static void voegLijstToeAanGesorteerdeLijst(ArrayList<String> nieuweLijst, ArrayList<String> oudeLijst) {
        for (String naam : nieuweLijst) {
            voegNaamToeAanGesorteerdeLijst(naam, oudeLijst);
        }
    }

    public static void maakBestandVanLijst(ArrayList<String> lijst, String bestandsnaam) {
        try {
            PrintWriter printWriter = new PrintWriter(bestandsnaam);
            for (String naam : lijst) {
                printWriter.println(naam);
            }
            printWriter.close();
        } catch (Exception Exception) {
            System.out.println(Exception.getMessage());
        }
    }
}
