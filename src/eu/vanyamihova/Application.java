package eu.vanyamihova;

import eu.vanyamihova.console.ConsoleMenu;
import eu.vanyamihova.explorer.DirectoryScanner;
import eu.vanyamihova.explorer.FileContentReader;
import eu.vanyamihova.models.BuildingSchema;
import eu.vanyamihova.models.BuildingSchemaBuilder;
import eu.vanyamihova.search.SearchFactory;
import eu.vanyamihova.search.Searchable;
import eu.vanyamihova.utils.PathPrinter;

import java.io.File;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public class Application {

    public static void main(String[] args) {
        DirectoryScanner directoryScanner = new DirectoryScanner();
        File file = directoryScanner.scan();

        if (file == null) {
            System.out.println("There is no existing file");
            return;
        }

        FileContentReader fileContentReader = new FileContentReader();
        String content = fileContentReader.read(file);

        BuildingSchema buildingSchema = new BuildingSchema();
        BuildingSchemaBuilder buildingSchemaBuilder = new BuildingSchemaBuilder(buildingSchema);
        buildingSchemaBuilder.buildFrom(content);

        PathPrinter pathPrinter = new PathPrinter(buildingSchema);
        SearchFactory factory = new SearchFactory(buildingSchema, pathPrinter);
        ConsoleMenu consoleMenu = new ConsoleMenu();

        Searchable searchable = consoleMenu.createSearch(factory);
        buildingSchema.resetAllNodes();

        int[] points = consoleMenu.createDestination();
        boolean result = searchable.search(points[0], points[1]);
        if (result) {
            System.out.println("---------- HAVE A PATH ----------");
            pathPrinter.print();
        } else {
            System.out.println("---------- THERE IS NO PATH ----------");
        }
    }

}
