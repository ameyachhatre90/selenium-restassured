package utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type File utils.
 */
public class FileUtils {

    /**
     * Read all lines string.
     *
     * @param filePath the file path
     * @return the string
     * @throws IOException the io exception
     */
    public static String readAllLines(String filePath) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return IOUtils.toString(reader);
        }
    }
}
