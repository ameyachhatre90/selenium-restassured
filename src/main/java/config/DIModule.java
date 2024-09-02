package config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import factory.BrowserProvider;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * This class configures the Guice dependency injection container for the application.
 * It defines bindings for various dependencies used throughout the code.
 */
public class DIModule extends AbstractModule {

    /**
     * Configures the bindings for the Guice injector.
     * - Binds the WebDriver interface to a BrowserProvider in Singleton scope.
     * - Binds properties from config file using bindProperties.
     */
    @Override
    protected void configure() {
        bind(WebDriver.class).toProvider(BrowserProvider.class).in(Singleton.class);
        //loading properties to the guice
        Names.bindProperties(binder(), getProperties());
    }

    /**
     * Retrieves the config properties.
     *
     * @return The config properties.
     */
    private Properties getProperties() {
        return Configuration.getConfiguration().getProperties();
    }
}
