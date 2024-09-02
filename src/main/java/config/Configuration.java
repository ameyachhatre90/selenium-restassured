package config;

import exceptions.StopTestException;
import utils.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * This class is responsible for loading and managing configuration properties.
 * It provides methods to access properties from a configuration file (`config.properties`)
 * and system properties. It uses a singleton pattern to ensure only one instance
 * of the Configuration class exists.
 */
public class Configuration {

    private final static String[] files = new String[]{"config.properties"};
    private final Properties props;

    private static volatile Configuration configuration;

    /**
     * Loads the config files and system properties into a Properties object.
     *
     * @throws StopTestException throws when provided file is not exist
     */
    public Configuration() throws StopTestException {
        props = new Properties(System.getProperties());
        for (String filename : files) {
            try (InputStream fstream = new FileInputStream(ResourceUtils.getResourceFilePathAbsPath(filename))) {
                props.load(fstream);
            } catch (IOException fnfe) {
                throw new StopTestException("properties file not found");
            }
        }
        props.putAll(System.getProperties());
    }

    /**
     * Creates a single instance of the Configuration class using the Singleton pattern.
     * If no instance exists, it creates a new one.
     *
     * @return the Configuration instance
     * @throws StopTestException throws if a configuration file is not found.
     */
    public static synchronized Configuration getConfiguration() throws StopTestException {
        if (configuration == null)
            configuration = new Configuration();
        return configuration;
    }

    /**
     * Returns the loaded Properties object containing all configuration properties.
     *
     * @return the loaded Properties object
     */
    public Properties getProperties() {
        return props;
    }

    /**
     * Retrieves the value of a specific property from the loaded Properties object.
     *
     * @param propertyName the name of the property to retrieve
     * @return the value of the property, or null if not found
     */
    public String getProperty(final String propertyName) {
        return props.getProperty(propertyName);
    }

    /**
     * Checks if the "gridRun" property is set to "true" (case-insensitive).
     *
     * @return true if grid is enabled, false otherwise
     */
    public boolean isGridEnabled() {
        return getProperty("gridRun").equalsIgnoreCase("true");
    }

    /**
     * Retrieves the URL of the Grid Hub server from the "gridUrl" property.
     *
     * @return a URL object representing the Grid Hub server details
     * @throws StopTestException throws if the "gridUrl" property is not set or in an invalid format
     */
    public URL getGridHubServerDetails() throws StopTestException {
        String hubServerDetails = getProperty("gridUrl");

        if (hubServerDetails == null) {
            throw new StopTestException(
                    "Grid hub details not set - please set 'gridUrl'");
        }
        try {
            return new URL(hubServerDetails);
        } catch (Throwable e) {
            throw new StopTestException(
                    "Grid hub is not in currect format - please set 'gridUrl' in correct format");
        }
    }
}
