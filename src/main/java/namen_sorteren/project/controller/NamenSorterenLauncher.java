package namen_sorteren.project.controller;

import java.util.ArrayList;
import static namen_sorteren.project.controller.NameFileProcessor.*;


public class NamenSorterenLauncher {
    private final static String NAME_FILE_OLD = "resources/9.2 GesorteerdeNamenOrigineel.txt";
    private final static String NAME_FILE_EXTRA = "resources/9.2 ExtraNamen.txt";
    private final static String NAME_FILE_NEW = "resources/9.2 GesorteerdeNamenNieuw.txt";


    public static void main(String[] args) {

        System.out.printf("Het bestand bevat %d namen\n", countNamesInFile(NAME_FILE_OLD));

        ArrayList<String> namenLijstOrigineel = readNamesOfFile(NAME_FILE_OLD);
        System.out.println(namenLijstOrigineel);

        AddNameToSortedList("José", namenLijstOrigineel);
        AddNameToSortedList("Theo", namenLijstOrigineel);
        ArrayList<String> namenLijstExtra = readNamesOfFile(NAME_FILE_EXTRA);
        System.out.println(namenLijstExtra);
        addListToSortedList(namenLijstExtra, namenLijstOrigineel);
        System.out.println(namenLijstOrigineel);
        makeFileFromList(namenLijstOrigineel, NAME_FILE_NEW);
    }
}
