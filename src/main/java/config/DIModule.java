package config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import factory.BrowserProvider;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * The type Di module.
 */
public class DIModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WebDriver.class).toProvider(BrowserProvider.class).in(Singleton.class);
        //loading properties to the guice
        Names.bindProperties(binder(), getProperties());
    }

    private Properties getProperties() {
        return Configuration.getConfiguration().getProperties();
    }
}
