package eu.vanyamihova.console;

import eu.vanyamihova.models.link.LinkType;
import eu.vanyamihova.search.SearchFactory;
import eu.vanyamihova.search.Searchable;
import eu.vanyamihova.search.SearchableType;

import java.util.Scanner;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-06-23
 */
public class ConsoleMenu {

    private Scanner in = new Scanner(System.in);
    private SearchableType searchableType = null;
    private LinkType linkType = null;

    public Searchable createSearch(SearchFactory factory) {
        introduction();

        showSearchableMenu();
        readSearchableTypeFromConsole();

        if (searchableType == SearchableType.AVOID_LINK_TYPES) {
            showMenuForAvoidingLinkTypes();
            readLinkTypeFromConsole();
        }
        return factory.factory(searchableType, linkType);
    }

    public int[] createDestination() {
        System.out.println("Моля, изберете начало:");
        int start = in.nextInt();

        System.out.println("Моля, изберете край:");
        int end = in.nextInt();
        return new int[]{start, end};
    }

    private void introduction() {
        System.out.println("Здравейте!");
    }

    private void showSearchableMenu() {
        System.out.println("Моля изберете един от механизмите за откриване на стая: (1, 2, 3)");
        System.out.println("\t1. Намиране на път до стая, като се избягват определен тип преход – например избягваме стълби между етажи, поради болки в краката...");
        System.out.println("\t2. Намиране на стая като се възползвате от координатите и етажите, за да достигнете до стаята с минимален брой стъпки (преминаване през минимален брой стаи)");
        System.out.println("\t3. Намиране на път до стая, като се използва преход lift между етажите. Ако между два етажа няма такъв преход да се използва преход climb, но разходите за този преход да се удвоят при пресмятане на разходите за достигане на целта.");
    }

    private void readSearchableTypeFromConsole() {
        int searchableTypeFromConsole = in.nextInt();
        searchableType = SearchableType.getSearchTypeByType(searchableTypeFromConsole);

        if (searchableType == null) {
            System.out.println("Невалидна опция! Изберете отново: (1, 2, 3)");
            readSearchableTypeFromConsole();
        }
    }

    private void showMenuForAvoidingLinkTypes() {
        System.out.println("Моля изберете тип преход за избягване: (1, 2)");
        System.out.println("\t1. Стълби");
        System.out.println("\t2. Асансьор");
    }

    private void readLinkTypeFromConsole() {
        int linkTypeFromConsole = in.nextInt();
        linkType = LinkType.getLinkTypeByType(linkTypeFromConsole);

        if (linkType == null) {
            System.out.println("Невалидна опция! Изберете отново: (1, 2)");
            readLinkTypeFromConsole();
        }
    }
}
