package DevTools;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Map;

public class ConsoleLogsCapture {
    public static void main(String[] args) {
        // Set up WebDriver with WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Set Chrome options to enable logging
        ChromeOptions options = new ChromeOptions();
        options.setCapability("goog:loggingPrefs", Map.of("browser", "ALL"));

        ChromeDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://rahulshettyacademy.com/angularAppdemo/");

            driver.findElement(By.linkText("Browse Products")).click();
            driver.findElement(By.partialLinkText("Selenium")).click();
            driver.findElement(By.cssSelector(".add-to-cart")).click();
            driver.findElement(By.xpath("//a[.='Cart']"));
            driver.findElement(By.id("exampleInputEmail")).clear();
            driver.findElement(By.id("exampleInputEmail")).sendKeys("2");

            LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
            List<LogEntry> logs = entry.getAll();

            for (LogEntry e : logs) {
                System.out.println(e.getMessage());
            }
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
