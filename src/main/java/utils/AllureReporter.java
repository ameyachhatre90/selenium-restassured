package utils;

import io.qameta.allure.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Base64;

public class AllureReporter {

    @Step("{Step}")
    public void printReport(String step) {
        System.out.println("Step: " + step);
    }

    @Attachment("Attaching screenshot")
    public void attachScreenshot(byte[] screenshotBytes, String fileName) {
        Allure.addAttachment(fileName, new ByteArrayInputStream(screenshotBytes));
    }
}
