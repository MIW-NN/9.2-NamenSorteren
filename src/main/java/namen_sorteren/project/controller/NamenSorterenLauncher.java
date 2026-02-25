package namen_sorteren.project.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import static namen_sorteren.project.controller.NamenBestandVerwerker.*;


public class NamenSorterenLauncher {
    private final static String NAMEN_BESTAND_ORIGINEEL = "src/main/resources/9.2 GesorteerdeNamenOrigineel.txt";
    private final static String NAMEN_BESTAND_EXTRA = "src/main/resources/9.2 ExtraNamen.txt";
    private final static String NAMEN_BESTAND_NIEUW = "src/main/resources/9.2 GesorteerdeNamenNieuw.txt";


    public static void main(String[] args) throws URISyntaxException {

        System.out.printf("Het bestand bevat %d namen\n", telNamenBestand(NAMEN_BESTAND_ORIGINEEL));

        ArrayList<String> namenLijstOrigineel = leesNamenBestand(NAMEN_BESTAND_ORIGINEEL);
        System.out.println(namenLijstOrigineel);

        voegNaamToeAanGesorteerdeLijst("Jos\u00e9", namenLijstOrigineel);
        voegNaamToeAanGesorteerdeLijst("Theo", namenLijstOrigineel);
        ArrayList<String> namenLijstExtra = leesNamenBestand(NAMEN_BESTAND_EXTRA);
        System.out.println(namenLijstExtra);
        voegLijstToeAanGesorteerdeLijst(namenLijstExtra, namenLijstOrigineel);
        System.out.println(namenLijstOrigineel);
        maakBestandVanLijst(namenLijstOrigineel, NAMEN_BESTAND_NIEUW);
    }
}
