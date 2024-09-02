package utils;

import org.testng.Reporter;

/**
 * This utility class provides methods for logging messages to both the TestNG report and the console.
 */
public class ReporterUtils {

    /**
     * Log the message to both TestNG Report and System
     *
     * @param value the value
     */
    public static void log(String value) {
        Reporter.log("<p>" + value + "</p>", true);
        System.out.println(value);
    }
}
