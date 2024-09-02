package utils;

import io.qameta.allure.*;
import java.io.ByteArrayInputStream;

/**
 * Utility class for generating Allure reports.
 */
public class AllureReporter {

    /**
     *
     * @param step The text to be displayed as a step in the Allure report.
     */
    @Step("{Step}")
    public void printReport(String step) {
        System.out.println("Step: " + step);
    }

    /**
     * Attaches a screenshot to the Allure report.
     *
     * @param screenshotBytes The byte array containing the screenshot data.
     * @param fileName The desired filename for the screenshot attachment.
     */
    @Attachment("Attaching screenshot")
    public void attachScreenshot(byte[] screenshotBytes, String fileName) {
        Allure.addAttachment(fileName, new ByteArrayInputStream(screenshotBytes));
    }
}
