package namen_sorteren.project.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import static namen_sorteren.project.controller.NameFileProcessor.*;


public class NamenSorterenLauncher {
    private final static String NAME_FILE_OLD = "9.2 GesorteerdeNamenOrigineel.txt";
    private final static String NAME_FILE_EXTRA = "9.2 ExtraNamen.txt";
    private final static String NAME_FILE_NEW = "9.2 GesorteerdeNamenNieuw.txt";


    public static void main(String[] args) throws URISyntaxException {
        var classloader = Thread.currentThread().getContextClassLoader();
        var pathToExtraNames = classloader.getResource(NAME_FILE_EXTRA).toURI().getPath();
        var pathToOldFile = classloader.getResource(NAME_FILE_OLD).toURI().getPath();
        var pathToNewFile= classloader.getResource(NAME_FILE_NEW).toURI().getPath();

    }
}
