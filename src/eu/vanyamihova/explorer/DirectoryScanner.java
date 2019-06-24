package eu.vanyamihova.explorer;

import java.io.File;
import java.util.Arrays;

/**
 * @author Vanya Mihova - Delta Source Bulgaria <vanyamihova@deltasource.eu>
 * @since 2019-05-27
 */
public final class DirectoryScanner {

    private static final String PATH = "data";

    public File scan() {
        File folder = new File(PATH);
        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("No files in the directory");
            return null;
        }

        Arrays.sort(files, (o1, o2) -> (int) (o2.lastModified() - o1.lastModified()));

        for (File file : files) {
            if (file.isFile()) {
                return file;
            }
        }
        return null;
    }

}
