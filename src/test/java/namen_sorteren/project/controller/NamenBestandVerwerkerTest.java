package namen_sorteren.project.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;

class NamenBestandVerwerkerTest {

    @TempDir
    Path tijdelijkeMap;

    // --- telNamenBestand ---

    @Test
    void telNamenBestand_drieRegels_geeftDrie() throws IOException {
        Path bestand = tijdelijkeMap.resolve("namen.txt");
        try (PrintWriter schrijver = new PrintWriter(bestand.toFile())) {
            schrijver.println("Alice");
            schrijver.println("Bob");
            schrijver.println("Charlie");
        }
        assertEquals(3, NamenBestandVerwerker.telNamenBestand(bestand.toString()));
    }

    @Test
    void telNamenBestand_leegBestand_geeftNul() throws IOException {
        Path bestand = tijdelijkeMap.resolve("leeg.txt");
        bestand.toFile().createNewFile();
        assertEquals(0, NamenBestandVerwerker.telNamenBestand(bestand.toString()));
    }

    // --- leesNamenBestand ---

    @Test
    void leesNamenBestand_drieNamen_geeftListMetDrieNamen() throws IOException {
        Path bestand = tijdelijkeMap.resolve("namen.txt");
        try (PrintWriter schrijver = new PrintWriter(bestand.toFile())) {
            schrijver.println("Alice");
            schrijver.println("Bob");
            schrijver.println("Charlie");
        }
        ArrayList<String> namen = NamenBestandVerwerker.leesNamenBestand(bestand.toString());
        assertEquals(3, namen.size());
        assertEquals("Alice", namen.get(0));
        assertEquals("Charlie", namen.get(2));
    }

    // --- maakBestandVanLijst ---

    @Test
    void maakBestandVanLijst_schrijftNamenNaarBestand() throws IOException {
        ArrayList<String> lijst = new ArrayList<>();
        lijst.add("Alice");
        lijst.add("Bob");
        lijst.add("Charlie");

        Path uitvoer = tijdelijkeMap.resolve("uitvoer.txt");
        NamenBestandVerwerker.maakBestandVanLijst(lijst, uitvoer.toString());

        ArrayList<String> teruggelezen = NamenBestandVerwerker.leesNamenBestand(uitvoer.toString());
        assertEquals(3, teruggelezen.size());
        assertEquals("Alice", teruggelezen.get(0));
    }
}
