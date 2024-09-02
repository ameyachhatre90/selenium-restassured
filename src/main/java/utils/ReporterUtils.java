package utils;

import org.testng.Reporter;

/**
 * Customer Reporting utils
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
