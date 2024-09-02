package utils;

import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * This utility class provides methods for obtaining absolute paths to resource files.
 */
public class ResourceUtils {

    /**
     * Retrieves the absolute path of a resource file based on its relative path within the classpath.
     *
     */
    public static String getResourceFilePathAbsPath(final String relativePath) {
        try {
            return Paths.get(ClassLoader.getSystemResource(relativePath).toURI()).toString();
        } catch (URISyntaxException exception) {
            return "";
        }
    }
}
