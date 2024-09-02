package utils;

import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * The type Resource utils.
 */
public class ResourceUtils {

    /**
     * Gets resource file path abs path.
     *
     * @param relativePath the relative path
     * @return the resource file path abs path
     */
    public static String getResourceFilePathAbsPath(final String relativePath) {
        try {
            return Paths.get(ClassLoader.getSystemResource(relativePath).toURI()).toString();
        } catch (URISyntaxException exception) {
            return "";
        }
    }
}
