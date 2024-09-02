package config;

import exceptions.StopTestException;
import utils.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Configuration {

    private final static String[] files = new String[]{"config.properties"};
    private final Properties props;

    private static volatile Configuration configuration;

    /**
     * Load the config.properties into Properties
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
     * Creates the Configuration
     *
     * @return configuration
     * @throws StopTestException throws if properties file not exist
     */
    public static synchronized Configuration getConfiguration() throws StopTestException {
        if (configuration == null)
            configuration = new Configuration();
        return configuration;
    }

    /**
     * Returns the Properties
     *
     * @return loaded properties
     */
    public Properties getProperties() {
        return props;
    }

    /**
     * Return the property value of provided key
     *
     * @param propertyName property name
     * @return property value
     */
    public String getProperty(final String propertyName) {
        return props.getProperty(propertyName);
    }

    public boolean isGridEnabled() {
        return getProperty("gridRun").equalsIgnoreCase("true");
    }

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
