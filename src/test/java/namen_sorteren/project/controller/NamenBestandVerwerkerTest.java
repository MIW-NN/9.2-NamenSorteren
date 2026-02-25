package namen_sorteren.project.controller;


import static namen_sorteren.project.controller.NamenBestandVerwerker.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class NamenBestandVerwerkerTest {
    private final static String NAMEN_BESTAND_ORIGINEEL = "9.2 GesorteerdeNamenOrigineel.txt";
    private final static String NAMEN_BESTAND_EXTRA = "9.2 ExtraNamen.txt";
    private final static String NAMEN_BESTAND_NIEUW = "9.2 GesorteerdeNamenNieuw.txt";

    private String padNaarExtraNamen;
    private String padNaarGesorteerdNamen;
    private String padNaarNieuwBestand;

    @Before
    public void stelPadIn()  throws URISyntaxException{
        var classloader = Thread.currentThread().getContextClassLoader();
        padNaarExtraNamen = classloader.getResource(NAMEN_BESTAND_EXTRA).toURI().getPath();
        padNaarGesorteerdNamen = classloader.getResource(NAMEN_BESTAND_ORIGINEEL).toURI().getPath();
        padNaarNieuwBestand= classloader.getResource(NAMEN_BESTAND_NIEUW).toURI().getPath();
    }


    @Test
    public void testTelNamenBestand() {
        Assert.assertEquals(17,telNamenBestand(padNaarExtraNamen));
    }

    @Test
    public void testLeesNamenBestand() {
        var namen = leesNamenBestand(padNaarExtraNamen);

        Assert.assertEquals(17,namen.size());
        Assert.assertTrue(namen.contains("Ted"));
    }

    @Test
    public void testVoegNaamToe() {
        var namen = leesNamenBestand(padNaarGesorteerdNamen);

        voegNaamToeAanGesorteerdeLijst("Hans", namen);

        Assert.assertTrue(namen.contains("Hans"));
        Assert.assertEquals("Hans", namen.get(3));
    }

    @Test
    public void testVoegLijstToe() {
        var namen = leesNamenBestand(padNaarGesorteerdNamen);

        voegLijstToeAanGesorteerdeLijst(new ArrayList<>(Arrays.asList("Hans","Peter")), namen);

        Assert.assertTrue(namen.contains("Hans"));
        Assert.assertTrue(namen.contains("Peter"));
        Assert.assertEquals("Hans", namen.get(3));
        Assert.assertEquals("Peter", namen.get(8));
    }

    @Test
    public void testMaakBestand() throws FileNotFoundException {
        var namen = leesNamenBestand(padNaarGesorteerdNamen);

        maakBestandVanLijst(namen, padNaarNieuwBestand);

        var newNames = leesNamenBestand(padNaarNieuwBestand);
        Assert.assertFalse(newNames.isEmpty());
        Assert.assertEquals(9, newNames.size());
        Assert.assertEquals("Mark", newNames.get(5));

        PrintWriter printWriter = new PrintWriter(padNaarNieuwBestand);
        printWriter.print("");
        printWriter.close();
    }
}
