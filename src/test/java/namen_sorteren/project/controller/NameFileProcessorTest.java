package namen_sorteren.project.controller;


import static namen_sorteren.project.controller.NameFileProcessor.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class NameFileProcessorTest {
    private final static String NAME_FILE_OLD = "9.2 GesorteerdeNamenOrigineel.txt";
    private final static String NAME_FILE_EXTRA = "9.2 ExtraNamen.txt";
    private final static String NAME_FILE_NEW = "9.2 GesorteerdeNamenNieuw.txt";

    private String pathToExtraNames;
    private String pathToSortedNames;
    private String pathToNewFile;

    @Before
    public void setPath()  throws URISyntaxException{
        var classloader = Thread.currentThread().getContextClassLoader();
        pathToExtraNames = classloader.getResource(NAME_FILE_EXTRA).toURI().getPath();
        pathToSortedNames = classloader.getResource(NAME_FILE_OLD).toURI().getPath();
        pathToNewFile= classloader.getResource(NAME_FILE_NEW).toURI().getPath();
    }


    @Test
    public void testCountNamesInFile() {
        Assert.assertEquals(17,countNamesInFile(pathToExtraNames));
    }

    @Test
    public void testReadNamesOfFile() {
        var names = readNamesOfFile(pathToExtraNames);

        Assert.assertEquals(17,names.size());
        Assert.assertTrue(names.contains("Ted"));
    }

    @Test
    public void testAddNameToSortedList() {
        var names = readNamesOfFile(pathToSortedNames);

        AddNameToSortedList("Hans", names);

        Assert.assertTrue(names.contains("Hans"));
        Assert.assertEquals("Hans", names.get(3));
    }

    @Test
    public void testAddListToSortedList() {
        var names = readNamesOfFile(pathToSortedNames);

        addListToSortedList(new ArrayList<>(Arrays.asList("Hans","Peter")), names);

        Assert.assertTrue(names.contains("Hans"));
        Assert.assertTrue(names.contains("Peter"));
        Assert.assertEquals("Hans", names.get(3));
        Assert.assertEquals("Peter", names.get(8));
    }

    @Test
    public void testMakeFileFromList() throws FileNotFoundException {
        var names = readNamesOfFile(pathToSortedNames);

        makeFileFromList(names, pathToNewFile);

        var newNames = readNamesOfFile(pathToNewFile);
        Assert.assertFalse(newNames.isEmpty());
        Assert.assertEquals(9, newNames.size());
        Assert.assertEquals("Mark", newNames.get(5));

        PrintWriter printWriter = new PrintWriter(pathToNewFile);
        printWriter.print("");
        printWriter.close();
    }
}