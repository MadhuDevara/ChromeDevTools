package DevTools;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SetGlobalLocation {
    public static void main(String[] args) {

        // Set up ChromeDriver
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        try {
            // Create DevTools session
            DevTools devTools = driver.getDevTools();
            devTools.createSession();

            // Set Geolocation
            Map<String, Object> coordinates = new HashMap<>();
            coordinates.put("latitude", 40);
            coordinates.put("longitude", 3);
            coordinates.put("accuracy", 1);
            driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

            // Navigate to Google and search for Netflix
            driver.get("http://www.google.com");
            driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);

            // Click on the Netflix link
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement netflixLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".LC20lb")));
            netflixLink.click();

            // Wait for Netflix page to load and print the text of a specific element
            WebElement storyCardTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".our-story-card-title")));
            System.out.println("Title Text: " + storyCardTitle.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
